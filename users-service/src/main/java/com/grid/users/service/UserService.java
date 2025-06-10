package com.grid.users.service;

import com.grid.common.dto.PagedResponse;
import com.grid.users.dto.UserDTO;
import com.grid.users.exceptions.UserNotFoundException;
import com.grid.users.model.Role;
import com.grid.users.model.User;
import com.grid.users.repository.UserRepository;
import com.grid.users.specifications.UserSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final WebClient webClient;

    public UserService(UserRepository userRepository, WebClient webClient) {
        this.userRepository = userRepository;
        this.webClient = webClient;
    }

    public void validateEmployeeExists(Long employeeId) {
        String url = "http://employees-service/v1/employees/" + employeeId;

        webClient.get().uri(url).retrieve().onStatus(status -> status.is4xxClientError(), response -> Mono.error(new RuntimeException("Employee not found: " + employeeId))).bodyToMono(Void.class).block();
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public PagedResponse<UserDTO> getAllUsers(int page, int size, List<String> sortBy, String direction, String username, Role role) {

        Specification<User> spec = Specification.where(null);

        if (username != null && !username.isBlank()) {
            spec = spec.and(UserSpecification.hasUsername(username));
        }

        if (role != null) {
            spec = spec.and(UserSpecification.hasRole(role.toString()));
        }

        Sort sort = Sort.by(sortBy.stream().map(field -> direction.equalsIgnoreCase("desc") ? Sort.Order.desc(field) : Sort.Order.asc(field)).toList());

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<User> userPage = userRepository.findAll(spec, pageable);

        List<UserDTO> content = userPage.getContent().stream().map(user -> {
            UserDTO dto = new UserDTO();
            dto.setUsername(user.getUsername());
            dto.setPassword(user.getPassword());
            dto.setRole(user.getRole());
            return dto;
        }).toList();

        return new PagedResponse<>(content, userPage.getNumber(), userPage.getSize(), userPage.getTotalElements(), userPage.getTotalPages(), userPage.isLast());
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id: " + id));

        if (userDTO.getPassword() != null) {
            existingUser.setPassword(userDTO.getPassword());
        }

        if (userDTO.getRole() != null) {
            existingUser.setRole(userDTO.getRole());
        }

        if (userDTO.getUsername() != null) {
            existingUser.setUsername(userDTO.getUsername());
        }

        return userRepository.save(existingUser);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
