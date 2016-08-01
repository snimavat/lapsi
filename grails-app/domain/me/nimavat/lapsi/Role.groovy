package me.nimavat.lapsi

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Transient

@EqualsAndHashCode(includes='authority')
@ToString(includes='authority', includeNames=true, includePackage=false)
class Role implements Serializable {

	public static final String ADMIN = "ROLE_ADMIN"

	String name

	Role(String authority) {
		this.name = authority
	}

	static constraints = {
		name blank: false, unique: true
	}

	static mapping = {
		cache true
	}

	@Transient
	String getSpringSecRoleName() {
		return "ROLE_" + name
	}
}
