package statistics;

import com.google.inject.persist.Transactional;
import util.jpa.GenericJpaDao;

import javax.persistence.Query;
import java.util.List;

/**
 * DAO class for the {@link ProfileStatistics} entity.
 */
public class ProfileStatisticsDao extends GenericJpaDao<ProfileStatistics> {

    public ProfileStatisticsDao() {
        super(ProfileStatistics.class);
    }

    /**
     * Returns all of the registered players from the database with win and lose records.
     * @return a {@code ProfileStatistics} type {@code List}
     */
    @Transactional
    public List<ProfileStatistics> allProfiles() {
        return entityManager.createQuery("SELECT u FROM ProfileStatistics u ORDER BY u.playername", ProfileStatistics.class)
                .getResultList();
    }

    /**
     * Returns all of the registered players
     * @return the playername of all the registered players in a {@code List}.
     */
    @Transactional
    public List profileNames() {
        return entityManager.createQuery("SELECT u.playername FROM ProfileStatistics u ORDER BY u.playername")
                .getResultList();
    }

    /**
     * Updates the database by adding a win for the selected player's record.
     * @param playername the player's name who should get the win.
     */
    @Transactional
    public void updateWinner(String playername){
        String hql = "UPDATE ProfileStatistics u SET u.wins=u.wins+1" + "WHERE u.playername = :playername";
        Query q = entityManager.createQuery(hql);
        q.setParameter("playername", playername);
        q.executeUpdate();
        entityManager.flush();
    }

    /**
     * Updates the database by adding a lose for the selected player's record.
     * @param playername the player's name who should get the lose.
     */
    @Transactional
    public void updateLoser(String playername){
        String hql = "UPDATE ProfileStatistics u SET u.losses=losses+1" + "WHERE u.playername = :playername";
        Query q = entityManager.createQuery(hql);
        q.setParameter("playername", playername);
        q.executeUpdate();
        entityManager.flush();
    }
}
