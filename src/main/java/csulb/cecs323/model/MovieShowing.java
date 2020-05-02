package csulb.cecs323.model;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@IdClass(MovieShowingPK.class)
@Table(name="movieshowings")
public class MovieShowing {

    @Id
    @ManyToOne
    @JoinColumn(name= "movieID", referencedColumnName = "id")
    private Movie movie;

    @Id
    @ManyToOne
    @JoinColumn(name= "theaterID", referencedColumnName = "id")
    private Theater theater;


    private GregorianCalendar openingDate;
    private GregorianCalendar dateOfLastShow;


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
