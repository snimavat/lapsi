import me.nimavat.lapsi.TemplateLister
import me.nimavat.springsecurity.LapsiUserDetailsService

// Place your Spring DSL code here
beans = {
    xmlns context:"http://www.springframework.org/schema/context"
    context.'component-scan'('base-package': "me.nimavat")

    userDetailsService(LapsiUserDetailsService)
    templateLister(TemplateLister) {
        grailsApplication = ref("grailsApplication")
    }
}
