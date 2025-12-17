package lp.JavaFxClient.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lp.JavaFxClient.DTO.InstituicaoDTO;
import lp.JavaFxClient.services.ApiService;

public class CriarInstituicaoController {

    @FXML
    private TextField nomeField;

    @FXML
    private Label mensagemLabel;

    private final ApiService apiService = new ApiService();

    @FXML
    private void registarInstituicao() {
        try {
            // validação 
            if (nomeField.getText().trim().isEmpty()) {
                mensagemLabel.setText("O nome da instituição é obrigatório.");
                return;
            }

            InstituicaoDTO instituicao = new InstituicaoDTO();
            instituicao.setNome(nomeField.getText().trim());

            String resposta = apiService.post("/voluntariado/instituicoes", instituicao);

            if (resposta != null && resposta.startsWith("ERROR")) {
                mensagemLabel.setText(resposta);
            } else {
                mensagemLabel.setText("Instituição registada com sucesso.");
                nomeField.clear();
            }

        } catch (Exception e) {
            mensagemLabel.setText("Erro ao registar instituição.");
            e.printStackTrace();
        }
    }
}