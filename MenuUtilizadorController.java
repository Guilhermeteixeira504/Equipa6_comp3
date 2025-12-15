package lp.JavaFxClient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuUtilizadorController {

    @FXML
    private void onVerProgramas() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/programavoluntariadolista.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Programas de Voluntariado");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onInscrever() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/inscricao.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Inscrição em Programa");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSair(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();
        stage.close();
    }
}