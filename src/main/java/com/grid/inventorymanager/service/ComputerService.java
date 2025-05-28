package com.grid.inventorymanager.service;

import com.grid.inventorymanager.dto.ComputerPatchDTO;
import com.grid.inventorymanager.exceptions.ComputerNotFoundException;
import com.grid.inventorymanager.model.Computer;
import com.grid.inventorymanager.repository.ComputerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Computer> findAll(Pageable pageable) {


        return computerRepository.findAll(pageable);
    }

    public void update(Computer computer) {
        computerRepository.save(computer);
    }

    public void deleteById(Long id) {
        computerRepository.deleteById(id);
    }

    public void update(Long id, ComputerPatchDTO dto) {
        Computer computer = computerRepository.findById(id)
                .orElseThrow(() -> new ComputerNotFoundException("id: " + id));


        if (dto.getName() != null) computer.setName(dto.getName());
        if (dto.getDescription() != null) computer.setDescription(dto.getDescription());
        if (dto.getRam() != null) computer.setRam(dto.getRam());
        if (dto.getDisk() != null) computer.setDisk(dto.getDisk());
        if (dto.getCore() != null) computer.setCore(dto.getCore());
        if (dto.getScreenState() != null) computer.setScreenState(dto.getScreenState());
        if (dto.getKeyboardState() != null) computer.setKeyboardState(dto.getKeyboardState());
        if (dto.getShellState() != null) computer.setShellState(dto.getShellState());
        if (dto.getComments() != null) computer.setComments(dto.getComments());

        computerRepository.save(computer);
    }
}
