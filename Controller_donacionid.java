
package bloodbanks.donaciones;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class Controller_donacionid implements Initializable {

    
    public modelo_donacionid modelo;
    
    @FXML
    public TextField txtid;
    @FXML
    private Label lbltitulo;
    @FXML
    private Button btnaceptar;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    


    @FXML
    private void btnaceptar(MouseEvent event) {
        this.modelo.eliminar(); 
    }

    @FXML
    private void txtid_keyt(KeyEvent event) {
        this.modelo.txtid_key(event);
    }
    
}
