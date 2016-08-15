package me.nimavat.lapsi

import grails.compiler.GrailsCompileStatic
import grails.core.GrailsApplication
import grails.plugin.cache.CacheEvict
import grails.plugin.cache.Cacheable
import grails.transaction.Transactional
import groovy.transform.CompileDynamic
import org.apache.commons.lang.StringUtils
import org.grails.web.json.JSONObject
import org.springframework.beans.factory.config.AutowireCapableBeanFactory

@GrailsCompileStatic
class ContentService {
	private static final String PATH_SEPERATOR = "/"

	GrailsApplication grailsApplication
	TemplateRenderer templateRenderer

	@Cacheable(value = 'pagecontent', key = "#site.concat('-').concat(#uri)", condition = "#cache == true")
	public String renderPage(String site, String uri, boolean cache = true) {
		return renderPage(site, findPage(uri))
	}

	@CompileDynamic
	Map<String, String> findSpaceAndUri(String uri) {
		String actualUri = uri

		log.debug("Resolving space and uri for uri $actualUri")

		if (StringUtils.isBlank(uri)) return [space: LapsiSpace.DEFAULT.name, uri: LapsiUtils.lapsiConfig.homePage]

		if (uri.startsWith("/")) uri = (uri - PATH_SEPERATOR)

		String spaceUri
		String spaceName

		//it is a uri like  hello-world.html etc - without "/"
		if (!uri.contains(PATH_SEPERATOR)) {

			log.debug "Request received for uri without space $uri"
			LapsiSpace space = LapsiSpace.DEFAULT
			spaceName = space.name

		} else {

			(spaceUri, uri) = uri.split(PATH_SEPERATOR, 2)
			LapsiSpace space = findSpaceByUri(spaceUri)

			if (!space) {
				log.debug "No space found for uri ${actualUri}"
				//there's no space with this uri, so consider whole uri as a page which is in default space
				uri = spaceUri + PATH_SEPERATOR + uri
				spaceName = LapsiSpace.DEFAULT.name
			} else {
				//uri contains a space name, which exists
				spaceName = space.name
				log.debug "Found space $spaceName for uri $actualUri"
			}

		}

		log.info "Resolved space:$spaceName, page:$uri for uri:$actualUri"
		return [space: spaceName, uri: uri]

	}


	public String renderPage(String site, LapsiPage page) {
		return templateRenderer.render(site, page.template, [page: page])
	}

	@CacheEvict(value = "pagecontent", key = "#site.concat('-').concat(#uri)")
	public LapsiPage updateContent(String site, String uri, JSONObject json) {
		updateContent(site, findPage(uri), json)
	}

	LapsiPage findPage(String uri) {
		Map<String, String> spaceAndUri = findSpaceAndUri(uri)
		LapsiSpace space = LapsiSpace.findByName(spaceAndUri.space)
		LapsiPage page = LapsiPage.findBySpaceAndUri(space, spaceAndUri.uri)

		if(!page) {
			throw new ContentNotFoundException(uri)
		} else {
			return page
		}

	}

	@Transactional
	public LapsiPage updateContent(String site, LapsiPage page, JSONObject json) {
		page.setJson(json)
		page.save()
		return page
	}

	@Transactional(readOnly = true)
	LapsiSpace findSpaceByUri(String spaceUri) {
		return LapsiSpace.findByUri(spaceUri)
	}


	@CompileDynamic
	List<String> findBlocks(String site) {
		ConfigObject siteConfig = LapsiUtils.lapsiConfig.sites[site]
		List blocks = []
		if(siteConfig.isSet('blocks')) {
			blocks = siteConfig.blocks.collect {Class<Block> blockClass ->
				Block b = blockClass.newInstance()
				return b.name
			}
		}

		return blocks
	}

	@CompileDynamic
	Block findBlock(String site, String name) {
		ConfigObject siteConfig = (ConfigObject)LapsiUtils.lapsiConfig.sites[site]
		Class<Block> blockClass
		Block block

		if(siteConfig.isSet('blocks')) {
			 blockClass = siteConfig.blocks.find {Class<Block> bc ->
				Block b = bc.newInstance()
				return b.name == name
			}

			if(blockClass != null) {
				block = blockClass.newInstance()
				int byName = AutowireCapableBeanFactory.AUTOWIRE_BY_NAME
				grailsApplication.mainContext.autowireCapableBeanFactory.autowireBeanProperties(block, byName, false)
			}
		}

		return block
	}

	public String renderBlock(String site, String blockName) {
		Block block = LapsiBlock.findByName(blockName)
		if(block) {
			return block.content
		} else {
			block = findBlock(site, blockName)
			return  block ? templateRenderer.renderBlock(site, block, null) : ""
		}
	}

}
