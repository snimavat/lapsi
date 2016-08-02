package me.nimavat.lapsi

import grails.core.GrailsApplication
import grails.util.Environment
import groovy.util.logging.Slf4j
import org.apache.tools.ant.DirectoryScanner
import org.springframework.core.io.Resource

@Slf4j
class TemplateLister {
    public static final String PATH_TO_LAYOUTS = "grails-app/views/layouts"
    public static final String PATH_TO_WEB_INF_LAYOUTS = "WEB-INF/classes/layouts";

    GrailsApplication grailsApplication

    /**
     * Returns list of layout names
     * @return
     */
    List<String> templatesForSite(String siteName) {
        log.debug "Finding layout templates for site $siteName"
        return scanForTemplates(scanPaths(siteName))
    }

    //TODO change to do LayoutFinder for dev/prod env
    List<String> scanPaths(String siteName) {
        List paths = []
        if(Environment.warDeployed) {
            String layoutPath = PATH_TO_WEB_INF_LAYOUTS + "/" + siteName
            String absolutePath = grailsApplication.parentContext.getResource(layoutPath).file.absolutePath

            log.debug "War deployed: Looking for layouts in $absolutePath"

            paths << absolutePath
        } else {
            paths << PATH_TO_LAYOUTS + "/" + siteName
        }
    }

    private List<String> scanForTemplates(List<String> paths) {
        DirectoryScanner scanner = new DirectoryScanner()
        List filesToProcess = []

        paths.each {String path ->
            if (new File(path).exists()) {
                scanner.setIncludes(["**/*.gsp"] as String[])
                scanner.setBasedir(path)
                scanner.setCaseSensitive(false)
                scanner.scan()
                filesToProcess += scanner.getIncludedFiles().flatten()
            }
        }

        filesToProcess.unique()
        filesToProcess = filesToProcess.collect {String fileName ->
            int extensionIndex = fileName.lastIndexOf('.gsp')
            return extensionIndex != -1 ? fileName.substring(0, extensionIndex) : fileName
        }

        return filesToProcess
    }

    // Fetches The Actual Layout File Contents for parsing
    String templateContent(String siteName, String name) {
        log.debug "Fetching layout content - site: $siteName, layout: $name"

        if (Environment.warDeployed) {
            Resource resource = grailsApplication.parentContext.getResource(PATH_TO_WEB_INF_LAYOUTS + "/${siteName}/${name}.gsp")
            if (resource.exists()) {
                return resource.inputStream.text
            } else {
                log.error "No layout found for site: $siteName, layout: $name"
            }

        } else {
            File layoutFile = new File(PATH_TO_LAYOUTS + "/${siteName}", "${name}.gsp")
            if (layoutFile.exists()) {
                return layoutFile.text
            }
        }
        return null
    }

}
