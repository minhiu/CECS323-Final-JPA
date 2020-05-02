package csulb.cecs323.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable

public class MovieShowingPK {
    @Column(name="movie_ID")
    private long movieId;
    @Column(name="theater_ID")
    private long theaterId;

    public boolean equals(Object object)
    {
        if(object instanceof MovieShowingPK){
            MovieShowingPK pk = (MovieShowingPK)object;
            return movieId == pk.movieId && theaterId ==pk.theaterId;
        }
        else{
            return false;
        }
    }

    public int hashCode()
    {
        return (int)(movieId+theaterId);
    }
}
