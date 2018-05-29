/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Area;
import Model.ProcesoSeleccion;
import Model.Puesto;
import Model.database.AreaDB;
import Model.database.ProcesoSeleccionDB;
import Model.database.PuestoDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class Proceso_Seleccion_nuevoController implements Initializable {

    @FXML
    private Button BtnLogOut;
    
    @FXML
    private TextArea campoDescripcion;

    @FXML
    private ComboBox<String> comboBoxArea;

    @FXML
    private ComboBox<String> comboBoxPuesto;

    @FXML
    private DatePicker campoFechaIn;

    @FXML
    private DatePicker campoFechaFin;
    
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    
    private ProcesoSeleccion _proceso;

    private ObservableList<String> areasObservable;
    private ObservableList<String> puestosObservable;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        _proceso = new ProcesoSeleccion();
        areasObservable = FXCollections.observableArrayList();
        AreaDB areadb = new AreaDB();
        List<Area> listArea = areadb.obtenerArea();
        for(int i=0; i<listArea.size(); i++){
            areasObservable.add(listArea.get(i).getNombre());
        }
        comboBoxArea.getItems().addAll(areasObservable);
        
    }

    @FXML
    void click_comboArea(ActionEvent event) {
        PuestoDB puestodb = new PuestoDB();
        AreaDB areadb = new AreaDB();
        
        comboBoxPuesto.setDisable(false);
        puestosObservable = FXCollections.observableArrayList();
        Area area = areadb.obtenerAreaxNombre(comboBoxArea.getSelectionModel().getSelectedItem());
        List<Puesto> listPuesto = puestodb.obtenerPuestoxArea(area.getId_area());
        for(int i=0; i<listPuesto.size(); i++){
            puestosObservable.add(listPuesto.get(i).getDescripcion());
        }
        comboBoxPuesto.getItems().clear();
        comboBoxPuesto.getItems().addAll(puestosObservable);
        comboBoxPuesto.setDisable(false);
    }

    @FXML
    private void click_menu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Menu/MenuAdmin.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void click_salida(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seguridad/Login.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void boton_guardar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seleccion/proceso_Seleccion_main.fxml"));
        
        ProcesoSeleccionDB procesodb = new ProcesoSeleccionDB();
        AreaDB areadb = new AreaDB();
        PuestoDB puestodb = new PuestoDB();
        Area area = new Area();
        Puesto puesto = new Puesto();
        
        _proceso.setArea(comboBoxArea.getSelectionModel().getSelectedItem());
        area = areadb.obtenerAreaxNombre(_proceso.getArea());
        _proceso.setId_area(area.getId_area());
        _proceso.setPuesto(comboBoxPuesto.getSelectionModel().getSelectedItem());
        puesto = puestodb.obtenerPuestoxNombre(_proceso.getPuesto());
        _proceso.setId_puesto(puesto.getId_puesto());
        _proceso.setFechaIn(Date.valueOf(campoFechaIn.getValue()));
        _proceso.setFechaIn(Date.valueOf(campoFechaFin.getValue()));
        _proceso.setDescripcion(campoDescripcion.getText());
        
        procesodb.crearProcesoSeleccion(_proceso);
        
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
    }   
    
    @FXML
    private void boton_cancelar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seleccion/proceso_Seleccion_main.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void boton_Postulante(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seleccion/postulantes_main.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void boton_proceso(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seleccion/proceso_Seleccion_main.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void boton_historico(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seleccion/proceso_Seleccion_historico.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void boton_administrar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Configuración/cuenta_main.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void boton_evaluacion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Evaluaciones/tipo_Evaluaciones_main.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();  
    }

    @FXML
    private void Boton_perfiles(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Perfiles/perfil_main.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void boton_cargos(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Puestos/area_main.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void boton_carga(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/CargaMasiva/cargaData.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
