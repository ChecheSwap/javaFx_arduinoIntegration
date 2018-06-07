
package utilerias;

import java.io.File;
import javafx.stage.FileChooser;

public class fileCh {
    private FileChooser.ExtensionFilter imageFilter = null;
    FileChooser fc = null;
    
    public fileCh(){
        
        this.imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        this.fc = new FileChooser();
        this.fc.setTitle("Seleccione Documento deseado");
        this.fc.getExtensionFilters().add(imageFilter);
        
    }
        
        
    
    public File open(){
    
        File i = null;
        try{
            i = this.fc.showOpenDialog(null);                 
        }
        catch(Exception ex){
            msg.error(ex.toString());
        }
        
        
        return i;
    }
    
}
