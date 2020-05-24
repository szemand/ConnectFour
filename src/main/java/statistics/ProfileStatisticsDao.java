package statistics;

import com.google.inject.persist.Transactional;
import util.jpa.GenericJpaDao;

import java.util.List;

public class ProfileStatisticsDao extends GenericJpaDao<ProfileStatistics> {

    public ProfileStatisticsDao() {
        super(ProfileStatistics.class);
    }

    @Transactional
    public List<ProfileStatistics> allProfiles() {
        return entityManager.createQuery("SELECT u.playername, u.wins, u.losses, trunc((u.wins/(u.wins + u.losses))*100) FROM ProfileStatistics u ORDER BY u.playername", ProfileStatistics.class)
                .getResultList();
    }
}
