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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class Perfil_puesto_softwaresController implements Initializable {

    @FXML
    private TableView<Perfil> tablaSoftware;

    @FXML
    private TableColumn<Perfil, String> colSoftware;

    @FXML
    private TableColumn<Perfil, String> colOpciones;
        
    @FXML
    private Button BtnLogOut;
    @FXML
    private Color x4;
    @FXML
    private Font x3;

    private int idPuesto;
    private Perfil softwareSeleccionado;
    private List<Perfil> listSoftwareSeleccionado;
    
    private ObservableList<String> opciones;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        opciones = FXCollections.observableArrayList();
        softwareSeleccionado = new Perfil();
        listSoftwareSeleccionado = new ArrayList<>();
        PerfilDB perfildb = new PerfilDB();
        List<Perfil> listSoftware = perfildb.llenarGrillaxTipo("Manejo de Software");
        for(int i=0; i<listSoftware.size();i++){
            listSoftware.get(i).setSoftwareSeleccionado(0);
            tablaSoftware.getItems().add(listSoftware.get(i));
        }
        colSoftware.setCellValueFactory(new PropertyValueFactory<>("campos"));
        
        opciones.add("Ninguno");
        opciones.add("Basico");
        opciones.add("Intermedio");
        opciones.add("Avanzado");
        
        //colOpciones.setCellValueFactory(new PropertyValueFactory<>("opciones"));
        colOpciones.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), opciones));
        colOpciones.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Perfil, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Perfil, String> event) {
                System.out.println("Value : " + event.getNewValue());
                if(event.getNewValue().equals("Ninguno"))
                   softwareSeleccionado.setSoftwareSeleccionado(0);
                else if(event.getNewValue().equals("Basico"))
                   softwareSeleccionado.setSoftwareSeleccionado(1); 
                else if(event.getNewValue().equals("Intermedio"))
                    softwareSeleccionado.setSoftwareSeleccionado(2);
                else if(event.getNewValue().equals("Avanzado"))
                    softwareSeleccionado.setSoftwareSeleccionado(3);
                agregarLista(softwareSeleccionado);
            }
        });
        tablaSoftware.setEditable(true);
        
        setCellValueFromTableToTextField();
    }  
    
    private void agregarLista(Perfil perfilSeleccionado){
        for(int i=0; i<listSoftwareSeleccionado.size(); i++){
            if(listSoftwareSeleccionado.get(i).getCampos().equals(perfilSeleccionado.getCampos()))
                listSoftwareSeleccionado.remove(listSoftwareSeleccionado.get(i));
        }
        listSoftwareSeleccionado.add(perfilSeleccionado);
    }
    
    public void SetIdPuesto(int id){
        idPuesto = id;
    }
    
    private void setCellValueFromTableToTextField(){
        tablaSoftware.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                Perfil pl = tablaSoftware.getItems().get(tablaSoftware.getSelectionModel().getSelectedIndex());
                PerfilDB perfildb = new PerfilDB();
                softwareSeleccionado = pl;
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
    private void boton_siguiente(ActionEvent event) throws IOException {
        
        
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Puestos/perfil_puesto_competencias.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/Puestos/perfil_puesto_competencias.fxml"));
        Parent root = fxmlLoader.load();     
        Perfil_puesto_competenciasController competenciaMain = fxmlLoader.getController();
        
        PerfilDB perfildb = new PerfilDB();
        perfildb.agregarSoftwareXPuesto(idPuesto, listSoftwareSeleccionado);
        
        competenciaMain.SetIdPuesto(idPuesto);        
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void boton_cancelar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Puestos/puestos_nuevo.fxml"));
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
