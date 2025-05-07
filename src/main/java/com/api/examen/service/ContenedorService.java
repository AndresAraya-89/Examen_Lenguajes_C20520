package com.api.examen.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.examen.entity.Barco;
import com.api.examen.entity.Contenedor;
import com.api.examen.repository.BarcoRepository;
import com.api.examen.repository.ContenedorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContenedorService {

    @Autowired
    private BarcoRepository barcoRepository;
    @Autowired
    private ContenedorRepository contenedorRepository;

    // Agrega un nuevo contenedor
    public Contenedor agregar(Contenedor contenedor) {

            if (contenedor.getBarco().getCapacidad() > 0) {
                return contenedorRepository.save(contenedor);
            }
             return contenedorRepository.save(null);
    }

    // Lista todos los contenedores
    public List<Contenedor> listar() {
        return contenedorRepository.findAll();
    }

    // Lista todos los contenedores con el mismo id del barco
    public List<Contenedor> listarContenedor_IdBarco(int idBarco) {
        List<Contenedor> listaGeneral = contenedorRepository.findAll();
        List<Contenedor> listaSalida = new ArrayList<>();

        for (int i = 0; i < listaGeneral.size(); i++) {

            if (listaGeneral.get(i).getBarco().getId_barco() == idBarco) {
                listaSalida.add(listaGeneral.get(i));
            }
        }

        return contenedorRepository.findAll();
    }

    // Consulta un contenedor por Id
    public Optional<Contenedor> consultaPorId(int id) {
        return contenedorRepository.findById(id);
    }

    // Elimina un contenedor por Id
    public void eliminar(int id) {
        contenedorRepository.deleteById(id);
    }

    // Actualiza un contenedor por Id
    public Contenedor actualizar(int id, Contenedor contenedor) {

        // Pregunto si el contenedor existe primero
        Contenedor contenedorExistente = contenedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contenedor no encontrado con ID: " + id));

        // Actualizar relación con barco, para veridicar que exista
        // Cuando el id del barco es 1, el contenedor esta en sala de espera...
        if (contenedor.getBarco() != null && contenedor.getBarco().getId_barco() != 0) {
            Barco barco = barcoRepository.findById(contenedor.getBarco().getId_barco())
                    .orElseThrow(() -> new RuntimeException(
                            "Barco no encontrado con ID: " + contenedor.getBarco().getId_barco()));
            contenedorExistente.setBarco(barco);
        }

        // Actualizo todos los atributos del contenedor
        contenedorExistente.setVin(contenedor.getVin());
        contenedorExistente.setFecha(obtenerFecha());

        return contenedorRepository.save(contenedorExistente);
    }

    public String obtenerFecha() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }

}
