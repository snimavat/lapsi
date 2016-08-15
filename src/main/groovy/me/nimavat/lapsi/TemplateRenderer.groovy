package me.nimavat.lapsi

import grails.gsp.PageRenderer
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@Slf4j
@CompileStatic
class TemplateRenderer {

    @Autowired
    PageRenderer groovyPageRenderer

    @Autowired
    TemplateService templateService

    String render(String site, String template, Map model) {
        String layoutFile = templateService.layoutFileByName(site, template)
        if(!layoutFile) {
            log.error "No layout gsp found for template $template, site $site"
            layoutFile = templateService.defaultTemplateForSite(site)
        }
        String path = "/layouts/${site}/$layoutFile"
        return  groovyPageRenderer.render([view:path, model:model])
    }

    String renderBlock(String site, Block block, LapsiPage page) {
        assert block != null
        Map model = block.model ?: [:]

        String path = "/blocks/${site}/$block.name"
        groovyPageRenderer.render([template: path, model: model])
    }
}
