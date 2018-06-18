/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Area;
import Model.Evaluacion;
import Model.Pregunta;
import Model.Puesto;
import Model.Respuesta;
import Model.TipoEvaluacion;
import Model.database.AreaDB;
import Model.database.EvaluacionDB;
import Model.database.PuestoDB;
import Model.database.TipoEvaluacionDB;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
public class Evaluaciones_nuevoController implements Initializable {

    @FXML
    private Button BtnLogOut;
    @FXML
    private TextField campoNombre;

    @FXML
    private TextArea campoDesc;

    @FXML
    private Button buton_archivo;

    @FXML
    private TextField textBoxArchivo;
    
    @FXML
    private ComboBox<String> comboArea;

    @FXML
    private ComboBox<String> comboPuesto;
    
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    
    private Evaluacion _evaluacion;
    private int idTipo;
    
    private File preguntaFile;
    
    private ObservableList<String> areasObservable;
    private ObservableList<String> puestosObservable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        _evaluacion = new Evaluacion();
        areasObservable = FXCollections.observableArrayList();
        AreaDB areadb = new AreaDB();
        List<Area> listArea = areadb.obtenerArea();
        for(int i=0; i<listArea.size(); i++){
            areasObservable.add(listArea.get(i).getNombre());
        }
        comboArea.getItems().addAll(areasObservable);
    }  
    
    @FXML
    void click_comboArea(ActionEvent event) {
        PuestoDB puestodb = new PuestoDB();
        AreaDB areadb = new AreaDB();
        
        comboPuesto.setDisable(false);
        puestosObservable = FXCollections.observableArrayList();
        Area area = areadb.obtenerAreaxNombre(comboArea.getSelectionModel().getSelectedItem());
        List<Puesto> listPuesto = puestodb.obtenerPuestoxArea(area.getId_area());
        for(int i=0; i<listPuesto.size(); i++){
            puestosObservable.add(listPuesto.get(i).getNombre());
        }
        comboPuesto.getItems().clear();
        comboPuesto.getItems().addAll(puestosObservable);
        comboPuesto.setDisable(false);
    }

    public void setIdTipoEvaluacion(int codTipo){
        idTipo = codTipo;
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
    void subir_archivo(ActionEvent event) {
        //Examinar
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if(file==null) return;
        String name= file.getName();
        String[] extension= name.split("\\.");
        if((file!= null) && extension[1].equals("csv")){
            textBoxArchivo.setText(file.getName());
            preguntaFile = file;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setHeaderText(null);
            alert.setContentText("Formato no soportado (solo se aceptan archivos .csv)");
            alert.showAndWait();
            preguntaFile = null;
            return;
        }     
    }
    
    private void cargarEvaluacion(int id_evaluacion) throws FileNotFoundException, IOException{
        if(preguntaFile == null) return;
        FileReader fr= new FileReader(preguntaFile);
        BufferedReader br = new BufferedReader(fr);
        String line;
        //line=br.readLine();
        EvaluacionDB evaluaciondb = new EvaluacionDB();      
        List<Pregunta> listPreguntas = new ArrayList<>();
        List<Respuesta> listRespuestas = new ArrayList<>();
        while((line = br.readLine())!=null){
            Pregunta pregunta = new Pregunta();
            String[] parts = line.split(";");
            try{
                pregunta.setTexto(parts[0]);
                pregunta.setRespuesta_correcta(parts[parts.length-1]);
                for(int i=1; i<parts.length-1; i++){
                    Respuesta respuesta = new Respuesta();
                    respuesta.setTexto(parts[i]);
                    pregunta.agregarRespuesta(respuesta);
                }
                listPreguntas.add(pregunta);
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta");
                alert.setHeaderText(null);
                alert.setContentText("Error en el formato de los datos");
                alert.showAndWait();            
                return;
            }
        }
        for(int i=0; i<listPreguntas.size(); i++){
            evaluaciondb.crearPregunta(id_evaluacion, listPreguntas.get(i));
            listRespuestas = listPreguntas.get(i).getListaRespuesta();
            for(int j=0; j<listRespuestas.size(); j++){
                evaluaciondb.crearRespuesta(listPreguntas.get(i).getId_pregunta(), listRespuestas.get(j));
            }
        }
    }
    
    @FXML
    private void boton_guardar(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Evaluaciones/evaluacion_main.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/Evaluaciones/evaluacion_main.fxml"));
        Parent root = fxmlLoader.load();  
        Evaluacion_mainController evaluacionMain = fxmlLoader.getController();
        
        PuestoDB puestodb = new PuestoDB();
        Puesto puesto = new Puesto();
        TipoEvaluacionDB tipoEvaluaciondb = new TipoEvaluacionDB();
        TipoEvaluacion tipoEvaluacion = new TipoEvaluacion();
        tipoEvaluacion = tipoEvaluaciondb.obtenerTipoEvaluacionxId(idTipo);
        
        EvaluacionDB evaluaciondb = new EvaluacionDB();
        _evaluacion.setNombre(campoNombre.getText());
        _evaluacion.setDescripcion(campoDesc.getText());
        _evaluacion.setId_tipoEvaluacion(idTipo);
        _evaluacion.setTipoEvaluacion(tipoEvaluacion.getNombre());
        evaluaciondb.crearEvaluacion(_evaluacion);
        puesto = puestodb.obtenerPuestoxNombre(comboPuesto.getSelectionModel().getSelectedItem());
        evaluaciondb.crearEvaluacionxPuesto(puesto.getId_puesto(),_evaluacion.getId_evaluacion());
        cargarEvaluacion(_evaluacion.getId_evaluacion());
        
        evaluacionMain.afterInitialize(tipoEvaluacion);
        
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void boton_cancelar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Evaluaciones/evaluacion_main.fxml"));
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
