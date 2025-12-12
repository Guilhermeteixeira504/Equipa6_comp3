package lp.JavaFxClient.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

import lp.JavaFxClient.model.InscricaoDTO;
import lp.JavaFxClient.services.ApiService;

public class InscricaoViewController {

    // tabela
    @FXML private TableView<InscricaoDTO> tableInscricoes;
    @FXML private TableColumn<InscricaoDTO, Long> colId;
    @FXML private TableColumn<InscricaoDTO, Long> colPrograma;
    @FXML private TableColumn<InscricaoDTO, Long> colVoluntario;
    @FXML private TableColumn<InscricaoDTO, Integer> colHoras;

    // campos
    @FXML private TextField txtId;
    @FXML private TextField txtProgramaId;
    @FXML private TextField txtVoluntarioId;
    @FXML private TextField txtHoras;

    // botoes
    @FXML private Button btnNovo;
    @FXML private Button btnGuardar;
    @FXML private Button btnEliminar;
    @FXML private Button btnAtualizar;

    private final ApiService apiService = new ApiService();
    private final ObservableList<InscricaoDTO> listaInscricoes =
            FXCollections.observableArrayList();

    // inicializacao
    @FXML
    public void initialize() {
        configurarTabela();
        configurarSelecaoTabela();
        carregarInscricoes();
    }

    private void configurarTabela() {
        colId.setCellValueFactory(c ->
                new SimpleLongProperty(c.getValue().getId()).asObject());

        colPrograma.setCellValueFactory(c ->
                new SimpleLongProperty(c.getValue().getProgramaId()).asObject());

        colVoluntario.setCellValueFactory(c ->
                new SimpleLongProperty(c.getValue().getVoluntarioId()).asObject());

        colHoras.setCellValueFactory(c ->
                new SimpleIntegerProperty(
                        c.getValue().getnHorasRealizadas()).asObject());

        tableInscricoes.setItems(listaInscricoes);
    }

    private void configurarSelecaoTabela() {
        tableInscricoes.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        preencherFormulario(newVal);
                    }
                });
    }

    // carregar dados
    private void carregarInscricoes() {
        try {
            listaInscricoes.setAll(apiService.getInscricoes());
        } catch (Exception e) {
            mostrarErro("Erro ao carregar inscrições: " + e.getMessage());
        }
    }

    // formulario
    private void preencherFormulario(InscricaoDTO dto) {
        txtId.setText(String.valueOf(dto.getId()));
        txtProgramaId.setText(String.valueOf(dto.getProgramaId()));
        txtVoluntarioId.setText(String.valueOf(dto.getVoluntarioId()));
        txtHoras.setText(String.valueOf(dto.getnHorasRealizadas()));
    }

    // botoes
    @FXML
    private void onNovo() {
        txtId.clear();
        txtProgramaId.clear();
        txtVoluntarioId.clear();
        txtHoras.clear();
        tableInscricoes.getSelectionModel().clearSelection();
    }

    @FXML
    private void onGuardar() {
        try {
            if (txtProgramaId.getText().isEmpty()
                    || txtVoluntarioId.getText().isEmpty()
                    || txtHoras.getText().isEmpty()) {
                mostrarErro("Preencha todos os campos obrigatórios.");
                return;
            }

            InscricaoDTO dto = new InscricaoDTO();
            dto.setProgramaId(Long.parseLong(txtProgramaId.getText()));
            dto.setVoluntarioId(Long.parseLong(txtVoluntarioId.getText()));
            dto.setnHorasRealizadas(
                    Integer.parseInt(txtHoras.getText()));

            if (txtId.getText().isEmpty()) {
                // criar
                InscricaoDTO criar =
                        apiService.criarInscricao(dto);
                listaInscricoes.add(criar);
                mostrarInfo("Inscrição criada com sucesso!");
            } else {
                // atualizar
                dto.setId(Long.parseLong(txtId.getText()));
                apiService.atualizarInscricao(dto.getId(), dto);
                carregarInscricoes();
                mostrarInfo("Inscrição atualizada com sucesso!");
            }

            onNovo();

        } catch (Exception e) {
            mostrarErro("Erro ao guardar inscrição: " + e.getMessage());
        }
    }

    @FXML
    private void onEliminar() {
        try {
            if (txtId.getText().isEmpty()) {
                mostrarErro("Selecione uma inscrição primeiro.");
                return;
            }

            long id = Long.parseLong(txtId.getText());
            apiService.eliminarInscricao(id);
            carregarInscricoes();
            mostrarInfo("Inscrição eliminada com sucesso!");
            onNovo();

        } catch (Exception e) {
            mostrarErro("Erro ao eliminar: " + e.getMessage());
        }
    }

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