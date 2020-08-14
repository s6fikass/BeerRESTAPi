package beerapp


import grails.persistence.Entity
import io.micronaut.core.annotation.Introspected

@Entity
@Introspected
class Ingredient {


    List<Malt> malt
    List<Hops> hops
    String yeast

    static mapping = {
        malt cascade:'all'
        hops cascade:'all'
    }
    static hasMany = [hops: Hops, malt: Malt ]
}
