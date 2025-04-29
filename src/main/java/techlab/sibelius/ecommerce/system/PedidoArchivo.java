package techlab.sibelius.ecommerce.system;

import techlab.sibelius.ecommerce.domain.Pedido;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PedidoArchivo {
    // Atributos
    private static final String ARCHIVO = "pedidos.json";
    private final ObjectMapper mapper;

    // Constructor
    public PedidoArchivo() {
        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    // Metodos
    public void guardarPedido(Pedido pedido) throws IOException {
        List<Pedido> pedidos = obtenerTodos();
        pedidos.add(pedido);
        guardarTodos(pedidos);
    }

    public Optional<Pedido> buscarPorId(UUID id) throws IOException {
        return obtenerTodos().stream()
                .filter(p -> p.getIdPedido().equals(id))
                .findFirst();
    }

    public List<Pedido> obtenerTodos() throws IOException {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists() || archivo.length() == 0) {
            return new ArrayList<>();
        }
        return mapper.readValue(archivo, new TypeReference<List<Pedido>>() {});
    }

    public void actualizarPedido(Pedido pedido) throws IOException {
        List<Pedido> pedidos = obtenerTodos();
        pedidos.removeIf(p -> p.getIdPedido().equals(pedido.getIdPedido()));
        pedidos.add(pedido);
        guardarTodos(pedidos);
    }

    public List<Pedido> buscarPorCliente(String clienteId) throws IOException {
        return obtenerTodos().stream()
                .filter(p -> p.getIdClientePedido().toString().equals(clienteId))
                .toList();
    }

    public List<Pedido> buscarPorEstado(String estado) throws IOException {
        return obtenerTodos().stream()
                .filter(p -> p.getEstadoPedido().equalsIgnoreCase(estado))
                .toList();
    }

    private void guardarTodos(List<Pedido> pedidos) throws IOException {
        mapper.writeValue(new File(ARCHIVO), pedidos);
    }
}