package com.us.train.moviecatalogservice.resources;

import com.us.train.moviecatalogservice.models.CatalogItem;
import com.us.train.moviecatalogservice.models.Movie;
import com.us.train.moviecatalogservice.models.Rating;
import com.us.train.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.xml.ws.WebServiceClient;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalogItem(@PathVariable("userId") String userId) {

        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);

        return userRating.getRatings().stream().map(p ->  {
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + p.getRating(), Movie.class);
            /*  Movie movie = webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/movies/" + p.getRating())
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
                */

            // java -Dserver.port=8086 -jar *.jar
            return new CatalogItem(movie.getName(), "desc", p.getRating());
        }).collect(Collectors.toList());
    }
}
