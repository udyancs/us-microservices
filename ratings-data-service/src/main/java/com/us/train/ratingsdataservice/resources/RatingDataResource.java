package com.us.train.ratingsdataservice.resources;

import com.us.train.ratingsdataservice.models.Rating;
import com.us.train.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingDataResource {
    @RequestMapping("/{userId}")
    public Rating getRating(@PathVariable("userId") String userId){
        return new Rating("", 4);
    }

    @RequestMapping("users/{userId}")
    public UserRating getRatings(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 3),
                new Rating("5678", 4)
        );
        return new UserRating(ratings);
    }
}
