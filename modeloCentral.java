
package bloodbanks.central;

import arduino.ardoSerial;
import bloodbanks.Main;
import bloodbanks.donaciones.modelo_donacion;
import bloodbanks.donaciones.modelo_donacionid;
import bloodbanks.donaciones.modelo_searchdonaciones;
import bloodbanks.donadores.altaDonador;
import bloodbanks.donadores.modelo_getDonador;
import bloodbanks.donadores.modelo_searchDonador;
import bloodbanks.donadores.updateDonador;
import bloodbanks.modelo_inicio;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utilerias.msg;

public class modeloCentral {
    
   private Controller_central base = null;
   private Stage mystage = null;
   
   private ardoSerial ardo;
   
   private modelo_inicio model;

   public modeloCentral(modelo_inicio model){
       this.model = model;
       this.initialize();
       
   }   
   
   public modeloCentral(ardoSerial ardo){
       this.ardo = ardo;
       this.initialize();
       
   }
    public modeloCentral(){
        this.initialize();
    }
    
    private void initialize(){
        this.base = Main.manager.openFXML("/bloodbanks/central/FXMLcentral.fxml","Inicio SGBS",true,true).getController();                            
        
        this.mystage = (Stage)this.base.btnminimiza.getScene().getWindow(); 
        
        this.base.mymodel = this;                
                
        this.base.txtusrname.setText(Main.usuario.nombre + " " +Main.usuario.apellido);
        
        this.initvals_paneles();
        
        this.base.btnBancos.requestFocus();
                 
    }
    
    public modeloCentral(Controller_central base){
        this.base = base;                        
    }
        
    public void close(){
        
       if(msg.yesno("¿Desea Salir?")){
            msg.ok("Hasta Luego.");
            Platform.exit();
            System.exit(0);
       }
    }
    
    public void minimiza(){
        this.mystage.setIconified(true);

    }
    
    public void donadores_visible(){
                        
        if(!this.base.paneldonadores.isVisible()){
            this.base.paneldonadores.setVisible(true);
            this.base.panelbancos.setVisible(false);
            this.base.paneldonaciones.setVisible(false);            
        }
        else{
            this.initvals_paneles();
        }                 
    }
    
    
    public void donaciones_visible(){
                        
        if(!this.base.paneldonaciones.isVisible()){
            this.base.paneldonadores.setVisible(false);
            this.base.panelbancos.setVisible(false);            
            this.base.panelbancos.setVisible(false);
            this.base.paneldonaciones.setVisible(true);
        }
        else{
            this.initvals_paneles();
        }         
    }
    
    public void bancos_visible(){
                        
        if(!this.base.panelbancos.isVisible()){
            this.base.paneldonadores.setVisible(false);            
            this.base.panelbancos.setVisible(true);
            this.base.paneldonaciones.setVisible(false);
        }
        else{
            this.initvals_paneles();
        }         
    }
    
    
    private void initvals_paneles(){
        this.base.paneldonaciones.setVisible(true);
        this.base.paneldonadores.setVisible(false);
        this.base.panelbancos.setVisible(false);       
        
    }
    
    public void btnagregardonador(){
        altaDonador alta = new altaDonador();
    }
    
    public void btnupdatedonador(){
        modelo_getDonador g = new modelo_getDonador("UPDATE_DONADOR");
    }
    
    public void btndeletedonador(){
        modelo_getDonador g = new modelo_getDonador("DELETE_DONADOR");
    }
    
    public void btnverdonador(){
        modelo_searchDonador s = new modelo_searchDonador();
    }
    
    public void btninsertdonacion(){
        modelo_donacion d = new modelo_donacion();
    }
    
    public void btneliminardonacion(){
        modelo_donacionid d = new modelo_donacionid();
    }
    
    public void btnsearchdonaciones(){
        modelo_searchdonaciones t = new modelo_searchdonaciones();
    }
    
    public void btnbancoInfo(){
        String print = 
                "      \n\n"+"     -Identificador: "+String.valueOf(Main.banco.id)
                +"     \n"+"     -Nombre: "+Main.banco.nombre
                +"     \n"+"     -Fecha Creacion: "+Main.banco.fecha
                +"     \n"+"     -Calle: "+Main.banco.calle
                +"     \n"+"     -Numero: "+String.valueOf(Main.banco.numero)
                +"     \n"+"     -Colonia: "+Main.banco.colonia
                +"     \n"+"     -Ciudad: "+Main.banco.ciudad
                +"     \n"+"     -Estado: "+Main.banco.estado
                +"     \n"+"     -Pais: "+Main.banco.pais;
                
        msg.ok("*** Información de Banco de Sangre Local *** "+print);
    }
}
