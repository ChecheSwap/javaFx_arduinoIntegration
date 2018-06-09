package db;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import entities.bancosangre;
import entities.donacion;
import entities.donador;
import entities.usuario;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import utilerias.img;
import utilerias.msg;

public class dbop {

    private static final String USER = "root";
    private static final String PASS = "NULL";
    private static final String BD_NAME = "bloodBanks";
    private static final int PORT = 3306;
    private static final String SERVER_NAME = "localhost";

    private final DataSource dsource;
    private Connection xconn = null;
    private Statement stmt = null;

    private img imgops = null;

    public dbop() {

        this.imgops = new img();
        MysqlDataSource mds = new MysqlDataSource();

        mds.setUser(USER);
        mds.setPassword(PASS);
        mds.setDatabaseName(BD_NAME);
        mds.setServerName(SERVER_NAME);
        mds.setPortNumber(PORT);
        mds.setURL(mds.getURL() + "?useSSL=false");

        this.dsource = (DataSource) mds;
    }

    //DONACION FUNCS
    public List<donacion> getDonaciones(String value, int tipo) {

        List<donacion> send = new ArrayList<donacion>() {
        };

        if (this.connectionOpen()) {

            String SQLquery = SQL.SP_GET_DONACIONES;

            try (CallableStatement cstmt = this.xconn.prepareCall(SQLquery)) {
                cstmt.setString(1, value);
                cstmt.setInt(2, tipo);

                ResultSet rs = cstmt.executeQuery();

                while (rs.next()) {
                    send.add(this.fillDonacion(rs));
                }

                cstmt.close();
                rs.close();

            } catch (SQLException ex) {
                msg.error(ex.getMessage());
            }
        }

        this.connectionClose();
        return send;
    }

    private donacion fillDonacion(ResultSet rs) throws SQLException {
        donacion d = new donacion();

        d.setId(rs.getInt(1));
        d.setFecha(rs.getString(2));
        d.setDetalles(rs.getString(3));
        d.setComentarios(rs.getString(4));
        d.setNssdonador(rs.getString(5));
        d.setIdbanco(rs.getInt(6));
        d.setIdusuario(rs.getInt(7));
        return d;
    }

    public boolean existDonacion(donacion d) {
        boolean flag = false;

        if (this.connectionOpen()) {
            String SQLquery = SQL.SP_GET_DONACION;

            try (CallableStatement cstmt = this.xconn.prepareCall(SQLquery)) {
                cstmt.setInt(1, d.getId());

                ResultSet rs = cstmt.executeQuery();

                if (rs.next()) {
                    flag = true;
                }

                rs.close();

                cstmt.close();
            } catch (SQLException ex) {
                msg.error(ex.toString());
            }
        }

        return flag;
    }

    public boolean deleteDonacion(donacion d) {
        boolean flag = false;

        if (this.connectionOpen()) {

            String SQLquery = SQL.SP_DELETE_DONACION;

            try (CallableStatement cstmt = this.xconn.prepareCall(SQLquery)) {

                cstmt.setInt(1, d.getId());

                cstmt.executeUpdate();

                cstmt.close();

                flag = true;
            } catch (SQLException ex) {
                msg.error(ex.toString());
            }
        }

        return flag;
    }

    public boolean insertDonacion(donacion d) {
        boolean flag = false;

        if (this.connectionOpen()) {

            String SQLquery = SQL.SP_INSERT_DONACION;

            try (CallableStatement cstmt = this.xconn.prepareCall(SQLquery)) {
                this.fillstmtDonacion(cstmt, d);

                cstmt.executeUpdate();

                cstmt.close();

                flag = !flag;
            } catch (SQLException ex) {
                msg.error(ex.getMessage());
            }

        }

        this.connectionClose();

        return flag;
    }

    private void fillstmtDonacion(PreparedStatement cstmt, donacion d) throws SQLException {
        cstmt.setString(1, d.getFecha());
        cstmt.setString(2, d.getDetalles());
        cstmt.setString(3, d.getComentarios());
        cstmt.setString(4, d.getNssdonador());
        cstmt.setInt(5, d.getIdbanco());
        cstmt.setInt(6, d.getIdusuario());
    }

    public int getCurrentDonacion() {
        int send = -1;
        if (this.connectionOpen()) {
            String SQLquery = SQL.SP_GET_CURRENT_DONACION;
            try (CallableStatement cstmt = this.xconn.prepareCall(SQLquery)) {

                ResultSet rs = cstmt.executeQuery();

                if (rs.next()) {
                    send = rs.getInt(1);
                }

                rs.close();
                cstmt.close();
            } catch (SQLException ex) {
                msg.error(ex.getMessage());
            }
        }

        this.connectionClose();
        return send;
    }

    public boolean hasDonaciones(String nss) {
        boolean flag = false;

        if (this.connectionOpen()) {

            String SQLquery = SQL.SP_GET_DONACION_NSS;

            try (CallableStatement cstmt = this.xconn.prepareCall(SQLquery)) {
                cstmt.setString(1, nss);

                ResultSet rs = cstmt.executeQuery();

                if (rs.next()) {
                    flag = true;
                }

                cstmt.close();

                rs.close();
            } catch (SQLException ex) {
                msg.error(ex.getMessage());
            }
        }

        this.connectionClose();

        return flag;
    }

    //DONADOR FUNCS
    public List<donador> getDonadores(String value, int tipo) {

        List<donador> result = new ArrayList<donador>() {
        };

        if (this.connectionOpen()) {

            String SQLquery = SQL.SP_GET_DONADORES;

            try (CallableStatement cstmt = this.xconn.prepareCall(SQLquery)) {
                cstmt.setString(1, value);
                cstmt.setInt(2, tipo);

                ResultSet rs = cstmt.executeQuery();

                while (rs.next()) {

                    try {
                        result.add(this.fillDonador(rs));
                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                    }
                }

                cstmt.close();
                rs.close();
            } catch (SQLException ex) {
                msg.error(ex.getMessage());
            }
        }

        this.connectionClose();

        return result;
    }

    public boolean deleteDonador(donador d) {

        boolean flag = false;

        if (this.connectionOpen()) {

            String SQLquery = SQL.SP_DELETE_DONADOR;

            try (CallableStatement cstmt = this.xconn.prepareCall(SQLquery)) {

                cstmt.setString(1, d.getNss());

                cstmt.executeUpdate();

                flag = true;

                cstmt.close();
            } catch (SQLException ex) {
                msg.error(ex.getMessage());
            }

        }

        this.connectionClose();

        return flag;
    }

    public boolean updateDonador(donador d) {
        boolean flag = false;

        if (this.connectionOpen()) {

            String SQLquery = SQL.SP_UPDATE_DONADOR;

            try (CallableStatement pstmt = this.xconn.prepareCall(SQLquery)) {

                this.fillStmtDonador(pstmt, d);

                pstmt.executeUpdate();

                pstmt.close();

                flag = true;
            } catch (SQLException ex) {
                msg.error(ex.getMessage());
            }
        }

        this.connectionClose();

        return flag;
    }

    public donador getDonador(String nss) {
        donador d = null;

        if (this.connectionOpen()) {

            String SQLquery = SQL.SP_GET_DONADOR;

            try (PreparedStatement pstmt = this.xconn.prepareStatement(SQLquery)) {

                pstmt.setString(1, nss);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    d = this.fillDonador(rs);
                }

                rs.close();
                pstmt.close();
            } catch (Exception ex) {
                msg.error(ex.getMessage());
            }
        }

        this.connectionClose();

        return d;
    }

    public boolean altaDonador(List<donador> mylista) {
        boolean flag = false;

        if (this.connectionOpen()) {

            String SQLquery = SQL.SP_INSERT_DONADOR;

            try (PreparedStatement pstmt = this.xconn.prepareStatement(SQLquery);) {

                for (donador d : mylista) {
                    this.fillStmtDonador(pstmt, d);
                    pstmt.addBatch();
                }

                pstmt.executeBatch();

                pstmt.close();

                flag = true;
            } catch (SQLException ex) {
                msg.error(ex.getMessage());
            }
        }

        this.connectionClose();

        return flag;
    }

    public boolean altaDonador(donador d) {
        boolean flag = false;

        if (this.connectionOpen()) {

            String SQLquery = SQL.SP_INSERT_DONADOR;

            try (PreparedStatement pstmt = this.xconn.prepareStatement(SQLquery)) {

                this.fillStmtDonador(pstmt, d);

                if (pstmt.executeUpdate() == 1) {
                    flag = true;
                }

                pstmt.close();
            } catch (Exception ex) {
                msg.error(ex.getMessage());
                System.out.println(ex.getMessage());
            }

        }

        this.connectionClose();

        return flag;
    }

    private void fillStmtDonador(PreparedStatement pstmt, donador d) throws SQLException {

        pstmt.setString(1, d.getNss());
        pstmt.setString(2, d.getGenero());
        pstmt.setString(3, d.getNacimiento());
        pstmt.setString(4, d.getNombre());
        pstmt.setString(5, d.getApellido());
        pstmt.setString(6, d.getTelefono());
        pstmt.setBinaryStream(7, imgops.getBinary(d.getImagen()));
        pstmt.setInt(8, d.getNumero());
        pstmt.setString(9, d.getCalle());
        pstmt.setString(10, d.getColonia());
        pstmt.setString(11, d.getCiudad());
        pstmt.setString(12, d.getEstado());
        pstmt.setString(13, d.getDetalles());
    }

    private donador fillDonador(ResultSet rs) throws Exception {

        donador d = new donador();

        d.setNss(rs.getString(2) != null ? rs.getString(2) : "");
        d.setGenero(rs.getString(3) != null ? rs.getString(3) : "");
        d.setNacimiento(rs.getString(4) != null ? rs.getString(4) : "");
        d.setNombre(rs.getString(5) != null ? rs.getString(5) : "");
        d.setApellido(rs.getString(6) != null ? rs.getString(6) : "");
        d.setTelefono(rs.getString(7) != null ? rs.getString(7) : "");
        d.setNumero(rs.getInt(9));
        d.setCalle(rs.getString(10) != null ? rs.getString(10) : "");
        d.setColonia(rs.getString(11) != null ? rs.getString(11) : "");
        d.setCiudad(rs.getString(12) != null ? rs.getString(12) : "");
        d.setEstado(rs.getString(13) != null ? rs.getString(13) : "");
        d.setDetalles(rs.getString(14) != null ? rs.getString(14) : "");

        try {
            d.setImagen(imgops.getImage(rs.getBlob(8).getBinaryStream()));
        } catch (Exception ex) {
            System.out.println("No existe Imagen!");
        }

        return d;
    }

    public boolean existeDonador(donador d) {
        boolean flag = false;

        if (this.connectionOpen()) {

            String SQLquery = SQL.SP_GET_DONADOR;

            try (PreparedStatement cstmt = this.xconn.prepareStatement(SQLquery);) {

                cstmt.setString(1, d.getNss());

                ResultSet rs = cstmt.executeQuery();

                if (rs.next()) {
                    flag = true;
                }

                cstmt.close();
                rs.close();
            } catch (SQLException ex) {
                msg.error(ex.getMessage());
                System.out.println(ex.getMessage());
            }
        }

        this.connectionClose();

        return flag;
    }

    //USER
    public usuario getUsuario(String id) {
        usuario usr = null;
        
        if (this.connectionOpen()) {
            
            String SQLquery = SQL.SP_GET_USER_BYID;

            try (CallableStatement cstmt = this.xconn.prepareCall(SQLquery)) {
                cstmt.setString(1, id);
                
                ResultSet rs = cstmt.executeQuery();
                
                if(rs.next()){
                    usr = new usuario();
                    
                    this.fillUser(rs, usr);
                }
                
                
                cstmt.close();
                rs.close();

            } catch (Exception ex) {
                msg.error(ex.getMessage());
            }

        }
        
        return usr;
    }

    public usuario getUsuario(String alias, String pass) {
        usuario usr = null;
        if (this.connectionOpen()) {
            String SQLquery = SQL.SP_GET_USER;

            try (CallableStatement cstmt = this.xconn.prepareCall(SQLquery)) {
                cstmt.setString(1, alias);
                cstmt.setString(2, pass);

                ResultSet rs = cstmt.executeQuery();

                if (rs.next()) {

                    usr = new usuario();
                    
                    this.fillUser(rs, usr);
                }

                rs.close();
                cstmt.close();
            } catch (SQLException ex) {
                msg.error(ex.getMessage());
            }
        }
        return usr;
    }

    private void fillUser(ResultSet rs, usuario usr) {
        
        try{
            usr.id = rs.getInt(1);
            usr.nombre = rs.getString(2);
            usr.apellido = rs.getString(3);
            usr.alias = rs.getString(4);
            usr.password = rs.getString(5);
        }
        catch(Exception ex){
            msg.error(ex.getMessage());
        }
    }

    //BANCO
    public bancosangre getBancoLocal() {
        bancosangre send = null;

        if (this.connectionOpen()) {
            String SQLquery = SQL.SP_GET_LOCAL_BANCO;

            try (CallableStatement cstmt = this.xconn.prepareCall(SQLquery)) {

                ResultSet rs = cstmt.executeQuery();

                if (rs.next()) {
                    send = new bancosangre();
                    send.id = rs.getInt(1);
                    send.nombre = rs.getString(2);
                    send.fecha = rs.getString(3);
                    send.calle = rs.getString(4);
                    send.numero = rs.getInt(5);
                    send.colonia = rs.getString(6);
                    send.ciudad = rs.getString(7);
                    send.estado = rs.getString(8);
                    send.pais = rs.getString(9);
                }
                rs.close();
                cstmt.close();

            } catch (SQLException ex) {
                msg.error(ex.toString());
            }
        }

        this.connectionClose();

        return send;
    }

    //CONN
    public boolean connectionOpen() {
        boolean flag = false;

        try {
            this.xconn = (Connection) this.dsource.getConnection();
            flag = true;
        } catch (SQLException ex) {
            msg.error(ex.getMessage());
        }

        return flag;
    }

    public boolean connectionClose() {

        boolean flag = false;

        if (!(this.xconn == null)) {
            try {
                this.xconn.close();
                flag = true;
            } catch (SQLException ex) {
                msg.error(ex.getMessage());
            }
        } else {
            flag = true;
        }
        return flag;
    }
}
