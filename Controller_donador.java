
package bloodbanks.donadores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utilerias.msg;




public class Controller_donador implements Initializable {
    
    public String type = "UNKNOW";
    
    public altaDonador alta = null;
    
    public updateDonador update = null;
    
    public modelo_Donador mymodel = null;
    
    public Image nullImage = new Image("/images/donador.png");
            
    
    public ImageView btnminimiza;    
    public ImageView btnclose;
    @FXML
    public TextField txtnombre;
    @FXML
    public TextField txtcontacto;
    @FXML
    public TextField txtapellido;
    @FXML
    public TextField txtseguridadsocial;
    @FXML
    public ComboBox<String> cbgenero;
    @FXML
    public TextField txtnumero;
    @FXML
    public DatePicker dpnacimiento;
    @FXML
    public TextArea rtbdetalles;
    @FXML
    public TextField txtcalle;
    @FXML
    public TextField txtcolonia;
    @FXML
    public TextField txtciudad;
    @FXML
    public ComboBox<String> cbestado;
    @FXML
    public Button btnaceptar;
    @FXML
    public Button btncancel;
    @FXML
    public Label lbltitulo;
    @FXML
    public ImageView ivimagen;
    @FXML
    public Button btnimage;
    
    public ObservableList<String> generos;
    public ObservableList<String> edos;
    @FXML
    private Button btnquitar;
    @FXML
    public AnchorPane basePane;
    
    public Stage mystage = null;            
    @FXML
    public CheckBox cbclose;
    
    public Controller_donador(){                                
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
    }    
  
    @FXML
    private void txtnumero_KeyPressed(KeyEvent event) {                
    }

    @FXML
    private void btnaceptar(MouseEvent event) {
        
        switch(this.type){
            case("INSERT"):{
                this.alta.alta();
                break;
            }
            case("UPDATE"):{
                this.update.update();
                break;
            }
        }
    }

    @FXML
    private void btncancelar(MouseEvent event) {
        switch(this.type){
            case("INSERT"):{
                this.mymodel.clearAll();
                break;
            }
            case("UPDATE"):{                
                this.mymodel.fillControls(this.update.d);
                break;
            }
        }
        
    }

    @FXML
    private void btnquitar_click(MouseEvent event) {        
        this.mymodel.defaultImage();        
    }


    @FXML
    private void btnselectImage_click(MouseEvent event) {
        this.mymodel.selectImage();  
    }

}



