/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author patyy
 */
public class sceneCURPController implements Initializable {

    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyMMdd");
    private ObservableMap<String, String> listSexo;
    private ObservableMap<String, String> listEstado;

    private final String[] CONSONANTES = {"B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"};
    private final String[] VOCALES = {"A", "E", "I", "O", "U"};
    private String sexo = "";
    private String estado = "";
    @FXML
    private TextField txtApellidoPaterno;

    @FXML
    private TextField txtApellidoMaterno;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox comboBoxEstado;

    @FXML
    private DatePicker dpFechaNacimiento;

    @FXML
    private ComboBox comoboboxGenero;

    @FXML
    private TextField txtCuerp;

    @FXML
    private Button btnGenerar;

    @FXML
    private Button btnBorrar;

    @FXML
    void btnBorrar(ActionEvent event) {

    }

    @FXML
    void btnGenerar(ActionEvent event) {
        StringBuilder generadorCURP = new StringBuilder();
        generadorCURP.append(txtApellidoPaterno.getText().substring(0, 1).concat(obtenerVocal(txtApellidoPaterno.getText())));
        generadorCURP.append(txtApellidoMaterno.getText().substring(0, 1));
        generadorCURP.append(enCasoDeJoseYMaria(txtNombre.getText()).substring(0, 1));
        LocalDate fecha = dpFechaNacimiento.getValue();

        generadorCURP.append(formatoFecha.format(fecha));
        generadorCURP.append(sexo);
        generadorCURP.append(estado);
        generadorCURP.append(obtenerConstante(txtApellidoPaterno.getText()));
        generadorCURP.append(obtenerConstante(txtApellidoMaterno.getText()));
         generadorCURP.append(obtenerConstante(enCasoDeJoseYMaria(txtNombre.getText())));

        System.out.println(" " + comoboboxGenero.getValue());
        txtCuerp.setText(generadorCURP.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HashMap map = new HashMap<String, String>();
        // TODO
        map.put("Hombre", "H");
        map.put("Mujer", "M");
        listSexo = FXCollections.observableMap(map);
        comoboboxGenero.getItems().setAll(listSexo.keySet());

        comoboboxGenero.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String valor = (String) listSexo.get(newValue);
                sexo = valor;
            }
        });

        HashMap mapEstados = new HashMap<String, String>();
        mapEstados.put("AGUASCALIENTES", "AS");
        mapEstados.put("Jalisco", "JC");
         mapEstados.put("D.F.", "DF");
          mapEstados.put("TLAXCALA", "TL");
           mapEstados.put("DURANGO", "DG");
        
        listEstado = FXCollections.observableMap(mapEstados);
        comboBoxEstado.getItems().setAll(listEstado.keySet());
        comboBoxEstado.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String valor = (String) listEstado.get(newValue);
                estado = valor;
            }
        });
      

    }

    private String obtenerVocal(String apellido) {

        String vocal = "X";
        for (int n = 1; n < apellido.length(); n++) {
            for (String vocalAux : VOCALES) {
                String aux = "" + apellido.charAt(n);
                if (aux.equals(vocalAux)) {
                    vocal = aux;
                    return vocal;
                }
            }
        }
        return vocal;
    }

    private String obtenerConstante(String apellido) {

        String constante = "X";
        for (int n = 1; n < apellido.length(); n++) {
            for (String vocalAux : CONSONANTES) {
                String aux = "" + apellido.charAt(n);
                if (aux.equals(vocalAux)) {
                    System.out.println(" " + aux);
                    constante = aux;
                    return constante;
                }
            }
        }
        return constante;
    }
    
    private String enCasoDeJoseYMaria(String nombre){
        String jose = "JOSE";
         String maria = "MARIA";
         int i = nombre.indexOf(jose);
         System.out.println("i " +  i);
         if(nombre.indexOf(jose) >= 0 ){
             return  nombre.replace(jose, "").trim();
         }
         if(nombre.indexOf(jose) >= 0 ){
             return  nombre.replace(maria, "").trim();
         }
        
        
        
        return nombre;
    }

}
