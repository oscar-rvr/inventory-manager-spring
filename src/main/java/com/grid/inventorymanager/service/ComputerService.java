package com.grid.inventorymanager.service;

import com.grid.inventorymanager.model.Computer;
import com.grid.inventorymanager.repository.ComputerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComputerService {
    private final ComputerRepository computerRepository;

    public Computer create(Computer computer) {
        return computerRepository.save(computer);
    }

    public Optional<Computer> findById(Long id) {
        return computerRepository.findById(id);
    }

    public List<Computer> findAll() {
        return computerRepository.findAll();
    }

    public void update(Computer computer) {
        computerRepository.save(computer);
    }

    public void deleteById(Long id) {
        computerRepository.deleteById(id);
    }
}
