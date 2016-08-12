package lapsi

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import me.nimavat.core.ApplicationConfigurationLoader
import me.nimavat.lapsi.SiteNameResolver
import me.nimavat.lapsi.SubDomainRedirectFilter
import org.apache.commons.lang.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.context.EnvironmentAware
import org.springframework.context.annotation.Bean
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

    @Bean
    @Autowired
    FilterRegistrationBean domainRedirectFilter(SiteNameResolver siteNameResolver) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        SubDomainRedirectFilter filter = new SubDomainRedirectFilter()
        filter.siteNameResolver = siteNameResolver
        registrationBean.setFilter(filter)
        registrationBean.setOrder(1)

        return registrationBean
    }


    @Override
    void setEnvironment(Environment environment) {
        ApplicationConfigurationLoader.load(this, environment)
    }

}