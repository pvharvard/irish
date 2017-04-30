package irish

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:'tune', action:'index')
        "/analysis"(controller:'tune', action:'analysis')
        //"/"(controller:'home')
        //"/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
