package com.grid.assetmovements.client;

import com.grid.assetmovements.exceptions.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EmployeeClient {

    private final WebClient webClient;

    public void validateEmployeeExists(Long employeeId) {
        String url = "http://employees-service/v1/employees/" + employeeId;

        webClient.get().uri(url).retrieve().onStatus(status -> status.is4xxClientError(), response -> Mono.error(new EmployeeNotFoundException("id: " + employeeId))).bodyToMono(Void.class).block();
    }
}
