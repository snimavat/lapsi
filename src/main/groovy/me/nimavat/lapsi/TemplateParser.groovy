package me.nimavat.lapsi

import org.springframework.stereotype.Service

@Service
class TemplateParser {

    public Map<String, String> metaInfoForLayoutContents(layout, contents) {
        if (!contents) return null

        List lines = contents.split("\n")
        Map<String, String> layoutMeta = [layout: layout, name: layout]
        boolean metaFound = false

        lines.each { line ->
            def directive = directiveForLine(line)
            if (directive) {
                metaFound = true
                switch (directive.command) {
                    case 'template_name':
                        def templateName = directive.args.join(" ").trim()
                        if (templateName) {
                            layoutMeta.name = templateName
                        }
                        break
                }
            }
        }
        if (metaFound) return layoutMeta
        return null
    }

    private directiveForLine(String line) {
        String directive = line.find(/\/\/=(.*)/) { fullMatch, directive -> return directive }?.trim()

        if (directive) {
            def argsWithDirective = directive.split(" ")
            def command = argsWithDirective[0].toLowerCase()
            def args = argsWithDirective[1..argsWithDirective.size() - 1]
            return [command: command, args: args]
        }
        return null
    }
}
