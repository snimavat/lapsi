package lapsi

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import me.nimavat.core.ApplicationConfigurationLoader
import org.apache.commons.lang.StringUtils
import org.springframework.context.EnvironmentAware
import org.springframework.core.env.Environment

class Application extends GrailsAutoConfiguration implements EnvironmentAware {
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


    @Override
    void setEnvironment(Environment environment) {
        ApplicationConfigurationLoader.load(this, environment)
    }

}