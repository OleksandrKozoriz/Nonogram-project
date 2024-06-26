package sk.tuke.nonogram.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQuery(
        name = "Comment.getComments",
        query = "SELECT c FROM Comment c WHERE c.game=:game"
)
@NamedQuery(
        name = "Comment.resetComments",
        query = "DELETE FROM Comment "
)

public class Comment implements Serializable {
    @Id
    @GeneratedValue
    private int indent;
    private String player;
    private String game;
    private String comment;
    private Date commentedOn;

    public Comment(){}
    public Comment(String player, String game, String comment, Date commentedOn) {
        this.player = player;
        this.game = game;
        this.comment = comment;
        this.commentedOn = commentedOn;
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
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Date getCommentedOn() {
        return commentedOn;
    }
    public void setCommentedOn(Date commentedOn) {
        this.commentedOn = commentedOn;
    }
    public int getIndent() {
        return indent;
    }
    public void setIndent(int indent) {
        this.indent = indent;
    }
}