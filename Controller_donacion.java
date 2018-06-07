
package bloodbanks.donaciones;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class Controller_donacion implements Initializable {

    
    public modelo_donacion modelo = null;
    @FXML
    private ImageView ivimagen;
    @FXML
    public TextArea txtdetalles;
    @FXML
    public DatePicker dpfecha;
    @FXML
    public TextArea txtcomentarios;
    public ComboBox<String> cbdonador;
    @FXML
    public CheckBox cbclose;
    @FXML
    public Button btncancel;
    @FXML
    public Button btnaceptar;
    @FXML
    public Label lbltitulo;
    @FXML
    public ImageView btnsearch;
    @FXML
    public TextField txtnss;
    @FXML
    public TextField txtid;
    @FXML
    public TextField txtbanco;
    @FXML
    public TextField txtusuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btncancelar(MouseEvent event) {
        this.modelo.btncancelar();
    }

    @FXML
    private void btnaceptar(MouseEvent event) {
        this.modelo.insertarDonacion();
    }

    @FXML
    private void btnsearch_click(MouseEvent event) {
        this.modelo.btnsearch();
    }

    @FXML
    private void btnselectImage_click(MouseEvent event) {
    }
    
}
