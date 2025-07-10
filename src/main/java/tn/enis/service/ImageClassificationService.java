package tn.enis.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.util.List;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.enis.dto.ClassificationResponse;

@Service
public class ImageClassificationService {

    private final WebClient webClient;

    @Value("${huggingface.api.url}")
    private String apiUrl;

    @Value("${huggingface.api.token}")
    private String apiToken;

    public ImageClassificationService(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public ClassificationResponse classify(MultipartFile file) {
        try {
            byte[] imageBytes = file.getBytes();

            List<ClassificationResponse> response = webClient.post()
                    .uri(apiUrl)
                    .header(HttpHeaders.AUTHORIZATION, apiToken)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .bodyValue(imageBytes)
                    .retrieve()
                    .bodyToFlux(ClassificationResponse.class)
                    .collectList()
                    .block();

            if (response != null && !response.isEmpty()) {
                return response.get(0); // Meilleure prédiction
            } else {
                throw new RuntimeException("Empty response from Hugging Face");
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read image file", e);
        }
    }
}


