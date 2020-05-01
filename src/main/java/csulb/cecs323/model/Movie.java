package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;
import java.util.GregorianCalendar;

@Entity
@Table(name="movies")
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private MPAARating mpaaRating;
    private int runtime;
    private int budget;
    private int grossEarnings;
    private int tomatoMeter;

    @Temporal(TemporalType.DATE)
    private GregorianCalendar dateReleased;

    public Movie() {
    }

    public Movie(String title, MPAARating mpaaRating, int runtime, int budget, int grossEarnings, int tomatoMeter) {
        this.title = title;
        this.mpaaRating = mpaaRating;
        this.runtime = runtime;
        this.budget = budget;
        this.grossEarnings = grossEarnings;
        this.tomatoMeter = tomatoMeter;
    }
}
