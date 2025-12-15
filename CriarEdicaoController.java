package lp.JavaFxClient.controllers;

import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lp.JavaFxClient.DTO.EdicaoDTO;
import lp.JavaFxClient.services.ApiService;

public class CriarEdicaoController {
private final ApiService api = new ApiService();
	
	@FXML
	private TextField txtAno;
	@FXML
	private TextField txtDataInicio;
	@FXML
	private TextField txtDataFim;
	@FXML
	private Button buttonCriar;

	@FXML
	private void onCriar(ActionEvent event) {
		try {
		EdicaoDTO dto = new EdicaoDTO();
		dto.setAno(Integer.parseInt(txtAno.getText()));
		dto.setDataInicio(LocalDate.parse(txtDataInicio.getText()));
		dto.setDataFim(LocalDate.parse(txtDataFim.getText()));
		
		String response = api.post("/voluntariado/tiposvoluntariados", dto);
		
		new Alert(Alert.AlertType.INFORMATION,"Edição criada!").showAndWait();
        txtAno.getScene().getWindow().hide();

		} catch (Exception e) {
			new Alert(Alert.AlertType.ERROR, "Erro: " + e.getMessage()).showAndWait();
			}
	}

}
