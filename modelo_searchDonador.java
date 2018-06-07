
package bloodbanks.donadores;

import bloodbanks.Main;
import db.dbop;
import entities.donador;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utilerias.mydate;

/**
 *
 * @author Master
 */
public class modelo_searchDonador {
    
    private Controller_searchDonador base = null;
    private ObservableList<donador> listDonadores = null;
    
    private dbop db = null;        
    
    public modelo_searchDonador(){
        this.base = Main.manager.openFXML("/bloodbanks/donadores/FXMLsearchDonador.fxml","Lista de donadores",false,false).getController();      
        this.db = new dbop();
        this.base.tabla.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        
        this.initializeList();
        
        this.base.modelo = this;
        
        this.searchAlldonadores();
        this.base.txtnss.requestFocus();
        
    }
    
    public void searchnombre(){
        this.listDonadores.clear();
        
        this.base.dpnacimiento.setValue(null);
        this.base.txtnss.setText("");
        
        this.fillLista(this.db.getDonadores(this.base.txtnombre.getText().trim(),4));
        
    }
    public void searchfecha(){
        this.listDonadores.clear();
        
        this.base.txtnss.setText("");
        this.base.txtnombre.setText("");
        
        LocalDate date = this.base.dpnacimiento.getValue();
        
        if(date != null){                        
            this.fillLista(this.db.getDonadores(date.format(mydate.formato).trim(), 3));
        }
        
        
    }
    public void searchnss(){              
        this.listDonadores.clear();
        this.base.dpnacimiento.setValue(null);
        this.base.txtnombre.setText("");
        this.fillLista(this.db.getDonadores(this.base.txtnss.getText().trim(), 2));
    }
    
    public void searchAlldonadores(){
        
        this.base.txtnss.setText("");
        this.base.txtnombre.setText("");
        this.base.dpnacimiento.setValue(null);
        
        this.listDonadores.clear();
        this.fillLista(this.db.getDonadores(this.base.txtnss.getText().trim(), 1));
    }
    
    private void fillLista(List<donador>lista){
        for(donador x:lista){
            this.listDonadores.add(x);
        }
    }
    
    private void initializeList(){
        this.listDonadores = FXCollections.observableArrayList();      
                
        TableColumn a = new TableColumn("Numero de Seguridad Social");
        TableColumn b = new TableColumn("Genero");
        TableColumn c = new TableColumn("Fecha de Nacimiento");  
        TableColumn nameT = new TableColumn("Nombre Completo");
        TableColumn d = new TableColumn("Nombre");
        TableColumn e = new TableColumn("Apellido");
        TableColumn address = new TableColumn("Direccion del Donador");
        TableColumn f = new TableColumn("Telefono de Contacto");
        TableColumn g = new TableColumn("Numero");
        TableColumn h = new TableColumn("Calle");
        TableColumn i = new TableColumn("Colonia");        
        TableColumn j = new TableColumn("Ciudad");
        TableColumn k = new TableColumn("Estado");
        TableColumn l = new TableColumn("Detalles");
        
        a.setCellValueFactory(new PropertyValueFactory<>("nss"));
        b.setCellValueFactory(new PropertyValueFactory<>("genero"));
        c.setCellValueFactory(new PropertyValueFactory<>("nacimiento"));
        d.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        e.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        f.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        g.setCellValueFactory(new PropertyValueFactory<>("numero"));
        h.setCellValueFactory(new PropertyValueFactory<>("calle"));
        i.setCellValueFactory(new PropertyValueFactory<>("colonia"));
        j.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        k.setCellValueFactory(new PropertyValueFactory<>("estado"));
        l.setCellValueFactory(new PropertyValueFactory<>("detalles"));
                        
        this.base.tabla.setItems(this.listDonadores);
        
        this.base.tabla.getColumns().addAll(a,b,c,nameT,f,address);
        
        nameT.getColumns().addAll(d,e);
        
        address.getColumns().addAll(g,h,i,j,k,l);        
    }
    
}
