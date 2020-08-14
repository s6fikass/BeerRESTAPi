package beerapp


import grails.persistence.Entity
import io.micronaut.core.annotation.Introspected

@Entity
@Introspected
class PrepatationMethod {

    List<Mash> mash_temp
    Fermentation fermentation
    String twist

    static mapping = {
        mash_temp cascade:'all'
        fermentation cascade:'all'
    }
    static hasMany = [mash_temp: Mash]
}
