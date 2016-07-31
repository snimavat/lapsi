package me.nimavat.lapsi


class LapsiTagLib {
    static namespace = "lp"

    def partial = { attrs, body ->
        String partialName = attrs.partial
        LapsiPage page = attrs.page
        boolean editMode = attrs.editMode

        if(editMode) {
            out << """
                    <div data-editable data-name="${partialName}">
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
        String url = page.absoluteUri
        String contentPrefix = "content"
        out << """<a href="${g.createLink(controller: 'content', params: [url:url])}" class="page-link">${page.name}</a>"""
    }

	/**
	 * Render a block
     */
    def block = { attrs ->
        String blockName = attrs.name
        LapsiBlock block = LapsiBlock.findByName(blockName)
        if(block) {
            out << block.content
        } else {
            log.warn "No block found with name $blockName"
        }
    }

}
