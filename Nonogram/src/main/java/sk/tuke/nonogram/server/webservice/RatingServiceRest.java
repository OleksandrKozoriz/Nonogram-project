package sk.tuke.nonogram.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.nonogram.entity.Rating;
import sk.tuke.nonogram.service.RatingService;

@CrossOrigin
@RestController
@RequestMapping("/api/rating")
public class RatingServiceRest {
    @Autowired
    private RatingService ratingService;

    @GetMapping("/{game}/{player}")
    public int getRating(@PathVariable("game") String game, @PathVariable("player") String player) {
        return ratingService.getRating(game, player);
    }

    @GetMapping("/{game}/average")
    public int getAverageRating(@PathVariable("game") String game) {
        return ratingService.getAverageRating(game);
    }

    @PostMapping
    public void SetRating(@RequestBody Rating rating) {
        ratingService.setRating(rating);
    }
}
