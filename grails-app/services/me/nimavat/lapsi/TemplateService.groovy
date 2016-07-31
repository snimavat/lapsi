package me.nimavat.lapsi

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class TemplateService {
    TemplateLister templateLister
    TemplateParser templateParser

    Map defaultTemplateForSite(String site) {
        return templatesForSite(site)[0]
    }

    List<Map> templatesForSite(String siteName) {
        List<String> layouts = templateLister.templatesForSite(siteName)
        List<Map> layoutMetaList = []

        layouts.each { layout ->
            String contents = templateLister.templateContent(siteName, layout)
            Map meta = templateParser.metaInfoForLayoutContents(layout, contents)
            if (meta) {
                layoutMetaList << meta + [layout: layout]

            }
        }
        return layoutMetaList
    }

}
