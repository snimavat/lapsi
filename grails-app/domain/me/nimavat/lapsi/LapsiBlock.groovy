package me.nimavat.lapsi

import grails.databinding.BindingFormat

class LapsiBlock implements Block {

	@BindingFormat("lowercase")
	String name

	@BindingFormat("titlecase")
	String subject

	String description
	String content


	static transients = ['model']

	static mapping = {
		content type: "text"
	}

	static constraints = {
		name nullable: false, blank: false, unique: true
		subject nullable: true, blank: false
		description nullable: true, widget: "textarea"
		content nullable: false, blank: false, widget: "textarea"
	}


	Map getModel() {
		return [content: content, subject:subject]
	}
}
