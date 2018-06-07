
package bloodbanks.donadores;

import entities.donador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Controller_searchDonador implements Initializable {
    
    public modelo_searchDonador modelo = null;
    @FXML
    public TableView<donador> tabla;
    @FXML
    public TextField txtnombre;
    @FXML
    public DatePicker dpnacimiento;
    @FXML
    public TextField txtnss;
    @FXML
    public Button btnfecha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void txtnombre_keyp(KeyEvent event) {
        this.modelo.searchnombre();
    }

    @FXML
    private void txtnss_keyp(KeyEvent event) {
        this.modelo.searchnss();
    }

    @FXML
    private void btnfecha_click(MouseEvent event) {
        this.modelo.searchfecha();
    }
    
}
