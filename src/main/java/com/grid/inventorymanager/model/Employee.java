package com.grid.inventorymanager.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    public Employee(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }
/*
    //Relacion con movimientos
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<AssetMovements> assetMovements;
    */
}
