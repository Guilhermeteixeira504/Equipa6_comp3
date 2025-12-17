package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lp.JavaFxClient.DTO.UtilizadorDTO;
import lp.JavaFxClient.services.ApiService;
import session.SessaoUtilizador;

public class MenuLoginController {
	
	private final ApiService api = new ApiService();
	private final ObjectMapper mapper = new ObjectMapper();
	
	@FXML
	private TextField txtemail;
	@FXML
	private PasswordField txtpass;
	@FXML
	private Button entrarButton;
	
	@FXML
	private void onEntrar() {
		String email = txtemail.getText().trim();
		String pass = txtpass.getText().trim();
		
		try {
			UtilizadorDTO dto = new UtilizadorDTO();
			dto.setEmail(email);
			dto.setPassword(pass);
			
			String json = api.post("/voluntariado/utilizadores/login",dto);
			if(json.startsWith("ERROR")) {
				showError(json); //mudar para -> mensagemLabel.setText("Credenciais inválidas");
				return;
			}
			
			UtilizadorDTO user = mapper.readValue(json, UtilizadorDTO.class);

			SessaoUtilizador.setUtilizadorId(user.getId());
			SessaoUtilizador.setEmail(user.getEmail());
			SessaoUtilizador.setTipoUtilizador(user.getTipoUtilizador());

			abrirMenu(user);
			
		} catch (Exception e) {
			System.out.println("ERRO:");
            e.printStackTrace();
		 }
	}

	private void abrirMenu(UtilizadorDTO u) {
		try {
			if("ADMIN".equalsIgnoreCase(u.getTipoUtilizador())) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/menuAdmin.fxml"));
				Parent root = loader.load();
				Stage stage = new Stage();
				stage.setTitle("Menu Admin");
				stage.setScene(new Scene(root));
				stage.show();
			}
			else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/menuUtilizador.fxml"));
				Parent root = loader.load();
				Stage stage = new Stage();
				stage.setTitle("Menu Utilizador");
				stage.setScene(new Scene(root));
				stage.show();
			}
			
			// Fechar janela de login
			Stage janelaLogin = (Stage) txtemail.getScene().getWindow();
			janelaLogin.close();
			
		} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private void showError(String msg) {
		Alert a = new Alert(Alert.AlertType.ERROR,msg);
		a.showAndWait();
	}
	
}