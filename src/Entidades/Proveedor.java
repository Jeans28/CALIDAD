/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author reant
 */
public class Proveedor {
    private int idProveedor;
    private String proveedor;

    public Proveedor() {
    }

    public Proveedor(int idProveedor, String proveedor) {
        this.idProveedor = idProveedor;
        this.proveedor = proveedor;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }


}
