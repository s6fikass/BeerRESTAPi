package beerapp

import grails.persistence.Entity
import io.micronaut.core.annotation.Introspected

@Entity
@Introspected
class Amount {
    Float value
    String unit

    static constraints = {
        value unique: true
    }
}
