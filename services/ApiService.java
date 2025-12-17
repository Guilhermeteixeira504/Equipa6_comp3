package lp.JavaFxClient.services;

import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ApiService {

    private static final String BASE_URL = "http://localhost:8080";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper;

    public ApiService() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }
    
    // get
    public String get(String path) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + path))
                    .GET()
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }

    // post
    public String post(String path, Object bodyObject) {
        try {
            String json = mapper.writeValueAsString(bodyObject);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + path))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }

    // put
    public String put(String path, Object bodyObject) {
        try {
            String json = mapper.writeValueAsString(bodyObject);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + path))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }

    // delete
    public String delete(String path) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + path))
                    .DELETE()
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}