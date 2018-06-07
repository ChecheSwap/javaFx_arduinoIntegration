
package bloodbanks.donaciones;

import bloodbanks.Main;
import bloodbanks.donadores.modelo_searchDonador;
import db.dbop;
import entities.donacion;
import entities.donador;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilerias.msg;
import utilerias.mydate;


public class modelo_donacion {
    private Controller_donacion base = null;
    private donacion d;
    private dbop db;
    private Stage stage;
    public modelo_donacion(){
        this.base = Main.manager.openFXML("/bloodbanks/donaciones/FXMLdonacion.fxml","Agregar Donacion", false, false).getController();
        this.db = new dbop();
        this.stage = (Stage)this.base.btnaceptar.getScene().getWindow();
        this.initData();
        
        
        this.base.modelo = this;
    }
    
    private void initData(){
        this.base.txtid.setText(String.valueOf(this.db.getCurrentDonacion()));
        this.base.txtbanco.setText(String.valueOf(Main.banco.id));
        this.base.txtusuario.setText(String.valueOf(Main.usuario.id));
        this.base.dpfecha.setValue(LocalDate.now());
        this.base.txtnss.setText("");
        this.base.txtcomentarios.setText("");
        this.base.txtdetalles.setText("");
        this.base.txtnss.requestFocus();
    }
    
    private boolean fillData(){
        this.d = new donacion();
        
        String nss = this.base.txtnss.getText().trim();
        
        if((!"".equals(nss))&&(this.db.existeDonador(new donador(nss)))){
            this.d.setNssdonador(nss);
        }
        else{
            msg.error("Numero de seguridad Social Invalido!");
            this.base.txtnss.requestFocus();
            return false;
        }
        LocalDate date =this.base.dpfecha.getValue();
        this.d.setFecha(date!=null?date.format(mydate.formato).trim():null);
        this.d.setDetalles(this.base.txtdetalles.getText());
        this.d.setComentarios(this.base.txtcomentarios.getText());
        this.d.setIdbanco(Integer.valueOf(this.base.txtbanco.getText().trim()));
        this.d.setIdusuario(Integer.valueOf(this.base.txtusuario.getText().trim()));
        
        return true;
        
    }
    public void btnsearch(){
        modelo_searchDonador d = new modelo_searchDonador();        
    }
    
    public void btncancelar(){
        this.initData();
    }
    
    public void insertarDonacion(){
        if(this.fillData()){
            
            if(this.db.insertDonacion(this.d)){
                msg.ok("Donacion registrada.");
                
                if(!this.base.cbclose.selectedProperty().getValue()){
                    this.initData();
                }
                else{
                    this.stage.close();
                }
            }
            else{
                msg.error("Error al registrar donacion.");
                this.base.txtnss.requestFocus();   
            }
        }
    }
    
    public void close(){
        this.stage.close();
    }
   
}
