package me.nimavat.crudify

import grails.artefact.Artefact
import grails.core.GrailsDomainClass
import grails.core.GrailsDomainClassProperty
import grails.orm.HibernateCriteriaBuilder
import grails.orm.PagedResultList
import grails.transaction.Transactional
import org.grails.core.artefact.DomainClassArtefactHandler

@Artefact("Controller")
@Transactional(readOnly = true)
abstract class BaseCrudController<T> {
	static allowedMethods = [index: "GET", list: "GET", create: "GET", edit: "GET", save: "POST"]
	
	Class<T> domainClass

	BaseCrudController(Class clazz) {
		domainClass = clazz
	}

	final def index() {
		redirect(action: "list", params: params)
	}

	final def list()  {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.offset = params.offset ? params.long("offset") : 0

		List<GrailsDomainClassProperty> props = getGrailsDomainClass().persistentProperties

		HibernateCriteriaBuilder criteria = domainClass.createCriteria()

		PagedResultList list = criteria.list(max: params.max, offset:params.offset) {
			props.each { GrailsDomainClassProperty p ->
				String name = p.name
				Class type = p.type

				if(params[name] && !p.isAssociation()) {
					if(type == String) ilike(name, params[name] + "%")
					else eq(name, type.cast(params[name]))
				}
			}
		}

		["domainClass":domainClass, "entityList": list, ("totalCount"): list.totalCount]
	}

	def create()  {
		T entity = createInstace()
		bind(params, entity)
		render(view:(resourceName+"-crud-form"), model:model(entity))
	}

	def edit() {
		T entity = domainClass.read(params.id)
		if (!entity) {
			flash.error = message(code: 'default.not.found.message', args: [message(code: 'content.label', default: 'Content'), params.id])
			redirect(action: "list")
			return
		}
		render(view:(resourceName+"-crud-form"), model:model(entity))
	}

	@Transactional
	def save()  {
		if(params.id) {
			T entity = domainClass.get(params.id)
			if(entity) {
				bind(params, entity)
				if(onUpdate(entity)) {
					//show success message - redirect to list
					flash.message = message(code: 'default.updated.message', args: [message(code: "${resourceName}.label", default: resourceName), entity.id])
					redirect(action:"list")
				} else {
					//Show validation errors
					render(view:(resourceName+"-crud-form"), model:model(entity))
				}
			} else {
				//show not found message
				render(view:(resourceName+"-crud-form"), model:model(entity))
			}
		} else {
			def entity = createInstace()
			bind(params, entity)
			if(onSave(entity)) {
				//show success
				flash.message = message(code: 'default.created.message', args: [message(code: "${resourceName}.label", default: resourceName), entity.id])
				redirect(action:"list")
			} else {
				//show validation errors
				render(view:(resourceName+"-crud-form"), model:model(entity))
			}
		}
	}

	private Map model(T entity) {
		Map model = [entityInstance: entity]
		model.putAll(extraModel(entity))
		return model
	}
	protected Map extraModel(T entity) {
		return [:]
	}

	protected boolean onSave(T entity) {
		return entity.save(flush: true)
	}

	protected boolean onUpdate(T entity) {
		return entity.save(flush: true)
	}

	protected T createInstace() {
		return domainClass.newInstance()
	}

	protected T bind(def params, T entity) {
		entity.properties = params
		return entity
	}

	public getResourceName() {
		return domainClass.simpleName.toLowerCase()
	}

	private final GrailsDomainClass getGrailsDomainClass() {
		return grailsApplication.getArtefact(DomainClassArtefactHandler.TYPE, domainClass.name)
	}
}