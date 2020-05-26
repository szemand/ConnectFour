package statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Class representing a player's information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProfileStatistics {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * The name or nickname of the player.
     */
    @Column(nullable = false)
    private String playername;

    /**
     * The number of the player's won matches.
     */
    private int wins;

    /**
     * The number of the player's lost matches.
     */
    private int losses;
}
