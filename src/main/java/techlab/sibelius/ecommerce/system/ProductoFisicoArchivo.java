package techlab.sibelius.ecommerce.system;

import techlab.sibelius.ecommerce.domain.ProductoFisico;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductoFisicoArchivo {
    // Atributos
    private static final String ARCHIVO = "productos_fisicos.json";
    private final ObjectMapper objectMapper;

    // Constructor
    public ProductoFisicoArchivo() {
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    // Metodos
    public void guardarProductoFisico(ProductoFisico producto) throws IOException {
        List<ProductoFisico> productos = obtenerTodos();
        productos.add(producto);
        guardarTodos(productos);
    }

    public Optional<ProductoFisico> buscarPorId(UUID id) throws IOException {
        return obtenerTodos().stream()
                .filter(p -> p.getIdProductoFisico().equals(id))
                .findFirst();
    }

    public List<ProductoFisico> obtenerTodos() throws IOException {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists() || archivo.length() == 0) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(archivo, new TypeReference<>() {});
    }

    public void actualizarProductoFisico(ProductoFisico producto) throws IOException {
        List<ProductoFisico> productos = obtenerTodos();
        productos.removeIf(p -> p.getIdProductoFisico().equals(producto.getIdProductoFisico()));
        productos.add(producto);
        guardarTodos(productos);
    }

    public List<ProductoFisico> buscarPorSku(String sku) throws IOException {
        return obtenerTodos().stream()
                .filter(p -> p.getSkuProductoFisico().equals(sku))
                .collect(Collectors.toList());
    }

    public boolean actualizarUbicacion(UUID id, String nuevaUbicacion) throws IOException {
        Optional<ProductoFisico> productoOpt = buscarPorId(id);
        if (productoOpt.isPresent()) {
            ProductoFisico producto = productoOpt.get();
            producto.setUbicacionProductoFisico(nuevaUbicacion);
            actualizarProductoFisico(producto);
            return true;
        }
        return false;
    }

    public List<ProductoFisico> buscarPorEstado(String estado) throws IOException {
        return obtenerTodos().stream()
                .filter(p -> p.getEstadoProductoFisico().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
    }

    private void guardarTodos(List<ProductoFisico> productos) throws IOException {
        objectMapper.writeValue(new File(ARCHIVO), productos);
    }
}
