package lp.JavaFxClient.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import lp.JavaFxClient.DTO.InscricaoRequestDTO;
import lp.JavaFxClient.DTO.ProgramaVoluntariadoDTO;
import lp.JavaFxClient.services.ApiService;

public class InscreverProgramaController {

    // tabela
    @FXML
    private TableView<ProgramaVoluntariadoDTO> tableProgramas;

    @FXML
    private TableColumn<ProgramaVoluntariadoDTO, String> colTitulo;

    @FXML
    private TableColumn<ProgramaVoluntariadoDTO, String> colLocalizacao;

    @FXML
    private TableColumn<ProgramaVoluntariadoDTO, Integer> colVagas;

    @FXML
    private TableColumn<ProgramaVoluntariadoDTO, Integer> colTotalHoras;

    // service
    private final ApiService apiService = new ApiService();

    // lista
    private final ObservableList<ProgramaVoluntariadoDTO> lista =
            FXCollections.observableArrayList();

    //  deve vir do login
    private final Long voluntarioId = 1L;

    // inicialização
    @FXML
    public void initialize() {
        configurarTabela();
        carregarProgramas();
    }

    private void configurarTabela() {

        colTitulo.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getTitulo()
                )
        );

        colLocalizacao.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getLocalizacao()
                )
        );

        colVagas.setCellValueFactory(c ->
                new SimpleIntegerProperty(
                        c.getValue().getNumeroVagas()
                ).asObject()
        );

        colTotalHoras.setCellValueFactory(c ->
                new SimpleIntegerProperty(
                        c.getValue().getTotalHoras()
                ).asObject()
        );

        tableProgramas.setItems(lista);
    }

    // carrega apenas programas com vagas disponíveis
    private void carregarProgramas() {
        try {
            lista.setAll(
                    apiService.getProgramasVoluntariado()
                            .stream()
                            .filter(p -> p.getNumeroVagas() > 0)
                            .toList()
            );
        } catch (Exception e) {
            alerta("Erro", "Erro ao carregar programas.");
            e.printStackTrace();
        }
    }

    // botão Inscrever-me
    @FXML
    private void onInscrever() {

        ProgramaVoluntariadoDTO programa =
                tableProgramas.getSelectionModel().getSelectedItem();

        if (programa == null) {
            alerta("Aviso", "Selecione um programa.");
            return;
        }

        try {
            apiService.post(
                    "/voluntariado/inscricoes",
                    new InscricaoRequestDTO(
                            programa.getId(),
                            voluntarioId
                    )
            );

            alerta("Sucesso", "Inscrição realizada com sucesso!");
            carregarProgramas();

        } catch (Exception e) {
            alerta("Erro", "Erro ao efetuar inscrição.");
            e.printStackTrace();
        }
    }

    // botão Voltar
    @FXML
    private void onVoltar() {
        tableProgramas.getScene().getWindow().hide();
    }

    // alertas
    private void alerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}