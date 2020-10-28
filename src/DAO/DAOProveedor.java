/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidades.Proveedor;
import Utilidades.Conexion;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author reant
 */
public class DAOProveedor extends Conexion implements ICRUDS<Proveedor>{

    private String sql;
    @Override
    public void Insertar(Proveedor objetoT) {
        sql = "INSERT INTO PROVEEDOR (proveedor) VALUES ";
        sql = sql.concat("('"+objetoT.getProveedor()+"')");
        try {
            conex=getConexion();
            pstm=conex.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conex.close();
                pstm.close();;
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void Editar(Proveedor objetoT) {
        try {
            int idPro = objetoT.getIdProveedor();
            String nombre = objetoT.getProveedor();
            sql = "UPDATE PROVEEDOR SET proveedor='"+nombre+"' WHERE idProveedor="+idPro;
            
            conex=getConexion();
            pstm=conex.prepareStatement(sql);
            pstm.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DAOProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conex.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void Eliminar(int ID) {
        sql = "DELETE FROM PROVEEDOR WHERE idProveedor = "+ID;
        
        try {
            conex=getConexion();
            pstm=conex.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conex.close();
                pstm.close();;
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }     
    }

    @Override
    public Proveedor BuscarporID(int ID) {
        sql = "SELECT * FROM PROVEEDOR WHERE idProveedor="+ID;
        
        try {
            conex=getConexion();
            pstm=conex.prepareStatement(sql);
            rsset=pstm.executeQuery();
            rsset.next();
            
            Proveedor retorno = new Proveedor(ID,rsset.getString("Nombre"));
            return retorno;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conex.close();
                pstm.close();;
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
        return null;
    }

    public Proveedor BuscarporNombre(String Nombre){
        sql = "SELECT * FROM PROVEEDOR WHERE proveedor='"+Nombre+"'";
        
        try {
            conex=getConexion();
            pstm=conex.prepareStatement(sql);
            rsset=pstm.executeQuery();
            rsset.next();
            
            Proveedor retorno = new Proveedor(rsset.getInt(1),Nombre);
            return retorno;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conex.close();
                pstm.close();;
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
        return null;
        
    }
    @Override
    public List<Proveedor> Listar() {
        sql = "SELECT * FROM PROVEEDOR";
        List<Proveedor> lista = new ArrayList<>();
        try {
            conex=getConexion();
            pstm=conex.prepareStatement(sql);
            rsset=pstm.executeQuery();
            while(rsset.next()){
                Proveedor n = new Proveedor (rsset.getInt(1),rsset.getString(2));
                lista.add(n);
            }
            return lista;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conex.close();
                pstm.close();;
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
}
