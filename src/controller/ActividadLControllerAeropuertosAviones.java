package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import model.Aeropuerto;

public class ActividadLControllerAeropuertosAviones implements Initializable{

    @FXML
    private ToggleGroup grupoRadioButton;
    
    @FXML
    private Menu menuAeropuertos;

    @FXML
    private Menu menuAviones;

    @FXML
    private RadioButton rbPrivado;

    @FXML
    private RadioButton rbPublico;
    
    @FXML
    private TableView<Aeropuerto> tvTabla;

    @FXML
    private TableColumn<Aeropuerto, Integer> tcAño;

    @FXML
    private TableColumn<Aeropuerto, String> tcCalle;

    @FXML
    private TableColumn<Aeropuerto, Integer> tcCapacidad;

    @FXML
    private TableColumn<Aeropuerto, String> tcCiudad;
    
    @FXML
    private TableColumn<Aeropuerto, Integer> tcFinanciacion;

    @FXML
    private TableColumn<Aeropuerto, Integer> tcId;

    @FXML
    private TableColumn<Aeropuerto, String> tcNombre;

    @FXML
    private TableColumn<Aeropuerto, Integer> tcNumero;

    @FXML
    private TableColumn<Aeropuerto, String> tcPais;

    @FXML
    private TableColumn<Aeropuerto, Integer> tcSocios;
    
    @FXML
    private TableColumn<Aeropuerto, Integer> tcTrabajadores;

    @FXML
    private TextField tfFiltro;

    @FXML
    void filtrarPorNombre(KeyEvent event) {

    }

    @FXML
    void publicoPrivado(ActionEvent event) {
    	if (rbPublico.isSelected()) {
    		tcSocios.setVisible(false);
    		tcFinanciacion.setVisible(true);
    		tcTrabajadores.setVisible(true);
    		tcCapacidad.setPrefWidth(105);
    	}else {
    		tcSocios.setVisible(true);
    		tcFinanciacion.setVisible(false);
    		tcTrabajadores.setVisible(false);
    		tcCapacidad.setPrefWidth(238);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

}
