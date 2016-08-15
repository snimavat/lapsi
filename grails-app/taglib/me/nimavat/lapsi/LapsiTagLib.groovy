package me.nimavat.lapsi

import grails.plugin.springsecurity.SpringSecurityUtils


class LapsiTagLib {
    static namespace = "lp"

    ContentService contentService

    def partial = { attrs, body ->
        String partialName = attrs.partial
        LapsiPage page = attrs.page
        boolean editMode = attrs.editMode != null ? attrs.editMode : SpringSecurityUtils.ifAllGranted(Role.ADMIN)

        if(editMode) {
            out << """
                    <div data-editable data-name="${partialName}" class="${attrs['class'] ?: ''}">
                       ${page.partial(partialName) ?: body()}
                     </div>
                """
        } else {
            out << page.partial(partialName)
        }

    }

	/**
     * Create a page link
     *
     * @attr page - the page to generate the link for
     */
    def pageLink = { attrs ->
        LapsiPage page = attrs.page
        String uri = page.absoluteUri
        out << """<a href="${g.createLink(uri: uri)}" class="page-link">${page.name}</a>"""
    }

	/**
	 * Render a block
     */
    def block = { attrs ->
        String blockName = attrs.name
        out << contentService.renderBlock("nimavat", blockName)
    }

}
