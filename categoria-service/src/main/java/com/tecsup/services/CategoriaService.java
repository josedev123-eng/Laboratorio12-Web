package com.tecsup.services;

import com.tecsup.model.Categoria;
import com.tecsup.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public List<Categoria> listar() {
        return repo.findAll();
    }

    public Categoria guardar(Categoria c) {
        return repo.save(c);
    }

    public Categoria obtener(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Categoria actualizar(Long id, Categoria c) {
        Categoria actual = obtener(id);
        if (actual == null) {
            return null;
        }
        actual.setNombre(c.getNombre());
        actual.setEstado(c.getEstado());
        return repo.save(actual);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
