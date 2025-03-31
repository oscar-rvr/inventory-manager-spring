package com.grid.inventorymanager.repository;

import com.grid.inventorymanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>
{
}
