package com.grid.inventorymanager.repository;

import com.grid.inventorymanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{
    //Sustituye a INTERFACE CRUD REPOSITORY DEL GURU, incluye la los metodos de la interface de CRUD mas metodos mas complejos. es

}
