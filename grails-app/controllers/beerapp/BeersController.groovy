package beerapp



class BeersController {
    BeerService BeerService

    def index() {
        List<Beer> beers = BeerService.saveALLBeers()//Beers()

        [beers: beers]
    }

    def ListBeers(){


    }

//    def ListAllBeersWeather(){
//        ForecastWeather forecastWeather = openweathermapService.forecastWeather(params.city)
//        [forecastWeather: forecastWeather]
//    }
}
