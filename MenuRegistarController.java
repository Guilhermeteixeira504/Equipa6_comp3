package lp.JavaFxClient.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import lp.JavaFxClient.DTO.UtilizadorDTO;
import lp.JavaFxClient.services.ApiService;

public class MenuRegistarController {

    private final ApiService api = new ApiService();

    @FXML
    private TextField txtnome;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtmorada;

    @FXML
    private TextField txttelemovel;

    @FXML
    private Button btnregistar;

    @FXML
    private MenuButton tipoMenuButton;
    
    @FXML
    private String tipoSelecionado = "USER"; // valor por defeito

    // ===== MENU BUTTON =====
    @FXML
    private void selecionarAdmin() {
        tipoSelecionado = "ADMIN";
        tipoMenuButton.setText("Admin");
    }

    @FXML
    private void selecionarUtilizador() {
        tipoSelecionado = "USER";
        tipoMenuButton.setText("Utilizador");
    }

    // ===== BOTÃO REGISTAR =====
    @FXML
    private void onEntrar() {

        String nome = txtnome.getText().trim();
        String email = txtemail.getText().trim();
        String password = txtpassword.getText().trim();
        String morada = txtmorada.getText().trim();
        String telemovelTexto = txttelemovel.getText().trim();

        // ===== VALIDAÇÕES =====

        if (nome.isEmpty()) {
            alertaErro("O nome não pode estar vazio!");
            return;
        }

        if (email.isEmpty()) {
            alertaErro("O email não pode estar vazio!");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            alertaErro("Email inválido!");
            return;
        }

        int posArroba = email.indexOf("@");
        int posPonto = email.lastIndexOf(".");
        if (posArroba > posPonto) {
            alertaErro("Email inválido!");
            return;
        }

        if (password.isEmpty()) {
            alertaErro("A password não pode estar vazia!");
            return;
        }

        if (morada.isEmpty()) {
            alertaErro("A morada não pode estar vazia!");
            return;
        }

        if (telemovelTexto.isEmpty()) {
            alertaErro("O telemóvel não pode estar vazio!");
            return;
        }

        if (!telemovelTexto.matches("\\d+")) {
            alertaErro("O telemóvel deve conter apenas números!");
            return;
        }

        // ===== TRY/CATCH APENAS PARA PARSE + API =====
        try {
            int numero = Integer.parseInt(telemovelTexto);

            if (numero < 900000000 || numero > 999999999) {
                alertaErro("Número de telemóvel inválido!");
                return;
            }

            UtilizadorDTO dto = new UtilizadorDTO();
            dto.setNome(nome);
            dto.setEmail(email);
            dto.setPassword(password);
            dto.setMorada(morada);
            dto.setTelemovel(numero);
            dto.setTipoUtilizador(tipoSelecionado);

            api.post("/voluntariado/utilizadores", dto);

            new Alert(Alert.AlertType.INFORMATION,
                    "Registo efetuado com sucesso!").showAndWait();

            // fecha janela
            txtnome.getScene().getWindow().hide();

        } catch (Exception e) {
            alertaErro("Erro ao registar utilizador.");
        }
    }

    // ===== MÉTODO AUXILIAR =====
    private void alertaErro(String mensagem) {
        new Alert(Alert.AlertType.ERROR, mensagem).showAndWait();
    }
    
    public void loadUtilizador(UtilizadorDTO dto) {
    	 txtnome.setText(dto.getNome());
    	 txtemail.setText(dto.getEmail());
    	 txttelemovel.setText((String.valueOf(dto.getTelemovel())));
    }
    
}