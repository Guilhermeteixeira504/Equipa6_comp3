package lp.JavaFxClient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuUtilizadorController {
	
	@FXML
	private Button btnVerProgramas;
	@FXML
	private Button btnInscrever;
	@FXML
	private Button btnSair;

    @FXML
    void onVerProgramas(ActionEvent event) {
        abrirJanela(
            "/programa-voluntariado-lista.fxml",
            "Programas de Voluntariado"
        );
    }

    @FXML
    void onInscrever(ActionEvent event) {
        abrirJanela(
            "/inscrever-programa.fxml",
            "Inscrição em Programa"
        );
    }

    @FXML
    void onSair(ActionEvent event) {
        ((Stage)((javafx.scene.Node)event.getSource())
                .getScene().getWindow()).close();
    }

    private void abrirJanela(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}