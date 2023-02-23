package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Table(name="Customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name="first_name")
    private String first_name;
    @Column(name="last_name")
    private String last_name;

    @Column(name = "customer_code")
    private String customer_code;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_customer_id", referencedColumnName = "customer_id")
    private List<Dependent> dependent;


}
