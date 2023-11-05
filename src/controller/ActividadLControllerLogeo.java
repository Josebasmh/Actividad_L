package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
}