package csulb.cecs323.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Studio class to model studios of a Movie database; a Studio can produce many movies.
 * In the case of Movies, the Studio can be one of many Studios that produces the same Movie.
 */
@Entity
@Table(name="studios", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@NamedQuery(name= Studio.FIND_STUDIO, query = "SELECT s.name FROM Studio s WHERE s.name=:name")
public class Studio {
    public static final String FIND_STUDIO = "Studio.findMovie";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Country country;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "moviestudios", joinColumns = @JoinColumn(name = "studio_Id"),
            inverseJoinColumns = @JoinColumn(name = "movie_Id"))
    private List<Movie> movies =  new ArrayList<Movie>();

    public Studio(){
    }

    public Studio(String name, Country country){
        this.name = name;
        this.country = country;
    }

    public Long getStudioID() { return id; }

    public void setStudioID(Long studioID) { this.id = studioID; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Country getCountry() { return country; }

    public void setCountry(Country country) { this.country = country; }

    public List<Movie> getMovies() { return movies; }

    public void setMovies(List<Movie> movies) { this.movies = movies; }

    public void addMovie(Movie movie) { this.movies.add(movie); }

    public boolean equals(Object object) {
        if(object instanceof Studio) {
            Studio s = (Studio) object;
            return name == s.name;
        } else {
            return false;
        }
    }
}
