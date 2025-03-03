package csulb.cecs323.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
/**
 * MovieShowingPK class created to establish a composite primary key for the MovieShowing class.
 */

@Embeddable

public class MovieShowingPK implements Serializable{
    @Column(name="movie_ID")
    private long movieId;
    @Column(name="theater_ID")
    private long theaterId;

    public MovieShowingPK(){}
    public MovieShowingPK(long movieId, long theaterId)
    {
        this.movieId=movieId;
        this.theaterId=theaterId;
    }

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

    public void setTheaterId(long theaterId) {
        this.theaterId = theaterId;
    }

    public long getTheaterId() {
        return theaterId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public long getMovieId() {
        return movieId;
    }

    public int hashCode()
    {
        return (int)(movieId+theaterId);
    }
}
