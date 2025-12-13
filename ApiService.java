package lp.JavaFxClient.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lp.JavaFxClient.model.InscricaoDTO;
import lp.JavaFxClient.model.ProgramaVoluntariadoDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiService {

    private static final String BASE_URL = "http://localhost:8080";

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    // método get
    private String get(String path) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
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

    
    // métodos lista de programas voluntariado
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
}