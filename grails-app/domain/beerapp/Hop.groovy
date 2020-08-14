package beerapp


import grails.persistence.Entity
import io.micronaut.core.annotation.Introspected

@Entity
@Introspected
class Hop {

    String name
    Amount amount
    String add
    String attribute

    static mapping = {
        amount cascade:'all'
    }
    static belongsTo = [ingredient:Ingredient]
}
