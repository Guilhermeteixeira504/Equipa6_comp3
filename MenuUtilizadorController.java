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
	private Button buttonVerProgramas;
	@FXML
	private Button buttonSair;
	@FXML
	private Button buttonAlterarPass;

    @FXML
    private void onVerProgramas(ActionEvent event) {
        abrirJanela(
            "/listarprogramasutilizador.fxml",
            "Programas de Voluntariado"
        );
    }
    
    @FXML
    private void onAlterarPalavraPasse(ActionEvent event) {
    	abrirJanela(
                "/alterarpassword.fxml",
                "Alterar palavra passe"
            );
    }

    @FXML
    private void onSair(ActionEvent event) {
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