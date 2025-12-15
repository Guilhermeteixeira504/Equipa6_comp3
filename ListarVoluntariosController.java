package lp.JavaFxClient.controllers;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lp.JavaFxClient.DTO.VoluntarioDTO;
import lp.JavaFxClient.services.ApiService;

public class ListarVoluntariosController {
	private final ApiService api = new ApiService();
	private final ObjectMapper mapper = new ObjectMapper();
	
	@FXML
	private TableView <VoluntarioDTO> voluntariosTable;
	@FXML
	private TableColumn <VoluntarioDTO,String>colunaNome;
	@FXML
	private TableColumn <VoluntarioDTO,String>colunaEmail;
	@FXML
	private TableColumn <VoluntarioDTO,Integer>colunaContacto;
	
	public void initialize() {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaContacto.setCellValueFactory(new PropertyValueFactory<>("contacto"));

        loadVoluntarios();
    }

	private void loadVoluntarios() {
		try {
			String json = api.get("/voluntariado/voluntarios");
			if (json.startsWith("ERROR:")) {
					showError(json);
					return;
				 }
			List<VoluntarioDTO> voluntarios =
						mapper.readValue(json, new TypeReference<List<VoluntarioDTO>>() {});
					 voluntariosTable.getItems().setAll(voluntarios);
		}catch (Exception e) {
		 showError("Erro a carregar voluntários: " + e.getMessage());
		}
}
	
	private void showError(String msg) {
		Alert a = new Alert(Alert.AlertType.ERROR, msg);
		a.showAndWait();
		}
}