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
import Model.Usuario;
import Model.database.EvaluacionDB;
import Model.database.ProcesoSeleccionDB;
import Model.database.PuestoDB;
import Model.database.UsuarioDB;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class Proceso_Seleccion_postulanteController implements Initializable {

    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private Button BtnLogOut;
    
    @FXML
    private TableView<Usuario> tablaPostulantes;

    @FXML
    private TableColumn<Usuario, Integer> colId;

    @FXML
    private TableColumn<Usuario, String> colApellido;

    @FXML
    private TableColumn<Usuario, String> colNombre;
    
    @FXML
    private TableColumn<Usuario, Double> colPuntaje;
    
    @FXML
    private Color x2;
    @FXML
    private Font x1;
    
    @FXML
    private Label tituloPuesto;
    @FXML
    private TextField textBoxDni;

    @FXML
    private TextField textBoxFecha;

    @FXML
    private TextField textBoxNombre;

    @FXML
    private TextField textBoxApPaterno;

    @FXML
    private TextField textBoxApMaterno;
    
    @FXML
    private Button botonPerfilEscoger;
     
    @FXML
    private Button saveReturnBtn;
    

    private int id_proceso;
    private int id_puesto;
    private int id_postulante;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        UsuarioDB usuariodb = new UsuarioDB();
//        List<Usuario> listUsuario = usuariodb.obtenerUsuario();
        ProcesoSeleccionDB procesodb = new ProcesoSeleccionDB();
        List<Usuario> listUsuario = procesodb.obtenerPostulantes(id_proceso);
        
        for(int i=0; i<listUsuario.size(); i++){
            if(listUsuario.get(i).getTipo_Usuario() == 1)
                tablaPostulantes.getItems().add(listUsuario.get(i));
        }
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id_usuario"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellidoPa"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPuntaje.setCellValueFactory(new PropertyValueFactory<>("puntaje"));
        
        setCellValueFromTableToTextField();
    }

    private void setCellValueFromTableToTextField(){
        tablaPostulantes.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                Usuario pl = tablaPostulantes.getItems().get(tablaPostulantes.getSelectionModel().getSelectedIndex());
                UsuarioDB usuariodb = new UsuarioDB();
                pl = usuariodb.obtenerUsuarioxId(pl.getId_usuario());
                textBoxNombre.setText(String.valueOf(pl.getNombre()));
                textBoxApPaterno.setText(String.valueOf(pl.getApellidoPa()));
                textBoxApMaterno.setText(String.valueOf(pl.getApellidoMa()));
                textBoxDni.setText(String.valueOf(pl.getDni()));
                textBoxFecha.setText(String.valueOf(pl.getFechaNac()));
                botonPerfilEscoger.setDisable(false);
            }
        });
        
    }
    
    public void afterRegister(int codProceso){
        ProcesoSeleccionDB procesodb = new ProcesoSeleccionDB();
        ProcesoSeleccion proceso = new ProcesoSeleccion();
        proceso = procesodb.obtenerProcesoxId(codProceso);
        tituloPuesto.setText(proceso.getPuesto());
        id_puesto = proceso.getId_puesto();
        id_proceso = proceso.getId_proceso();
        List<Usuario> listUsuario = procesodb.obtenerPostulantes(id_proceso);
        
        for(int i=0; i<listUsuario.size(); i++){
            if(listUsuario.get(i).getTipo_Usuario() == 1)
                tablaPostulantes.getItems().add(listUsuario.get(i));
        }
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id_usuario"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellidoPa"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        setCellValueFromTableToTextField();
    }

    public void afterInitialize(ProcesoSeleccion proceso){
        tituloPuesto.setText(proceso.getPuesto());
        id_puesto = proceso.getId_puesto();
        id_proceso = proceso.getId_proceso();
        ProcesoSeleccionDB procesodb = new ProcesoSeleccionDB();
        List<Usuario> listUsuario = procesodb.obtenerPostulantes(id_proceso);
        
        for(int i=0; i<listUsuario.size(); i++){
            if(listUsuario.get(i).getTipo_Usuario() == 1)
                tablaPostulantes.getItems().add(listUsuario.get(i));
        }
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id_usuario"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellidoPa"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        setCellValueFromTableToTextField();
    }
    
//    public void AfterSelect(int id_Proceso){
//        
//        for(int i=0; i<listUsuario.size(); i++){
//            if(listUsuario.get(i).getSeleccionProceso() == 1)
//                tablaPostulantes.getItems().add(listUsuario.get(i));
//        }
//        
//        colId.setCellValueFactory(new PropertyValueFactory<>("id_usuario"));
//        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellidoPa"));
//        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
//        
//        setCellValueFromTableToTextField();
//    }
    
    @FXML
    private void boton_escoger_perfiles(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seleccion/postulante_perfil.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/Seleccion/postulante_perfil.fxml"));
        Parent root = fxmlLoader.load(); 
        Postulante_perfilController postulantePerfil = fxmlLoader.getController();
        
        
        Usuario postulante = tablaPostulantes.getSelectionModel().getSelectedItem();
        postulantePerfil.setIdPostulante(postulante.getId_usuario());
        postulantePerfil.setIdPuesto(id_puesto);
        postulantePerfil.setIdProceso(id_proceso);
        
        Scene scene = new Scene(root);     
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void handleSaveReturnBtn(ActionEvent event) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Guardar reporte");
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF(*.pdf)", "*.pdf"));
//        
//        Stage stage = (Stage) saveReturnBtn.getScene().getWindow();
//        File file = fileChooser.showSaveDialog(stage);
//        
//        if (file != null) {
//            if(!file.getName().contains("."))
//                file = new File(file.getAbsolutePath() + ".pdf");
//            
//        }

        ProcesoSeleccionDB procesoSelecciondb = new ProcesoSeleccionDB();
        ProcesoSeleccion proceso = new ProcesoSeleccion(); 
        EvaluacionDB evaluaciondb = new EvaluacionDB();
        Evaluacion evaluacionPsico = new Evaluacion();
        Evaluacion evaluacionCono = new Evaluacion();
        List<Pregunta> listPreguntaPsico = new ArrayList<>();
        List<Pregunta> listPreguntaCono = new ArrayList<>();
        List<Pregunta> ListPreguntaUsuarioPsico = new ArrayList<>();
        List<Pregunta> ListPreguntaUsuarioCono = new ArrayList<>();
        
        
        Usuario postulante = tablaPostulantes.getSelectionModel().getSelectedItem();
        proceso = procesoSelecciondb.obtenerProcesoxPostulante(postulante.getId_usuario());
        evaluacionPsico = evaluaciondb.obtenerEvaluacionxPuesto(proceso.getId_puesto(),0);
        listPreguntaPsico = evaluaciondb.obtenerPreguntasxEvaluacion(evaluacionPsico.getId_evaluacion());
        evaluacionCono = evaluaciondb.obtenerEvaluacionxPuesto(proceso.getId_puesto(),1);
        listPreguntaCono = evaluaciondb.obtenerPreguntasxEvaluacion(evaluacionCono.getId_evaluacion());
        ListPreguntaUsuarioPsico = evaluaciondb.obtenerRespuesta(postulante.getId_usuario(), evaluacionPsico.getId_evaluacion());
        ListPreguntaUsuarioCono = evaluaciondb.obtenerRespuesta(postulante.getId_usuario(), evaluacionCono.getId_evaluacion());
        
        
        List<Respuesta> listRespuestaPsico = new ArrayList<>();
        List<Respuesta> listRespuestaCono = new ArrayList<>();
        
        
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                  new FileOutputStream("Resultados de "+postulante.getApellidoPa()+" "+postulante.getApellidoMa()+".txt"), "utf-8"));
            
            writer.write("Test Psicologico"+System.getProperty( "line.separator" ));
            for(int i=0; i<listPreguntaPsico.size(); i++){
                writer.write("Pregunta ("+i+ System.getProperty( "line.separator" ));
                listRespuestaPsico = listPreguntaPsico.get(i).getListaRespuesta();
                for(int j=0; j<listRespuestaPsico.size(); j++){
                    if(listRespuestaPsico.get(j).getTexto().equals(listPreguntaPsico.get(i).getRespuesta_correcta()))
                        writer.write("     Respuesta ("+j+"   Puntaje = "+1 +System.getProperty( "line.separator" ));
                    else
                        writer.write("     Respuesta ("+j+"   Puntaje = "+0 +System.getProperty( "line.separator" ));
                }
                writer.write("Resultado = "+ ListPreguntaUsuarioPsico.get(i).getPuntaje() +System.getProperty( "line.separator" ));
            }
            writer.write(System.getProperty( "line.separator" ));
            writer.write("Test Conocimiento"+System.getProperty( "line.separator" ));
            for(int i=0; i<listPreguntaCono.size(); i++){
                writer.write("Pregunta ("+i+ System.getProperty( "line.separator" ));
                listRespuestaCono = listPreguntaCono.get(i).getListaRespuesta();
                for(int j=0; j<listRespuestaCono.size(); j++){
                    if(listRespuestaCono.get(j).getTexto().equals(listPreguntaCono.get(i).getRespuesta_correcta()))
                        writer.write("     Respuesta ("+j+"   Puntaje = "+1 +System.getProperty( "line.separator" ));
                    else
                        writer.write("     Respuesta ("+j+"   Puntaje = "+0 +System.getProperty( "line.separator" ));
                }
                writer.write("Resultado = "+ ListPreguntaUsuarioCono.get(i).getPuntaje() +System.getProperty( "line.separator" ));
            }
            writer.close();
            
        } catch (IOException ex) {
            // Report
        } finally {
           try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registro de Resultados");
        alert.setHeaderText("Resultados impresos con éxito");
        alert.setContentText("Los resultados de  "+ postulante.getApellidoPa()+" ha sido registrado con éxito.");
        alert.showAndWait();
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
    private void boton_agregar_postulante(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seleccion/postulante_agregar.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/Seleccion/postulante_agregar.fxml"));
        Parent root = fxmlLoader.load();  
        Postulante_agregarController postulantesMain = fxmlLoader.getController();
        
        postulantesMain.SetProceso(id_proceso);
        
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
