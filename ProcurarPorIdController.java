package lp.JavaFxClient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lp.JavaFxClient.services.ApiService;

public class ProcurarPorIdController {
private final ApiService api = new ApiService();
	
	@FXML
	private TextField txtPrograma;
	@FXML
	private Button buttonCriar;
	@FXML
	private TextArea resultadoArea;
	
	@FXML
	private void onProcurarPrograma(ActionEvent event) {
		try {
			//for(ProgramaVoluntariado programa : programas)
		} catch (Exception e) {
			new Alert(Alert.AlertType.ERROR, "Erro: " + e.getMessage()).showAndWait();
			}
	}
}
