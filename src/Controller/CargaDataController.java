/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Area;
import Model.Perfil;
import Model.Puesto;
import Model.database.AreaDB;
import Model.database.PerfilDB;
import Model.database.PuestoDB;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class CargaDataController implements Initializable {

    @FXML
    private Button BtnLogOut;
    @FXML
    private TextField textBoxAreas;
    @FXML
    private TextField textBoxPuestos;
     @FXML
    private TextField textBoxSoftware;
    @FXML
    private TextField textBoxCompetencia;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    
    private File areaFile;
    private File puestoFile;
    private File softwareFile;
    private File competenciaFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void boton_carga(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/CargaMasiva/cargaData.fxml"));
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
    private void onMouseClickExaminarAreas(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if(file==null) return;
        String name= file.getName();
        String[] extension= name.split("\\.");
        if((file!= null) && extension[1].equals("csv")){
            textBoxAreas.setText(file.getName());
            areaFile = file;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Alerta");
             alert.setHeaderText(null);
             alert.setContentText("Formato no soportado (solo se aceptan archivos .csv)");
             alert.showAndWait();
             areaFile = null;
            return;
        }
    }

    @FXML
    private void onMouseClickCargarAreas(MouseEvent event) throws FileNotFoundException, IOException {
        if(areaFile == null) return;
        FileReader fr= new FileReader(areaFile);
        BufferedReader br = new BufferedReader(fr);
        String line;
        //line=br.readLine();
        AreaDB areadb = new AreaDB();
        List<Area> listAreas = new ArrayList<>();
        while((line=br.readLine())!=null){
            Area area = new Area();
            String[] parts = line.split(";");
            try{
                area.setNombre(parts[0]);
                area.setDescripcion(parts[1]);
                listAreas.add(area);
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta");
                alert.setHeaderText(null);
                alert.setContentText("Error en el formato de los datos");
                alert.showAndWait();            
                return;
            }
        }
        for(int i= 0; i<listAreas.size(); i++){
            areadb.crearArea(listAreas.get(i));
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mesaje");
        alert.setHeaderText(null);
        alert.setContentText("Se guardaron lo datos con éxito");
        alert.showAndWait();
    }

    @FXML
    private void onMouseClickExaminarPuestos(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if(file==null) return;
        String name= file.getName();
        String[] extension= name.split("\\.");
        if((file!= null) && extension[1].equals("csv")){
            textBoxPuestos.setText(file.getName());
            puestoFile = file;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Alerta");
             alert.setHeaderText(null);
             alert.setContentText("Formato no soportado (solo se aceptan archivos .csv)");
             alert.showAndWait();
             puestoFile = null;
            return;
        }
    }

    @FXML
    private void onMouseClickCargarPuestos(MouseEvent event) throws FileNotFoundException, IOException {
        if(puestoFile == null) return;
        FileReader fr= new FileReader(puestoFile);
        BufferedReader br = new BufferedReader(fr);
        String line;
        //line=br.readLine();
        AreaDB areadb = new AreaDB();
        PuestoDB puestodb = new PuestoDB();        
        List<Puesto> listPuestos = new ArrayList<>();
        while((line=br.readLine())!=null){
            Area area = new Area();
            Puesto puesto = new Puesto();
            String[] parts = line.split(";");
            try{
                area = areadb.obtenerAreaxNombre(parts[0]);
                puesto.setId_area(area.getId_area());
                puesto.setNombre(parts[1]);
                puesto.setDescripcion(parts[2]);
                listPuestos.add(puesto);
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta");
                alert.setHeaderText(null);
                alert.setContentText("Error en el formato de los datos");
                alert.showAndWait();            
                return;
            }
        }
        for(int i= 0; i<listPuestos.size(); i++){
            puestodb.crearPuesto(listPuestos.get(i));
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mesaje");
        alert.setHeaderText(null);
        alert.setContentText("Se guardaron lo datos con éxito");
        alert.showAndWait();
    }

    @FXML
    private void onMouseClickExaminarSoftware(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if(file==null) return;
        String name= file.getName();
        String[] extension= name.split("\\.");
        if((file!= null) && extension[1].equals("csv")){
            textBoxSoftware.setText(file.getName());
            softwareFile = file;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Alerta");
             alert.setHeaderText(null);
             alert.setContentText("Formato no soportado (solo se aceptan archivos .csv)");
             alert.showAndWait();
             softwareFile = null;
            return;
        }
    }

    @FXML
    private void onMouseClickCargarSoftware(MouseEvent event) throws FileNotFoundException, IOException {
        if(softwareFile == null) return;
        FileReader fr= new FileReader(softwareFile);
        BufferedReader br = new BufferedReader(fr);
        String line;
        //line=br.readLine();
        PerfilDB perfildb = new PerfilDB();
        List<Perfil> listPerfil = new ArrayList<>();
        while((line=br.readLine())!=null){
            Perfil perfil = new Perfil();
            String[] parts = line.split(",");
            try{
                perfil = perfildb.obtenerPerfilxNombre("Manejo de Software");
                perfil.setCampos(parts[0]);
                listPerfil.add(perfil);
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta");
                alert.setHeaderText(null);
                alert.setContentText("Error en el formato de los datos");
                alert.showAndWait();            
                return;
            }
        }
        for(int i= 0; i<listPerfil.size(); i++){
            perfildb.crearPerfil(listPerfil.get(i));
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mesaje");
        alert.setHeaderText(null);
        alert.setContentText("Se guardaron lo datos con éxito");
        alert.showAndWait();
        
    }

    @FXML
    private void onMouseClickExaminarCompetencia(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if(file==null) return;
        String name= file.getName();
        String[] extension= name.split("\\.");
        if((file!= null) && extension[1].equals("csv")){
            textBoxCompetencia.setText(file.getName());
            competenciaFile = file;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Alerta");
             alert.setHeaderText(null);
             alert.setContentText("Formato no soportado (solo se aceptan archivos .csv)");
             alert.showAndWait();
             competenciaFile = null;
            return;
        }
    }

    @FXML
    private void onMouseClickCargarCompetencia(MouseEvent event) throws FileNotFoundException, IOException {
        if(competenciaFile == null) return;
        FileReader fr= new FileReader(competenciaFile);
        BufferedReader br = new BufferedReader(fr);
        String line;
        //line=br.readLine();
        PerfilDB perfildb = new PerfilDB();
        List<Perfil> listPerfil = new ArrayList<>();
        while((line=br.readLine())!=null){
            Perfil perfil = new Perfil();
            String[] parts = line.split(",");
            try{
                perfil = perfildb.obtenerPerfilxNombre("Competencias");
                perfil.setCampos(parts[0]);
                listPerfil.add(perfil);
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta");
                alert.setHeaderText(null);
                alert.setContentText("Error en el formato de los datos");
                alert.showAndWait();            
                return;
            }
        }
        for(int i= 0; i<listPerfil.size(); i++){
            perfildb.crearPerfil(listPerfil.get(i));
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mesaje");
        alert.setHeaderText(null);
        alert.setContentText("Se guardaron lo datos con éxito");
        alert.showAndWait();
    }
    
}
