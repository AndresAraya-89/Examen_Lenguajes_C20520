package com.api.examen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.api.examen.entity.Contenedor;
import com.api.examen.service.ContenedorService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/contenedor")
@AllArgsConstructor
public class ContenedorController {

    @Autowired
    private final ContenedorService contenedorService;

    // Listar todos los contenedores
    @GetMapping
    public String listarContenedores(Model model) {
        model.addAttribute("contenedores", contenedorService.listar());
        model.addAttribute("nuevoContenedor", new Contenedor());
        return "contenedor";
    }

    // Listar contenedores por ID de barco
    @GetMapping("/barco/{idBarco}")
    public String listarContenedoresPorBarco(@PathVariable Integer idBarco, Model model) {
        model.addAttribute("contenedores", contenedorService.listarContenedor_IdBarco(idBarco));
        return "contenedor";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        Contenedor contenedor = contenedorService.consultaPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de contenedor inválido: " + id));
        model.addAttribute("contenedor", contenedor);
        return "editarContenedor";
    }

    // Procesar actualización
    @PostMapping("/actualizar")
    public String actualizarContenedor(@ModelAttribute Contenedor contenedor) {
        contenedorService.actualizar(contenedor.getId_contenedor(), contenedor);
        return "redirect:/contenedor";
    }

    // Mostrar formulario de creación
    @GetMapping("/agregar")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("nuevoContenedor", new Contenedor());
        return "agregar-contenedor";
    }

    // Procesar creación
    @PostMapping("/agregar")
    public String crearContenedor(@ModelAttribute("nuevoContenedor") Contenedor contenedor) {
        contenedorService.agregar(contenedor);
        return "redirect:/contenedor";
    }

    // Eliminar contenedor
    @GetMapping("/eliminar/{id}")
    public String eliminarContenedor(@PathVariable Integer id) {
        contenedorService.eliminar(id);
        return "redirect:/contenedor";
    }

    // Consultar contenedor por ID
    @GetMapping("/{id}")
    public String consultarContenedorPorId(@PathVariable Integer id, Model model) {
        Contenedor contenedor = contenedorService.consultaPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("Contenedor no encontrado con ID: " + id));
        model.addAttribute("contenedor", contenedor);
        return "detalle-contenedor";
    }
}