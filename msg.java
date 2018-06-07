
package utilerias;

import javax.swing.JOptionPane;

public class msg {
    
    public static boolean yesno(String msg){
        
        int dr = JOptionPane.showConfirmDialog(null,msg,"Confirmar",JOptionPane.YES_NO_OPTION);
        
        return JOptionPane.YES_OPTION == dr;
    }    
    public static void ok(String msg){
        
        JOptionPane.showMessageDialog(null,msg,"Informacion",JOptionPane.INFORMATION_MESSAGE);        
    }    
    public static void error(String msg){        
        JOptionPane.showMessageDialog(null,msg,"Error", JOptionPane.ERROR_MESSAGE);        
    }      
    
    public static void danger(String msg){
        JOptionPane.showMessageDialog(null,msg,"Advertencia", JOptionPane.WARNING_MESSAGE);       
    }
    
}
