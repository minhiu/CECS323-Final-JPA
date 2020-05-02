package csulb.cecs323.model;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@IdClass(MovieShowingPK.class)
@Table(name="movieshowings")
public class MovieShowing {

    @Id
    private long movieId;
    @Id
    private long theaterId;

    private GregorianCalendar openingDate;
    private GregorianCalendar dateOfLastShow;

    @ManyToOne
    private Theater theater;

    @ManyToOne
    private Movie movie;

    public MovieShowing(GregorianCalendar openingDate, GregorianCalendar dateOfLastShow)
    {
        this.openingDate=openingDate;
        this.dateOfLastShow=dateOfLastShow;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public long getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(long theaterId) {
        this.theaterId = theaterId;
    }

    public void setDateOfLastShow(GregorianCalendar dateOfLastShow) {
        this.dateOfLastShow = dateOfLastShow;
    }

    public GregorianCalendar getDateOfLastShow() {
        return dateOfLastShow;
    }

    public void setOpeningDate(GregorianCalendar openingDate) {
        this.openingDate = openingDate;
    }

    public GregorianCalendar getOpeningDate() {
        return openingDate;
    }
}
