package lp.JavaFxClient.controllers;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lp.JavaFxClient.DTO.UtilizadorDTO;
import lp.JavaFxClient.services.ApiService;

public class ListarUtilizadoresController {
	
	private final ApiService api = new ApiService();
	private final ObjectMapper mapper = new ObjectMapper();
	
	@FXML
	private TableView <UtilizadorDTO> utilizadoresTable;
	@FXML
	private TableColumn <UtilizadorDTO,String>colunaNome;
	@FXML
	private TableColumn <UtilizadorDTO,String>colunaEmail;
	@FXML
	private TableColumn <UtilizadorDTO,Integer>colunaContacto;
	@FXML
	private TableColumn <UtilizadorDTO,String>colunaTipoUtilizador;
	
	public void initialize() {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaContacto.setCellValueFactory(new PropertyValueFactory<>("telemovel"));
        colunaTipoUtilizador.setCellValueFactory(new PropertyValueFactory<>("tipoUtilizador"));

        loadUtilizadores();
    }
		private void loadUtilizadores() {
		try {
			String json = api.get("/voluntariado/utilizadores");
			if (json.startsWith("ERROR:")) {
					showError(json);
					return;
				 }
			List<UtilizadorDTO> utilizadores =
						mapper.readValue(json, new TypeReference<List<UtilizadorDTO>>() {});
					 utilizadoresTable.getItems().setAll(utilizadores);
		}catch (Exception e) {
		 showError("Error loading students: " + e.getMessage());
		}
}
	
	private void showError(String msg) {
		Alert a = new Alert(Alert.AlertType.ERROR, msg);
		a.showAndWait();
		}
	
}

