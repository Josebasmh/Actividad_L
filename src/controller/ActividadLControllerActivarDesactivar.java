package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import model.Aeropuerto;
import model.Avion;

public class ActividadLControllerActivarDesactivar {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ChoiceBox<Aeropuerto> cbAeropuerto;

    @FXML
    private ChoiceBox<Avion> cbAvion;

    @FXML
    private RadioButton rbActivado;

    @FXML
    private RadioButton rbDesactivado;

    @FXML
    private ToggleGroup tgEstado;

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void guardar(ActionEvent event) {

    }

}
