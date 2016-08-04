package me.nimavat.lapsi

import grails.compiler.GrailsCompileStatic
import grails.plugin.cache.CacheEvict
import grails.plugin.cache.Cacheable
import grails.transaction.Transactional
import groovy.transform.CompileDynamic
import org.apache.commons.lang.StringUtils
import org.grails.web.json.JSONObject

@GrailsCompileStatic
class ContentService {
	private static final String PATH_SEPERATOR = "/"

	TemplateRenderer templateRenderer

	@Cacheable(value = 'pagecontent', key = "#site.concat('-').concat(#url)", condition = "#cache == true")
	public String renderPage(String site, String url, boolean cache = true) {
		return renderPage(site, findPage(url))
	}

	@CompileDynamic
	Map<String, String> findSpaceAndUri(String uri) {
		String actualUri = uri

		log.debug("Resolving space and uri for url $actualUri")

		if (StringUtils.isBlank(uri)) return [space: LapsiSpace.DEFAULT.name, uri: LapsiUtils.lapsiConfig.homePage]

		if (uri.startsWith("/")) uri = (uri - PATH_SEPERATOR)

		String spaceUri
		String spaceName

		//it is a url like  hello-world.html etc - without "/"
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
				//url contains a space name, which exists
				spaceName = space.name
				log.debug "Found space $spaceName for uri $actualUri"
			}

		}

		log.info "Resolved space:$spaceName, page:$uri for url:$actualUri"
		return [space: spaceName, uri: uri]

	}


	public String renderPage(String site, LapsiPage page) {
		return templateRenderer.render(site, page.template, [page: page])
	}

	@CacheEvict(value = "pagecontent", key = "#site.concat('-').concat(#url)")
	public LapsiPage updateContent(String site, String url, JSONObject json) {
		updateContent(site, findPage(url), json)
	}

	LapsiPage findPage(String uri) {
		Map<String, String> spaceAndUri = findSpaceAndUri(uri)
		LapsiSpace space = LapsiSpace.findByName(spaceAndUri.space)
		LapsiPage page = LapsiPage.findBySpaceAndUrl(space, spaceAndUri.uri)

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

}
