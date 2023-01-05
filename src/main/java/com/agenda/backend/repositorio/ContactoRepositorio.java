/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agenda.backend.repositorio;

import com.agenda.backend.modelo.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aguir
 */
@Repository
public interface ContactoRepositorio extends JpaRepository<Contacto, String>{
    
}
