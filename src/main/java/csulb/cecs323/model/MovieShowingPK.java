package csulb.cecs323.model;


import java.io.Serializable;

public class MovieShowingPK implements Serializable {
    private long movieId;
    private long theaterId;

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

    public int hashCode()
    {
        return (int)(movieId+theaterId);
    }
}
