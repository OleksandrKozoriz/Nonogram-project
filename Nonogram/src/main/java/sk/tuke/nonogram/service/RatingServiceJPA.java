package sk.tuke.nonogram.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sk.tuke.nonogram.entity.Rating;

@Transactional
public class RatingServiceJPA implements RatingService{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void setRating(Rating rating) throws RatingException {
        entityManager.
                createQuery("DELETE FROM Rating r WHERE r.player=:player AND r.game=:game").
                setParameter("player", rating.getPlayer()).
                setParameter("game", rating.getGame()).
                executeUpdate();
        entityManager.persist(rating);
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        return (int) Math.round(
                (double) entityManager.
                createNamedQuery("Rating.getAverageRating").
                setParameter("game", game).
                setMaxResults(1).
                getSingleResult()
        );
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        return (int) entityManager.
                createNamedQuery("Rating.getRating").
                setParameter("game", game).
                setParameter("player", player).
                setMaxResults(1).
                getSingleResult();
    }

    @Override
    public void reset() throws RatingException {
        entityManager.createNamedQuery("Rating.resetRating").executeUpdate();
    }
}
