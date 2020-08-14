package beerapp


import grails.persistence.Entity
import io.micronaut.core.annotation.Introspected

@Entity
@Introspected
class Ingredient {


    List<Malt> malt
    List<Hop> hops
    String yeast

    static mapping = {
        malt cascade:'all'
        hops cascade:'all'
    }
    static belongsTo = [beer:Beer]
    static hasMany = [hops: Hop, malt: Malt ]
}
