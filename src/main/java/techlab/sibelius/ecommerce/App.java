package techlab.sibelius.ecommerce;

import techlab.sibelius.ecommerce.domain.Cliente;
import techlab.sibelius.ecommerce.system.ClienteController;
import techlab.sibelius.ecommerce.domain.Producto;
import techlab.sibelius.ecommerce.system.ProductoController;
import techlab.sibelius.ecommerce.domain.Pedido;
import techlab.sibelius.ecommerce.system.PedidoController;
import techlab.sibelius.ecommerce.domain.ProductoFisico;
import techlab.sibelius.ecommerce.system.ProductoFisicoController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class App {
    // Atributos
    private static final Scanner scanner = new Scanner(System.in);
    private static final ClienteController clienteController = new ClienteController();
    private static final ProductoController productoController = new ProductoController();
    private static final PedidoController pedidoController = new PedidoController();
    private static final ProductoFisicoController productoFisicoController = new ProductoFisicoController();

    // MAIN
    public static void main(String[] args) {
        mostrarMenuPrincipal();
        System.exit(0);
    }

    // Metodos
    private static void mostrarMenuPrincipal() { // private static void -> Devuelve void
        boolean ejecucion = true;
        while (ejecucion) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Menu clientes");
            System.out.println("2. Menu productos");
            System.out.println("3. Menu pedidos");
            System.out.println("4. Menu productos fisicos");
            System.out.println("\n");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    mostrarMenuClientes();
                    break;
                case 2:
                    mostrarMenuProductos();
                    break;
                case 3:
                    mostrarMenuPedidos();
                    break;
                case 4:
                    mostrarMenuProductosFisicos();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    ejecucion = false;
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void mostrarMenuClientes(){
        System.out.println("\n=== Menu clientes ===");
        System.out.println("1. Crear nuevo cliente");
        System.out.println("2. Buscar cliente por ID");
        System.out.println("3. Listar todos los clientes");
        System.out.println("4. Actualizar cliente");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();

        switch (opcion) {
            case 1:
                crearCliente();
                break;
            case 2:
                buscarCliente();
                break;
            case 3:
                listarClientes();
                break;
            case 4:
                actualizarCliente();
            case 0:
                System.out.println("Saliendo del menu clientes...");
                return;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }

    private static void mostrarMenuProductos(){
        System.out.println("\n=== Menu productos ===");
        System.out.println("1. Crear nuevo producto");
        System.out.println("2. Buscar producto por SKU");
        System.out.println("3. Listar todos los productos");
        System.out.println("4. Actualizar producto");
        System.out.println("5. Actualizar stock");
        System.out.println("6. Consultar precio");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();

        switch (opcion) {
            case 1:
                crearProducto();
                break;
            case 2:
                buscarProducto();
                break;
            case 3:
                listarProductos();
                break;
            case 4:
                actualizarProducto();
            case 5:
                actualizarStock();
            case 6:
                consultarPrecio();
            case 0:
                System.out.println("Saliendo del menu productos...");
                return;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }

    private static void mostrarMenuPedidos(){
        System.out.println("\n=== Menu pedidos ===");
        System.out.println("1. Crear nuevo pedido");
        System.out.println("2. Buscar pedido por ID");
        System.out.println("3. Listar todos los pedidos");
        System.out.println("4. Actualizar pedido");
        System.out.println("5. Eliminar pedido");
        System.out.println("6. Actualizar Estado");
        System.out.println("7. Obtener pedidos de cliente por ID");
        System.out.println("8. Calcular total del pedido");
        System.out.println("9. Agregar producto al pedido");
        System.out.println("10. Remover producto del pedido");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();

        switch (opcion) {
            case 1:
                crearPedido();
                break;
            case 2:
                buscarPedido();
                break;
            case 3:
                listarPedidos();
                break;
            case 4:
                actualizarPedido();
                break;
            case 5:
                eliminarPedido();
                break;
            case 6:
                actualizarEstadoPedido();
                break;
            case 7:
                obtenerPedidosDeCliente();
                break;
            case 8:
                calcularTotalPedido();
                break;
            case 9:
                agregarProductoAPedido();
                break;
            case 10:
                removerProductoDePedido();
                break;
            case 0:
                System.out.println("Saliendo del menu productos...");
                return;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }

    private  static void mostrarMenuProductosFisicos(){
        System.out.println("\n=== Menu productos fisicos ===");
        System.out.println("1. Crear nuevo producto fisico");
        System.out.println("2. Buscar producto fisico por ID");
        System.out.println("3. Listar todos los productos fisicos");
        System.out.println("4. Buscar producto fisico por SKU");
        System.out.println("5. Buscar productos fisicos por estado");
        System.out.println("6. Actualizar producto fisico");
        System.out.println("7. Actualizar ubicacion de producto fisico");
        System.out.println("0. Salir"); // buscarProductosFisicosPorEstado
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();

        switch (opcion) {
            case 1:
                crearProductoFisico();
                break;
            case 2:
                buscarProductoFisico();
                break;
            case 3:
                listarProductosFisicos();
                break;
            case 4:
                buscarProductosFisicosPorSku();
            case 5:
                buscarProductosFisicosPorEstado();
                break;
            case 6:
                actualizarProductoFisico();
                break;
            case 7:
                actualizarUbicacionProductoFisico();
            case 0:
                System.out.println("Saliendo del menu clientes...");
                return;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }

    private static void crearCliente() {
        System.out.println("\n--- NUEVO CLIENTE ---");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        try {
            clienteController.crearCliente(nombre, direccion,email, direccion, telefono);
            System.out.println("Cliente creado exitosamente!");
        } catch (IOException e) {
            System.err.println("Error al guardar el cliete: " + e.getMessage());
        }
    }

    private static void buscarCliente() {
        System.out.println("\n--- BUSCAR CLIENTE ---");
        System.out.print("Ingrese ID del cliente: ");
        String id = scanner.nextLine();


        try {
            Optional<Cliente> cliente = clienteController.buscarCliente(id);
            if (cliente.isPresent()) {
                mostrarDetallesCliente(cliente.get());
            } else {
                System.out.println("Cliente no encontrado.");
            }
        } catch (IOException e) {
            System.err.println("Error al buscar cliente: " + e.getMessage());
        }
    }

    private static void listarClientes() {
        System.out.println("\n--- LISTADO DE CLIENTES ---");
        try {
            List<Cliente> clientes = clienteController.obtenerTodosClientes();
            if (clientes.isEmpty()) {
                System.out.println("No hay clientes registrados.");
            } else {
                clientes.forEach(App::mostrarResumenCliente);
            }
        } catch (IOException e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
        }
    }

    private static void actualizarCliente() {
        System.out.println("\n--- ACTUALIZAR CLIENTE ---");
        System.out.print("Ingrese ID del cliente a actualizar: ");
        String id = scanner.nextLine();

        try {
            Optional<Cliente> clienteExistente = clienteController.buscarCliente(id);
            if (clienteExistente.isEmpty()) {
                System.out.println("Cliente no encontrado.");
                return;
            }

            Cliente cliente = clienteExistente.get();
            mostrarDetallesCliente(cliente);

            System.out.println("\nIngrese nuevos datos (deje en blanco para mantener el valor actual):");

            System.out.print("Nombre (" + cliente.getNombreCliente() + "): ");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) cliente.setNombreCliente(nombre);

            System.out.print("Email (" + cliente.getEmailCliente() + "): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) cliente.setEmailCliente(email);

            System.out.print("Dirección (" + cliente.getDireccionCliente() + "): ");
            String direccion = scanner.nextLine();
            if (!direccion.isEmpty()) cliente.setDireccionCliente(direccion);

            System.out.print("Teléfono (" + cliente.getTelefonoCliente() + "): ");
            String telefono = scanner.nextLine();
            if (!telefono.isEmpty()) cliente.setTelefonoCliente(telefono);

            clienteController.actualizarCliente(cliente);
            System.out.println("Cliente actualizado exitosamente!");
        } catch (IOException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    private static void mostrarResumenCliente(Cliente cliente) {
        System.out.printf("[%s] %s - %s%n",
                cliente.getIdCliente(),
                cliente.getNombreCliente(),
                cliente.getEmailCliente());
    }

    private static void mostrarDetallesCliente(Cliente cliente) {
        System.out.println("\nDetalles del cliente:");
        System.out.println("ID: " + cliente.getIdCliente());
        System.out.println("Nombre: " + cliente.getNombreCliente());
        System.out.println("Email: " + cliente.getEmailCliente());
        System.out.println("Dirección: " + cliente.getDireccionCliente());
        System.out.println("Teléfono: " + cliente.getTelefonoCliente());
        System.out.println("Fecha Registro: " + cliente.getFechaRegistro());
    }

    private static void crearProducto() {
        System.out.println("\n--- NUEVO PRODUCTO ---");

        System.out.print("SKU: ");
        String sku = scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Descripción (opcional): ");
        String descripcion = scanner.nextLine();

        try {
            Producto producto = productoController.crearProducto(sku, nombre, precio);
            if (!descripcion.isEmpty()) {
                producto.setDescripcionProducto(descripcion);
                productoController.actualizarProducto(producto);
            }
            System.out.println("Producto creado exitosamente!");
        } catch (IOException e) {
            System.err.println("Error al guardar el producto: " + e.getMessage());
        }
    }

    private static void buscarProducto() {
        System.out.println("\n--- BUSCAR PRODUCTO ---");
        System.out.print("Ingrese SKU del producto: ");
        String sku = scanner.nextLine();

        try {
            Optional<Producto> producto = productoController.buscarProducto(sku);
            if (producto.isPresent()) {
                mostrarDetallesProducto(producto.get());
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (IOException e) {
            System.err.println("Error al buscar producto: " + e.getMessage());
        }
    }

    private static void listarProductos() {
        System.out.println("\n--- LISTADO DE PRODUCTOS ---");
        try {
            List<Producto> productos = productoController.obtenerTodosLosProductos();
            if (productos.isEmpty()) {
                System.out.println("No hay productos registrados.");
            } else {
                productos.forEach(App::mostrarResumenProducto);
            }
        } catch (IOException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }
    }

    private static void actualizarProducto() {
        System.out.println("\n--- ACTUALIZAR PRODUCTO ---");
        System.out.print("Ingrese SKU del producto a actualizar: ");
        String sku = scanner.nextLine();

        try {
            Optional<Producto> productoExistente = productoController.buscarProducto(sku);
            if (productoExistente.isEmpty()) {
                System.out.println("Producto no encontrado.");
                return;
            }

            Producto producto = productoExistente.get();
            mostrarDetallesProducto(producto);

            System.out.println("\nIngrese nuevos datos (deje en blanco para mantener el valor actual):");

            System.out.print("Nombre (" + producto.getNombreProducto() + "): ");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) producto.setNombreProducto(nombre);

            System.out.print("Precio (" + producto.getPrecioProducto() + "): ");
            String precioInput = scanner.nextLine();
            if (!precioInput.isEmpty()) {
                producto.setPrecioProducto(Double.parseDouble(precioInput));
            }

            System.out.print("Descripción (" + producto.getDescripcionProducto() + "): ");
            String descripcion = scanner.nextLine();
            if (!descripcion.isEmpty()) producto.setDescripcionProducto(descripcion);

            productoController.actualizarProducto(producto);
            System.out.println("Producto actualizado exitosamente!");
        } catch (IOException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: El precio debe ser un número válido.");
        }
    }

    private static void actualizarStock() {
        System.out.println("\n--- ACTUALIZAR STOCK ---");
        System.out.print("Ingrese SKU del producto: ");
        String sku = scanner.nextLine();

        System.out.print("Nuevo stock: ");
        int stock = leerEntero();

        try {
            productoController.actualizarStock(sku, stock);
            System.out.println("Stock actualizado exitosamente!");
        } catch (IOException e) {
            System.err.println("Error al actualizar stock: " + e.getMessage());
        }
    }

    private static void consultarPrecio() {
        System.out.println("\n--- CONSULTAR PRECIO ---");
        System.out.print("Ingrese SKU del producto: ");
        String sku = scanner.nextLine();

        try {
            double precio = productoController.obtenerPrecio(sku);
            System.out.println("Precio del producto: " + precio);
        } catch (IOException e) {
            System.err.println("Error al consultar precio: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void mostrarResumenProducto(Producto producto) {
        System.out.printf("[%s] %s - $%.2f | Stock: %d%n",
                producto.getSkuProducto(),
                producto.getNombreProducto(),
                producto.getPrecioProducto(),
                producto.getStockProducto());
    }

    private static void mostrarDetallesProducto(Producto producto) {
        System.out.println("\nDetalles del producto:");
        System.out.println("SKU: " + producto.getSkuProducto());
        System.out.println("Nombre: " + producto.getNombreProducto());
        System.out.println("Descripción: " + producto.getDescripcionProducto());
        System.out.println("Precio: $" + producto.getPrecioProducto());
        System.out.println("Stock: " + producto.getStockProducto());
    }

    private static void crearPedido() {
        System.out.println("\n--- CREAR NUEVO PEDIDO ---");
        System.out.print("Ingrese ID del cliente: ");
        String idCliente = scanner.nextLine();

        try {
            pedidoController.crearPedido(idCliente);
            System.out.println("Pedido creado exitosamente!");
        } catch (IOException e) {
            System.err.println("Error al crear pedido: " + e.getMessage());
        }
    }

    private static void buscarPedido() {
        System.out.println("\n--- BUSCAR PEDIDO ---");
        System.out.print("Ingrese ID del pedido: ");
        String idPedido = scanner.nextLine();

        try {
            Optional<Pedido> pedido = pedidoController.buscarPedido(UUID.fromString(idPedido));
            if (pedido.isPresent()) {
                mostrarDetallesPedido(pedido.get());
            } else {
                System.out.println("Pedido no encontrado.");
            }
        } catch (IOException e) {
            System.err.println("Error al buscar pedido: " + e.getMessage());
        }
    }

    private static void listarPedidos() {
        System.out.println("\n--- LISTADO DE PEDIDOS ---");
        try {
            List<Pedido> pedidos = pedidoController.obtenerTodosLosPedidos();
            if (pedidos.isEmpty()) {
                System.out.println("No hay pedidos registrados.");
            } else {
                pedidos.forEach(App::mostrarResumenPedido);
            }
        } catch (IOException e) {
            System.err.println("Error al obtener pedidos: " + e.getMessage());
        }
    }

    private static void actualizarPedido() {
        System.out.println("\n--- ACTUALIZAR PEDIDO ---");
        System.out.print("Ingrese ID del pedido a actualizar: ");
        String idPedido = scanner.nextLine();

        // Depende de qué campos permitas actualizar. Por ahora dejémoslo simple.
        System.out.println("Actualizar pedido aún no implementado en detalle.");
    }

    private static void eliminarPedido() {
        System.out.println("\n--- ELIMINAR PEDIDO ---");
        System.out.print("Ingrese ID del pedido a eliminar: ");
        String idPedido = scanner.nextLine();

        try {
            pedidoController.eliminarPedido(UUID.fromString(idPedido));
            System.out.println("Pedido eliminado exitosamente!");
        } catch (IOException e) {
            System.err.println("Error al eliminar pedido: " + e.getMessage());
        }
    }

    private static void actualizarEstadoPedido() {
        System.out.println("\n--- ACTUALIZAR ESTADO DEL PEDIDO ---");
        System.out.print("Ingrese ID del pedido: ");
        String idPedido = scanner.nextLine();

        System.out.print("Ingrese nuevo estado: ");
        String nuevoEstado = scanner.nextLine();

        try {
            pedidoController.actualizarEstado(UUID.fromString(idPedido), nuevoEstado);
            System.out.println("Estado actualizado exitosamente!");
        } catch (IOException e) {
            System.err.println("Error al actualizar estado del pedido: " + e.getMessage());
        }
    }

    private static void obtenerPedidosDeCliente() {
        System.out.println("\n--- OBTENER PEDIDOS POR CLIENTE ---");
        System.out.print("Ingrese ID del cliente: ");
        String idCliente = scanner.nextLine();

        try {
            List<Pedido> pedidos = pedidoController.obtenerPedidosPorCliente(idCliente);
            if (pedidos.isEmpty()) {
                System.out.println("El cliente no tiene pedidos.");
            } else {
                pedidos.forEach(App::mostrarResumenPedido);
            }
        } catch (IOException e) {
            System.err.println("Error al obtener pedidos: " + e.getMessage());
        }
    }

    private static void calcularTotalPedido() {
        System.out.println("\n--- CALCULAR TOTAL DEL PEDIDO ---");
        System.out.print("Ingrese ID del pedido: ");
        String idPedido = scanner.nextLine();

        try {
            double total = pedidoController.calcularTotal(UUID.fromString(idPedido));
            System.out.println("Total del pedido: $" + total);
        } catch (IOException e) {
            System.err.println("Error al calcular total del pedido: " + e.getMessage());
        }
    }

    private static void agregarProductoAPedido() {
        System.out.println("\n--- AGREGAR PRODUCTO AL PEDIDO ---");
        System.out.print("Ingrese ID del pedido: ");
        String idPedido = scanner.nextLine();

        System.out.print("Ingrese UUID del producto fisico: ");
        String idProductoFisico = scanner.nextLine();
        try {
            ProductoFisico productoFisico = productoFisicoController.buscarProductoFisico(UUID.fromString(idProductoFisico))
                    .orElseThrow(() -> new IllegalArgumentException("Producto físico no encontrado"));
            pedidoController.agregarProducto(UUID.fromString(idPedido), productoFisico);
            System.out.println("Producto agregado exitosamente al pedido!");
        } catch (IOException e) {
            System.err.println("Error al buscar producto físico: " + e.getMessage());
        }
    }

    private static void removerProductoDePedido() {
        System.out.println("\n--- REMOVER PRODUCTO DEL PEDIDO ---");
        System.out.print("Ingrese ID del pedido: ");
        String idPedido = scanner.nextLine();

        System.out.print("Ingrese UUID del producto fisico: ");
        String idProductoFisico = scanner.nextLine();

        try {
            ProductoFisico productoFisico = productoFisicoController.buscarProductoFisico(UUID.fromString(idProductoFisico))
                    .orElseThrow(() -> new IllegalArgumentException("Producto físico no encontrado"));
            pedidoController.removerProducto(UUID.fromString(idPedido), productoFisico);
            System.out.println("Producto removido exitosamente del pedido!");
        } catch (IOException e) {
            System.err.println("Error al remover producto: " + e.getMessage());
        }
    }

    // Metodos auxiliares para mostrar pedidos
    private static void mostrarResumenPedido(Pedido pedido) {
        System.out.printf("[%s] Cliente: %s - Estado: %s%n",
                pedido.getIdPedido(),
                pedido.getIdClientePedido(),
                pedido.getEstadoPedido());
    }

    private static void mostrarDetallesPedido(Pedido pedido) {
        System.out.println("\nDetalles del pedido:");
        System.out.println("ID Pedido: " + pedido.getIdPedido());
        System.out.println("ID Cliente: " + pedido.getIdClientePedido());  // Asegúrate de acceder correctamente al ID del cliente
        System.out.println("Fecha: " + pedido.getFechaAltaPedido());
        System.out.println("Estado: " + pedido.getEstadoPedido());
        System.out.println("Productos:");

        pedido.getProductos().forEach((productoFisico) -> {
            System.out.println("UUID: " + productoFisico.getIdProductoFisico());
            System.out.println("Estado: " + productoFisico.getEstadoProductoFisico());
            System.out.println("Número de Serie: " + productoFisico.getNumeroSerieProductoFisico());
            System.out.println("SKU: " + productoFisico.getTipoProductoFisico().getSkuProducto());
            System.out.println("Nombre: " + productoFisico.getTipoProductoFisico().getNombreProducto());
            System.out.println("Descripcion: " + productoFisico.getTipoProductoFisico().getDescripcionProducto());
            System.out.println("Precio: " + productoFisico.getTipoProductoFisico().getPrecioProducto());
        });

    }

    private static void crearProductoFisico() {
        System.out.println("\n--- CREAR PRODUCTO FISICO ---");

        System.out.print("SKU del producto base: ");
        String sku = scanner.nextLine();

        System.out.print("Estado (opcional, presionar Enter para 'Disponible'): ");
        String estado = scanner.nextLine();
        if (estado.isEmpty()) estado = "Disponible";

        System.out.print("Numero de serie (opcional, puede ser vacio): ");
        String numeroSerie = scanner.nextLine();

        System.out.print("Ubicacion (opcional, presionar Enter para 'Almacen Principal'): ");
        String ubicacion = scanner.nextLine();
        if (ubicacion.isEmpty()) ubicacion = "Almacen Principal";

        try {
            ProductoFisico nuevo = productoFisicoController.crearProductoFisico(sku, estado, numeroSerie, ubicacion);
            System.out.println("Producto fisico creado con ID: " + nuevo.getIdProductoFisico());
        } catch (IOException e) {
            System.err.println("Error al guardar producto fisico: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }


    private static void buscarProductoFisico() {
        System.out.println("\n--- BUSCAR PRODUCTO FISICO ---");

        System.out.print("Ingrese el UUID del producto fisico: ");
        String id = scanner.nextLine();

        try {
            Optional<ProductoFisico> productoFisico = productoFisicoController.buscarProductoFisico(UUID.fromString(id));
            if (productoFisico.isPresent()) {
                mostrarDetallesProductoFisico(productoFisico.get());
            } else {
                System.out.println("Producto fisico no encontrado.");
            }
        } catch (IOException e) {
            System.err.println("Error al buscar producto fisico: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("UUID invalido: " + e.getMessage());
        }
    }

    private static void listarProductosFisicos() {
        System.out.println("\n--- LISTA DE PRODUCTOS FISICOS ---");

        try {
            List<ProductoFisico> productos = productoFisicoController.listarProductosFisicos();
            productos.forEach(App::mostrarDetallesProductoFisico);
        } catch (IOException e) {
            System.err.println("Error al listar productos fisicos: " + e.getMessage());
        }
    }

    private static void buscarProductosFisicosPorSku() {
        System.out.println("\n--- BUSCAR PRODUCTOS FISICOS POR SKU ---");

        System.out.print("Ingrese el SKU del producto: ");
        String sku = scanner.nextLine();

        try {
            List<ProductoFisico> productos = productoFisicoController.buscarProductosFisicosPorSku(sku);
            if (productos.isEmpty()) {
                System.out.println("No se encontraron productos fisicos con ese SKU.");
            } else {
                productos.forEach(App::mostrarDetallesProductoFisico);
            }
        } catch (IOException e) {
            System.err.println("Error al buscar productos fisicos: " + e.getMessage());
        }
    }

    private static void buscarProductosFisicosPorEstado() {
        System.out.println("\n--- BUSCAR PRODUCTOS FISICOS POR ESTADO ---");

        System.out.print("Ingrese el estado: ");
        String estado = scanner.nextLine();

        try {
            List<ProductoFisico> productos = productoFisicoController.buscarProductosFisicosPorEstado(estado);
            if (productos.isEmpty()) {
                System.out.println("No se encontraron productos fisicos con ese estado.");
            } else {
                productos.forEach(App::mostrarDetallesProductoFisico);
            }
        } catch (IOException e) {
            System.err.println("Error al buscar productos fisicos: " + e.getMessage());
        }
    }

    private static void actualizarProductoFisico() {
        System.out.println("\n--- ACTUALIZAR PRODUCTO FISICO ---");

        System.out.print("Ingrese el UUID del producto fisico: ");
        String id = scanner.nextLine();

        System.out.print("Nuevo estado (opcional): ");
        String nuevoEstado = scanner.nextLine();
        if (nuevoEstado.isEmpty()) nuevoEstado = null;

        System.out.print("Nuevo numero de serie (opcional): ");
        String nuevoNumeroSerie = scanner.nextLine();
        if (nuevoNumeroSerie.isEmpty()) nuevoNumeroSerie = null;

        System.out.print("Nueva ubicacion (opcional): ");
        String nuevaUbicacion = scanner.nextLine();
        if (nuevaUbicacion.isEmpty()) nuevaUbicacion = null;

        try {
            ProductoFisico actualizado = productoFisicoController.actualizarProductoFisico(UUID.fromString(id), nuevoEstado, nuevoNumeroSerie, nuevaUbicacion);
            System.out.println("Producto fisico actualizado:");
            mostrarDetallesProductoFisico(actualizado);
        } catch (IOException e) {
            System.err.println("Error al actualizar producto fisico: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void actualizarUbicacionProductoFisico() {
        System.out.println("\n--- ACTUALIZAR UBICACION DE PRODUCTO FISICO ---");

        System.out.print("Ingrese el UUID del producto fisico: ");
        String id = scanner.nextLine();

        System.out.print("Nueva ubicacion: ");
        String nuevaUbicacion = scanner.nextLine();

        try {
            boolean actualizado = productoFisicoController.actualizarUbicacionProducto(UUID.fromString(id), nuevaUbicacion);
            if (actualizado) {
                System.out.println("Ubicacion actualizada exitosamente.");
            } else {
                System.out.println("No se pudo actualizar la ubicacion (producto no encontrado).");
            }
        } catch (IOException e) {
            System.err.println("Error al actualizar ubicacion: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("UUID invalido: " + e.getMessage());
        }
    }

    private static void mostrarDetallesProductoFisico(ProductoFisico pf) {
        System.out.println("\nUUID: " + pf.getIdProductoFisico());
        System.out.println("Producto: " + pf.getTipoProductoFisico().getNombreProducto() + " (SKU: " + pf.getTipoProductoFisico().getSkuProducto() + ")");
        System.out.println("Estado: " + pf.getEstadoProductoFisico());
        System.out.println("Numero de Serie: " + pf.getNumeroSerieProductoFisico());
        System.out.println("Ubicacion: " + pf.getUbicacionProductoFisico());
    }


    private static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada no válida. Ingrese un número: ");
            }
        }
    }
}