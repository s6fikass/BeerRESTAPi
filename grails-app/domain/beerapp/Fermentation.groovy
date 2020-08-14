package beerapp
import grails.persistence.Entity
import io.micronaut.core.annotation.Introspected

@Entity
@Introspected
class Fermentation {
    Temperature temp
    static mapping = {
        temp cascade:'all'

    }
}
