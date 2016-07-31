package me.nimavat.lapsi.admin

import grails.compiler.GrailsCompileStatic
import grails.plugin.cache.Cacheable
import me.nimavat.core.AdminSection
import me.nimavat.crudify.BaseCrudController
import me.nimavat.lapsi.LapsiBlock
import me.nimavat.lapsi.LapsiSpace

@AdminSection(name="Blocks", order=3)
@GrailsCompileStatic
class BlockController extends BaseCrudController<LapsiBlock> {
	static namespace = 'admin'

	BlockController() { super(LapsiBlock) }

	public getResourceName() { return "block" }
}
