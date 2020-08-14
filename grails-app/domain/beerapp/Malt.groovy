package beerapp


import grails.persistence.Entity
import io.micronaut.core.annotation.Introspected

@Entity
@Introspected
class Malt {
    String name
    Amount amount

    static mapping = {
        amount cascade:'all'

    }


    static belongsTo = [ingredient:Ingredient]
}
