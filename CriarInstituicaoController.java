package lp.JavaFxClient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lp.JavaFxClient.DTO.InstituicaoDTO;
import lp.JavaFxClient.services.ApiService;

public class CriarInstituicaoController {

	private final ApiService api = new ApiService();
	
	@FXML
	private TextField txtNome;
	@FXML
	private Button buttonCriar;
	
	@FXML
	private void onCriar(ActionEvent event) {
		try {
		InstituicaoDTO dto = new InstituicaoDTO();
		dto.setNome(txtNome.getText());
		
		String response = api.post("/voluntariado/instituicoes", dto);
		
		new Alert(Alert.AlertType.INFORMATION,"Instituição criada!").showAndWait();
        txtNome.getScene().getWindow().hide();

		} catch (Exception e) {
			new Alert(Alert.AlertType.ERROR, "Erro: " + e.getMessage()).showAndWait();
			}
	}
}
