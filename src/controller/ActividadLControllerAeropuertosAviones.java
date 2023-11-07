package controller;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import dao.AeropuertoDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.RegistroTabla;

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
    private TableView<RegistroTabla> tvTabla;

    @FXML
    private TableColumn<RegistroTabla, Integer> tcAño;

    @FXML
    private TableColumn<RegistroTabla, String> tcCalle;

    @FXML
    private TableColumn<RegistroTabla, Integer> tcCapacidad;

    @FXML
    private TableColumn<RegistroTabla, String> tcCiudad;
    
    @FXML
    private TableColumn<RegistroTabla, Integer> tcFinanciacion;

    @FXML
    private TableColumn<RegistroTabla, Integer> tcId;

    @FXML
    private TableColumn<RegistroTabla, String> tcNombre;

    @FXML
    private TableColumn<RegistroTabla, Integer> tcNumero;

    @FXML
    private TableColumn<RegistroTabla, String> tcPais;

    @FXML
    private TableColumn<RegistroTabla, Integer> tcSocios;
    
    @FXML
    private TableColumn<RegistroTabla, Integer> tcTrabajadores;

    @FXML
    private TextField tfFiltro;
    
    // Variables de clase
    AeropuertoDao aDao= new AeropuertoDao();
    static ObservableList<RegistroTabla>listaRegistros;
    static ObservableList<RegistroTabla>listaFiltrada;
    private boolean bPrivado=true;
    

    @FXML
    void filtrarPorNombre(KeyEvent event) {
    	String sFiltro = tfFiltro.getText(); 
    	
    	// Iterador para la lista y se añade a una lista auxiliar para mostrar en la tabla
    	Iterator<RegistroTabla>it = listaRegistros.iterator();
    	listaFiltrada.clear();    	
    	while(it.hasNext()) {
    		RegistroTabla rt = it.next();
    		if (rt.getNombre().contains(sFiltro)) {
    			listaFiltrada.add(rt);
    		}
    	}
    }

    @FXML
    void publicoPrivado(ActionEvent event) {
    	if (rbPrivado.isSelected()){
    		bPrivado=true;
    	}else{
    		bPrivado=false;
    	}
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
    	rellenarTabla();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rellenarTabla();
	}

	public void rellenarTabla() {
		listaRegistros= aDao.cargarAeropuertos(bPrivado);
		listaFiltrada= aDao.cargarAeropuertos(bPrivado);
		
		tcId.setCellValueFactory(new PropertyValueFactory<RegistroTabla, Integer>("id"));
		tcNombre.setCellValueFactory(new PropertyValueFactory<RegistroTabla, String>("nombre"));
		tcPais.setCellValueFactory(new PropertyValueFactory<RegistroTabla, String>("pais"));
		tcCiudad.setCellValueFactory(new PropertyValueFactory<RegistroTabla, String>("ciudad"));
		tcCalle.setCellValueFactory(new PropertyValueFactory<RegistroTabla, String>("calle"));
		tcNumero.setCellValueFactory(new PropertyValueFactory<RegistroTabla, Integer>("numero"));
		tcAño.setCellValueFactory(new PropertyValueFactory<RegistroTabla, Integer>("anio"));
		tcCapacidad.setCellValueFactory(new PropertyValueFactory<RegistroTabla, Integer>("capacidad"));
		if (bPrivado) {
			tcSocios.setCellValueFactory(new PropertyValueFactory<RegistroTabla, Integer>("socios"));			
		}else {
			tcTrabajadores.setCellValueFactory(new PropertyValueFactory<RegistroTabla, Integer>("num_trabajadores"));
			tcFinanciacion.setCellValueFactory(new PropertyValueFactory<RegistroTabla, Integer>("financiacion"));
		}
		tvTabla.setItems(listaFiltrada);
	}
}
