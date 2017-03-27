package imageUploading

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        name homepage: "/" {
            controller = "imageUploading"
            action = "index"
        }

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
