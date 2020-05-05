package csulb.cecs323.model;

import javax.persistence.*;
import java.util.GregorianCalendar;

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
