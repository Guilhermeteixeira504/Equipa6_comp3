package lp.JavaFxClient.controllers;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lp.JavaFxClient.DTO.EdicaoDTO;
import lp.JavaFxClient.services.ApiService;

public class CriarEdicaoController {

    @FXML private TextField anoField;
    @FXML private TextField numeroVagasField;
    @FXML private TextField dataInicioField;
    @FXML private TextField dataFimField;
    @FXML private TextField programaIdField;
    @FXML private Label mensagemLabel;

    private final ApiService apiService = new ApiService();

    @FXML
    private void onCriar() {

        try {
            // validacao
            if (anoField.getText().isEmpty()
                    || numeroVagasField.getText().isEmpty()
                    || dataInicioField.getText().isEmpty()
                    || dataFimField.getText().isEmpty()
                    || programaIdField.getText().isEmpty()) {

                mensagemLabel.setText("Preencha todos os campos.");
                return;
            }

            EdicaoDTO edicao = new EdicaoDTO();
            edicao.setAno(Integer.parseInt(anoField.getText()));
            edicao.setNumeroVagas(Integer.parseInt(numeroVagasField.getText()));
            edicao.setDataInicio(LocalDate.parse(dataInicioField.getText()));
            edicao.setDataFim(LocalDate.parse(dataFimField.getText()));
            edicao.setProgramaId(Long.parseLong(programaIdField.getText()));

            String resposta = apiService.post("/edicoes", edicao);

            if (resposta != null && resposta.startsWith("ERROR")) {
                mensagemLabel.setText(resposta);
            } else {
                mensagemLabel.setText("Edição criada com sucesso.");
                limparCampos();
            }

        } catch (Exception e) {
            mensagemLabel.setText("Erro ao criar edição.");
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        anoField.clear();
        numeroVagasField.clear();
        dataInicioField.clear();
        dataFimField.clear();
        programaIdField.clear();
    }
}