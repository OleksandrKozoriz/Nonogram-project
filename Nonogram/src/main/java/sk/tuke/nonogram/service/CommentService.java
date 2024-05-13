package sk.tuke.nonogram.service;


import sk.tuke.nonogram.entity.Comment;
import java.util.List;

public interface CommentService {
    void addComment(Comment comment) throws CommentException;
    List<Comment> getComments(String game) throws CommentException;
    void reset() throws CommentException;
}
