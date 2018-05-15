/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import Model.database.UsuarioDB;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class LoginController implements Initializable {

    @FXML
    private TextField campo_Usuario;
    
    @FXML
    private PasswordField campo_contrasena;
    
    @FXML
    private Button boton_Entrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        UsuarioDB usuariodb = new UsuarioDB();
        List<Usuario> listUsuario = usuariodb.obtenerUsuario();
        for(int i=0; i<listUsuario.size(); i++){
            if((listUsuario.get(i).getTipo_Usuario() == 0) && (campo_Usuario.getText().equals(listUsuario.get(i).getNombre_usuario()))
                    && campo_contrasena.getText().equals(listUsuario.get(i).getContrasena())){
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Menu/Menu_Administrador.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();  
            }
        }
        
        
        if(campo_Usuario.getText().equals("Administrador")){
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Menu/Menu_Administrador.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();  
        }
        if(campo_Usuario.getText().equals("Postulante")){
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Menu/MenuPostulante.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
        }
          
    }
    
}
