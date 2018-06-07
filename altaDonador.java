
package bloodbanks.donadores;

import bloodbanks.Main;
import db.dbop;
import entities.donador;
import utilerias.msg;


public class altaDonador{
    
    private Controller_donador base = null;
    private modelo_Donador modelo= null;
    private donador d;
    private dbop mydb = null;
    
    public altaDonador(){
        this.mydb = new dbop();
        this.base = Main.manager.openFXML("/bloodbanks/donadores/FXMLdonador.fxml", "Alta Donador", false, false).getController();
        this.base.lbltitulo.setText(">>Registrar Donador");
        
        this.modelo = new modelo_Donador(this.base);               
        this.base.type = "INSERT";

        this.base.alta = this;
    }
    
    public void alta(){
        
        this.d = new donador();
        
        d.setNss(this.base.txtseguridadsocial.getText().trim());
        
        if(!"".equals(this.d.getNss())){
            
            if(!this.mydb.existeDonador(this.d)){
                
                this.modelo.fillDonador(this.d);
                
                if(this.mydb.altaDonador(d)){
                    msg.ok("El Donador con NSS:"+d.getNss()+" Se ha dado de alta.");
                    
                    if(this.base.cbclose.isSelected()){
                        this.modelo.close();
                    }
                    
                    this.modelo.clearAll();                   
                }
                else{
                    msg.error("Problema al registrar donador.");
                }
                
            }
            else{
                msg.danger("Ya existe este Numero de Seguridad Social!");                
            }
        }
        else{
            msg.danger("Ingrese Numero de Seguridad Social!");
                                 
        } 
        
         this.base.txtseguridadsocial.requestFocus();  
    }
    

    
    
    
    
}
