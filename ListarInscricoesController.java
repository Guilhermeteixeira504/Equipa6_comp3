package lp.JavaFxClient.controllers;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lp.JavaFxClient.DTO.InscricaoDTO;
import lp.JavaFxClient.services.ApiService;

public class ListarInscricoesController {
	private final ApiService api = new ApiService();
	private final ObjectMapper mapper = new ObjectMapper();
	
	@FXML
	private TableView <InscricaoDTO> inscricoesTable;
	@FXML
	private TableColumn <InscricaoDTO, Long> colunaVoluntario;
	@FXML
	private TableColumn <InscricaoDTO, Long> colunaPrograma;
	
	public void initialize() {
        colunaVoluntario.setCellValueFactory(new PropertyValueFactory<>("voluntarioId"));
        colunaPrograma.setCellValueFactory(new PropertyValueFactory<>("programaId"));

        loadInscricoes();
    }
		private void loadInscricoes() {
		try {
			String json = api.get("/voluntariado/inscricoes");
			if (json.startsWith("ERROR:")) {
					showError(json);
					return;
				 }
			List<InscricaoDTO> inscricoes =
						mapper.readValue(json, new TypeReference<List<InscricaoDTO>>() {});
					 inscricoesTable.getItems().setAll(inscricoes);
		}catch (Exception e) {
		 showError("Erro a Carregar Inscrições: " + e.getMessage());
		}
}
	
	private void showError(String msg) {
		Alert a = new Alert(Alert.AlertType.ERROR, msg);
		a.showAndWait();
		}
	

}