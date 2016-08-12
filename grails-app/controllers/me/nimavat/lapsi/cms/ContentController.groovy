package me.nimavat.lapsi.cms

import grails.plugin.springsecurity.SpringSecurityUtils
import me.nimavat.lapsi.ContentNotFoundException
import me.nimavat.lapsi.ContentService
import me.nimavat.lapsi.Role
import me.nimavat.lapsi.SiteNameResolver
import me.nimavat.lapsi.core.Tenant
import org.grails.web.json.JSONObject

class ContentController {
	static allowedMethods = [show: 'GET', update:'POST']
	public static final String DEFAULT_SITE = "javaprimer"

	ContentService contentService
	SiteNameResolver siteNameResolver

	def show(String uri) {
		String site = siteNameResolver.getSiteName() ?: DEFAULT_SITE
		log.debug("Request received for site: $site, uri $uri")
		boolean cache = SpringSecurityUtils.ifNotGranted(Role.ADMIN)

		render text: contentService.renderPage(site, uri, cache)
	}

	def update(String uri) {
		if(SpringSecurityUtils.ifNotGranted(Role.ADMIN)) {
			render status: 404
			return
		}

		JSONObject json = request.JSON

		String site = siteNameResolver.getSiteName() ?: DEFAULT_SITE
		log.debug "Updating content of site:$site, page $uri"

		contentService.updateContent(site, uri, json.regions)
		render status: 204
	}

	def handleContentNotFoundException(ContentNotFoundException e) {
		log.error "Not found $request.requestURI"
		render status: 404
	}
}
