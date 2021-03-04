package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "locations")
public class Location extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long locationid;

    @Column(nullable = false)
    private String country;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "locations")
//    @JsonIgnoreProperties(value = {"itemsForSale", "locations"})
    private User user;

//    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnoreProperties(value = "location", allowSetters = true)
//    private List<locationItems> items = new ArrayList<>();

    public Location(){
    }

    public Location(String country) {
        this.country = country;
    }

    public Long getLocationid() {
        return locationid;
    }

    public void setLocationid(Long locationid) {
        this.locationid = locationid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public List<MarketLocationItems> getItems() {
//        return items;
//    }
//
//    public void setItems(List<MarketLocationItems> items) {
//        this.items = items;
//    }
}
