package me.nimavat.lapsi.core

class Tenant {
    String name
    String domain

    Boolean enabled = true

    String notes

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false
        domain blank: false
    }
}
