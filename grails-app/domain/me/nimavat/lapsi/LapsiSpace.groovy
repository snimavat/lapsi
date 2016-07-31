package me.nimavat.lapsi

import grails.databinding.BindingFormat

class LapsiSpace implements Serializable {

	static final String BLANK = ""

	@BindingFormat("titlecase")
	String name

	@BindingFormat("lowercase")
	String uri

	static constraints = {
		name nullable: false, blank: false, unique: true
		uri blank: true, nullable: false, unique: true
	}

	public static LapsiSpace getDEFAULT() {
		return LapsiSpace.findByUri(BLANK)
	}
}
