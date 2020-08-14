package beerapp
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import grails.persistence.*

@Entity
@Introspected
class Beer {
    @JsonProperty("id")
    Integer id
    String name
    String tagline
    String first_brewed
    String description
    String image_url
    Float abv
    Float ibu
    Integer target_fg
    Integer target_og
    Integer ebc
    Integer srm
    Float ph
    Integer attenuation_level
    Volume volume
    Volume boil_volume
    PrepatationMethod method
    Ingredient ingredients
    List<String> food_pairing
    String brewers_tips
    String contributed_by

    static mapping = {
        ingredients cascade:'all'
        method cascade:'all'
        food_pairing cascade:'all'
        volume cascade:'all'
        boil_volume cascade:'all'
    }



    Beer(Beer beer){
        this.volume=beer.volume

        this.abv=beer.abv
        this.attenuation_level=beer.attenuation_level
        this.boil_volume=beer.volume
        this.brewers_tips=beer.brewers_tips
        this.contributed_by=beer.contributed_by
        this.description=beer.description
        this.ebc=beer.ebc
        this.first_brewed=beer.first_brewed
        this.ibu=beer.ibu
        this.image_url=beer.image_url
        this.id=beer.id
        this.method=beer.method
        this.name=beer.name
        this.ph=beer.ph
        this.srm=beer.srm
        this.tagline=beer.tagline
        this.target_fg=beer.target_fg
        this.target_og=beer.target_og

        this.ingredients= new ArrayList<Ingredient>()
        this.ingredients.addAll(beer.ingredients)
        this.food_pairing= new ArrayList<String>()
        this.food_pairing.addAll(beer.food_pairing)

    }
}
