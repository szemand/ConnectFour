package statistics;

import com.google.inject.persist.Transactional;
import util.jpa.GenericJpaDao;

import javax.persistence.Query;
import java.util.List;

public class ProfileStatisticsDao extends GenericJpaDao<ProfileStatistics> {

    public ProfileStatisticsDao() {
        super(ProfileStatistics.class);
    }

    @Transactional
    public List<ProfileStatistics> allProfiles() {
        return entityManager.createQuery("SELECT u FROM ProfileStatistics u ORDER BY u.playername", ProfileStatistics.class)
                .getResultList();
    }

    @Transactional
    public List profileNames() {
        return entityManager.createQuery("SELECT u.playername FROM ProfileStatistics u ORDER BY u.playername")
                .getResultList();
    }

    @Transactional
    public void updateWinner(String playername){
        String hql = "UPDATE ProfileStatistics u SET u.wins=u.wins+1" + "WHERE u.playername = :playername";
        Query q = entityManager.createQuery(hql);
        q.setParameter("playername", playername);
        q.executeUpdate();
        entityManager.flush();
    }

    @Transactional
    public void updateLoser(String playername){
        String hql = "UPDATE ProfileStatistics u SET u.losses=losses+1" + "WHERE u.playername = :playername";
        Query q = entityManager.createQuery(hql);
        q.setParameter("playername", playername);
        q.executeUpdate();
        entityManager.flush();
    }
}
