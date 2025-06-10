package com.grid.assetmovements.client;

import com.grid.assetmovements.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/*
esta es la clase dedicada a consumir endpoints de user-service
usara webcleint de config
hace llamada de deste tipo al uri
webClient.get().uri("http://users-service/v1/users/{id}", id)
*/
@RequiredArgsConstructor
@Component
public class UserClient {

    private final WebClient webClient;

    public UserDTO getUserById(Long userId) {
        return webClient.get().uri("http://users-service/v1/users/{id}", userId).retrieve().bodyToMono(UserDTO.class).block();
    }
}
