package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.RecursoNoEncontradoException;
import com.example.demo.model.Actividad;
import com.example.demo.repository.ActividadRepository;

@Service
public class ActividadService {
    @Autowired
    private ActividadRepository repositorio;

    public List<Actividad> listar() { return repositorio.findAll(); }

  public Actividad buscar(Long id) {
    return repositorio.findById(id).orElseThrow(() ->
      new RecursoNoEncontradoException("Actividad no encontrada: " + id));
  }

  public Actividad guardar(Actividad actividad) {
    return repositorio.save(actividad);
  }

  public Actividad actualizar(Long id, Actividad datos) {
    Actividad actual = buscar(id);
    actual.setNombre(datos.getNombre());
    actual.setDescripcion(datos.getDescripcion());
    actual.setPrecio(datos.getPrecio());
    actual.setCupos(datos.getCupos());
    actual.setCategoria(datos.getCategoria());
    return repositorio.save(actual);
  }

  public void eliminar(Long id) {
    repositorio.delete(buscar(id));
  }
}