package sk.tuke.nonogram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.tuke.nonogram.entity.Comment;
import sk.tuke.nonogram.entity.Rating;

import java.util.Arrays;


public class RatingServiceRestClient implements RatingService {
    private final String url = "http://localhost:8081/api/rating";

//    @Autowired
//    private RestTemplate restTemplate;
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void setRating(Rating rating) throws RatingException {
        restTemplate.postForEntity(url, rating, Rating.class);
    }

    @Override
    public int getAverageRating(String gameName) throws RatingException {
        return restTemplate.getForObject(url + "/" + gameName + "/average/" , Integer.class);
    }

    @Override
    public int getRating(String gameName, String player) throws RatingException {
        return restTemplate.getForObject(url + "/" + gameName + "/" + player, Integer.class);
    }

    @Override
    public void reset() throws RatingException {
        throw new UnsupportedOperationException("Not supported via web service");
    }
}
