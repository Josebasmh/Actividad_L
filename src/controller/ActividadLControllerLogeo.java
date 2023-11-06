package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Propiedades;

public class ActividadLControllerLogeo implements Initializable{

    @FXML
    private Button btnLogin;

    @FXML
    private TextField tfContrasena;

    @FXML
    private TextField tfUsuario;

    @FXML
    void logear(ActionEvent event) {
    	String sUsuario = tfUsuario.getText();
    	String sContrasena = tfContrasena.getText();
    	
    	String txtUsuario = Propiedades.getValor("usuario");
    	String txtContrasena = Propiedades.getValor("contrasena");
    	if(sUsuario.equals(txtUsuario)&&sContrasena.equals(txtContrasena)) {
    		crearVentanaAux();
    	}else {
    		ventanaAlerta("E", "Datos incorrectos.");
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	static void ventanaAlerta(String tipoAlerta, String mensaje) {
		Alert alert = null;
		switch (tipoAlerta) {
			case ("E"):
				alert = new Alert(Alert.AlertType.ERROR);
				break;
			case ("I"):
				alert = new Alert(Alert.AlertType.INFORMATION);
		}
        alert.setContentText(mensaje);
        alert.showAndWait();
	}
	void crearVentanaAux() {
		Stage arg0 = new Stage();
		arg0.setTitle("AVIONES - AEROPUERTOS"); 
		FlowPane aux;
		try {
			aux = (FlowPane)FXMLLoader.load(getClass().getResource("/fxml/listadoAeropuertos.fxml"));
			Scene scene = new Scene(aux,820,600);
			arg0.setScene(scene);
			arg0.setMinWidth(820);
			arg0.setMinHeight(600);
			arg0.initModality(Modality.APPLICATION_MODAL);
			arg0.show();
		} catch (IOException e) {
			System.out.println("La ventana no se abrió correctamente.");
			e.printStackTrace();
		}
	}
}