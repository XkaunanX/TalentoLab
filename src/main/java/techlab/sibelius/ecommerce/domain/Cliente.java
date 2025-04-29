package techlab.sibelius.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.UUID;

public class Cliente {
    private UUID idCliente;
    private String nombreCliente;
    private String emailCliente;
    private String direccionCliente;
    private String telefonoCliente;
    private LocalDate fechaRegistro;

    // Constructor para Jackson
    @JsonCreator
    public Cliente(
            @JsonProperty("idCliente") UUID idCliente,
            @JsonProperty("nombreCliente") String nombreCliente,
            @JsonProperty("emailCliente") String emailCliente,
            @JsonProperty("direccionCliente") String direccionCliente,
            @JsonProperty("telefonoCliente") String telefonoCliente,
            @JsonProperty("fechaRegistro") LocalDate fechaRegistro) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.emailCliente = emailCliente;
        this.direccionCliente = direccionCliente;
        this.telefonoCliente = telefonoCliente;
        this.fechaRegistro = fechaRegistro != null ? fechaRegistro : LocalDate.now();
    }

    // Constructor alternativo sin fecha (asigna fecha actual)
    public Cliente( String nombreCliente, String emailCliente,
                   String direccionCliente, String telefonoCliente) {
        this(UUID.randomUUID(), nombreCliente, emailCliente, direccionCliente, telefonoCliente, LocalDate.now());
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
