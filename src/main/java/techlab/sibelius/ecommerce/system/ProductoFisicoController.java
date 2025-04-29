package techlab.sibelius.ecommerce.system;

import techlab.sibelius.ecommerce.domain.Producto;
import techlab.sibelius.ecommerce.domain.ProductoFisico;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductoFisicoController {
    // Atributos
    private final ProductoFisicoArchivo productoFisicoArchivo;
    private final ProductoController productoController;

    // Constructor
    public ProductoFisicoController() {
        this.productoFisicoArchivo = new ProductoFisicoArchivo();
        this.productoController = new ProductoController();
    }

    // Metodos
    public ProductoFisico crearProductoFisico(String skuProducto, String estado, String numeroSerie, String ubicacion) throws IOException {
        Optional<Producto> producto = productoController.buscarProducto(skuProducto);
        if (producto.isEmpty()) {
            throw new IllegalArgumentException("No existe un producto con SKU: " + skuProducto);
        }
        ProductoFisico nuevo = new ProductoFisico(producto.get(), estado, numeroSerie, ubicacion);
        productoFisicoArchivo.guardarProductoFisico(nuevo);
        return nuevo;
    }

    public Optional<ProductoFisico> buscarProductoFisico(UUID id) throws IOException {
        return productoFisicoArchivo.buscarPorId(id);
    }

    public List<ProductoFisico> listarProductosFisicos() throws IOException {
        return productoFisicoArchivo.obtenerTodos();
    }

    public List<ProductoFisico> buscarProductosFisicosPorSku(String sku) throws IOException {
        return productoFisicoArchivo.buscarPorSku(sku);
    }

    public List<ProductoFisico> buscarProductosFisicosPorEstado(String estado) throws IOException {
        return productoFisicoArchivo.buscarPorEstado(estado);
    }

    public ProductoFisico actualizarProductoFisico(UUID id, String nuevoEstado, String nuevoNumeroSerie, String nuevaUbicacion) throws IOException {
        Optional<ProductoFisico> productoOpt = productoFisicoArchivo.buscarPorId(id);
        if (productoOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe producto físico con ID: " + id);
        }

        ProductoFisico producto = productoOpt.get();
        if (nuevoEstado != null) producto.setEstadoProductoFisico(nuevoEstado);
        if (nuevoNumeroSerie != null) producto.setNumeroSerieProductoFisico(nuevoNumeroSerie);
        if (nuevaUbicacion != null) producto.setUbicacionProductoFisico(nuevaUbicacion);

        productoFisicoArchivo.actualizarProductoFisico(producto);
        return producto;
    }

    public boolean actualizarUbicacionProducto(UUID id, String nuevaUbicacion) throws IOException {
        return productoFisicoArchivo.actualizarUbicacion(id, nuevaUbicacion);
    }

    public boolean existeProductoFisico(UUID id) throws IOException {
        return productoFisicoArchivo.buscarPorId(id).isPresent();
    }
}