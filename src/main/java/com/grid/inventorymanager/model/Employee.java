package com.grid.inventorymanager.model;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import java.util.List;
@Entity
@Table(name="employees")//Se mapea la tabla "employees"
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id autoincrementable
    private Integer id;

    private String name;
    private String mail;

    //Relacion con movimientos
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssetMovements> assetMovements;


    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private User user;
    // Adding role field with enum mapping

    @Enumerated(EnumType.STRING) // This ensures that the role is stored as a string in the database
    private Role role; // The Role enum
}