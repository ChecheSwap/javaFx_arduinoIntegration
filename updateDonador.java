
package bloodbanks.donadores;

import bloodbanks.Main;
import db.dbop;
import entities.donador;
import utilerias.msg;

public class updateDonador {
    
    private Controller_donador base = null;
    private modelo_Donador modelo = null;
    private dbop mydb = null;
    
    public donador d = null;
    
    
    public updateDonador(donador d){
        this.base = Main.manager.openFXML("/bloodbanks/donadores/FXMLdonador.fxml","Actualizar Donador",false,false).getController();
        this.modelo = new modelo_Donador(this.base);
        
        this.mydb = new dbop();
        
        this.base.lbltitulo.setText(">>Actualizar Donador");
        this.base.type = "UPDATE";          
        
        this.base.txtseguridadsocial.setEditable(false);
        
        this.d = d;
        this.modelo.fillControls(d);
        
        this.base.update = this;
    }
    
    public void update(){
        
        if(msg.yesno("Desea Actualizar al Donador con NSS: "+this.d.getNss()+" ?")){
            donador dnuevo = new donador();        
            this.modelo.fillDonador(dnuevo);
            
            if(this.mydb.updateDonador(dnuevo)){
                msg.ok("Actualizacion Exitosa");
                
                if(this.base.cbclose.isSelected()){
                    this.modelo.close();
                }
            }
            else{
                msg.error("Problema de Actualizacion");                
            }
        }
        
        this.base.txtseguridadsocial.requestFocus();
    }
    

    
}
