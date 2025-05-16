package com.e_commerce.e_commerce_demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "user_details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /*
     * Cascade is used here to propagate persistence operations from the parent (User)
     * to the child (Address) entities. This means that when a User is saved, updated,
     * or deleted, the same operations will automatically be applied to all associated
     * Address records.
     *
     * This is particularly useful for managing the lifecycle of child entities through
     * the parent, ensuring data consistency and reducing boilerplate code.
     *
     * For example:
     * - Saving a User with addresses will automatically persist those addresses.
     * - Deleting a User will also delete all associated addresses (if orphanRemoval = true).
     * It is unidirectional Mapping
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Address> address;

    private String phone;

    //bidirectional Mapping
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    public User( String name, List<Address> address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
