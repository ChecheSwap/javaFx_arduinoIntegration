
package bloodbanks.donadores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class Controller_getDonador implements Initializable {
    
    public modelo_getDonador modelo;
    @FXML
    private Button btnaceptar;
    @FXML
    private Label lbltitulo;
    @FXML
    public TextField txtnss;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnaceptar(MouseEvent event) {
       this.modelo.decide();
    }

    @FXML
    private void txtnss_keypressed(KeyEvent event) {
        this.modelo.onlyEnter(event);
    }
    
}
