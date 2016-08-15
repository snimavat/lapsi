package me.nimavat.core

import grails.boot.config.GrailsAutoConfiguration
import groovy.util.logging.Slf4j
import org.grails.core.io.DefaultResourceLocator
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.MapPropertySource
import org.springframework.core.io.Resource

@Slf4j
class ApplicationConfigurationLoader {

	private ApplicationConfigurationLoader() {}

	public static load(GrailsAutoConfiguration application, ConfigurableEnvironment environment) {
		if (application && environment) {
			DefaultResourceLocator resourceLocator = new DefaultResourceLocator()
			def applicationGroovy = application.getClass().classLoader.getResource('application.groovy')
			if (applicationGroovy) {
				def applicationConfiguration = new ConfigSlurper(grails.util.Environment.current.name).parse(applicationGroovy)
				for (def configLocation in applicationConfiguration.grails.config.locations) {
					ConfigObject config
					if(configLocation instanceof Class) {
						config = new ConfigSlurper(grails.util.Environment.current.name).parse(configLocation)
						configLocation = configLocation.simpleName
					} else if (configLocation instanceof String) {
						Resource configurationResource = resourceLocator.findResourceForURI(configLocation)
						if (configurationResource) {
							config = new ConfigSlurper(grails.util.Environment.current.name).parse(configurationResource.getURL())
						}
					}

					if(config) {
						environment.propertySources.addFirst(new MapPropertySource(configLocation, config))
					} else {
						log.warn "No configuration file found : $configLocation"
					}
				}
			}
		}
	}
}