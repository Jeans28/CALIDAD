/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Entidades.DET_Venta;
import Entidades.Usuario;
import Entidades.Venta;
import Utilidades.Conexion;
import DAO.DAOEmpleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author reant
 */
public class DAOVentas extends Conexion implements ICRUDS<Venta>{

    private String sql;
    
    public int VerUltimoNumVenta(){
        try {
            sql="SELECT NumVenta FROM VENTA ORDER BY NumVenta ASC";
            conex=getConexion();
            pstm=conex.prepareStatement(sql);
            rsset=pstm.executeQuery();
            
            int numventa = 999;
            while(rsset.next()){
                numventa=rsset.getInt(1);
            }
            return numventa;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DAOVentas.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conex.close();
                pstm.close();
                rsset.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOVentas.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        return 0;
    }
    
    @Override
    public void Insertar(Venta objetoT) {
        try {
            sql="INSERT INTO VENTA(usuario_id,observaciones,fecha,total) VALUES ("+objetoT.getEmpleado().getIdusuario()+",'"+objetoT.getObsventa()+"',"
                    +objetoT.getPrecioT()+")";      //Inserta la venta
            conex = getConexion();
            pstm = conex.prepareStatement(sql);
            pstm.executeUpdate();
            
            List<DET_Venta> compras = objetoT.getCompras();
            Iterator it = compras.iterator();
            while(it.hasNext()){
                DET_Venta tmp = (DET_Venta) it.next();
                conex.close();
                sql="INSERT INTO DETALLE_VENTA(iddetalle_venta,venta_id,producto_id,,cantidad,subtotal) VALUES ("+ VerUltimoNumVenta() +
                        ","+tmp.getV().getCodventa()+","+tmp.getP().getCodProducto()+","+tmp.getCantidad()+","+tmp.getPrecioN()+")";
                conex=getConexion();
                pstm=conex.prepareStatement(sql);
                pstm.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DAOVentas.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conex.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }

    @Override
    public void Editar(Venta objetoT) {
        
    }

    @Override
    public void Eliminar(int ID) {
        try {
            conex=getConexion();
            
            sql="DELETE FROM DETALLE_VENTA WHERE venta_id="+ID;
            pstm=conex.prepareStatement(sql);
            pstm.executeUpdate();
            
            sql="DELETE FROM VENTA WHERE idventa="+ID;
            pstm=conex.prepareStatement(sql);
            pstm.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DAOVentas.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conex.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
    }

    @Override
    public Venta BuscarporID(int ID) {
        DAOCliente dao_c = new DAOCliente();
        DAOEmpleado dao_e = new DAOEmpleado();
        try {
            sql = "SELECT * FROM VENTA WHERE idventa="+ID;
            conex=getConexion();
            pstm=conex.prepareStatement(sql);
            rsset=pstm.executeQuery();
            
            Cliente nc;
            Usuario ne;
            Venta ven = null;
            
            while(rsset.next()){
                int numventa = rsset.getInt(1);
                String fechaE = rsset.getString(3);
                nc = dao_c.BuscarporID(rsset.getInt(4));
                ne = dao_e.BuscarporID(rsset.getInt(5));
                
                List<DET_Venta> compras = Ver_DET_VENTAS(numventa);
                ven = new Venta(numventa, fechaE, compras, nc, ne);                
            }
            return ven;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DAOVentas.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conex.close();
                pstm.close();
                rsset.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public List<Venta> Listar() {
        DAOCliente dao_c = new DAOCliente();
        DAOEmpleado dao_e = new DAOEmpleado();
        
        Cliente nc = null;
        Usuario ne=null;
        List<Venta> lista = new ArrayList<>();
        
        try {
            sql="SELECT * FROM VENTA";
            conex=getConexion();
            pstm=conex.prepareStatement(sql);
            rsset=pstm.executeQuery();
            
            while(rsset.next()){
                int numventa = rsset.getInt(1);
                String fechaE = rsset.getString(3);
                nc = dao_c.BuscarporID(rsset.getInt(4));
                ne = dao_e.BuscarporID(rsset.getInt(5));
                
                List<DET_Venta> compras = Ver_DET_VENTAS(numventa);
                
                Venta ven = new Venta(numventa, fechaE, compras, nc, ne);
                lista.add(ven);
            }
            return lista;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DAOVentas.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conex.close();
                pstm.close();
                rsset.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public List<DET_Venta> Ver_DET_VENTAS(int NUMVENTA){
        try {
            Connection cn = getConexion();
            PreparedStatement ps = null;
            ResultSet set = null;
            try {
                List<DET_Venta> lista = new ArrayList<>();
                String q = "SELECT * FROM DETALLE_VENTA WHERE IDDETALLE_VENTA="+NUMVENTA;
                DAOProducto daoP = new DAOProducto();
                
                ps = cn.prepareStatement(q);
                set = ps.executeQuery();
                
                while(set.next()){
                    DET_Venta aux = new DET_Venta( daoP.BuscarporID(set.getInt(2)) , set.getInt(3));
                    lista.add(aux);
                }
                return lista;
            } catch (SQLException ex) {
                Logger.getLogger(DAOVentas.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    cn.close();
                    ps.close();
                    set.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
