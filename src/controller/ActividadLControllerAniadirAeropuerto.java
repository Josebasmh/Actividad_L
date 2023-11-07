package controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.RegistroTabla;

public class ActividadLControllerAniadirAeropuerto implements Initializable{

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Label lbFinanciacion;
    
    @FXML
    private Label lbTrabajadores;
    
    @FXML
    private ToggleGroup pp;

    @FXML
    private RadioButton rbPrivado;

    @FXML
    private RadioButton rbPublico;

    @FXML
    private TextField tfAnio;

    @FXML
    private TextField tfCalle;

    @FXML
    private TextField tfCapacidad;

    @FXML
    private TextField tfCiudad;

    @FXML
    private TextField tfFinanciacion;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfNumero;

    @FXML
    private TextField tfPais;

    @FXML
    private TextField tfTrabajadores;
    
    RegistroTabla r;
    boolean bPrivado;

    @FXML
    void cancelar(ActionEvent event) {
    	Node node = (Node)event.getSource();
    	Stage stage = (Stage) node.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void guardar(ActionEvent event) {
    	if (ActividadLControllerAeropuertosAviones.registro.getId()==0) {
			aniadir();
		}else {
			modificar();
		}
    }

    @FXML
    void publicoPrivado(ActionEvent event) {
    	if (rbPrivado.isSelected()) {
    		bPrivado=true;
    		tfTrabajadores.setVisible(false);
			lbTrabajadores.setText("");
			lbFinanciacion.setText("Nº Socios:");
    	}else {
    		tfTrabajadores.setVisible(true);
    		lbFinanciacion.setText("Financiación:");
    		lbTrabajadores.setText("Número de trabajadores:");
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Creación de variables para no escribir toda esa parrafada. 
		r=ActividadLControllerAeropuertosAviones.registro;
		bPrivado= ActividadLControllerAeropuertosAviones.bPrivado;
		if (r.getId()!=0) {
			tfNombre.setText(r.getNombre());
			tfPais.setText(r.getPais());
			tfCiudad.setText(r.getCiudad());
			tfCalle.setText(r.getCalle());
			tfNumero.setText(r.getNumero().toString());
			tfAnio.setText(r.getAnio().toString());
			tfCapacidad.setText(r.getCapacidad().toString());
			if (bPrivado) {
				rbPrivado.setSelected(true);
				tfTrabajadores.setVisible(false);
				lbTrabajadores.setText("");
				lbFinanciacion.setText("Nº Socios:");
				tfFinanciacion.setText(r.getSocios().toString());
			}else {
				rbPublico.setSelected(true);
				tfTrabajadores.setText(r.getNum_trabajadores().toString());
				tfFinanciacion.setText(r.getFinanciacion().toString());
			}
			rbPrivado.setDisable(true);
			rbPublico.setDisable(true);
		}else {
			rbPublico.setSelected(true);
		}
	}
    
    private void aniadir() {}
    private void modificar() {}
    	
}
