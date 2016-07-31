package lapsi

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.apache.commons.lang.StringUtils

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    void doWithDynamicMethods() {
        String.metaClass.titlecase = {
            def output = delegate.replaceAll( /\b[a-z]/, { it.toUpperCase() })
        }

        String.metaClass.capitalize = {
            StringUtils.capitalize(delegate)
        }
    }

}