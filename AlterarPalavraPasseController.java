package lp.JavaFxClient.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lp.JavaFxClient.DTO.VoluntarioDTO;
import lp.JavaFxClient.services.ApiService;

public class AlterarPalavraPasseController {
	
	@FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNovaPalavraPasse;

    private final ApiService api = new ApiService();
    
    @FXML
    private void onAlterar() {
    	try {
    		VoluntarioDTO dto = new VoluntarioDTO();
    		dto.setPassword(txtNovaPalavraPasse.getText());
    		
    		String response = api.put("/voluntariado/utilizadores/alterarpassword", dto);
    		
    		new Alert(Alert.AlertType.INFORMATION,"Password Alterada Com Sucesso!").showAndWait();
            txtEmail.getScene().getWindow().hide();

    		} catch (Exception e) {
    			new Alert(Alert.AlertType.ERROR, "Erro: " + e.getMessage()).showAndWait();
    		}
    }
}

