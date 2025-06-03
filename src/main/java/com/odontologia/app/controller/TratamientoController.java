package com.odontologia.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.odontologia.app.model.Tratamiento;
import com.odontologia.app.repository.TratamientoRepository;
import java.util.List;

@Controller
@RequestMapping("/tratamiento")
public class TratamientoController {
    
    private final TratamientoRepository tratamientoRepo;

    public TratamientoController(TratamientoRepository tratamientoRepo) {
        this.tratamientoRepo = tratamientoRepo;
    }

    @GetMapping
    public String listar(Model model) {
        List<Tratamiento> tratamientos = tratamientoRepo.findAll();
        model.addAttribute("tratamientos", tratamientos);
        return "tratamiento/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("tratamiento", new Tratamiento());
        return "tratamiento/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Tratamiento tratamiento, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            return "tratamiento/form";
        }
        
        tratamientoRepo.save(tratamiento);
        return "redirect:/tratamiento";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Tratamiento tratamiento = tratamientoRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("tratamiento", tratamiento);
        return "tratamiento/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        tratamientoRepo.deleteById(id);
        return "redirect:/tratamiento";
    }
}