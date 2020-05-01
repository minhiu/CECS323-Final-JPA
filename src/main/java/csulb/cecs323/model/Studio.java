package csulb.cecs323.model;

import javax.persistence.*;

@Entity
@Table(name="studios")
public class Studio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studioID;
    private String name;
    private String country;

    public Studio(){
    }

    public Studio(long studioID, String name, String country){
        this.studioID = studioID;
        this.name = name;
        this.country = country;
    }

    public Long getStudioID() { return studioID; }

    public void setStudioID(Long studioID) { this.studioID = studioID; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }
}
