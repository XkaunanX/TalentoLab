package techlab.sibelius.ecommerce.system;

import techlab.sibelius.ecommerce.domain.Pedido;
import techlab.sibelius.ecommerce.domain.ProductoFisico;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PedidoController {
    // Atributos
    private final PedidoArchivo pedidoArchivo;

    // Constructor
    public PedidoController() {
        this.pedidoArchivo = new PedidoArchivo();
    }

    // Metodos
    public Pedido crearPedido(String cliente) throws IOException {
        Pedido nuevoPedido = new Pedido(cliente);
        pedidoArchivo.guardarPedido(nuevoPedido);
        return nuevoPedido;
    }

    public Optional<Pedido> buscarPedido(UUID id) throws IOException {
        return pedidoArchivo.buscarPorId(id);
    }

    public List<Pedido> obtenerTodosLosPedidos() throws IOException {
        return pedidoArchivo.obtenerTodos();
    }

    public void actualizarPedido(Pedido pedidoActualizado) throws IOException {
        pedidoArchivo.actualizarPedido(pedidoActualizado);
    }

    public boolean eliminarPedido(UUID id) throws IOException {
        Optional<Pedido> pedidoOpt = pedidoArchivo.buscarPorId(id);
        if (pedidoOpt.isPresent()) {
            List<Pedido> pedidos = pedidoArchivo.obtenerTodos();
            pedidos.removeIf(p -> p.getIdPedido().equals(id));
            // Reemplazamos toda la lista sin el pedido eliminado
            // Esto es menos eficiente pero necesario ya que guardarTodos es privado
            pedidos.forEach(p -> {
                try {
                    pedidoArchivo.actualizarPedido(p);
                } catch (IOException e) {
                    throw new RuntimeException("Error al actualizar pedidos", e);
                }
            });
            return true;
        }
        return false;
    }

    public void actualizarEstado(UUID id, String estado) throws IOException {
        Optional<Pedido> pedidoOpt = pedidoArchivo.buscarPorId(id);
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.setEstadoPedido(estado);
            pedidoArchivo.actualizarPedido(pedido);
        }
    }

    public List<Pedido> obtenerPedidosPorCliente(String clienteId) throws IOException {
        return pedidoArchivo.buscarPorCliente(clienteId);
    }

    public double calcularTotal(UUID id) throws IOException {
        return pedidoArchivo.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"))
                .calcularTotal();
    }

    public void agregarProducto(UUID pedidoId, ProductoFisico producto) throws IOException {
        Optional<Pedido> pedidoOpt = pedidoArchivo.buscarPorId(pedidoId);
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.agregarProducto(producto);
            pedidoArchivo.actualizarPedido(pedido);
        }
    }

    public void removerProducto(UUID pedidoId, ProductoFisico producto) throws IOException {
        Optional<Pedido> pedidoOpt = pedidoArchivo.buscarPorId(pedidoId);
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.removerProducto(producto);
            pedidoArchivo.actualizarPedido(pedido);
        }
    }
}