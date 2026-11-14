package com.tecsup.services;

import com.tecsup.model.Cliente;
import com.tecsup.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public List<Cliente> listar() {
        return repo.findAll();
    }

    public Cliente guardar(Cliente c) {
        return repo.save(c);
    }

    public Cliente obtener(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Cliente actualizar(Long id, Cliente c) {
        Cliente actual = obtener(id);
        if (actual == null) {
            return null;
        }
        actual.setNombre(c.getNombre());
        actual.setApellidos(c.getApellidos());
        actual.setDireccion(c.getDireccion());
        actual.setEstado(c.getEstado());
        actual.setNroDni(c.getNroDni());
        return repo.save(actual);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
