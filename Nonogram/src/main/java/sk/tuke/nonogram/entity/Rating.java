package sk.tuke.nonogram.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.NamedQuery;

import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQuery(
        name = "Rating.getAverageRating",
        query = "SELECT FLOOR(AVG(r.rating)) FROM Rating r WHERE r.game=:game"
)
@NamedQuery(
        name = "Rating.getRating",
        query = "SELECT r.rating FROM Rating r WHERE r.game=:game AND r.player=:player ORDER BY r.rating DESC"
)
@NamedQuery(
        name = "Rating.resetRating",
        query = "DELETE FROM Rating"
)

public class Rating implements Serializable {
    @Id
    @GeneratedValue
    private int indent;
    private String player;
    private String game;
    private int rating;
    private Date ratedOn;

    public Rating(){}
    public Rating(String player, String game, int rating, Date ratedOn) {
        this.player = player;
        this.game = game;
        this.rating = rating;
        this.ratedOn = ratedOn;
    }

    public String getPlayer() {
        return player;
    }
    public void setPlayer(String player) {
        this.player = player;
    }
    public String getGame() {
        return game;
    }
    public void setGame(String game) {
        this.game = game;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public Date getRatedOn() {
        return ratedOn;
    }
    public void setRatedOn(Date ratedOn) {
        this.ratedOn = ratedOn;
    }
    public int getIndent() {
        return indent;
    }
    public void setIndent(int indent) {
        this.indent = indent;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "game='" + game + '\'' +
                ", player='" + player + '\'' +
                ", rating=" + rating +
                ", ratedOn=" + ratedOn +
                '}';
    }
}
