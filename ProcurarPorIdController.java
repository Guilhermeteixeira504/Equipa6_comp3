package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lp.JavaFxClient.DTO.ProgramaVoluntariadoDTO;
import lp.JavaFxClient.services.ApiService;

public class ProcurarPorIdController {
private final ApiService api = new ApiService();
private final ObjectMapper mapper = new ObjectMapper();
	
	@FXML
	private TextField txtPrograma;
	@FXML
	private Button buttonProcurar;
	@FXML
	private TextArea resultadoArea;
	
	  private void onProcurarPrograma(ActionEvent event) {
	        try {

	            String idText = txtPrograma.getText();
	            int id = Integer.parseInt(idText);
	            
	            String endpoint = "/voluntariado/programasvoluntariado/" + id;
	            String json = api.get(endpoint);
	            
	            if (json.startsWith("ERROR:")) {
	                resultadoArea.setText("Programa com ID " + id + " não encontrado.");
	                return;
	            }
	            
	            ProgramaVoluntariadoDTO programa = mapper.readValue(json, ProgramaVoluntariadoDTO.class);
	            
	            resultadoArea.setText(programa.toString());
	            
	        } catch (Exception e) {
	            resultadoArea.setText("Erro: " + e.getMessage());
	        }
	    }
}
