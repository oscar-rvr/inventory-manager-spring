package com.grid.assetmovements.client;

import com.grid.assetmovements.exceptions.AssetNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AssetClient {

    private final WebClient webClient;

    public void validateAssetExists(Long assetId) {
        String url = "http://assets-service/v1/assets/" + assetId;

        webClient.get()
                .uri(url)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> Mono.error(new AssetNotFoundException("id: " + assetId)))
                .bodyToMono(Void.class)
                .block();
    }
}
