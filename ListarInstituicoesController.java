package lp.JavaFxClient.controllers;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lp.JavaFxClient.DTO.InstituicaoDTO;
import lp.JavaFxClient.services.ApiService;

public class ListarInstituicoesController {

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    private TableView<InstituicaoDTO> instituicoesTable;

    @FXML
    private TableColumn<InstituicaoDTO, String> colunaNome;

    @FXML
    private TableColumn<InstituicaoDTO, String> colunaProgramas;

    @FXML
    public void initialize() {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaProgramas.setCellValueFactory(new PropertyValueFactory<>("programasAsString"));

        loadInstituicoes();
    }

    private void loadInstituicoes() {
        try {
            String json = api.get("/voluntariado/instituicoes");

            if (json.startsWith("ERROR:")) {
                showError(json);
                return;
            }

            List<InstituicaoDTO> instituicoes =
                    mapper.readValue(json, new TypeReference<List<InstituicaoDTO>>() {});

            instituicoesTable.getItems().setAll(instituicoes);

        } catch (Exception e) {
            showError("Erro a carregar instituições: " + e.getMessage());
        }
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.showAndWait();
    }
}