//package com.lambdaschool.foundation.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "categories")
//@JsonIgnoreProperties("item")
//public class Category {
//
//    @Id
//    @Column(nullable = false, unique = true)
//    private String type;
//
//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("categories")
//    private List<Items> items = new ArrayList<>();
//
//    public Category(){}
//
//    public Category(String type) {
//        this.type = type;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public List<Items> getItems() {
//        return items;
//    }
//
//    public void setItems(List<Items> items) {
//        this.items = items;
//    }
//}
//
