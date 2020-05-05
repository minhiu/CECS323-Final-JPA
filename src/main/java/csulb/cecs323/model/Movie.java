package csulb.cecs323.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.GregorianCalendar;

@Entity
@Table(name="movies")
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private MPAARating mpaaRating;
    private int runtime;
    private int budget;
    private int grossEarnings;
    private int tomatoMeter;

    @Temporal(TemporalType.DATE)
    private GregorianCalendar dateReleased;

    @OneToMany(mappedBy = "movie")
    private List<MovieShowing> movieshowings;

    @OneToOne
    private Movie sequel;

    @ManyToMany(mappedBy = "movies")
    private List<Studio> studios = new ArrayList<Studio>();

    public Movie() { }

    public Movie(String title, GregorianCalendar dateReleased, MPAARating mpaaRating, int runtime, int budget, int grossEarnings, int tomatoMeter) {
        this.title = title;
        this.dateReleased = dateReleased;
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

    public List<Studio> getStudios() { return studios; }

    public void setStudios(List<Studio> studios) { this.studios = studios; }

    public Movie getSequel() { return sequel; }

    public void setSequel(Movie sequel) { this.sequel = sequel; }

    public List<MovieShowing> getMovieShowings() { return movieshowings; }

    public void setMovieshowings(List<MovieShowing> movieshowings) { this.movieshowings = movieshowings; }
}
