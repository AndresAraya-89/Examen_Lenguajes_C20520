package com.api.examen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.examen.entity.Barco;
import com.api.examen.service.BarcoService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/barco")
@AllArgsConstructor
public class BarcoController {

    @Autowired
    private final BarcoService barcoService;

    // Listar todos los barcos
    @GetMapping
    public String listarBarcos(Model model) {
        model.addAttribute("barcos", barcoService.listar());
        model.addAttribute("nuevoBarco", new Barco()); // Para el formulario de creación
        return "barco";
    }

    // Editar un barco
    @GetMapping("/editar/{id}")
    public String editarBarco(@PathVariable Integer id, Model model) {
        Barco barco = barcoService.consultaPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de barco inválido: " + id));
        model.addAttribute("barco", barco);
        return "editarBarco";
    }

    // Crear barco
    @PostMapping("/actualizar")
    public String actualizarBarco(@ModelAttribute Barco barco) {
        barcoService.actualizar(barco.getId_barco(), barco);
        return "actualizarBarco";
    }

    // Agregar barco
    // Debes agregar este método para manejar GET /barco/agregar
    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("nuevoBarco", new Barco());
        return "agregarBarco";
    }

    // El POST queda igual
    @PostMapping("/agregar")
    public String crearBarco(@ModelAttribute Barco barco) {
        barcoService.agregar(barco);
        return "redirect:/barco"; // Mejor redireccionar a la lista
    }

    // Eliminar barco
    @GetMapping("/eliminar/{id}")
    public String eliminarBarco(@PathVariable Integer id) {
        barcoService.eliminar(id);
        return "redirect:/barco";
    }

    // Consultar por id del barco
    @GetMapping("/{id}")
    public String consultaPorId(@PathVariable Integer id, Model model) {
        Barco barco = barcoService.consultaPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Barco no encontrado con ID: " + id));
        model.addAttribute("barco", barco);
        return "barcoConsultar";
    }

}
