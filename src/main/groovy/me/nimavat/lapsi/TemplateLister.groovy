package me.nimavat.lapsi

import grails.core.GrailsApplication
import grails.util.Environment
import org.apache.tools.ant.DirectoryScanner
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

@Service
class TemplateLister {
    public static final String PATH_TO_LAYOUTS = "grails-app/views/layouts"
    public static final String PATH_TO_WEB_INF_LAYOUTS = "/WEB-INF/classes/layouts";
    GrailsApplication grailsApplication

    /**
     * Returns list of layout names
     * @return
     */
    List<String> templatesForSite(String siteName) {
        return scanForTemplates(scanPaths(siteName))
    }

    List<String> scanPaths(String siteName) {
        List paths = []
        if(Environment.warDeployed) {
            paths << PATH_TO_WEB_INF_LAYOUTS + "/" + siteName
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
            def extensionIndex = fileName.lastIndexOf('.gsp')
            return extensionIndex != -1 ? fileName.substring(0, extensionIndex) : fileName
        }

        return filesToProcess
    }

    // Fetches The Actual Layout File Contents for parsing
    String templateContent(String siteName, String name) {
        if (Environment.warDeployed) {
            Resource resource = grailsApplication.parentContext.getResource(PATH_TO_WEB_INF_LAYOUTS + "layouts/${siteName}/${name}.gsp")
            if (resource.exists()) {
                return resource.inputStream.text
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
