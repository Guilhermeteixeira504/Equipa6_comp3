package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lp.JavaFxClient.DTO.ProgramaVoluntariadoDTO;
import lp.JavaFxClient.services.ApiService;

public class ProcurarPorIdController {
    
    private final ApiService api = new ApiService();
    private ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());
    
    @FXML
    private TextField txtIdPrograma;
    
    @FXML
    private Button btnProcurar;
    
    @FXML
    private Button btnLimpar;
    
    @FXML
    private TableView<ProgramaVoluntariadoDTO> programasTable;
    
    @FXML
    private TableColumn<ProgramaVoluntariadoDTO, String> colunaTitulo;
    
    @FXML
    private TableColumn<ProgramaVoluntariadoDTO, String> colunaLocalizacao;
    
    @FXML
    private TableColumn<ProgramaVoluntariadoDTO, Integer> colunaVagas;
    
    @FXML
    private TableColumn<ProgramaVoluntariadoDTO, Integer> colunaHoras;

    
    private ObservableList<ProgramaVoluntariadoDTO> programasList;
    
    @FXML
    public void initialize() {
        // Configurar as colunas
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        colunaVagas.setCellValueFactory(new PropertyValueFactory<>("numeroVagas"));
        colunaHoras.setCellValueFactory(new PropertyValueFactory<>("totalHoras"));
        
        programasList = FXCollections.observableArrayList();
        programasTable.setItems(programasList);
    }
    
    @FXML
    private void onProcurar(ActionEvent event) {
        try {
            String idText = txtIdPrograma.getText().trim();
            
            if (idText.isEmpty()) {
                showAlert("Erro", "Por favor, insira um ID.");
                return;
            }
            
            long id = Long.parseLong(idText);
            
            String endpoint = "/voluntariado/programasvoluntariado/" + id;
            String json = api.get(endpoint);
            
            if (json.startsWith("ERROR:")) {
                showAlert("Não encontrado", "Programa com ID " + id + " não encontrado.");
                programasList.clear();
                return;
            }
            
            ProgramaVoluntariadoDTO programa = mapper.readValue(json, ProgramaVoluntariadoDTO.class);
            
            // Limpar e adicionar o programa encontrado
            programasList.clear();
            programasList.add(programa);
            
        } catch (NumberFormatException e) {
            showAlert("Erro", "ID inválido. Deve ser um número.");
        } catch (Exception e) {
            showAlert("Erro", "Erro ao procurar programa: " + e.getMessage());
        }
    }
    
    @FXML
    private void onLimpar(ActionEvent event) {
        txtIdPrograma.clear();
        programasList.clear();
    }
    
    private void showAlert(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}