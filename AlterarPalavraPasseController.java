package lp.JavaFxClient.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import lp.JavaFxClient.DTO.AlterarPasswordDTO;
import lp.JavaFxClient.services.ApiService;
import session.SessaoUtilizador;

public class AlterarPalavraPasseController {

    @FXML
    private PasswordField txtNovaPalavraPasse;

    private final ApiService api = new ApiService();

    @FXML
    private void onAlterar() {
        try {
            Long utilizadorId = SessaoUtilizador.getUtilizadorId();
            String email = SessaoUtilizador.getEmail();

            if (utilizadorId == null || email == null) {
                showError("Sessão inválida. Faça login novamente.");
                return;
            }

            AlterarPasswordDTO dto = new AlterarPasswordDTO();
            dto.setEmail(email);
            dto.setNovaPassword(txtNovaPalavraPasse.getText());

            api.put("/voluntariado/utilizadores/alterarpassword", dto);

            new Alert(Alert.AlertType.INFORMATION,
                    "Password alterada com sucesso!").showAndWait();

            txtNovaPalavraPasse.getScene().getWindow().hide();

        } catch (Exception e) {
            showError("Erro ao alterar password: " + e.getMessage());
        }
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}
