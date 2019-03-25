package com.us.train.movieinfoservice.resources;

import com.us.train.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieInfoResource {

    @RequestMapping("/{movieId}")
    public Movie getMovie(@PathVariable("movieId") String movieId) {
        return new Movie(movieId, "test Movie");
    }
}
