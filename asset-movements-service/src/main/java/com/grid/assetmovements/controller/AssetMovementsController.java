package com.grid.assetmovements.controller;

import com.grid.assetmovements.dto.AssetMovementsDTO;
import com.grid.assetmovements.exceptions.AssetNotFoundException;
import com.grid.assetmovements.exceptions.EmployeeNotFoundException;
import com.grid.assetmovements.model.AssetMovements;
import com.grid.assetmovements.model.AssetMovementsId;
import com.grid.assetmovements.service.AssetMovementsService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/movements")
public class AssetMovementsController {

    private final AssetMovementsService assetMovementsService;
    private final WebClient.Builder webClientBuilder;

    @GetMapping
    public List<AssetMovements> showAssetMovements() {
        return assetMovementsService.findAll();
    }

    @GetMapping("/emp/{employeeId}")
    public List<AssetMovements> showAssetMovementsByEmployee(@PathVariable Long employeeId) {
        AssetMovementsId id = new AssetMovementsId();
        id.setEmployeeId(employeeId);

        AssetMovements assetMovements = new AssetMovements();
        assetMovements.setId(id);

        return assetMovementsService.findAll(Example.of(assetMovements));
    }

    @GetMapping("/asset/{assetId}")
    public List<AssetMovements> showAssetMovementsByAsset(@PathVariable Long assetId) {
        AssetMovementsId id = new AssetMovementsId();
        id.setAssetId(assetId);

        AssetMovements assetMovements = new AssetMovements();
        assetMovements.setId(id);

        return assetMovementsService.findAll(Example.of(assetMovements));
    }

    @GetMapping("/{assetId}/{employeeId}")
    public List<AssetMovements> showSpecificAssetMovements(@PathVariable Long assetId, @PathVariable Long employeeId) {
        AssetMovementsId id = new AssetMovementsId(assetId, employeeId);

        AssetMovements assetMovements = new AssetMovements();
        assetMovements.setId(id);

        return assetMovementsService.findAll(Example.of(assetMovements));
    }

    @PostMapping
    public ResponseEntity<AssetMovements> createMovement(@Valid @RequestBody AssetMovementsDTO dto,
                                                         @RequestHeader("X-User-Id") Long userId) {

        AssetMovements assetMovements = AssetMovements.builder()
                .id(new AssetMovementsId(dto.getEmployeeId(), dto.getAssetId()))
                .assetMovementDate(dto.getAssetMovementDate())
                .movementType(dto.getMovementType())
                .build();

        AssetMovements saved = assetMovementsService.create(assetMovements, userId);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).body(saved);
    }

    private void validateEmployeeExists(Long employeeId) {
        String url = "http://employees-service/v1/employees/" + employeeId;

        webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> Mono.error(new EmployeeNotFoundException("id: " + employeeId)))
                .bodyToMono(Void.class)
                .block();
    }

    private void validateAssetExists(Long assetId) {
        String url = "http://assets-service/v1/assets/" + assetId;

        webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> Mono.error(new AssetNotFoundException("id: " + assetId)))
                .bodyToMono(Void.class)
                .block();
    }

    @GetMapping("/test-employees")
    public Mono<String> testEmployeesService() {
        return webClientBuilder.build()
                .get()
                .uri("http://employees-service/v1/employees/ping")
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> System.out.println("✅ Response: " + response))
                .doOnError(error -> System.err.println("❌ Error: " + error.getMessage()));
    }


}
