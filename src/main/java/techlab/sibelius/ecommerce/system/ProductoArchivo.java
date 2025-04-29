package techlab.sibelius.ecommerce.system;

import techlab.sibelius.ecommerce.domain.Producto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoArchivo {
    // Atributos
    private static final String ARCHIVO_PRODUCTOS = "productos.json";
    private final ObjectMapper objectMapper;

    // Constructor
    public ProductoArchivo() {
        this.objectMapper = new ObjectMapper();
        // Configuración de Jackson
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    // Metodos
    public void guardarProducto(Producto producto) throws IOException {
        List<Producto> productos = obtenerTodosLosProductos();
        productos.add(producto);
        guardarListaProductos(productos);
    }

    public Optional<Producto> buscarProducto(String sku) throws IOException {
        return obtenerTodosLosProductos().stream()
                .filter(p -> p.getSkuProducto().equals(sku))
                .findFirst();
    }

    public List<Producto> obtenerTodosLosProductos() throws IOException {
        File archivo = new File(ARCHIVO_PRODUCTOS);

        if (!archivo.exists() || archivo.length() == 0) {
            return new ArrayList<>();
        }

        return objectMapper.readValue(archivo, new TypeReference<List<Producto>>() {});
    }

    public void actualizarProducto(Producto productoActualizado) throws IOException {
        List<Producto> productos = obtenerTodosLosProductos();
        productos.removeIf(p -> p.getSkuProducto().equals(productoActualizado.getSkuProducto()));
        productos.add(productoActualizado);
        guardarListaProductos(productos);
    }

    public boolean eliminarProducto(String sku) throws IOException {
        List<Producto> productos = obtenerTodosLosProductos();
        boolean eliminado = productos.removeIf(p -> p.getSkuProducto().equals(sku));
        if (eliminado) {
            guardarListaProductos(productos);
        }
        return eliminado;
    }

    private void guardarListaProductos(List<Producto> productos) throws IOException {
        objectMapper.writeValue(new File(ARCHIVO_PRODUCTOS), productos);
    }
}