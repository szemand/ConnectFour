package statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProfileStatistics {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String playername;

    private int wins;

    private int losses;
}
