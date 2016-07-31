package me.nimavat.core

import grails.core.GrailsApplication
import grails.core.GrailsClass
import org.grails.core.artefact.ControllerArtefactHandler

import javax.annotation.PostConstruct
import java.lang.annotation.Annotation

class AdminService {

	GrailsApplication grailsApplication
	private static final List adminControllers = []

	@PostConstruct
	void init(){
		log.debug "Initializing AdminService"

		grailsApplication.getArtefacts(ControllerArtefactHandler.TYPE).each { GrailsClass controllerClass ->
			Class clazz = controllerClass.clazz
			Annotation annotation = clazz.getAnnotation(AdminSection)
				if(annotation) {
					adminControllers << [name: annotation.name(), controller: controllerClass.logicalPropertyName, order: annotation.order()]
			}
			adminControllers.sort { it.order }
		}
	}

	List<Map> getAdminControllers() {
		return Collections.unmodifiableList(this.adminControllers)
	}
}
