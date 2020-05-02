package csulb.cecs323.model;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@Table(name="moviestudios")
public class MovieStudios {
    @Id
    private long movieID;
    @Id
    private long studioID;

    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Studio studio;

    public MovieStudios() {
    }
    public long getMovieID() {
        return movieID;
    }

    public void setMovieID(long movieID) {
        this.movieID = movieID;
    }

    public long getStudioID() { return studioID; }

    public void setStudioID(long studioID) { this.studioID = studioID; }
}

