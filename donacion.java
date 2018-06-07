
package entities;

public class donacion {
    private int id;
    private String fecha;
    private String detalles;
    private String comentarios;
    private String nssdonador;
    private int idbanco;
    private int idusuario;

    public donacion(){}
    public donacion(int id){
        this.id = id;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the detalles
     */
    public String getDetalles() {
        return detalles;
    }

    /**
     * @param detalles the detalles to set
     */
    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    /**
     * @return the comentarios
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @return the nssdonador
     */
    public String getNssdonador() {
        return nssdonador;
    }

    /**
     * @param nssdonador the nssdonador to set
     */
    public void setNssdonador(String nssdonador) {
        this.nssdonador = nssdonador;
    }

    /**
     * @return the idbanco
     */
    public int getIdbanco() {
        return idbanco;
    }

    /**
     * @param idbanco the idbanco to set
     */
    public void setIdbanco(int idbanco) {
        this.idbanco = idbanco;
    }

    /**
     * @return the idusuario
     */
    public int getIdusuario() {
        return idusuario;
    }

    /**
     * @param idusuario the idusuario to set
     */
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }


    
}
