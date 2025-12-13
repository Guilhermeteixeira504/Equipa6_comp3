package lp.JavaFxClient.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lp.JavaFxClient.DTO.InscricaoDTO;
import lp.JavaFxClient.DTO.ProgramaVoluntariadoDTO;

public class ApiService {

    private static final String BASE_URL = "http://localhost:8080";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

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

    // métodos inscricoes
    public List<InscricaoDTO> getInscricoes() throws Exception {
        String json = get("/voluntariado/inscricoes");

        return mapper.readValue(
                json,
                new TypeReference<List<InscricaoDTO>>() {}
        );
    }

    public InscricaoDTO criarInscricao(InscricaoDTO dto) throws Exception {
        String jsonBody = mapper.writeValueAsString(dto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/voluntariado/inscricoes"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        String json = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return mapper.readValue(json, InscricaoDTO.class);
    }

    public InscricaoDTO atualizarInscricao(Long id, InscricaoDTO dto) throws Exception {
        String jsonBody = mapper.writeValueAsString(dto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/voluntariado/inscricoes/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        String json = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return mapper.readValue(json, InscricaoDTO.class);
    }

    public void eliminarInscricao(Long id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/voluntariado/inscricoes/" + id))
                .DELETE()
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    // métodos lista de programas de voluntariado
    public List<ProgramaVoluntariadoDTO> getProgramasVoluntariado() {
        try {
            String json = get("/voluntariado/programasvoluntariado");

            return mapper.readValue(
                json,
                new TypeReference<List<ProgramaVoluntariadoDTO>>() {}
            );

        } catch (Exception e) {
            throw new RuntimeException(
                "Erro ao carregar programas de voluntariado", e
            );
        }
    }

    // inscrever voluntário num programa de voluntariado
    public InscricaoDTO inscreverPrograma(Long programaId, Long voluntarioId) {
        try {
            String json = """
                    {
                      "programaId": %d,
                      "voluntarioId": %d,
                      "nHorasRealizadas": 0
                    }
                    """.formatted(programaId, voluntarioId);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/voluntariado/inscricoes/inscrever"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            return mapper.readValue(response, InscricaoDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao efetuar inscrição", e);
        }
    }
}