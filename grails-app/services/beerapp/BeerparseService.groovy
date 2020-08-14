package beerapp

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import org.grails.web.json.JSONElement

@CompileStatic
class BeerparseService {

    @CompileDynamic
    static Volume volumeFromJsonElement(JSONElement json) {
        Volume volume = new Volume()
        volume.value=json.value
        volume.unit=json.unit
        volume.save()
        return  volume
    }

    @CompileDynamic
    static PrepatationMethod methodFromJsonElement(JSONElement json){
        PrepatationMethod method=new PrepatationMethod()
        method.twist=json.twist
        Temperature temp=new Temperature(value:json.fermentation.temp.value ,unit:json.fermentation.temp.unit )
        temp.save()
        method.fermentation= new Fermentation( temp:temp )
        method.mash_temp= new ArrayList<Mash>()
        Temperature temp2 =new Temperature(value:json.mash_temp[0].temp.value ,unit:json.mash_temp[0].temp.unit)
        temp2.save()
        Mash mash=new Mash(temp: temp2,duration: json.mash_temp[0].duration)
        method.mash_temp.add(mash)
        method.save()
        return method
    }

    @CompileDynamic
    static Ingredient ingredientsFromJsonElement(JSONElement json){

        Ingredient ingredients= new Ingredient()
        ingredients.yeast=json.yeast
        ingredients.hops = new ArrayList<Hops>()
        for(int i=0;i<json.hops.size();i++){
            Amount amount =new Amount(value:  json.hops[i].amount.value, unit: json.hops[i].amount.unit)
            amount.save()
            Hops temp= new Hops(name: json.hops[i].name, addition:json.hops[i].add, attribute:json.hops[i].attribute,
                    amount: amount)
            temp.save()
            ingredients.hops.add(temp)
        }
        ingredients.malt= new ArrayList<Malt>()
        for(int i=0;i<json.malt.size();i++){
            Amount amount=new Amount(value:  json.malt[i].amount.value, unit: json.malt[i].amount.unit)
            amount.save()
            Malt temp= new Malt(name: json.malt[i].name,  amount: amount)
            temp.save()
            ingredients.malt.add(temp)
        }
        ingredients.save()

        return ingredients

    }

    @CompileDynamic
    static Beer BeerFromJSONElement(JSONElement json) {

        Beer beer =new Beer()
        if ( json.volume ) {
            beer.volume = volumeFromJsonElement(json.volume)
        }
        if ( json.boil_volume ) {
            beer.boil_volume = volumeFromJsonElement(json.boil_volume)
        }
        if ( json.method ) {
            beer.method = methodFromJsonElement(json.method)
        }
        if ( json.ingredients ) {
            beer.ingredients=ingredientsFromJsonElement(json.ingredients)
        }

        beer.id=json.id
        beer.name = json.name
        beer.tagline =json.tagline
        beer.first_brewed=json.first_brewed
        beer.description=json.description
        beer.image_url=json.image_url
        beer.abv=json.abv
        beer.ibu=json.ibu
        beer.target_fg=json.target_fg
        beer.target_og=json.target_og
        beer.ebc=json.ebc
        beer.srm=json.srm
        beer.ph=json.ph
        beer.attenuation_level=json.attenuation_level
        beer.brewers_tips=json.brewers_tips
        beer.contributed_by=json.contributed_by

        beer.food_pairing= new ArrayList<>()
        beer.food_pairing.addAll(json.food_pairing)

        beer.save()

        return beer


    }
}
