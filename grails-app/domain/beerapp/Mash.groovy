package beerapp
import grails.persistence.Entity
import io.micronaut.core.annotation.Introspected

@Entity
@Introspected
class Mash {

    Temperature temp
    Integer duration

    static mapping = {
        temp cascade:'all'

    }
}
