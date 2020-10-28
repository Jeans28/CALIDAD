/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.sql.*;

/**
 *
 * @author reant
 */
public class Conexion {
    protected Connection conex = null;
    protected PreparedStatement pstm = null;
    protected CallableStatement cstm = null;
    protected ResultSet rsset= null;
    
    protected Connection getConexion() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto?serverTimezone=UTC", "root", "12345678");
    }
}
