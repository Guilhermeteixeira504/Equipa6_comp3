package lp.JavaFxClient.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MenuAdminController {
	
	@FXML
	private MenuItem itemCriarTipo;
	@FXML
	private MenuItem itemRegistarInstituicao;
	@FXML
	private MenuItem itemCriarPrograma;
	@FXML
	private MenuItem itemCriarEdicao;
	@FXML
	private MenuItem itemListarProgramas;
	@FXML
	private MenuItem itemProcurarPrograma;
	@FXML
	private MenuItem itemListarUtilizadores;
	@FXML
	private MenuItem itemListarVoluntarios;
	@FXML
	private MenuItem itemListarInscricoes;
	@FXML
	private MenuItem itemDefinirHorasTrabalhadas;
	@FXML
	private MenuItem itemListarInstituicoes;
	
	
	@FXML
	private void onCriarTipo() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/criarTipoVoluntariado.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Criar tipo de voluntariado");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	@FXML
	private void onListarU() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/utilizadoreslista.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Lista Utilizadores");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	@FXML
	private void onListarV() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/voluntarioslista.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Lista Voluntarios");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	@FXML
	private void onListarI() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/inscricoeslista.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Lista Inscrições");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	@FXML
	private void onListarInstituicoes() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/instituicoeslista.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Lista Instituições");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	@FXML
	private void onListarProgramas() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/programavoluntariadolista.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Lista Programas");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	@FXML
	private void onCriarPrograma() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/criarprograma.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Criar Programa");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	@FXML
	private void onRegistarInstituicao() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/criarinstituicao.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Criar Instituição");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	@FXML
	private void onCriarEdicao() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/criaredicao.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Criar Edição");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	@FXML
	private void onProcurarId() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/procurarporid.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Procurar programa");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	@FXML
	private void onDefinirHoras() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/defenirhoras.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Definir Horas");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
}
	
	
	
