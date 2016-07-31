import me.nimavat.lapsi.LapsiSpace
import me.nimavat.lapsi.Role
import me.nimavat.lapsi.User

class BootStrap {

    def init = { servletContext ->
        if(!LapsiSpace.DEFAULT) {
            LapsiSpace space = new LapsiSpace(name: "Default")
            space.uri = LapsiSpace.BLANK
            space.save(failOnError:true, flush:true)
        }

        User admin = User.findByUsername("admin")

        Role adminRole = Role.findByName("ADMIN")
        Role userRole = Role.findByName("USER")

        if(!adminRole) {
            adminRole = new Role(name: "ADMIN")
            adminRole.save(flush:true, failOnError: true)
        }

        if(!userRole) {
            userRole = new Role(name: "USER")
            userRole.save(flush:true, failOnError: true)

        }

        if(!admin){
            admin = new User(username: "admin", password: "admin123", email:"sudhir@nimavat.me")
            admin.save(flush:true, failOnError: true)
            admin.grantRole(userRole)
            admin.grantRole(adminRole)
        }

    }
    def destroy = {
    }
}
