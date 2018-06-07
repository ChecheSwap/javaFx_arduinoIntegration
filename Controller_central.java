
package bloodbanks.central;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utilerias.msg;


public class Controller_central implements Initializable {

    public  modeloCentral mymodel = null;
        
    @FXML
    public ImageView btnminimiza;
    @FXML
    public ImageView btnclose;
    @FXML
    public Pane btnDonaciones;
    @FXML
    public Pane btnDonadores;
    @FXML
    public Pane btnBancos;
    @FXML
    public Pane paneldonadores;
    @FXML
    public Pane paneldonaciones;
    @FXML
    public ImageView btnclose1121;
    @FXML
    public Pane panelbancos;
    @FXML
    public ImageView btneliminardonacion;
    public ImageView btnupdatedonacion;
    @FXML
    public ImageView btnadddonacion;
    @FXML
    public ImageView btneliminardonador;
    
    @FXML
    public ImageView btnverdonador;
    @FXML
    public ImageView btnadddonador;

    @FXML
    public ImageView btnverbanco;

    @FXML
    private ImageView btnactualizardonador;
    @FXML
    public TextField txtusrname;

    
    public Controller_central(){          
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
            
    }    

    @FXML
    private void btnminimiza_click(MouseEvent event) {                                            
        this.mymodel.minimiza();
    }

    @FXML
    private void btnclose_click(MouseEvent event) {                         
        this.mymodel.close();
    }

    @FXML
    private void btndonaciones_pressed(MouseEvent event) {
        this.mymodel.donaciones_visible();  
    }

    @FXML
    private void btndonadores_pressed(MouseEvent event) {
        this.mymodel.donadores_visible();
    }

    @FXML
    private void btnbancos_pressed(MouseEvent event) {
        this.mymodel.bancos_visible();
    }

    @FXML
    private void btnadddonacion_click(MouseEvent event) {
        this.mymodel.btninsertdonacion();
    }

    @FXML
    private void btneliminardonador_click(MouseEvent event) {
        this.mymodel.btndeletedonador();
    }

    @FXML
    private void btnactualizardonador_click(MouseEvent event) {
        this.mymodel.btnupdatedonador();
    }


    @FXML
    private void btnadddonador_click(MouseEvent event) {
        this.mymodel.btnagregardonador();
    }

    @FXML
    private void btnverdonador_click(MouseEvent event) {
        this.mymodel.btnverdonador();
    }

    @FXML
    private void btneliminardonacion_click(MouseEvent event) {
        this.mymodel.btneliminardonacion();
    }

    @FXML
    private void btnverdonacion_click(MouseEvent event) {
        this.mymodel.btnsearchdonaciones();
    }

    @FXML
    private void btnbanco_click(MouseEvent event) {
        this.mymodel.btnbancoInfo();
    }

    
}
