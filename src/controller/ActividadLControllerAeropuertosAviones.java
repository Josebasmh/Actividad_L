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
import javafx.scene.input.KeyEvent;

public class ActividadLControllerAeropuertosAviones implements Initializable{

    @FXML
    private Menu menuAeropuertos;

    @FXML
    private Menu menuAviones;

    @FXML
    private RadioButton rbPrivado;

    @FXML
    private RadioButton rbPublico;
    
    @FXML
    private TableView<?> tvTabla;

    @FXML
    private TableColumn<?, ?> tcAño;

    @FXML
    private TableColumn<?, ?> tcCalle;

    @FXML
    private TableColumn<?, ?> tcCapacidad;

    @FXML
    private TableColumn<?, ?> tcCiudad;

    @FXML
    private TableColumn<?, ?> tcId;

    @FXML
    private TableColumn<?, ?> tcNombre;

    @FXML
    private TableColumn<?, ?> tcNumero;

    @FXML
    private TableColumn<?, ?> tcPais;

    @FXML
    private TableColumn<?, ?> tcSocios;

    @FXML
    private TextField tfFiltro;

    @FXML
    void filtrarPorNombre(KeyEvent event) {

    }

    @FXML
    void publicoPrivado(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
