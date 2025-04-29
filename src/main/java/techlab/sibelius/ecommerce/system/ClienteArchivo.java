package techlab.sibelius.ecommerce.system;

import techlab.sibelius.ecommerce.domain.Cliente;
import com.fasterxml.jackson.core.type.TypeReference; // Deserializar JSON a tipos genericos
import com.fasterxml.jackson.databind.ObjectMapper; // Nucleo de la libreria
import com.fasterxml.jackson.databind.SerializationFeature; // Configuraciones para el ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule; // Enseña a Jackson a serializar/deserializar fechas de java.time.*
import java.io.File; // Archivos y directorios
import java.io.IOException; // Manejo de excepciones
import java.util.ArrayList; // Estructuras de coleccion de Java.
import java.util.List;
import java.util.Optional; // Contenedor de NULL
import java.util.UUID;

public class ClienteArchivo {
    // Nombre del archivo
    private static final String ARCHIVO_CLIENTES = "clientes.json";
    // Convierte una lista de objetos Cliente en un archivo JSON (serializacion).
    // Lee ese archivo JSON y lo convierte de nuevo en una lista de objetos Cliente (deserializacion).
    private final ObjectMapper mapper;

    // Constructor
    public ClienteArchivo() {
        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    // Metodos
    public void guardarCliente(Cliente cliente) throws IOException {
        List<Cliente> clientes = obtenerTodosLosClientes();
        clientes.add(cliente);
        guardarTodosClientes(clientes);
    }

    public Optional<Cliente> buscarCliente(String idCliente) throws IOException {
        UUID id = UUID.fromString(idCliente);
        return obtenerTodosLosClientes().stream()
                .filter(c -> c.getIdCliente().equals(id))
                .findFirst();
    }

    public List<Cliente> obtenerTodosLosClientes() throws IOException {
        File archivo = new File(ARCHIVO_CLIENTES);
        if (!archivo.exists() || archivo.length() == 0) {
            return new ArrayList<>();
        }
        try {
            return mapper.readValue(archivo, new TypeReference<List<Cliente>>() {});
        } catch (IOException e) {
            // Si hay error de lectura, retornar lista vacia
            System.err.println("Error al leer archivo, se retornará lista vacía: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void actualizarCliente(Cliente clienteActualizado) throws IOException {
        List<Cliente> clientes = obtenerTodosLosClientes();
        clientes.removeIf(c -> c.getIdCliente().equals(clienteActualizado.getIdCliente()));
        clientes.add(clienteActualizado);
        guardarTodosClientes(clientes);
    }

    private void guardarTodosClientes(List<Cliente> clientes) throws IOException {
        mapper.writeValue(new File(ARCHIVO_CLIENTES), clientes);
    }
}
