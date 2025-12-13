package lp.JavaFxClient.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import lp.JavaFxClient.dto.TiposVoluntariadoDTO;
import lp.JavaFxClient.services.ApiService;

public class CriarTipoVoluntariadoController {
	
	private final ApiService api = new ApiService();
	
	@FXML
	private TextField txtNome;
	@FXML
	private Button btnCriar;

	@FXML
	private void onCriar(ActionEvent event) {
		try {
		TiposVoluntariadoDTO dto = new TiposVoluntariadoDTO();
		dto.setNome(txtNome.getText());
		
		String response = api.post("/voluntariado/tiposvoluntariados", dto);
		
		new Alert(Alert.AlertType.INFORMATION,"Tipo de voluntariado criado!").showAndWait();
        txtNome.getScene().getWindow().hide();

		} catch (Exception e) {
			new Alert(Alert.AlertType.ERROR, "Erro: " + e.getMessage()).showAndWait();
			}
	}

}
