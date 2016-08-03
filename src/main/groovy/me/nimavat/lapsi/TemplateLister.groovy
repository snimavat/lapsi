package me.nimavat.lapsi

import grails.core.GrailsApplication
import grails.util.Environment
import groovy.util.logging.Slf4j
import org.apache.tools.ant.DirectoryScanner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.io.FileSystemResourceLoader
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component

@Slf4j
@Component
class TemplateLister {
    public static final String PATH_TO_LAYOUTS = "grails-app/views/layouts"
    public static final String PATH_TO_WEB_INF_LAYOUTS = "classpath:layouts"
    public static final String LAYOUT_DIR = Environment.warDeployed ? PATH_TO_WEB_INF_LAYOUTS : PATH_TO_LAYOUTS

    private final ResourceLoader resourceLoader = new FileSystemResourceLoader()

    @Autowired
    @Qualifier("grailsApplication")
    GrailsApplication grailsApplication

    /**
     * Returns list of layout file names for given site
     * @return
     */
    List<String> templatesForSite(String siteName) {
        log.debug "Finding layout templates for site $siteName"
        return listLayoutFiles(listLayoutDirectories(siteName))
    }

    private List<String> listLayoutDirectories(String siteName) {
        List paths = []

        String layoutDirPath =  resourceLoader.getResource(LAYOUT_DIR + File.separator + siteName).file.absolutePath
        log.debug "looking for layouts in dir $layoutDirPath"

        paths << layoutDirPath

        return paths
    }

    private List<String> listLayoutFiles(List<String> paths) {
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

        Resource layoutFile = resourceLoader.getResource(buildLayoutResourceName(siteName, name))
        if(layoutFile.exists()) return layoutFile.inputStream.text
        else {
            log.error "No layout found for site: $siteName, layout: $name"
            return null
        }
    }

    private String buildLayoutResourceName(String site, String layoutFile) {
        return LAYOUT_DIR + File.separator + site + File.separator + layoutFile + ".gsp"
    }

}
