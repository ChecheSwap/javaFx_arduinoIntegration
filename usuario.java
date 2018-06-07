
package entities;


public class usuario {
    public int id;
    public String nombre;
    public String apellido;
    public String alias;
    public String password;
    
    public usuario(){}
    
    public static void copy(usuario a, usuario b){
        a.id = b.id;
        a.nombre = b.nombre;
        a.apellido = b.apellido;
        a.alias = b.alias;
        a.password = b.password;
    }
}
