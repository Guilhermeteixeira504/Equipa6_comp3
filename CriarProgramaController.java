package lp.JavaFxClient.controllers;

import javafx.scene.control.TextArea;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lp.JavaFxClient.DTO.ProgramaVoluntariadoDTO;
import lp.JavaFxClient.services.ApiService;

public class CriarProgramaController {
private final ApiService api = new ApiService();
	
	@FXML
	private TextField txtTitulo;
	@FXML
	private TextArea txtDescricao;
	@FXML
	private TextField txtLocalizacao;
	@FXML
	private TextField txtVagas;
	@FXML
	private DatePicker dpDataInicio;
	@FXML
	private DatePicker dpDataFim;
	@FXML
	private TextField txtHoras;
	@FXML
	private Button btnCriar;
	
	@FXML
	private void onCriar(ActionEvent event) {
		try {
		ProgramaVoluntariadoDTO dto = new ProgramaVoluntariadoDTO();
		dto.setTitulo(txtTitulo.getText());
		dto.setDescricao(txtDescricao.getText());
		dto.setLocalizacao(txtLocalizacao.getText());
		dto.setNumeroVagas(Integer.parseInt(txtVagas.getText()));
		dto.setDataInicio(dpDataInicio.getValue());
		dto.setDataFim(dpDataFim.getValue());
		dto.setTotalHoras(Integer.parseInt(txtHoras.getText()));
		
		String response = api.post("/voluntariado/programasvoluntariado", dto);
		System.out.println("Resposta backend: " + response);
		
		new Alert(Alert.AlertType.INFORMATION,"Programa de Voluntariado criado!").showAndWait();
        txtTitulo.getScene().getWindow().hide();
		} catch (Exception e) {
			new Alert(Alert.AlertType.ERROR, "Erro: " + e.getMessage()).showAndWait();
			}
	}
}
