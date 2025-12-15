package lp.JavaFxClient.controllers;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lp.JavaFxClient.DTO.InstituicaoDTO;
import lp.JavaFxClient.services.ApiService;

public class RegistarInstituicaoController {

    @FXML private TextField nomeField;
    @FXML private TextField dataFundacaoField;
    @FXML private TextField programaIdField;
    @FXML private Label mensagemLabel;

    private final ApiService apiService = new ApiService();

    @FXML
    private void registarInstituicao() {

        try {
            // validações mínimas
            if (nomeField.getText().isEmpty()
                    || dataFundacaoField.getText().isEmpty()
                    || programaIdField.getText().isEmpty()) {

                mensagemLabel.setText("Preencha todos os campos.");
                return;
            }

            InstituicaoDTO instituicao = new InstituicaoDTO();
            instituicao.setNome(nomeField.getText());
            instituicao.setDataFundacao(LocalDate.parse(dataFundacaoField.getText()));
            instituicao.setProgramaId(Long.parseLong(programaIdField.getText()));

            String resposta = apiService.post("/instituicoes", instituicao);

            if (resposta != null && resposta.startsWith("ERROR")) {
                mensagemLabel.setText(resposta);
            } else {
                mensagemLabel.setText("Instituição registada com sucesso.");
                limparCampos();
            }

        } catch (Exception e) {
            mensagemLabel.setText("Erro ao registar instituição.");
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        nomeField.clear();
        dataFundacaoField.clear();
        programaIdField.clear();
    }
}