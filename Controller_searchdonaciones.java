
package bloodbanks.donaciones;

import entities.donacion;
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
import utilerias.txtOnlyNumbers;


public class Controller_searchdonaciones implements Initializable {
    
    public modelo_searchdonaciones modelo;
    @FXML
    public TableView<donacion> tabla;
    public TextField txtid;
    @FXML
    public DatePicker dpfecha;
    @FXML
    public TextField txtnss;
    @FXML
    public TextField txtdonacion;
    @FXML
    private Button btnfecha;
    @FXML
    private Button btnupda;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void txtnss_keyp(KeyEvent event) {
        this.modelo.fillTabla(3);
    }

    @FXML
    private void txtdonacion_keyp(KeyEvent event) {
        txtOnlyNumbers.check(event);
        this.modelo.fillTabla(4);
    }

    @FXML
    private void btnbuscar_click(MouseEvent event) {
        this.modelo.fillTabla(2);
    }

    @FXML
    private void btnupdate_click(MouseEvent event) {
        this.modelo.fillTabla(1);
    }
    
}
