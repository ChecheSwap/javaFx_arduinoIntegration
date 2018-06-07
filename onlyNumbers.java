
package utilerias;

public class onlyNumbers {
    
    
    public static boolean check(String word){
        
        for(Character c : word.toCharArray()){
            if(!Character.isDigit(c)){
                return false;
            }
        }                
        return true;
    }
}
