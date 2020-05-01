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

    @ManyToMany
    @JoinTable(name = "movieshowings")
    private List<Theater> theaters;

    public Movie() { }

    public Movie(String title, MPAARating mpaaRating, int runtime, int budget, int grossEarnings, int tomatoMeter) {
        this.title = title;
        this.mpaaRating = mpaaRating;
        this.runtime = runtime;
        this.budget = budget;
        this.grossEarnings = grossEarnings;
        this.tomatoMeter = tomatoMeter;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public GregorianCalendar getDateReleased() { return dateReleased; }

    public void setDateReleased(GregorianCalendar dateReleased) { this.dateReleased = dateReleased; }

    public MPAARating getMpaaRating() { return mpaaRating; }

    public void setMpaaRating(MPAARating mpaaRating) { this.mpaaRating = mpaaRating; }

    public int getRuntime() { return runtime; }

    public void setRuntime(int runtime) { this.runtime = runtime; }

    public int getBudget() { return budget; }

    public void setBudget(int budget) { this.budget = budget; }

    public int getGrossEarnings() { return grossEarnings; }

    public void setGrossEarnings(int grossEarnings) { this.grossEarnings = grossEarnings; }

    public int getTomatoMeter() { return tomatoMeter; }

    public void setTomatoMeter(int tomatoMeter) { this.tomatoMeter = tomatoMeter; }
}
