package com.grid.inventorymanager.repository;

import com.grid.inventorymanager.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<Computer, Integer>
{
}
