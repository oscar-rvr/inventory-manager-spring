package com.grid.inventorymanager.service;

import com.grid.inventorymanager.exceptions.UserNotFoundException;
import com.grid.inventorymanager.exceptions.VendorNotFoundException;
import com.grid.inventorymanager.model.User;
import com.grid.inventorymanager.model.Vendor;
import com.grid.inventorymanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id: " + id));

        if(user.getPassword() != null){
            existingUser.setPassword(user.getPassword());
        }

        if(user.getRole() != null){
            existingUser.setRole(user.getRole());
        }

        if(user.getUsername() != null){
            existingUser.setUsername(user.getUsername());
        }

        return userRepository.save(existingUser);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
