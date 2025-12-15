package lp.JavaFxClient.controllers;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lp.JavaFxClient.DTO.ProgramaVoluntariadoDTO;
import lp.JavaFxClient.services.ApiService;

public class ListarProgramasController {
	
	private final ApiService api = new ApiService();
	private final ObjectMapper mapper = new ObjectMapper();
	
	@FXML
	private TableView <ProgramaVoluntariadoDTO> programasTable;
	@FXML
	private TableColumn <ProgramaVoluntariadoDTO,String>colunaTitulo;
	@FXML
	private TableColumn <ProgramaVoluntariadoDTO,String>colunaLocalizacao;
	@FXML
	private TableColumn <ProgramaVoluntariadoDTO,Integer>colunaVagas;
	@FXML
	private TableColumn <ProgramaVoluntariadoDTO,Integer>colunaHoras;
	@FXML
	private TableColumn <ProgramaVoluntariadoDTO,LocalDate>colunaDataInicio;
	@FXML
	private TableColumn <ProgramaVoluntariadoDTO,LocalDate>colunaDataFim;
	
	public void initialize() {
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        colunaVagas.setCellValueFactory(new PropertyValueFactory<>("vagas"));
        colunaHoras.setCellValueFactory(new PropertyValueFactory<>("horas"));
        colunaDataInicio.setCellValueFactory(new PropertyValueFactory<>("data inicio"));
        colunaDataFim.setCellValueFactory(new PropertyValueFactory<>("data fim"));

        loadProgramas();
    }
		private void loadProgramas() {
		try {
			String json = api.get("/voluntariado/programasvoluntariado");
			if (json.startsWith("ERROR:")) {
					showError(json);
					return;
				 }
			List<ProgramaVoluntariadoDTO> programas =
						mapper.readValue(json, new TypeReference<List<ProgramaVoluntariadoDTO>>() {});
					 programasTable.getItems().setAll(programas);
		}catch (Exception e) {
		 showError("Erro a Carregar Programas de Voluntariado: " + e.getMessage());
		}
}
	
	private void showError(String msg) {
		Alert a = new Alert(Alert.AlertType.ERROR, msg);
		a.showAndWait();
		}
	
}
