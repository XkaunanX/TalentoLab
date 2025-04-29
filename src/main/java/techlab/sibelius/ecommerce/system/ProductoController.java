package techlab.sibelius.ecommerce.system;

import techlab.sibelius.ecommerce.domain.Producto;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ProductoController {
    // Atributos
    private final ProductoArchivo productoArchivo;

    // Constructor
    public ProductoController() {
        this.productoArchivo = new ProductoArchivo();
    }

    // Metodos
    public Producto crearProducto(String sku, String nombre, double precio) throws IOException {
        Producto nuevoProducto = new Producto(sku, nombre, precio);
        productoArchivo.guardarProducto(nuevoProducto);
        return nuevoProducto;
    }

    public Optional<Producto> buscarProducto(String sku) throws IOException {
        return productoArchivo.buscarProducto(sku);
    }

    public List<Producto> obtenerTodosLosProductos() throws IOException {
        return productoArchivo.obtenerTodosLosProductos();
    }

    public void actualizarProducto(Producto productoActualizado) throws IOException {
        productoArchivo.actualizarProducto(productoActualizado);
    }

    public boolean eliminarProducto(String sku) throws IOException {
        return productoArchivo.eliminarProducto(sku);
    }

    public void actualizarStock(String sku, int cantidad) throws IOException {
        Optional<Producto> productoOpt = productoArchivo.buscarProducto(sku);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            producto.setStockProducto(cantidad);
            productoArchivo.actualizarProducto(producto);
        }
    }

    public double obtenerPrecio(String sku) throws IOException {
        return productoArchivo.buscarProducto(sku)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"))
                .getPrecioProducto();
    }
}