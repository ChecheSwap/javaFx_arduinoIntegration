/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbanks.donaciones;

import bloodbanks.Main;
import db.dbop;
import entities.donacion;
import entities.donador;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import utilerias.msg;
import utilerias.onlyNumbers;


public class modelo_donacionid {

    private Controller_donacionid base;
    private dbop db;
    private donacion d;
    public modelo_donacionid(){
        this.base = Main.manager.openFXML("/bloodbanks/donaciones/FXMLdonacionid.fxml","Eliminar Donacion",false, false).getController();
        this.db = new dbop();
       
        this.base.modelo = this;
        
        this.base.txtid.requestFocus();
    }
    
    public void txtid_key(KeyEvent e){
        
        if(e.getCode()==KeyCode.ENTER){ 
            this.eliminar();
        }
    }
    public void eliminar(){
        
        String id = this.base.txtid.getText();
        
        if(onlyNumbers.check(id)){
            
            this.d = new donacion(Integer.valueOf(id));
            
            if(this.db.existDonacion(this.d)){
                
                if(msg.yesno("Confirma Eliminacion?")){
                    if(this.db.deleteDonacion(this.d)){
                        msg.ok("Donacion Eliminada.");
                        this.base.txtid.setText("");                    
                    }
                    else{
                        msg.error("Error al Eliminar Donacion");
                    }
                }
            }
            else{
                msg.danger("No existe Donacion.");
            }
        }
        else{
            msg.danger("Formato de Numero de donacion Invalido.");
            this.base.txtid.setText("");    
        }
        
        this.base.txtid.requestFocus();
    }
}
