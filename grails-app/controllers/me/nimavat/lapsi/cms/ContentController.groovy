package me.nimavat.lapsi.cms

import grails.plugin.springsecurity.SpringSecurityUtils
import me.nimavat.lapsi.ContentNotFoundException
import me.nimavat.lapsi.ContentService
import me.nimavat.lapsi.Role
import org.grails.web.json.JSONObject

class ContentController {
	static allowedMethods = [show: 'GET', update:'POST']
	private static final String site = "test"

	ContentService contentService

	def show(String url) {
		log.debug("Request received for uri $url")
		boolean cache = SpringSecurityUtils.ifNotGranted(Role.ADMIN)
		render text: contentService.renderPage(site, url, cache)
	}

	def update(String url) {
		if(SpringSecurityUtils.ifNotGranted(Role.ADMIN)) {
			render status: 404
			return
		}

		JSONObject json = request.JSON
		contentService.updateContent(site, url, json.regions)
		render status: 204
	}

	def handleContentNotFoundException(ContentNotFoundException e) {
		log.error "Not found $request.requestURI"
		render status: 404
	}
}
