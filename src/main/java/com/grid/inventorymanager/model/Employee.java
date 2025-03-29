package com.grid.inventorymanager.model;

import jdk.jfr.DataAmount;

@Entity
@Table(name="employees")
@Data
@DataArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String mail;

}
