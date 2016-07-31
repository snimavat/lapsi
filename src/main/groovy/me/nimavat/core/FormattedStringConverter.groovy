package me.nimavat.core

import grails.databinding.converters.FormattedValueConverter
import org.springframework.stereotype.Component

@Component
class FormattedStringConverter implements FormattedValueConverter {
	static final String UPPERCASE = "uppercase"
	static final String LOWERCASE = "lowercase"
	static final String TITLECASE = "titlecase"
	static final String CAPITALIZE = "capitalize"

	def convert(value, String format) {
		if (UPPERCASE == format) {
			value = value.toUpperCase()
		} else if (LOWERCASE == format) {
			value = value.toLowerCase()
		} else if(TITLECASE == format) {
			value = value.toLowerCase().titlecase()
		} else if(CAPITALIZE == format) {
			value = value.toLowerCase().capitalize()
		}
		value
	}

	Class getTargetType() {
		return String.class
	}
}
