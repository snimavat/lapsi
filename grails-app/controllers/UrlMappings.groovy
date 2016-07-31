class UrlMappings {

    static mappings = {

        "/login/$action"(controller: "login")
        "/admin/$controller/$action?/$id?(.$format)?"(namespace:"admin")

        String prefix = "content"
        String contentURI = (prefix ? '/' : '') + prefix

        "${contentURI}/$url**"(controller:"content") {
            action = ['GET': 'show', 'POST':'update']
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
