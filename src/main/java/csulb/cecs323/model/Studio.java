package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="studios")
public class Studio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studioID;
    private String name;
    private Country country;

    @ManyToMany
    @JoinTable(name = "movietudios")
    private List<Movie> movies;

    public Studio(){
    }

    public Studio(long studioID, String name, Country country){
        this.studioID = studioID;
        this.name = name;
        this.country = country;
    }

    public Long getStudioID() { return studioID; }

    public void setStudioID(Long studioID) { this.studioID = studioID; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Country getCountry() { return country; }

    public void setCountry(Country country) { this.country = country; }
}
