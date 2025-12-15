package lp.JavaFxClient.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import lp.JavaFxClient.DTO.InscricaoDTO;
import lp.JavaFxClient.services.ApiService;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class InscricaoController {

    // tabela
    @FXML private TableView<InscricaoDTO> tableInscricoes;
    @FXML private TableColumn<InscricaoDTO, Long> colPrograma;
    @FXML private TableColumn<InscricaoDTO, Integer> colHoras;

    // campos
    @FXML private TextField txtProgramaId;

    // botoes
    @FXML private Button btnGuardar;
    @FXML private Button btnAtualizar;

    private final ApiService apiService = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    private final ObservableList<InscricaoDTO> listaInscricoes =
            FXCollections.observableArrayList();

    // utilizador logado
    private Long voluntarioId;

    // inicializacao
    @FXML
    public void initialize() {
        configurarTabela();
    }

    private void configurarTabela() {
        colPrograma.setCellValueFactory(c ->
                new SimpleLongProperty(c.getValue().getProgramaId()).asObject());

        colHoras.setCellValueFactory(c ->
                new SimpleIntegerProperty(c.getValue().getnHorasRealizadas()).asObject());

        tableInscricoes.setItems(listaInscricoes);
    }

    // recebe o id do voluntario do login
    public void setVoluntarioId(Long id) {
        this.voluntarioId = id;
        carregarInscricoes();
    }

    // carregar dados (apenas as inscricoes do utilizador)
    private void carregarInscricoes() {
        if (voluntarioId == null) {
            return;
        }

        try {
            String json = apiService.get("/voluntariado/inscricoes");

            List<InscricaoDTO> todas =
                    mapper.readValue(json, new TypeReference<List<InscricaoDTO>>() {});

            listaInscricoes.setAll(
                    todas.stream()
                          .filter(i -> i.getVoluntarioId().equals(voluntarioId))
                          .toList()
            );

        } catch (Exception e) {
            mostrarErro("Erro ao carregar inscrições: " + e.getMessage());
        }
    }

    // botao inscrever-me
    @FXML
    private void onGuardar() {
        try {
            if (txtProgramaId.getText().isEmpty()) {
                mostrarErro("Indique o ID do programa.");
                return;
            }

            if (voluntarioId == null) {
                mostrarErro("Utilizador não identificado. Faça login novamente.");
                return;
            }

            InscricaoDTO dto = new InscricaoDTO();
            dto.setProgramaId(Long.parseLong(txtProgramaId.getText()));
            dto.setVoluntarioId(voluntarioId);

            apiService.post("/voluntariado/inscricoes/inscrever", dto);

            mostrarInfo("Inscrição efetuada com sucesso!");
            txtProgramaId.clear();
            carregarInscricoes();

        } catch (Exception e) {
            mostrarErro("Erro ao inscrever: " + e.getMessage());
        }
    }

    // atualizar lista
    @FXML
    private void onAtualizar() {
        carregarInscricoes();
        mostrarInfo("Lista atualizada com sucesso!");
    }

    // caixas de dialogo (erro ou informacao se alguma coisa correr bem ou mal)
    private void mostrarErro(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Erro");
        alert.setContentText(msg);
        alert.show();
    }

    private void mostrarInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Informação");
        alert.setContentText(msg);
        alert.show();
    }
}