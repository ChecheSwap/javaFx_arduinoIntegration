
package bloodbanks;

import app.Core;
import arduino.ardoSerial;
import bloodbanks.central.modeloCentral;
import bloodbanks.donaciones.modelo_donacion;
import bloodbanks.donaciones.modelo_donacionid;
import bloodbanks.donaciones.modelo_searchdonaciones;
import bloodbanks.donadores.altaDonador;
import bloodbanks.donadores.modelo_getDonador;
import bloodbanks.donadores.modelo_searchDonador;
import bloodbanks.donadores.updateDonador;
import db.dbop;
import entities.bancosangre;
import entities.donador;
import entities.usuario;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;
import mail.fxmail;
import utilerias.fileCh;
import utilerias.msg;



public class Main extends Application {
    
    public static final Core manager = new Core();    
    public static usuario usuario = new usuario();
    public static  bancosangre banco = new bancosangre();
        
    public static fxmail mail;  
        
                
    @Override
    public void start(Stage stage) throws Exception {
        
        modelo_inicio gendowsBase = new modelo_inicio();
     
        mail = new fxmail();
                                                  
    }    
    
    public static void main(String[] args) {
        launch(args);
        //Platform.setImplicitExit(false);
    }    
    
}

