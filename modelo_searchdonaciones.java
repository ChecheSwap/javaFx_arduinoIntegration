
package bloodbanks.donaciones;

import bloodbanks.Main;
import db.dbop;
import entities.donacion;
import entities.donador;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import utilerias.mydate;


public class modelo_searchdonaciones {
    
    private Controller_searchdonaciones base;
    
    private ObservableList<donacion> lista;
    
    private dbop db;
    
    public modelo_searchdonaciones(){
        this.base = Main.manager.openFXML("/bloodbanks/donaciones/FXMLsearchdonaciones.fxml","Donaciones", false,false).getController();    
        
        this.base.modelo = this;    
        
        
        this.initializeList();
                                  
        this.db = new dbop();
        
         
        
        this.fillTabla(1);
    }

    public void fillTabla(int tipo){
        
        
        String val = null;
        switch(tipo){
            case(4):{
                val = this.base.txtdonacion.getText().trim();
                
                this.base.dpfecha.setValue(null);
                this.base.txtnss.setText("");
                break;
            }
            case(3):{
                val = this.base.txtnss.getText().trim();
                
                this.base.txtdonacion.setText("");
                this.base.dpfecha.setValue(null);
                break;
            }
            case(2):{
                
                LocalDate l = this.base.dpfecha.getValue();
                
                if(l != null){
                    val = l.format(mydate.formato).trim();
                }
                
                this.base.txtnss.setText("");
                this.base.txtdonacion.setText("");
                break;
            }
            case(1):{
                                
                break;
            }
        }
        
        this.lista.clear();
        
        if("".equals(val)){
            tipo = 1;
        }
        
        for(donacion d : this.db.getDonaciones(val, tipo)){
            this.lista.add(d);
        }        
    }
        
    private void initializeList(){
        this.lista = FXCollections.observableArrayList();
        
        this.base.tabla.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);        
        
        TableColumn a = new TableColumn("Numero de Donacion");        
        TableColumn b = new TableColumn("Fecha");
        TableColumn c = new TableColumn("Detalles");
        TableColumn d = new TableColumn("Comentarios");
        TableColumn e = new TableColumn("NSS Donador");
        TableColumn f = new TableColumn("Numero Banco de sangre");
        TableColumn g = new TableColumn("Numero Usuario Alta");
        
        
        a.setCellValueFactory(new PropertyValueFactory<>("id"));
        b.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        c.setCellValueFactory(new PropertyValueFactory<>("detalles"));
        d.setCellValueFactory(new PropertyValueFactory<>("comentarios"));
        e.setCellValueFactory(new PropertyValueFactory<>("nssdonador"));
        f.setCellValueFactory(new PropertyValueFactory<>("idbanco"));
        g.setCellValueFactory(new PropertyValueFactory<>("idusuario"));                
        
        this.base.tabla.setItems(this.lista);
        
        this.base.tabla.getColumns().addAll(a,b,c,d,e,f);       
    }
}
