package csulb.cecs323.model;

import javax.persistence.*;
import java.util.GregorianCalendar;
/**
 * MovieShowing class to model movie showings of a Movie database.
 * A MovieShowing can show only one Movie, and be shown at only one Theater.
 * In the case of Movies, a MovieShowing may be one of many movie showings to show the same movie.
 * In the case of Theater, a MovieShowing may be one of many movie showings to be shown at the same theater.
 */
@Entity
@Table(name="movieshowings")
public class MovieShowing {

    @EmbeddedId
    private MovieShowingPK key;

    @MapsId("movieId")
    @JoinColumn(name= "movie_ID", referencedColumnName = "id")
    @ManyToOne
    private Movie movie;

    @MapsId("theaterId")
    @JoinColumn(name= "theater_ID", referencedColumnName = "id")
    @ManyToOne
    private Theater theater;

    @Temporal(TemporalType.DATE)
    private GregorianCalendar openingDate;
    @Temporal(TemporalType.DATE)
    private GregorianCalendar dateOfLastShow;

    public MovieShowing(){}

    public MovieShowing(GregorianCalendar openingDate, GregorianCalendar dateOfLastShow)
    {
        this.openingDate=openingDate;
        this.dateOfLastShow=dateOfLastShow;
    }

    public MovieShowing(MovieShowingPK key)
    {
        this.key= key;
    }

    public MovieShowingPK getMovieShowingKey() {
        return key;
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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }
}
