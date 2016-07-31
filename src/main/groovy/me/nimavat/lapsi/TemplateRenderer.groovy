package me.nimavat.lapsi

import grails.gsp.PageRenderer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TemplateRenderer {
    @Autowired
    PageRenderer groovyPageRenderer

    String render(String site, String template, Map model) {
        String path = "/layouts/${site}/$template"
        groovyPageRenderer.render([view:path, model:model])
    }
}
