package com.api.examen.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.examen.entity.Barco;
import com.api.examen.repository.BarcoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BarcoService {

    @Autowired
    private BarcoRepository barcoRepository;

    // Agrega un nuevo barco
    public Barco agregar(Barco barco) {
        return barcoRepository.save(barco);
    }

    // Lista todos los barcos
    public List<Barco> listar() {
        return barcoRepository.findAll();
    }

    // Consulta un barco por Id
    public Optional<Barco> consultaPorId(int id) {
        return barcoRepository.findById(id);
    }

    // Elimina un barco por Id
    public void eliminar(int id) {
        barcoRepository.deleteById(id);
    }

    // Actualiza un barco por Id
    public Barco actualizar(int id, Barco barco) {

        // Pregunto si el barco existe primero
        Barco barcoExistente = barcoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barco no encontrado con ID: " + id));

        // Actualizo todos los atributos del barco
        barcoExistente.setNombre(barco.getNombre());
        barcoExistente.setOrigen(barco.getOrigen());
        barcoExistente.setDestino(barco.getDestino());
        barcoExistente.setCapacidad(barco.getCapacidad());

        return barcoRepository.save(barcoExistente);
    }

}
