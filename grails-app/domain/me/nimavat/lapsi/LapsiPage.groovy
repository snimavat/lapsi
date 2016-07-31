package me.nimavat.lapsi

import grails.converters.JSON
import grails.databinding.BindingFormat
import org.grails.web.json.JSONObject

import javax.persistence.Transient

class LapsiPage implements Serializable{

    @BindingFormat("titlecase")
    String name

    @BindingFormat("lowercase")
    String url
    String template

    String draft

    //json formatted content
    String content

    JSONObject json

    String metaDescription
    String metaKeywords

    Boolean published = false

    Date dateCreated
    Date lastUpdated

    static transients = ["json"]
    static belongsTo = [parent: LapsiPage, space: LapsiSpace]
    static hasMany = [children: LapsiPage]

    static mapping = {
        content type: "text"
    }

    static constraints = {
        template nullable: false, blank: false
        name blank: false
        url blank: false
        metaDescription nullable: true, widget:"textarea"
        metaKeywords nullable: true, widget:"textarea"
        content blank: true, nullable: true
        draft blank: true, nullable: true

        parent nullable: true
        space nullable: false
        children lazy: true
        lastUpdated nullable: true
    }

    def beforeValidate() {
        content = json ? json.toString() : null
    }

    def afterLoad() {
        this.json = content ? JSON.parse(content) : new JSONObject()
    }

    void setJson(Map json) {
        json.each {String key, String val ->
            this.json.put(key, val)
        }
    }

    String partial(String name) {
        return getJson()[name]
    }

    @Transient
    String getAbsoluteUri() {
        return space.uri + "/" + url
    }

}
