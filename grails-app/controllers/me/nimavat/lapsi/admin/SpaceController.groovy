package me.nimavat.lapsi.admin

import grails.compiler.GrailsCompileStatic
import me.nimavat.core.AdminSection
import me.nimavat.crudify.BaseCrudController
import me.nimavat.lapsi.LapsiPage
import me.nimavat.lapsi.LapsiSpace
import me.nimavat.lapsi.TemplateService

@AdminSection(name="Spaces", order=1)
@GrailsCompileStatic
class SpaceController extends BaseCrudController<LapsiSpace> {
	static namespace = 'admin'

	SpaceController() { super(LapsiSpace) }

	public getResourceName() { return "space" }
}
