package com.ejemplo.biblioteca.service.ServiceImpl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.biblioteca.service.GenericService;

public abstract class GenericServiceImpl<T, ID> implements GenericService<T, ID> {

    protected final JpaRepository<T, ID> repository;

    public GenericServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidad no encontrado con id: " + id));
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(ID id, T entity) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No existe la entidad con ID: " + id);
        }
        return repository.save(entity);
    }
}
