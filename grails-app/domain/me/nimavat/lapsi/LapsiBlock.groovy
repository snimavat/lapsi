package me.nimavat.lapsi

import grails.databinding.BindingFormat

class LapsiBlock {

	@BindingFormat("lowercase")
	String name

	@BindingFormat("titlecase")
	String subject

	String description
	String content

	static mapping = {
		content type: "text"
	}

	static constraints = {
		name nullable: false, blank: false, unique: true
		subject nullable: true, blank: false
		description nullable: true, widget: "textarea"
		content nullable: false, blank: false, widget: "textarea"
	}
}
