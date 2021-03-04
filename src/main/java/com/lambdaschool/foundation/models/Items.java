package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.foundation.models.User;
import jdk.jfr.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
@JsonIgnoreProperties("hasPrice")
public class Items extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemid;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private double price;

//    private String url;

    @Transient
    public boolean hasPrice = false;


    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties({"items", "userroles"})
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "type")
//    @JsonIgnoreProperties({"items", "userroles"})
//    private Category category;


    public Items(){

    }

    public Items(String location, String name, String description, String category, double price, User user) {
        this.location = location;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getItemid() {
        return itemid;
    }

    public void setItemid(long itemid) {
        this.itemid = itemid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {
        hasPrice = true;
        this.price = price;
    }

//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
}
