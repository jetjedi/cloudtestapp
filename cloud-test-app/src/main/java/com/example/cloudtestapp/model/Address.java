package com.example.cloudtestapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/*
  I added an Address entity so each user can have one or more addresses.
  I am keeping this very basic on purpose because this is just a test app.
*/
@Entity
@Table(name = "addresses")
public class Address {

    // My primary key. Auto increments in MySQL so I do not set it myself.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
      This is the relationship back to User.
      Many addresses can belong to one user.
      I am making it required because an address without a user does not make any sense to me.
    */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    // I am marking simple fields as required just to keep the demo clean.
    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String zip;

    // Getters and setters. Nothing fancy here.
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }
}
