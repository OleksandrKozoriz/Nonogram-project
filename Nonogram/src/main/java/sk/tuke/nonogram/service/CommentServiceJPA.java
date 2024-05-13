package sk.tuke.nonogram.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sk.tuke.nonogram.entity.Comment;

import java.util.List;

@Transactional
public class CommentServiceJPA implements CommentService{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addComment(Comment comment) throws CommentException {
        entityManager.persist(comment);
    }

    @Override
    public List<Comment> getComments(String game) throws CommentException {
        return entityManager.
                createNamedQuery("Comment.getComments", Comment.class).
                setParameter("game", game).
                getResultList();
    }

    @Override
    public void reset() throws CommentException {
        entityManager.
                createNamedQuery("Comment.resetComments").
                executeUpdate();
    }
}
