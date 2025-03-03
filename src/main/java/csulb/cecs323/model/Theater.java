package csulb.cecs323.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Theater class to model theaters of a Movie database.
 * A theater can can show many MovieShowings.
 * In the case of MovieShowings, only one Theater can show that MovieShowing.
 */
@Entity
@Table(name = "theaters", uniqueConstraints = @UniqueConstraint(columnNames = "phone"))
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String region;
    private Country country;
    private int numberOfScreens;
    private String phone;
    @OneToMany(mappedBy = "theater", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MovieShowing> movieShowings = new ArrayList<MovieShowing>();

    public Theater(){}

    public Theater(String name, String city, String region, Country country, int numberOfScreens, String phone)
    {
        this.name = name;
        this.city = city;
        this.region = region;
        this.country = country;
        this.numberOfScreens = numberOfScreens;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name= name;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Country getCountry(){
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getNumberOfScreens() {
        return numberOfScreens;
    }

    public void setNumberOfScreens(int numberOfScreens) {
        this.numberOfScreens = numberOfScreens;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<MovieShowing> getMovieShowings() {
        return movieShowings;
    }

    public void setMovieShowings(List<MovieShowing> movieShowings) {
        this.movieShowings = movieShowings;
    }

    public void addMovieShowing(MovieShowing movieShowing) { this.movieShowings.add(movieShowing); }

}
