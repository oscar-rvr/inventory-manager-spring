package com.grid.assets.controller;

import com.grid.inventorymanager.dto.ComputerDTO;
import com.grid.inventorymanager.dto.ComputerPatchDTO;
import com.grid.inventorymanager.dto.PagedResponse;
import com.grid.inventorymanager.exceptions.ComputerNotFoundException;
import com.grid.inventorymanager.model.AssetMovements;
import com.grid.inventorymanager.model.Computer;
import com.grid.inventorymanager.service.ComputerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/computers")
public class ComputerController {

    private final ComputerService computerService;

    @GetMapping
    public ResponseEntity<PagedResponse<ComputerDTO>> retrieveAllComputers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") List<String> sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer ram,
            @RequestParam(required = false) Integer disk,
            @RequestParam(required = false) String core,
            @RequestParam(required = false) String screenState,
            @RequestParam(required = false) String seriesNumber,
            @RequestParam(required = false) String keyboardState,
            @RequestParam(required = false) String shellState,
            @RequestParam(required = false) String comments) {

        PagedResponse<ComputerDTO> response = computerService.findAll(page, size, sortBy, direction, name, description, ram, disk, core, screenState, seriesNumber, keyboardState, shellState, comments);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public Computer retrieveOneComputer(@PathVariable Long id) {
        return computerService.findById(id).orElseThrow(() -> new ComputerNotFoundException("id: " + id));
    }

    @PostMapping
    public ResponseEntity<Computer> createComputer(@Valid @RequestBody ComputerDTO dto) {
        Computer computer = Computer.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .seriesNumber(dto.getSeriesNumber())
                .ram(dto.getRam())
                .disk(dto.getDisk())
                .core(dto.getCore())
                .screenState(dto.getScreenState())
                .keyboardState(dto.getKeyboardState())
                .shellState(dto.getShellState())
                .comments(dto.getComments())
                .build();

        Computer saved = computerService.create(computer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteComputer(@PathVariable Long id) {
        // Validate that the computer is not related to a purchase
        computerService.deleteById(id);
    }

    @GetMapping(path = "/{id}/movements")
    public Set<AssetMovements> retrieveAll(@PathVariable Long id) {
        return computerService.findById(id)
                .orElseThrow(() -> new ComputerNotFoundException("id: " + id))
                .getEmployees();
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Void> updateComputerPartially(@PathVariable Long id, @RequestBody @Valid ComputerPatchDTO dto) {
        computerService.update(id, dto);
        return ResponseEntity.noContent().build();
    }
}
