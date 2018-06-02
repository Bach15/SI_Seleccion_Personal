/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Perfil;
import Model.ProcesoSeleccion;
import Model.Usuario;
import Model.database.ProcesoSeleccionDB;
import Model.database.UsuarioDB;
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
public class Postulante_agregarController implements Initializable {

    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private Button BtnLogOut;
    
    @FXML
    private TableColumn<Usuario, Integer> colId;

    @FXML
    private TableView<Usuario> tablaPostulante;
    
    @FXML
    private TableColumn<Usuario, String> colApPaterno;

    @FXML
    private TableColumn<Usuario, String> colApMaterno;

    @FXML
    private TableColumn<Usuario, String> colNombre;

    @FXML
    private TableColumn<Usuario, String> colOpcion;
    
    private ObservableList<String> opciones;
    
    private int id_Proceso;
    private Usuario postulanteSeleccionado;
    private List<Usuario> listUsuarioSeleccionado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        postulanteSeleccionado = new Usuario();
        listUsuarioSeleccionado = new ArrayList<>();
        UsuarioDB usuariodb = new UsuarioDB();
        List<Usuario> listUsuario = usuariodb.obtenerUsuario();
        for(int i=0; i<listUsuario.size(); i++){
            if(listUsuario.get(i).getTipo_Usuario() == 1){
                listUsuario.get(i).setSeleccionProceso(0);
                tablaPostulante.getItems().add(listUsuario.get(i));
            }    
        }
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id_usuario"));
        colApPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPa"));
        colApMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMa"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        opciones = FXCollections.observableArrayList();
        opciones.add("Ninguna");
        opciones.add("Seleccion");
        
        colOpcion.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), opciones));
        colOpcion.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Usuario, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Usuario, String> event) {
                System.out.println("Value : " + event.getNewValue());
                if(event.getNewValue().equals("Ninguna"))
                    postulanteSeleccionado.setSeleccionProceso(0);
                else 
                    postulanteSeleccionado.setSeleccionProceso(1);
                agregarLista(postulanteSeleccionado);
            }
        });
        tablaPostulante.setEditable(true);
        
        setCellValueFromTableToTextField();
    }
    
    private void agregarLista(Usuario usuarioSeleccionado){
        int repetido = 0;
        for(int i=0; i<listUsuarioSeleccionado.size(); i++){
            if(listUsuarioSeleccionado.get(i).getDni().equals(usuarioSeleccionado.getDni()))
                repetido = 1;
        }
        if(usuarioSeleccionado.getSeleccionProceso() == 1){
            if(repetido == 0)
                listUsuarioSeleccionado.add(usuarioSeleccionado);
        }
        else{
            if(repetido == 1)
                listUsuarioSeleccionado.remove(usuarioSeleccionado);
        }
    }
    
    private void setCellValueFromTableToTextField(){
        tablaPostulante.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                Usuario pl = tablaPostulante.getItems().get(tablaPostulante.getSelectionModel().getSelectedIndex());
                UsuarioDB usuariodb = new UsuarioDB();
                pl = usuariodb.obtenerUsuarioxId(pl.getId_usuario());
                postulanteSeleccionado = pl;
            }
            
        });
    }

    public void SetProceso(int idProceso){
        id_Proceso = idProceso;
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
    private void boton_cancelar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seleccion/proceso_Seleccion_postulante.fxml"));
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
        
        ProcesoSeleccionDB procesoDB = new ProcesoSeleccionDB();
        procesoDB.agregarPostulantes(id_Proceso, listUsuarioSeleccionado);
        ProcesoSeleccion proceso = procesoDB.obtenerProcesoxId(id_Proceso);
        procesoMain.afterInitialize(proceso);
        
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
