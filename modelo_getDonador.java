
package bloodbanks.donadores;

import bloodbanks.Main;
import db.dbop;
import entities.donador;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import utilerias.msg;

public class modelo_getDonador {
    
    private Controller_getDonador base = null;
    private dbop mydb = null;
    private donador d= null;
    String tipo = null;
    private Stage mystage = null;
    
    public modelo_getDonador(String tipo){
        
        this.base= Main.manager.openFXML("/bloodbanks/donadores/FXMLgetDonador.fxml","Numero de seguridad social", false, false).getController();
        this.base.txtnss.requestFocus();        
        this.mydb = new dbop();
        this.tipo = tipo;
        
        this.mystage = (Stage)this.base.txtnss.getScene().getWindow();        
        
        switch(this.tipo){
            case("DELETE_DONADOR"):{
                this.mystage.setTitle("Eliminar Donador");
                break;
            }
            case("UPDATE_DONADOR"):{
                this.mystage.setTitle("Actualizar donador");
                break;
            }
        }
        
        this.base.modelo = this;
    }
    
    public void decide(){
        
        this.d = new donador(this.base.txtnss.getText().trim());
        
        
        switch(this.tipo){
            case("DELETE_DONADOR"):{                                     
                                                
                if(this.existDonador()){
                    if(!this.hasDonations()){
                        
                        if(msg.yesno("Confirma Eliminar Donador?")){
                            if(this.mydb.deleteDonador(this.d)){
                                msg.ok("Donador Eliminado");
                                this.txtfocus();
                            }
                            else{
                                msg.error("Ha ocurrido un error en el proceso"); 
                                this.txtfocus();
                            }
                        }
                    }
                    else{
                        msg.danger("Este donador tiene donaciones. \n>>Imposible eliminar.");
                        this.txtfocus();
                    }
                }
                else{                    
                    this.txtfocus();
                }
                
                break;
            }
            
            case("UPDATE_DONADOR"):{
                                
                if(this.existDonador()){
                    updateDonador u = new updateDonador(this.mydb.getDonador(this.d.getNss()));
                    this.close();
                }
                
                break;
            }
        }
    }        
    
    private boolean existDonador(){        
        
        if(this.mydb.existeDonador(this.d)){
            return true;
        }
        else{
            msg.error("No existe este numero de seguridad Social!");
            this.base.txtnss.setText("");
            this.base.txtnss.requestFocus();
        }        
        return false;
    }
    
    private boolean hasDonations(){                
        
        if(this.mydb.hasDonaciones(this.d.getNss())){
            return true;
        }        
        return false;
    }
    
    private void close(){
        
        this.mystage.close();        
    }
    
    private void txtfocus(){
        this.base.txtnss.setText("");
        this.base.txtnss.requestFocus();
    }
    
    public void onlyEnter(KeyEvent e){
        if(!(e.getCode() == KeyCode.ENTER)){
            e.consume();
        }
        else{
            this.decide();
        }
    }
        
    
}
