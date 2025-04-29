package techlab.sibelius.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Producto {
    private final String skuProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private double precioProducto;
    private int stockProducto;

    @JsonCreator
    public Producto(
            @JsonProperty("sku") String skuProducto,
            @JsonProperty("nombre") String nombre,
            @JsonProperty("precio") double precio) {
        this.skuProducto = skuProducto;
        this.nombreProducto = nombre;
        this.precioProducto = precio;
        this.stockProducto = 0;
    }

    public Producto(String skuProducto, String nombreProducto, double precioProducto, int stockProducto) {
        this.skuProducto = skuProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.stockProducto = stockProducto;
    }

    // Getters y Setters (necesarios para Jackson)
    public String getSkuProducto() { return skuProducto; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public String getDescripcionProducto() { return descripcionProducto; }
    public void setDescripcionProducto(String descripcionProducto) { this.descripcionProducto = descripcionProducto; }

    public double getPrecioProducto() { return precioProducto; }
    public void setPrecioProducto(double precioProducto) { this.precioProducto = precioProducto; }

    public int getStockProducto() { return stockProducto; }
    public void setStockProducto(int stockProducto) { this.stockProducto = stockProducto; }
}
