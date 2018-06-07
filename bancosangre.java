
package entities;


public class bancosangre {
    public int id;
    public String nombre;
    public String fecha;
    public String calle;
    public int numero;
    public String colonia;
    public String ciudad;
    public String estado;
    public String pais;
    
    public bancosangre(){}
    
    
    public static void copy(bancosangre a, bancosangre b){
        a.id = b.id;
        a.nombre= b.nombre;
        a.fecha = b.fecha;
        a.calle = b.calle;
        a.numero = b.numero;
        a.colonia = b.colonia;
        a.ciudad = b.ciudad;
        a.estado = b.estado;
        a.pais = b.pais;
    }
}
