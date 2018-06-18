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
import javafx.scene.control.ComboBox;
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
public class Postulante_perfil_competenciaController implements Initializable {

    @FXML
    private Button BtnLogOut;
    
    @FXML
    private TableView<Perfil> tablaCompetencias;

    @FXML
    private TableColumn<Perfil, String> colCompetencia;

    @FXML
    private TableColumn<Perfil, String> colOpciones;
    
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    
    private int id_postulante;
    private int id_puesto;
    private int id_proceso;
    
    private ObservableList<String> opciones;
    private Perfil competenciaSeleccionado;
    private List<Perfil> listCompetenciaSeleccionado;
    private List<Perfil> listCompetencias;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
    }  
    
    private void agregarLista(Perfil perfilSeleccionado){
        for(int i=0; i<listCompetencias.size();i++){
            if(listCompetencias.get(i).getCampos().equals(perfilSeleccionado.getCampos())){
                int a = perfilSeleccionado.getPostulanteSelectCompetencia();
                int b = listCompetencias.get(i).getNivel();
                if(perfilSeleccionado.getPostulanteSelectCompetencia() > listCompetencias.get(i).getNivel()){
                    perfilSeleccionado.setPostulanteSelectCompetencia(listCompetencias.get(i).getNivel());
                    break;
                }
            }
        }
        for(int i=0; i<listCompetenciaSeleccionado.size(); i++){
            if(listCompetenciaSeleccionado.get(i).getCampos().equals(perfilSeleccionado.getCampos()))
                listCompetenciaSeleccionado.remove(listCompetenciaSeleccionado.get(i));
        }
        listCompetenciaSeleccionado.add(perfilSeleccionado);
    }
    
    private void setCellValueFromTableToTextField(){
        tablaCompetencias.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                Perfil pl = tablaCompetencias.getItems().get(tablaCompetencias.getSelectionModel().getSelectedIndex());
                PerfilDB perfildb = new PerfilDB();
                competenciaSeleccionado = pl;
            }        
        });
    }
        
    public void afterInitialize(int codPostulante, int codPuesto, int codProceso){
        id_postulante = codPostulante;
        id_puesto = codPuesto;
        id_proceso = codProceso;
        
        opciones = FXCollections.observableArrayList();
        competenciaSeleccionado = new Perfil();
        listCompetenciaSeleccionado = new ArrayList<>();
                
        PerfilDB perfildb = new PerfilDB();
        listCompetencias = perfildb.llenarGrillaxPuesto("Competencias",id_puesto);
        for(int i=0; i<listCompetencias.size();i++){
            listCompetencias.get(i).setPostulanteSelectCompetencia(0);
            tablaCompetencias.getItems().add(listCompetencias.get(i));
        }
        colCompetencia.setCellValueFactory(new PropertyValueFactory<>("campos"));
        
        opciones.add("Ninguno");
        opciones.add("Bajo");
        opciones.add("Medio Bajo");
        opciones.add("Medio Alto");
        opciones.add("Alto");
        
        colOpciones.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), opciones));
        colOpciones.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Perfil, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Perfil, String> event) {
                System.out.println("Value : " + event.getNewValue());
                if(event.getNewValue().equals("Ninguno"))
                    competenciaSeleccionado.setPostulanteSelectCompetencia(0);
                else if(event.getNewValue().equals("Bajo"))
                    competenciaSeleccionado.setPostulanteSelectCompetencia(1);
                else if(event.getNewValue().equals("Medio Bajo"))
                    competenciaSeleccionado.setPostulanteSelectCompetencia(2);
                else if(event.getNewValue().equals("Medio Alto"))
                    competenciaSeleccionado.setPostulanteSelectCompetencia(3);
                else if(event.getNewValue().equals("Alto"))   
                    competenciaSeleccionado.setPostulanteSelectCompetencia(4);
                agregarLista(competenciaSeleccionado);
            }
        });
        tablaCompetencias.setEditable(true);
        
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
    private void boton_guardar(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seleccion/proceso_Seleccion_postulante.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/Seleccion/proceso_Seleccion_postulante.fxml"));
        Parent root = fxmlLoader.load();  
        
        Proceso_Seleccion_postulanteController procesoMain = fxmlLoader.getController();        
        
        procesoMain.afterRegister(id_proceso);
        
        PerfilDB perfildb = new PerfilDB();
        perfildb.agregarCompetenciaXUsuario(id_postulante, listCompetenciaSeleccionado);
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
