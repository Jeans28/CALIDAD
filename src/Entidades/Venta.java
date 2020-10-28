package Entidades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Venta {

    private String codventa;
    private double precioT;
    private String fechaE;
    private Usuario empleado;
    private String obsventa;
    private List<DET_Venta> compras = new ArrayList<>();

    public Venta() {
    }

    public Venta(String codventa, double precioT, String fechaE, Usuario empleado, String obsventa) {
        this.codventa = codventa;
        this.precioT = precioT;
        this.fechaE = fechaE;
        this.empleado = empleado;
        this.obsventa = obsventa;
    }

    public String getCodventa() {
        return codventa;
    }

    public void setCodventa(String codventa) {
        this.codventa = codventa;
    }

    public double getPrecioT() {
        return precioT;
    }

    public void setPrecioT(double precioT) {
        this.precioT = precioT;
    }

    public String getFechaE() {
        return fechaE;
    }

    public void setFechaE(String fechaE) {
        this.fechaE = fechaE;
    }

    public Usuario getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Usuario empleado) {
        this.empleado = empleado;
    }

    public String getObsventa() {
        return obsventa;
    }

    public void setObsventa(String obsventa) {
        this.obsventa = obsventa;
    }
    public List<DET_Venta> getCompras() {
        return compras;
    }
    public void setCompras(List<DET_Venta> compras) {
        this.compras = compras;
        UpdatePrecioT();
    }
    public void añadir_compra(DET_Venta obj){
        if (! obj.equals(null)){
            this.compras.add(obj);
            this.precioT += obj.getPrecioN();
        }
    }
    private void UpdatePrecioT(){
        double precio=0;
        Iterator it = compras.iterator();
        while(it.hasNext()){
            DET_Venta aux = (DET_Venta) it.next();
            precio+=aux.getPrecioN();
        }
        this.precioT=precio;
    }
}
