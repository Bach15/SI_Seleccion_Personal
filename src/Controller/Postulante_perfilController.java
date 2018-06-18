/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class Postulante_perfilController implements Initializable {

    @FXML
    private Button BtnLogOut;
    @FXML
    private ComboBox<String> comboEducacion;
    @FXML
    private ComboBox<String> comboIngHablado;
    @FXML
    private ComboBox<String> comboIngEscrito;
    @FXML
    private ComboBox<String> comboIngLectura;
    @FXML
    private Color x4;
    @FXML
    private Font x3;

    private int id_postulante;
    private int id_puesto;
    private int id_proceso;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PerfilDB perfildb = new PerfilDB();
        List listPerfilEduca = perfildb.llenarComboxTipo("Estudios");
        comboEducacion.getItems().addAll(listPerfilEduca);
        List listPerfilIdioma = perfildb.llenarComboxTipo("Dominio del idioma");
        comboIngHablado.getItems().addAll(listPerfilIdioma);
        comboIngEscrito.getItems().addAll(listPerfilIdioma);
        comboIngLectura.getItems().addAll(listPerfilIdioma);
    }    

    public void setIdPostulante(int codigo){
        id_postulante = codigo;
    }
    
    public void setIdPuesto(int codPuesto){
        id_puesto = codPuesto;
    }
    
    public void setIdProceso(int codProceso){
        id_proceso = codProceso;
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
    private void boton_siguiente(ActionEvent event) throws IOException {
        PerfilDB perfildb = new PerfilDB();
        
        String educacionSeleccionada = comboEducacion.getSelectionModel().getSelectedItem();
        int estudios_min = obtenerEstudiosMin(educacionSeleccionada);
        int estudios_minXPuesto = perfildb.ObtenerEstudioXPuesto(id_puesto);
        if(estudios_min > estudios_minXPuesto) estudios_min = estudios_minXPuesto;
        perfildb.agregarEstudioXUsuario(id_postulante, estudios_min);
        
        String hablaMinUs = comboIngHablado.getSelectionModel().getSelectedItem();
        String escrituraMinUs = comboIngEscrito.getSelectionModel().getSelectedItem();
        String lecturaMinUs = comboIngLectura.getSelectionModel().getSelectedItem();
        int hablaMinPu = 0,escrituraMinPu= 0, lecturaMinPu = 0;
        perfildb.ObtenerIdiomaXPuesto(id_puesto, hablaMinPu, escrituraMinPu, lecturaMinPu);
        int habla = obtenerIdiomaMin(hablaMinUs);
        int escritura = obtenerIdiomaMin(escrituraMinUs);
        int lectura = obtenerIdiomaMin(lecturaMinUs);
        if(habla > hablaMinPu) habla = hablaMinPu;
        if(escritura > escrituraMinPu) escritura = escrituraMinPu;
        if(lectura > lecturaMinPu) lectura = lecturaMinPu;
        perfildb.agregarIdiomaXUsuario(id_postulante, habla, escritura, lectura);
        
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seleccion/postulante_perfil_software.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/Seleccion/postulante_perfil_software.fxml"));
        Parent root = fxmlLoader.load();     
        Postulante_perfil_softwareController softwareMain = fxmlLoader.getController();
        
        softwareMain.afterInitialize(id_postulante, id_puesto, id_proceso);
        
        Scene scene = new Scene(root);
       
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public int obtenerEstudiosMin(String estudios_min){
        if(estudios_min.equals("Ninguna")) return 0;
        else if(estudios_min.equals("Secundaria completa")) return 1;
        else if(estudios_min.equals("Estudiantes ultimos ciclos")) return 2;
        else if(estudios_min.equals("Egresado")) return 3;
        else if(estudios_min.equals("Bachiller")) return 4;
        else if(estudios_min.equals("Titulo")) return 5;
        return -1;
    }
    
    public int obtenerIdiomaMin(String idioma_min){
        if(idioma_min.equals("Ninguno")) return 1;
        else if(idioma_min.equals("Basico")) return 2;
        else if(idioma_min.equals("Intermedio")) return 3;
        else if(idioma_min.equals("Avanzado")) return 4;
        return 0;
    }

    @FXML
    private void boton_cancelar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seleccion/proceso_Seleccion_postulante.fxml"));
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
