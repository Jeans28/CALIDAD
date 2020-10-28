/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidades.Producto;
import Entidades.Proveedor;
import Utilidades.Conexion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author reant
 */
public class DAOProducto extends Conexion implements ICRUDS<Producto>{

    private String sql ;
    @Override
    public void Insertar(Producto objetoT) {
        String des=objetoT.getProducto();
        double precio = objetoT.getPrecioUni();
        int stck = objetoT.getStock();
        int idProveedor = objetoT.getProveedor().getIdProveedor();
        sql = "INSERT INTO PRODUCTO(descripcion,precioUni,Stock,idProveedor) VALUES (" + "'" + des + "'," + precio + "," + stck + "," + idProveedor + ")";
        
        try {
            conex = getConexion();
            pstm = conex.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
        }finally{
            try {
                conex.close();
                pstm.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void Editar(Producto objetoT) {
        int codProd = objetoT.getCodProducto();
        String des=objetoT.getDescripcion();
        double precio = objetoT.getPrecioUni();
        int stck = objetoT.getStock();
        int idProveedor = objetoT.getProveedor().getIdProveedor();
        sql = "UPDATE PRODUCTO SET "
                + "descripcion='"+des+"',"
                + "precioUni="+precio+","
                + "Stock="+stck+","
                + "idProveedor="+idProveedor+" "
                + "WHERE codProducto="+codProd;
        try {
            conex=getConexion();
            pstm=conex.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOProducto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conex.close();
                pstm.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }

    @Override
    public void Eliminar(int ID) {
        sql = "DELETE FROM PRODUCTO WHERE codProducto = "+ID;
        
        try {
            conex=getConexion();
            pstm=conex.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOProducto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conex.close();
                pstm.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }     
    }

    @Override
    public Producto BuscarporID(int ID) {
        try {
            sql = "SELECT * FROM PRODUCTO p INNER JOIN PROVEEDOR pr ON p.idProveedor=pr.idProveedor WHERE p.codProducto="+ID;
            conex=getConexion();
            pstm=conex.prepareStatement(sql);
            rsset=pstm.executeQuery();
            rsset.next();
            
            String des=rsset.getString(2);
            double precio = rsset.getDouble(3);
            int stck = rsset.getInt(4);
            Proveedor p = new Proveedor(rsset.getInt(5), rsset.getString(7));
            
            Producto retorno = new Producto(ID,des,precio,stck,p);
            return retorno;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOProducto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conex.close();
                pstm.close();
                rsset.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
        return null;
    }

    @Override
    public List<Producto> Listar() {
        sql = "SELECT * FROM PRODUCTO p INNER JOIN PROVEEDOR pr	ON p.idProveedor=pr.idProveedor";
        List<Producto> lista = new ArrayList<>();
        try {
            conex=getConexion();
            pstm=conex.prepareStatement(sql);
            rsset=pstm.executeQuery();
            
            while(rsset.next()){
                Producto n = new Producto (rsset.getInt(1),rsset.getString(2), rsset.getDouble(3), rsset.getInt(4),
                        new Proveedor(rsset.getInt(5), rsset.getString(7)));
                lista.add(n);
            }
            return lista;
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOProducto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conex.close();
                pstm.close();
                rsset.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public Producto BuscarporNombre(String descripcion){
        try {
            sql="SELECT * FROM PRODUCTO p INNER JOIN PROVEEDOR pr ON p.idProveedor=pr.idProveedor WHERE p.descripcion='"+descripcion+"'";
            conex=getConexion();
            pstm=conex.prepareStatement(sql);
            rsset=pstm.executeQuery();
            rsset.next();
            
            Producto retorno = new Producto(rsset.getInt(1),rsset.getString(2), rsset.getDouble(3), rsset.getInt(4),
                new Proveedor(rsset.getInt(5), rsset.getString(7)));
            
            return retorno;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DAOProducto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conex.close();
                pstm.close();
                rsset.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
        
    }
}
