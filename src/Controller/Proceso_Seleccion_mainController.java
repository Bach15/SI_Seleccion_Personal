/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ProcesoSeleccion;
import Model.database.ProcesoSeleccionDB;
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
public class Proceso_Seleccion_mainController implements Initializable {

    @FXML
    private Button BtnLogOut;
    
    @FXML
    private TableView<ProcesoSeleccion> tablaPuesto;

    @FXML
    private TableColumn<ProcesoSeleccion, Integer> colId;

    @FXML
    private TableColumn<ProcesoSeleccion, String> colPuesto;
    
    @FXML
    private Color x2;
    @FXML
    private Font x1;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    
    @FXML
    private TextArea textBoxDesc;

    @FXML
    private TextField textBoxPuesto;
    
    @FXML
    private Button boton_editar;
    @FXML
    private Button boton_candidatos;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ProcesoSeleccionDB procesodb = new ProcesoSeleccionDB();
        List<ProcesoSeleccion> listProceso = procesodb.obtenerProceso();
        for(int i=0; i<listProceso.size(); i++){
            tablaPuesto.getItems().add(listProceso.get(i));
        }
        colId.setCellValueFactory(new PropertyValueFactory<>("id_proceso"));
        colPuesto.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        
        setCellValueFromTableToTextField();
    }

    private void setCellValueFromTableToTextField(){
        tablaPuesto.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                ProcesoSeleccion pl = tablaPuesto.getItems().get(tablaPuesto.getSelectionModel().getSelectedIndex());
                ProcesoSeleccionDB procesodb = new ProcesoSeleccionDB();
                pl = procesodb.obtenerProcesoxId(pl.getId_proceso());
                textBoxPuesto.setText(String.valueOf(pl.getPuesto()));
                textBoxDesc.setText(String.valueOf(pl.getDecripcion()));
                boton_editar.setDisable(false);
                boton_candidatos.setDisable(false);
                
            }
        });        
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
    private void boton_nuevo_proceso(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seleccion/proceso_Seleccion_nuevo.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void boton_Editar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seleccion/proceso_Seleccion_editar.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void boton_proceso_postulante(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seleccion/proceso_Seleccion_postulante.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/Seleccion/proceso_Seleccion_postulante.fxml"));
        Parent root = fxmlLoader.load();     
        Proceso_Seleccion_postulanteController postulantesMain = fxmlLoader.getController();
        
        ProcesoSeleccion proceso = tablaPuesto.getSelectionModel().getSelectedItem();
        
        postulantesMain.afterInitialize(proceso);
        
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
