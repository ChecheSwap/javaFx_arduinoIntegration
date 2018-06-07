
package db;

public class SQL {
    public static final String SP_INSERT_DONADOR = "{CALL sp_insert_Donador(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    public static final String SP_GET_DONADOR = "{CALL sp_get_Donador(?)}";
    public static final String SP_UPDATE_DONADOR = "{CALL sp_update_Donador(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    public static final String SP_DELETE_DONADOR = "{CALL sp_delete_Donador(?)}";
    public static final String SP_GET_DONACION = "{CALL sp_get_Donacion(?)}";
    public static final String SP_GET_DONACION_NSS = "{CALL sp_get_Donacion_nss(?)}";
    public static final String SP_GET_DONADORES = "{CALL sp_get_Donadores(?,?)}";
    public static final String SP_GET_CURRENT_DONACION = "{CALL sp_get_currentDonacion()}";
    public static final String SP_INSERT_DONACION = "{CALL sp_insert_Donacion(?,?,?,?,?,?)}";
    public static final String SP_DELETE_DONACION = "{CALL sp_delete_Donacion(?)}";
    public static final String SP_GET_DONACIONES = "{CALL sp_get_Donaciones(?,?)}";
    public static final String SP_GET_LOCAL_BANCO = "{CALL sp_get_Local_Banco()}";
    public static final String SP_GET_USER = "{CALL sp_get_User(?,?)}";
    public static final String SP_GET_USER_BYID = "{CALL sp_get_User_Id(?)}";
    
}
