
package utilerias;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class txtOnlyNumbers {
        
    public static void check(KeyEvent e){
        

        if(e.getCode() == KeyCode.BACK_SPACE){
            return;
        }
        
        for(Character c : e.getCharacter().toCharArray()){
            if(!Character.isDigit(c)){
                e.consume();
            }
        }
            
        
    }
}
