package lp.JavaFxClient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuInicialController {
	
	@FXML
	private Button loginButton;
	@FXML
	private Button registarButton;
	@FXML
	private Button sairButton;

	@FXML
	void  onLogin(ActionEvent event) {
		try {
			FXMLLoader loader = new
			FXMLLoader(getClass().getResource("/login.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Login");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	@FXML
	void onRegistar(ActionEvent event) {
		try {
			FXMLLoader loader = new
					FXMLLoader(getClass().getResource("/registarutilizador.fxml"));
					Parent root = loader.load();
					Stage stage = new Stage();
					stage.setTitle("Registo");
					stage.setScene(new Scene(root));
					stage.show();

				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	
	@FXML
	private void onSair() {
		System.exit(0);
	}

}
