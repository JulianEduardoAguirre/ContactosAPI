/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agenda.backend.controlador;

import com.agenda.backend.excepciones.ResourceNotFoundException;
import com.agenda.backend.modelo.Contacto;
import com.agenda.backend.repositorio.ContactoRepositorio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author aguir
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactoControlador {
    
    @Autowired
    private ContactoRepositorio contactoRepositorio;
    
    //Obtener todos los contactos
    @GetMapping("/contactos")
    public List<Contacto> listarTodos(){
        return contactoRepositorio.findAll();
    }
    
    //Crear un contacto y guardarlo en la base de datos
    @PostMapping("/contactos")
    public Contacto crearContacto(@RequestBody Contacto contacto){
        return contactoRepositorio.save(contacto);
    }
    
    //Buscar un contacto por id
    @GetMapping("/contactos/{id}")
    public ResponseEntity<Contacto> buscarPorId(@PathVariable("id") String id, Model modelo){
        
        Contacto contacto = contactoRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contacto no encontrado"));
        
        return ResponseEntity.ok(contacto);
    }
    
    //Actualizar un contacto
    @PutMapping("/contactos/{id}")
    public ResponseEntity<Contacto> actualizarContacto(@PathVariable("id") String id, @RequestBody Contacto contactoDatos){
  
            Contacto contacto = contactoRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contacto no encontrado"));
        
            contacto.setNombre(contactoDatos.getNombre());
            contacto.setApellido(contactoDatos.getApellido());
            contacto.setEmail(contactoDatos.getEmail());
            
            Contacto contactoActualizado = contactoRepositorio.save(contacto);
            return ResponseEntity.ok(contactoActualizado);
    }
    
    //Eliminar un contacto
    @DeleteMapping("/contactos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable String id){

           Contacto contacto = contactoRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contacto no encontrado"));
 
           contactoRepositorio.delete(contacto);
           Map<String, Boolean> respuesta = new HashMap();
           respuesta.put("Borrado", Boolean.TRUE);
           return ResponseEntity.ok(respuesta);
    }
    
    
    
    
    
}
