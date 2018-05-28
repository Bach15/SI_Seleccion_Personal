/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Area;
import Model.Puesto;
import Model.database.PuestoDB;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class Puestos_mainController implements Initializable {

    @FXML
    private Button BtnLogOut;
    @FXML
    private Color x2;
    @FXML
    private Font x1;
    
    @FXML
    private TextField textBoxNombre;
    @FXML
    private TextArea textBoxDesc;
    
    @FXML
    private Button boton_editar;
    
    @FXML
    private TableView<Puesto> tablaPuestos;
    
    @FXML
    private TableColumn<Puesto, Integer> colId;

    @FXML
    private TableColumn<Puesto, String> colPuesto;
    
    @FXML
    private Label tituloArea;
    
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    
    private int idArea;
    private String nombreArea;
    private String descripcionArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PuestoDB puestodb = new PuestoDB();
        List<Puesto> listPuesto = puestodb.obtenerPuestoxArea(idArea);
        for(int i=0; i<listPuesto.size();i++){
            tablaPuestos.getItems().add(listPuesto.get(i));
        }
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id_puesto"));
        colPuesto.setCellValueFactory(new PropertyValueFactory<>("nombre")); 
        
        setCellValueFromTableToTextField();
    } 
    
    private void setCellValueFromTableToTextField(){
        tablaPuestos.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                Puesto pl = tablaPuestos.getItems().get(tablaPuestos.getSelectionModel().getSelectedIndex());
                PuestoDB puestodb = new PuestoDB();
                pl = puestodb.obtenerPuestoxId(pl.getId_puesto());
                textBoxNombre.setText(String.valueOf(pl.getNombre()));
                textBoxDesc.setText(String.valueOf(pl.getDescripcion()));
                boton_editar.setDisable(false);
            }
        });      
    }
 
    public void SetIdArea(int id){
        idArea = id;
    }
    
    public void setNombreArea(String nombre){
        nombreArea = nombre;
    }
    
    public void setDescripcionArea(String desc){
        descripcionArea = desc;
    }

    public void afterInitialize(Area area){
        tituloArea.setText(area.getNombre());
        idArea = area.getId_area();
        nombreArea = area.getNombre();
        descripcionArea = area.getDescripcion();
        
        PuestoDB puestodb = new PuestoDB();
        List<Puesto> listPuesto = puestodb.obtenerPuestoxArea(idArea);
        for(int i=0; i<listPuesto.size();i++){
            tablaPuestos.getItems().add(listPuesto.get(i));
        }
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id_puesto"));
        colPuesto.setCellValueFactory(new PropertyValueFactory<>("nombre")); 
        
        setCellValueFromTableToTextField();
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
    private void boton_nuevoPuesto(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Puestos/puestos_nuevo.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/Puestos/puestos_nuevo.fxml"));
        Parent root = fxmlLoader.load();     
        Puestos_nuevoController puestosNuevo = fxmlLoader.getController();

        
        puestosNuevo.setIdArea(idArea);
        puestosNuevo.setNombreArea(nombreArea);
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void boton_editar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Puestos/puestos_editar.fxml"));
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
