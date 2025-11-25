package com.ejemplo.biblioteca.service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.ejemplo.biblioteca.entity.Rol;
import com.ejemplo.biblioteca.repository.RolRepository;

@Service
public class RolServiceImpl extends GenericServiceImpl<Rol,Long>{

    public RolServiceImpl(RolRepository rolRepository) {
        super(rolRepository);
    }
    
}
