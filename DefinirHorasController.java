package lp.JavaFxClient.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lp.JavaFxClient.services.ApiService;

public class DefinirHorasController {

    @FXML
    private TextField inscricaoIdField;

    @FXML
    private TextField horasField;

    @FXML
    private TextArea resultadoArea;

    private final ApiService api = new ApiService();

    @FXML
    private void registarHoras() {
        try {
            long inscricaoId = Long.parseLong(inscricaoIdField.getText());
            int horas = Integer.parseInt(horasField.getText());

            String json = """
                {
                    "nHorasRealizadas": %d
                }
            """.formatted(horas);

            String response = api.put(
                "/voluntariado/inscricoes/" + inscricaoId + "/horas",
                json
            );

            if (response.startsWith("ERROR")) {
                resultadoArea.setText(response);
            } else {
                resultadoArea.setText("Horas registadas com sucesso!");
            }

        } catch (NumberFormatException e) {
            resultadoArea.setText("Erro: valores inválidos.");
        }
    }
}