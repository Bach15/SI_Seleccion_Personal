/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Evaluacion;
import Model.Pregunta;
import Model.ProcesoSeleccion;
import Model.Respuesta;
import Model.database.EvaluacionDB;
import Model.database.ProcesoSeleccionDB;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class Test_psicologicoController implements Initializable {

    @FXML
    private Button BtnLogOut;
    
    @FXML
    private TableView<Pregunta> tablaPsicologica;

    @FXML
    private TableColumn<Pregunta, String> colPregunta;

    @FXML
    private TableColumn<Pregunta, ComboBox> colRespuesta;

    private int id_usuario;
    
    private ObservableList<Pregunta> listPreguntasObservalble;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO          
    }   
    
    public void afterInitialize(int codUsuario){
        listPreguntasObservalble = FXCollections.observableArrayList();
        
        id_usuario = codUsuario;
        
        ProcesoSeleccionDB procesoSelecciondb = new ProcesoSeleccionDB();
        ProcesoSeleccion proceso = new ProcesoSeleccion(); 
        EvaluacionDB evaluaciondb = new EvaluacionDB();
        Evaluacion evaluacion = new Evaluacion();
        List<Pregunta> listPregunta = new ArrayList<>();
        List<Respuesta> listRespuesta = null;
        
        proceso = procesoSelecciondb.obtenerProcesoxPostulante(id_usuario);
        evaluacion = evaluaciondb.obtenerEvaluacionxPuesto(proceso.getId_puesto());
        listPregunta = evaluaciondb.obtenerPreguntasxEvaluacion(evaluacion.getId_evaluacion());
        for(int i=0; i<listPregunta.size(); i++){
//            tablaPsicologica.getItems().add(listPregunta.get(i));
            listPreguntasObservalble.add(listPregunta.get(i));
        }
        colPregunta.setCellValueFactory(new PropertyValueFactory<>("texto"));
        colRespuesta.setCellValueFactory(new PropertyValueFactory<>("comboRespuesta"));
              
//        colRespuesta.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Pregunta, ComboBox>>(){
//            @Override
//            public void handle(TableColumn.CellEditEvent<Pregunta, ComboBox> event) {
//                System.out.println("Value : " + event.getNewValue());
//            }
//        });
        
        tablaPsicologica.setItems(listPreguntasObservalble);
        tablaPsicologica.setEditable(true);
    }

    @FXML
    private void click_menu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Menu/MenuPostulante.fxml"));
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
    private void boton_siguiente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Test/test_psicologico.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
    }
    
}
