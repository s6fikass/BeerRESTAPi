package beerapp
import org.springframework.web.bind.annotation.RequestMapping


class BeersController {
    BeerService BeerService

    def index() {
        List<Beer> beers = BeerService.saveALLBeers()//Beers()

        [beers: beers]
    }

    def beerName(){
        Beer beer = BeerService.getBeerbyname()
        List<Beer> beers=new ArrayList<Beer>()
        beers.add(beer)
        [beers: beers]
    }

//    def ListAllBeersWeather(){
//        ForecastWeather forecastWeather = openweathermapService.forecastWeather(params.city)
//        [forecastWeather: forecastWeather]
//    }
}
