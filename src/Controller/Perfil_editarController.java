/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Perfil;
import Model.database.PerfilDB;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class Perfil_editarController implements Initializable {

    @FXML
    private Button BtnLogOut;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    
    @FXML
    private TableView<Perfil> tablaCampos;

    @FXML
    private TableColumn<Perfil, String> colCampos;

    @FXML
    private TableView<Perfil> tablaOpciones;

    @FXML
    private TableColumn<Perfil, String> colOpciones;
    
    @FXML
    private TextArea campoDesc;

    @FXML
    private TextField campoNombre;
    
    private int idPerfil;
    private String nombrePerfil;
    private String descPerfil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }  
    
    public void SetIdPerfil(int id){
        idPerfil = id;
    }
    
    public void SetNombrePerfil(String nombre){
        nombrePerfil = nombre;
    }
    
    public void SetDescPerfil(String desc){
        descPerfil = desc;
    }
    
    public void afterInitialize(){
        campoNombre.setText(nombrePerfil);
        campoDesc.setText(descPerfil);
        
        PerfilDB perfildb = new PerfilDB();
        Perfil perfil = perfildb.obtenerPerfilxNombre(nombrePerfil);
        if((nombrePerfil.equals("Estudios")) || (nombrePerfil.equals("Dominio del idioma"))){
            tablaOpciones.setDisable(false);
    
            List<Perfil> listOpciones = perfildb.obtenerOpcionesxPerfil(perfil);
            for(int i=0; i<listOpciones.size(); i++){
                tablaOpciones.getItems().add(listOpciones.get(i));
            }
            colOpciones.setCellValueFactory(new PropertyValueFactory<>("opciones"));
        }
        
        else if((nombrePerfil.equals("Manejo de Software")) || (nombrePerfil.equals("Competencias"))){
            tablaCampos.setDisable(false);
            
            List<Perfil> listCampos = perfildb.obtenerCamposxPerfil(perfil);
            for(int i=0; i<listCampos.size(); i++){
                tablaCampos.getItems().add(listCampos.get(i));
            }
            colCampos.setCellValueFactory(new PropertyValueFactory<>("campos"));
        }
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
    private void click_salida(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seguridad/Login.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void boton_guardar(ActionEvent event) throws IOException {
     
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Perfiles/perfil_main.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void boton_cancelar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Perfiles/perfil_main.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
