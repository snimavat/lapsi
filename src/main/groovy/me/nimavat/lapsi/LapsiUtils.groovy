package me.nimavat.lapsi

import grails.util.Holders
import groovy.transform.CompileStatic

@CompileStatic
class LapsiUtils {

	static public def getLapsiConfig() {
		return Holders.config.lapsi
	}
}
