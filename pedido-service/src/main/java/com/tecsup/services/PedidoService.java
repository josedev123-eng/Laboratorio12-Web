package com.tecsup.services;

import com.tecsup.client.ClienteClient;
import com.tecsup.client.ProductoClient;
import com.tecsup.dto.ClienteDTO;
import com.tecsup.dto.ProductoDTO;
import com.tecsup.model.Pedido;
import com.tecsup.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private ClienteClient clienteClient;

    @Autowired
    private ProductoClient productoClient;

    public List<Pedido> listar() {
        List<Pedido> pedidos = repo.findAll();
        for (Pedido p : pedidos) {
            completarDatos(p);
        }
        return pedidos;
    }

    public Pedido guardar(Pedido p) {
        // primero se valida que el cliente y el producto existan en sus servicios
        ClienteDTO cliente = clienteClient.obtenerCliente(p.getClienteId());
        ProductoDTO producto = productoClient.obtenerProducto(p.getProductoId());

        p.setEstado("PENDIENTE");
        p.setFecha(LocalDate.now());
        Pedido pedido = repo.save(p);

        pedido.setCliente(cliente);
        pedido.setProducto(producto);
        return pedido;
    }

    public Pedido obtener(Long id) {
        // 1. buscar el pedido en su propia base de datos
        Pedido pedido = repo.findById(id).orElse(null);
        if (pedido != null) {
            // 2 y 3. pedir cliente y producto por Feign
            completarDatos(pedido);
        }
        return pedido;
    }

    // no se elimina el pedido, solo se cambia el estado
    public Pedido cambiarEstado(Long id, String estado) {
        Pedido pedido = repo.findById(id).orElse(null);
        if (pedido == null) {
            return null;
        }
        pedido.setEstado(estado);
        repo.save(pedido);
        completarDatos(pedido);
        return pedido;
    }

    private void completarDatos(Pedido pedido) {
        pedido.setCliente(clienteClient.obtenerCliente(pedido.getClienteId()));
        pedido.setProducto(productoClient.obtenerProducto(pedido.getProductoId()));
    }
}
