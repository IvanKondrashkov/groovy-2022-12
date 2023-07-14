profile {
    name = "open"
    description = "Apache Tomcat"

    http {
        port = 80
        secure = false
    }

    https {
        port = 90
        secure = true
    }

    mappings = [
        {
            url = '/'
            active = false
        },
        {
            url = '/login'
            active = false
        }
    ]
}