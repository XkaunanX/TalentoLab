package techlab.sibelius.ecommerce.system;

import techlab.sibelius.ecommerce.domain.Cliente;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ClienteController {
    private final ClienteArchivo clienteArchivo;

    // Constructor -> Relacion de dependencia
    public ClienteController() {
        this.clienteArchivo = new ClienteArchivo();
    }

    // Metodos
    public void crearCliente(String id_cliente, String nombre, String email,
                             String direccion, String telefono) throws IOException {
        Cliente nuevo_cliente = new Cliente(nombre, email, direccion, telefono);
        clienteArchivo.guardarCliente(nuevo_cliente);
    }

    public Optional<Cliente> buscarCliente(String idCliente) throws IOException {
        return clienteArchivo.buscarCliente(idCliente);
    }

    // Metodo para obtener todos los clientes
    public List<Cliente> obtenerTodosClientes() throws IOException {
        return clienteArchivo.obtenerTodosLosClientes();
    }

    // Metodo para actualizar un cliente existente
    public void actualizarCliente(Cliente cliente) throws IOException {
        clienteArchivo.actualizarCliente(cliente);
    }
}