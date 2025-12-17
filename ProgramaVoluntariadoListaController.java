package lp.JavaFxClient.controllers;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lp.JavaFxClient.DTO.ProgramaVoluntariadoDTO;

public class ProgramaVoluntariadoListaController {

    @FXML private TableView<ProgramaVoluntariadoDTO> tableProgramas;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, String> colTitulo;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, String> colLocalizacao;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, Integer> colVagas;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, Object> colDataInicio;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, Object> colDataFim;

    //private final ApiService apiService = new ApiService();
    private ObservableList<ProgramaVoluntariadoDTO> listaProgramas =
            FXCollections.observableArrayList();

    // inicializacao
    @FXML
    public void initialize() {
        configurarTabela();
        carregarProgramas();
    }

    private void configurarTabela() {

        colTitulo.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getTitulo()));

        colLocalizacao.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getLocalizacao()));

        colVagas.setCellValueFactory(c ->
                new javafx.beans.property.SimpleIntegerProperty(c.getValue().getNumeroVagas()).asObject());

        colDataInicio.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getDataInicio()));

        colDataFim.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getDataFim()));

        tableProgramas.setItems(listaProgramas);
    }

    // carregar dados da API
    private void carregarProgramas() {
        try {
      //      listaProgramas.setAll(apiService.getProgramasVoluntariado());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}