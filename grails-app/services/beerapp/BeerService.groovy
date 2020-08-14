package beerapp

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import grails.gorm.transactions.Transactional
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.uri.UriBuilder
import org.apache.commons.logging.impl.SLF4JLog
import org.hibernate.internal.CoreLogging
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.ThreadContext
import groovy.util.logging.Slf4j

@Slf4j
@Transactional
class BeerService implements GrailsConfigurationAware{

    String punkAPIUrl
    BlockingHttpClient client
//    final Logger log = LogManager.getLogger(getClass())

    @Override
    void setConfiguration(Config co) {
        punkAPIUrl = co.getProperty('beerapi.url', String, 'http://api.punkapi.com')
        this.client = HttpClient.create(punkAPIUrl.toURL()).toBlocking()
    }

    List<Beer> getBeers() {
        try {

            UriBuilder uriBuilder = UriBuilder.of('/v2/beers')
                    .queryParam('page', "1")
                    .queryParam('per_page', "80")

            HttpRequest request = HttpRequest.GET(uriBuilder.build())
            return client.retrieve(request, Argument.listOf(Beer) )

        } catch (HttpClientResponseException e) {
            return "Error"
        }
    }

    List<Beer> saveALLBeers() {
//        int i=1
        List<Beer> res= new ArrayList<Beer>()

        for(int i=1;i<6;i++){
            try {

                UriBuilder uriBuilder = UriBuilder.of('/v2/beers')
                        .queryParam('page', i.toString())
                        .queryParam('per_page', "80")

                HttpRequest request = HttpRequest.GET(uriBuilder.build())

                List<Beer> temp_list= client.retrieve(request, Argument.listOf(Beer) )

                if(temp_list.size()==0){
                    return res
                }else{
                    res.addAll(temp_list)
                }
//        for(int i=0;i<res.size();i++){
//            res.get(i).save()
//        }
            } catch (HttpClientResponseException e) {
                return "Error"

            }

        }
        for(int i=0;i<res.size();i++){
            log.info(res.get(i))
//            res.get(i).save(flush: true)
            saveBeer(res.get(i))
        }
//        saveAll
        return res

        }
    void saveBeer(Beer beer){
        Beer resBeer= new Beer(beer)
//        resBeer.ingredients.addAll(beer.ingredients)
        resBeer.save(flush: true)
    }

    }


