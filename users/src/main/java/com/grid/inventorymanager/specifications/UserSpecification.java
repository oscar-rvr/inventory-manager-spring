package com.grid.inventorymanager.specifications;

import com.grid.inventorymanager.model.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> hasUsername(String username) {
        return (root, query, cb) -> cb.like(root.get("username"), "%" + username + "%");
    }

    public static Specification<User> hasRole(String role) {
        return (root, query, cb) -> cb.equal(root.get("role"), role);
    }
}
