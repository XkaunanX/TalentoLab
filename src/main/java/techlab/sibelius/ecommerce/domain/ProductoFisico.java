package techlab.sibelius.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class ProductoFisico {
    private final UUID idProductoFisico;
    private final Producto tipoProductoFisico;
    private String estadoProductoFisico;
    private String numeroSerieProductoFisico;
    private String ubicacionProductoFisico;

    @JsonCreator
    public ProductoFisico(
            @JsonProperty("tipoProductoFisico") Producto tipoProductoFisico,
            @JsonProperty("estado") String estado,
            @JsonProperty("numeroSerie") String numeroSerie,
            @JsonProperty("ubicacion") String ubicacion) {
        if (tipoProductoFisico == null) throw new IllegalArgumentException("Producto no puede ser nulo");
        this.idProductoFisico = UUID.randomUUID();
        this.tipoProductoFisico = tipoProductoFisico;
        this.estadoProductoFisico = estado != null ? estado : "Disponible";
        this.numeroSerieProductoFisico = numeroSerie;
        this.ubicacionProductoFisico = ubicacion != null ? ubicacion : "Almacén Principal";
    }

    // Constructor simplificado -> Ejemplo
    public ProductoFisico(Producto producto) {
        this(producto, "Disponible", null, "Almacén Principal");
    }

    public ProductoFisico(Producto tipoProductoFisico, String ubicacionProductoFisico) {
        this.idProductoFisico = UUID.randomUUID();
        this.tipoProductoFisico = tipoProductoFisico;
        this.estadoProductoFisico = estadoProductoFisico != null ? estadoProductoFisico : "Disponible";
        this.numeroSerieProductoFisico = UUID.randomUUID().toString();
        this.ubicacionProductoFisico = ubicacionProductoFisico;
    }

    // Getters
    public UUID getIdProductoFisico() { return idProductoFisico; }
    public Producto getTipoProductoFisico() { return tipoProductoFisico; }
    public String getEstadoProductoFisico() { return estadoProductoFisico; }
    public String getNumeroSerieProductoFisico() { return numeroSerieProductoFisico; }
    public String getUbicacionProductoFisico() { return ubicacionProductoFisico; }

    // Setters
    public void setEstadoProductoFisico(String estadoProductoFisico) {
        if (estadoProductoFisico != null && !estadoProductoFisico.isEmpty()) {
            this.estadoProductoFisico = estadoProductoFisico;
        }
    }

    public void setNumeroSerieProductoFisico(String numeroSerie) {
        this.numeroSerieProductoFisico = numeroSerie;
    }

    public void setUbicacionProductoFisico(String ubicacionProductoFisico) {
        if (ubicacionProductoFisico != null && !ubicacionProductoFisico.isEmpty()) {
            this.ubicacionProductoFisico = ubicacionProductoFisico;
        }
    }

    // Métodos delegados
    public String getSkuProductoFisico() { return tipoProductoFisico.getSkuProducto(); }
    public String getNombreProductoFisico() { return tipoProductoFisico.getNombreProducto(); }
    public String getDescripcionProductoFisico() { return tipoProductoFisico.getDescripcionProducto(); }
    public double getPrecioProductoFisico() { return tipoProductoFisico.getPrecioProducto(); }

    @Override
    public String toString() {
        return String.format("%s [ID: %s | Serie: %s | Estado: %s | Ubicación: %s]",
                tipoProductoFisico.getNombreProducto(),
                idProductoFisico.toString().substring(0, 8),
                numeroSerieProductoFisico != null ? numeroSerieProductoFisico : "N/A",
                estadoProductoFisico,
                ubicacionProductoFisico);
    }
}
