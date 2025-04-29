package techlab.sibelius.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido {
    private final UUID idPedido;
    private final UUID idClientePedido;
    private final LocalDate fechaAltaPedido;
    private final List<ProductoFisico> productos;
    private String estadoPedido;

    @JsonCreator
    public Pedido(
            @JsonProperty("idPedido") UUID idPedido,
            @JsonProperty("idClientePedido") UUID idClientePedido,
            @JsonProperty("fechaAltaPedido") LocalDate fechaAltaPedido,
            @JsonProperty("productos") List<ProductoFisico> productos,
            @JsonProperty("estadoPedido") String estadoPedido) {
        this.idPedido = idPedido != null ? idPedido : UUID.randomUUID();
        this.idClientePedido = idClientePedido;
        this.fechaAltaPedido = fechaAltaPedido != null ? fechaAltaPedido : LocalDate.now();
        this.productos = productos != null ? new ArrayList<>(productos) : new ArrayList<>();
        this.estadoPedido = estadoPedido != null ? estadoPedido : "Pendiente";
    }

    // Constructor -> La id_pedido se genera automaticamente, el estado del pedido Por defecto es "Pendiente"
    public Pedido(String idClientePedido) {
        this(UUID.randomUUID(), UUID.fromString(idClientePedido), LocalDate.now(), null, "Pendiente");
    }

    // Agrego producto como objeto a la lista List<ProductoFisico> productos
    public void agregarProducto(ProductoFisico producto) {
        if (producto == null) throw new IllegalArgumentException("Producto no puede ser nulo");
        productos.add(producto);
    }

    // Remuevo producto de la lista
    public void removerProducto(ProductoFisico producto) {
        productos.remove(producto);
    }

    // Calculo el total de pedido -> Por esto guardo a los productos como objetos
    public double calcularTotal() {
        return productos.stream()
                .mapToDouble(ProductoFisico::getPrecioProductoFisico)
                .sum();
    }

    // Getters
    public UUID getIdPedido() { return idPedido; }
    public UUID getIdClientePedido() { return idClientePedido; }
    public LocalDate getFechaAltaPedido() { return fechaAltaPedido; }
    public List<ProductoFisico> getProductos() { return new ArrayList<>(productos); }
    public String getEstadoPedido() { return estadoPedido; }

    // Setters
    public void setEstadoPedido(String estadoPedido) { this.estadoPedido = estadoPedido; }

    @Override
    public String toString() {
        return String.format("Pedido #%s [Cliente: %s | Fecha: %s | Productos: %d | Total: $%.2f | Estado: %s]",
                idPedido.toString().substring(0, 8),
                idClientePedido.toString().substring(0, 8),
                fechaAltaPedido,
                productos.size(),
                calcularTotal(),
                estadoPedido);
    }
}
