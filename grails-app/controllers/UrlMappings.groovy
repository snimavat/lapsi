class UrlMappings {
    static final String ADMIN_PREFIX = "admin"
    static final String CONTENT_PREFIX = ""

    static final List<String> FORBIDDEN = [
            'plugins',
            'WEB-INF',
            'assets',
            'console',
            'is-tomcat-running',
            'logoff',
            ADMIN_PREFIX
    ]

    static mappings = {

        //"/"(controller: "home")
        "/login/$action"(controller: "login")
        "/admin/$controller/$action?/$id?(.$format)?"(namespace:"admin")

        String prefix = CONTENT_PREFIX //TODO grab from config
        String contentURI = (prefix ? '/' : '') + prefix

        "${contentURI}/$uri**"(controller:"content") {
            action = ['GET': 'show', 'POST':'update']
            constraints {
                uri(validator: { path ->
                    return !FORBIDDEN.any { p -> return path?.startsWith(p) }
                })
            }
        }

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
