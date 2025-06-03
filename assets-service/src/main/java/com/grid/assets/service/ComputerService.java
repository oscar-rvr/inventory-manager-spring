package com.grid.assets.service;

import com.grid.assets.dto.ComputerDTO;
import com.grid.assets.dto.ComputerPatchDTO;
import com.grid.assets.exceptions.ComputerNotFoundException;
import com.grid.assets.model.Computer;
import com.grid.assets.repository.ComputerRepository;
import com.grid.assets.specifications.ComputerSpecification;
import com.grid.common.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public PagedResponse<ComputerDTO> findAll(int page,
                                              int size,
                                              List<String> sortBy,
                                              String direction,
                                              String name,
                                              String description,
                                              Integer ram,
                                              Integer disk,
                                              String core,
                                              String screenState,
                                              String seriesNumber,
                                              String keyboardState,
                                              String shellState,
                                              String comments) {

        Specification<Computer> spec = Specification.where(null);

        if (name != null && !name.isBlank())
            spec = spec.and(ComputerSpecification.hasName(name));
        if (description != null && !description.isBlank())
            spec = spec.and(ComputerSpecification.hasDescription(description));
        if (ram != null)
            spec = spec.and(ComputerSpecification.hasRam(ram));
        if (disk != null)
            spec = spec.and(ComputerSpecification.hasDisk(disk));
        if (core != null && !core.isBlank())
            spec = spec.and(ComputerSpecification.hasCore(core));
        if (seriesNumber != null && !seriesNumber.isBlank())
            spec = spec.and(ComputerSpecification.hasSeriesNumber(seriesNumber));

        Sort sort = Sort.by(sortBy.stream()
                .map(field -> direction.equalsIgnoreCase("desc")
                        ? Sort.Order.desc(field)
                        : Sort.Order.asc(field))
                .toList());

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Computer> computers = computerRepository.findAll(spec, pageable);

        List<ComputerDTO> content = computers.getContent().stream()
                .map(c -> ComputerDTO.builder()
                        .name(c.getName())
                        .description(c.getDescription())
                        .seriesNumber(c.getSeriesNumber())
                        .ram(c.getRam())
                        .disk(c.getDisk())
                        .core(c.getCore())
                        .screenState(c.getScreenState())
                        .keyboardState(c.getKeyboardState())
                        .shellState(c.getShellState())
                        .comments(c.getComments())
                        .build())
                .collect(Collectors.toList());


        return new PagedResponse<>(content,
                computers.getNumber(),
                computers.getSize(),
                computers.getTotalElements(),
                computers.getTotalPages(),
                computers.isLast());
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
