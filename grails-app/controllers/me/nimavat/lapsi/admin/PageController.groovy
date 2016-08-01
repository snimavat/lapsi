package me.nimavat.lapsi.admin

import grails.compiler.GrailsCompileStatic
import me.nimavat.core.AdminSection
import me.nimavat.crudify.BaseCrudController
import me.nimavat.lapsi.LapsiPage
import me.nimavat.lapsi.TemplateService
import me.nimavat.lapsi.core.Tenant

@AdminSection(name="Pages", order=2)
@GrailsCompileStatic
class PageController extends BaseCrudController<LapsiPage> {
	static namespace = 'admin'

	TemplateService templateService

	PageController() { super(LapsiPage) }

	Map extraModel(LapsiPage page) {
		List templates = templateService.templatesForSite(Tenant.JAVA_PRIMER)
		return [templates: templates]
	}

	protected boolean  onSave(LapsiPage page) {
		if(page && page.url && page.url.startsWith("/")) page.url = page.url - "/"
		return super.onSave(page)
	}

	protected boolean onUpdate(LapsiPage page) {
		if(page && page.url && page.url.startsWith("/")) page.url = page.url - "/"
		return super.onUpdate(page)
	}

	public getResourceName() {
		return "page"
	}
}
