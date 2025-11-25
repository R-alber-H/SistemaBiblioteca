package com.ejemplo.biblioteca.service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.ejemplo.biblioteca.entity.DetallePrestamo;
import com.ejemplo.biblioteca.repository.DetallePrestamoRepository;

@Service
public class DetallePrestamoServiceImpl extends GenericServiceImpl<DetallePrestamo,Long>{

    public DetallePrestamoServiceImpl(DetallePrestamoRepository detallePrestamoRepository) {
        super(detallePrestamoRepository);
    }
    
}
