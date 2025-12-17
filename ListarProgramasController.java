package lp.JavaFxClient.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import lp.JavaFxClient.DTO.ProgramaVoluntariadoDTO;
import lp.JavaFxClient.services.ApiService;

public class ListarProgramasController {
	
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
	private void onAssEdicao() {
		ProgramaVoluntariadoDTO selected = programasTable.getSelectionModel().getSelectedItem();
		 if (selected == null) {
			 showError("Selecione um programa primeiro!");
			 return;
			 
		 }
		 else {
			 TextInputDialog dialog = new TextInputDialog();
			 dialog.setHeaderText("Insira o id de edição: ");
			 dialog.setContentText("ID:");
			 Optional<String> resultado = dialog.showAndWait();

			    if (resultado.isPresent()) {
			        Long id = Long.parseLong(resultado.get());
			        selected.addEdicao(id);
			    }
		 }
	}
	
	@FXML
	private void onAssInstituicao() {
		ProgramaVoluntariadoDTO selected = programasTable.getSelectionModel().getSelectedItem();
		 if (selected == null) {
			 showError("Selecione um programa primeiro!");
			 return;
			 
		 }
		 else {
			 TextInputDialog dialog = new TextInputDialog();
			 dialog.setHeaderText("Insira o id da Instituição: ");
			 dialog.setContentText("ID:");
			 Optional<String> resultado = dialog.showAndWait();

			    if (resultado.isPresent()) {
			        Long id = Long.parseLong(resultado.get());
			        selected.setInstituicaoId(id);
			    }
		 }
	}
	
	@FXML
	private void onAssTipo() {
	    ProgramaVoluntariadoDTO selected =
	            programasTable.getSelectionModel().getSelectedItem();

	    if (selected == null) {
	        showError("Selecione um programa primeiro!");
	        return;
	    }

	    TextInputDialog dialog = new TextInputDialog();
	    dialog.setHeaderText("Insira o ID do Tipo de Voluntariado");
	    dialog.setContentText("ID:");

	    Optional<String> resultado = dialog.showAndWait();

	    if (resultado.isEmpty()) return;

	    try {
	        Long tipoId = Long.parseLong(resultado.get());

	        selected.setTiposVoluntariadoId(tipoId);

	        api.put(
	            "/voluntariado/programasvoluntariado/" + selected.getId(),
	            selected
	        );

	        new Alert(Alert.AlertType.INFORMATION,
	                "Tipo de voluntariado associado com sucesso!")
	                .showAndWait();

	        loadProgramas();

	    } catch (NumberFormatException e) {
	        showError("ID inválido!");
	    } catch (Exception e) {
	        showError("Erro ao associar tipo: " + e.getMessage());
	    }
	}

}