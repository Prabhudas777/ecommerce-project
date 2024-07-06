package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5,message = "Street must contain minimum 5 characters")
    private String street;
    @NotBlank
    @Size(min = 6,message = "Building Name must contain minimum 6 characters")
    private String buildingName;
    private String pinCode;
    private String city;
    private String state;
    @NotBlank
    @Size(min = 2,message = "Country must contain minimum 2 characters")
    private String country;

    public Address(String street, String buildingName, String pinCode, String city, String state, String country) {
        this.street = street;
        this.buildingName = buildingName;
        this.pinCode = pinCode;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> user = new ArrayList<>();
}
