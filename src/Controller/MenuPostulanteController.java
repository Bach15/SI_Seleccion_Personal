/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class MenuPostulanteController implements Initializable {

    @FXML
    private Button BtnLogOut;

    private int id_usuario;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setId_Usuario(int codUsuario){
        id_usuario = codUsuario;
    }
    
    @FXML
    private void boton_Usuario(MouseEvent event) {
    }

    @FXML
    private void boton_evaluacion(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Test/test_conocimientos.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/Test/test_psicologico.fxml"));
        Parent root = fxmlLoader.load(); 
        Test_psicologicoController testPsicologico = fxmlLoader.getController();
        
        testPsicologico.afterInitialize(id_usuario);
        
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
    }


    @FXML
    private void boton_salida(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Seguridad/Login.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
    }
    
}
