package csulb.cecs323.model;

import javax.persistence.*;

@Entity
@Table(name = "theater")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;
    private String city;
    private String region;
    private Enumerated country;
    private int numberOfScreens;
    private String phone;
    //asdsadsada
    public Theater(){}

    public Theater(String name, String city, String region, Enumerated country, int numberOfScreens, String phone)
    {
        this.name=name;
        this.city=city;
        this.region=region;
        this.country=country;
        this.numberOfScreens=numberOfScreens;
        this.phone=phone;
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

    public Enumerated getCountry(){
        return country;
    }

    public void setCountry(Enumerated country) {
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
}
