/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbanks.donadores;

import entities.donador;
import java.io.File;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utilerias.estados_Republica;
import utilerias.fileCh;
import utilerias.msg;
import utilerias.mydate;

/**
 *
 * @author Master
 */
public class modelo_Donador {
    
    private Controller_donador base = null;
    
    private fileCh showDialog = null;
    
    public modelo_Donador(Controller_donador base){
        this.base = base;                
        this.base.generos = FXCollections.observableArrayList("Masculino","Femenino" );
        this.base.cbgenero.setItems(base.generos);
        this.base.edos = FXCollections.observableArrayList();
        this.base.cbestado.setItems(base.edos);            
        this.base.edos.addAll(estados_Republica.estados);       
        this.base.txtseguridadsocial.requestFocus();
        this.showDialog = new fileCh();
        
        
        this.base.mystage = (Stage)this.base.btnaceptar.getScene().getWindow();
        this.base.mymodel = this;
                
    }
    
    public void clearAll(){
        this.base.txtseguridadsocial.setText("");
        this.base.cbgenero.setValue(null);        
        this.base.dpnacimiento.setValue(null);
        this.base.txtnombre.setText("");
        this.base.txtapellido.setText("");
        this.base.txtcontacto.setText("");
        this.base.ivimagen.setImage(this.base.nullImage);
        this.base.txtnumero.setText("");
        this.base.txtcalle.setText("");
        this.base.txtcolonia.setText("");
        this.base.txtciudad.setText("");
        this.base.cbestado.setValue(null);
        this.base.rtbdetalles.setText("");
        
        this.base.txtseguridadsocial.requestFocus();
    }
    
    public void fillControls(donador d){
        
        this.base.ivimagen.setImage(d.getImagen());
        this.base.cbgenero.setValue(d.getGenero());
        this.base.txtseguridadsocial.setText(d.getNss());
        
        if(!"".equals(d.getNacimiento())){
            this.base.dpnacimiento.setValue(LocalDate.parse(d.getNacimiento()));
        }
        
        this.base.txtnombre.setText(d.getNombre());
        this.base.txtapellido.setText(d.getApellido());
        this.base.txtcontacto.setText(d.getTelefono());
        this.base.txtcalle.setText(d.getCalle());                
        this.base.txtnumero.setText(String.valueOf(d.getNumero()));
        this.base.txtcolonia.setText(d.getColonia());        
        this.base.cbestado.setValue(d.getEstado());
        this.base.txtciudad.setText(d.getCiudad());
        this.base.rtbdetalles.setText(d.getDetalles());        
    }
    
    public void fillDonador(donador d){
        try{
            
            d.setNss(this.base.txtseguridadsocial.getText());
            
            d.setGenero(this.base.cbgenero.getValue()); 
            
            LocalDate date = this.base.dpnacimiento.getValue();
            
            if(date != null){                                
                d.setNacimiento(date.format(mydate.formato).trim());
            }
            
            d.setNombre(this.base.txtnombre.getText().trim());
            d.setApellido(this.base.txtapellido.getText().trim());
            d.setTelefono(this.base.txtcontacto.getText().trim());
            
            d.setImagen(this.base.ivimagen.getImage());
            
            String numero = this.base.txtnumero.getText().trim();
            
            if(!"".equals(numero)){               
                
                boolean correct = true;
                
                for(char ch:numero.toCharArray()){
                    if(!Character.isDigit(ch)){
                        correct =false;
                        
                        msg.danger("Campo Numero descartado");
                        break;
                    }
                }
                
                if(correct){
                    d.setNumero(Integer.valueOf(this.base.txtnumero.getText().trim()));               
                }
            }
            
            d.setCalle(this.base.txtcalle.getText().trim());
            d.setColonia(this.base.txtcolonia.getText().trim());
            d.setCiudad(this.base.txtciudad.getText().trim());
            d.setEstado(this.base.cbestado.getValue());
            d.setDetalles(this.base.rtbdetalles.getText().trim());             
        }
        catch(Exception ex){
            msg.error(ex.getMessage());
        }        
    }  
    
    public void selectImage(){
        
        msg.ok("Seleccione Imagen");
      
        File i = this.showDialog.open();
        
        if(!(i== null)){
            try{
                Image img = new Image(i.toURI().toString());
                this.base.ivimagen.setImage(img); 
            
            }
            catch(Exception ex){
                msg.error(ex.getMessage());
            }               
        }
    }
    
    public void defaultImage(){
        this.base.ivimagen.setImage(this.base.nullImage);
    }
    
    public void close(){
        this.base.mystage.close();
    }
    
}
