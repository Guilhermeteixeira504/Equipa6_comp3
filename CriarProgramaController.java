package lp.JavaFxClient.controllers;

import javafx.scene.control.TextArea;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
	private TextField txtDataInicio;
	@FXML
	private TextField txtDataFim;
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
		dto.setDataInicio(LocalDate.parse(txtDataInicio.getText()));
		dto.setDataFim(LocalDate.parse(txtDataFim.getText()));
		dto.setHoras(Integer.parseInt(txtHoras.getText()));
		dto.setEdicoesId(null);
		dto.setInscricoesId(null);
		dto.setInstituicaoId(null);
		dto.setTiposVoluntariadoId(null);
		String response = api.post("/voluntariado/programasvoluntariado", dto);
		
		new Alert(Alert.AlertType.INFORMATION,"Programa de Voluntariado criado!").showAndWait();
        txtTitulo.getScene().getWindow().hide();
		} catch (Exception e) {
			new Alert(Alert.AlertType.ERROR, "Erro: " + e.getMessage()).showAndWait();
			}
	}
}
