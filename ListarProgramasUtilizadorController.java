package lp.JavaFxClient.controllers;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lp.JavaFxClient.DTO.InscricaoDTO;
import lp.JavaFxClient.DTO.ProgramaVoluntariadoDTO;
import lp.JavaFxClient.services.ApiService;
import session.SessaoUtilizador;

public class ListarProgramasUtilizadorController {
	
	private final ApiService api = new ApiService();
	private ObjectMapper mapper = new ObjectMapper()
	        .registerModule(new JavaTimeModule());
	
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
	
	@FXML
	private Button btnInscrever;
	
	public void initialize() {
		colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        colunaVagas.setCellValueFactory(new PropertyValueFactory<>("numeroVagas"));
        colunaHoras.setCellValueFactory(new PropertyValueFactory<>("totalHoras"));
        colunaDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        colunaDataFim.setCellValueFactory(new PropertyValueFactory<>("dataFim"));

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
	
	@FXML
	private void onInscrever(ActionEvent event) {

	    ProgramaVoluntariadoDTO selected =
	            programasTable.getSelectionModel().getSelectedItem();

	    if (selected == null) {
	        showError("Selecione um programa primeiro!");
	        return;
	    }

	    Long utilizadorId = SessaoUtilizador.getUtilizadorId();

	    if (utilizadorId == null) {
	        showError("Sessão inválida. Faça login novamente.");
	        return;
	    }

	    InscricaoDTO dto = new InscricaoDTO();
	    dto.setProgramaId(selected.getId());
	    dto.setVoluntarioId(utilizadorId);

	    api.post("/voluntariado/inscricoes", dto);

	    new Alert(Alert.AlertType.INFORMATION,
	            "Inscrição efetuada com sucesso!").showAndWait();
	}
}