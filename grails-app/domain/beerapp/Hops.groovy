package beerapp
import grails.persistence.Entity
import io.micronaut.core.annotation.Introspected


@Introspected
@Entity
class Hops {

    String name
    Amount amount

    String addition
    String attribute

    static mapping = {
        amount cascade:'all'
    }

    static belongsTo = [ingredient:Ingredient]

}

