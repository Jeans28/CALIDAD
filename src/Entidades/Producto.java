package Entidades;

public class Producto {
    private int codProducto;
    private String producto;
    private double precioUni;
    private int Stock;
    private Proveedor proveedor;
    private String fecha;

    public Producto() {
    }

    public Producto(int codProducto, String producto, double precioUni, int Stock, Proveedor proveedor, String fecha) {
        this.codProducto = codProducto;
        this.producto = producto;
        this.precioUni = precioUni;
        this.Stock = Stock;
        this.proveedor = proveedor;
        this.fecha = fecha;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getPrecioUni() {
        return precioUni;
    }

    public void setPrecioUni(double precioUni) {
        this.precioUni = precioUni;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public void verDatos(){
        System.out.println("codProducto"+codProducto+"  Precio:"+precioUni+"Stock :"+Stock
                + "id Proveedor:"+proveedor.getIdProveedor()+"-"+proveedor.getProveedor());
    }
    
}
