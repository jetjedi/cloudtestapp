package com.example.cloudtestapp.model;

import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="First name is required")
    @Column(name="first_name", nullable=false, length=100)
    private String firstName;

    @NotBlank(message="Last name is required")
    @Column(name="last_name", nullable=false, length=100)
    private String lastName;

    @Email(message="Email must be valid")
    @NotBlank(message="Email is required")
    @Column(nullable=false, length=200, unique=true)
    private String email;
    
    /*
    I want to see all addresses that belong to a user.
    mappedBy points back to the field name in Address.
    Cascade makes it easy when I remove a user.
    I also turn on orphanRemoval so leftover child rows do not hang around.
	*/
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses = new ArrayList<>();
	
	public List<Address> getAddresses() { return addresses; }
	public void setAddresses(List<Address> addresses) { this.addresses = addresses; }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String v) { this.firstName = v; }

    public String getLastName() { return lastName; }
    public void setLastName(String v) { this.lastName = v; }

    public String getEmail() { return email; }
    public void setEmail(String v) { this.email = v; }
}
