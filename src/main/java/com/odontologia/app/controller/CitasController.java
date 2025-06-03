package com.odontologia.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.odontologia.app.model.Citas;
import com.odontologia.app.model.Paciente;
import com.odontologia.app.model.Tratamiento;
import com.odontologia.app.repository.CitasRepository;
import com.odontologia.app.repository.PacienteRepository;
import com.odontologia.app.repository.TratamientoRepository;

@Controller
@RequestMapping("/cita")
public class CitasController {
    
    @Autowired
    private CitasRepository citaRepository;
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private TratamientoRepository tratamientoRepository;
    
    @GetMapping
    public String listarCitas(Model model) {
        // ❌ PROBLEMA 1: Faltaba agregar las citas al modelo
        model.addAttribute("citas", citaRepository.findAll());
        return "cita/index";
    }
    
    @GetMapping("/nuevo")
    public String nuevaCita(Model model) {
        model.addAttribute("cita", new Citas());
        model.addAttribute("pacientes", pacienteRepository.findAll());
        model.addAttribute("tratamientos", tratamientoRepository.findAll());
        return "cita/form";
    }
    
    @PostMapping("/guardar")
    public String guardarCita(@ModelAttribute Citas cita, 
                             @RequestParam("tratamiento") Long tratamientoId) {
        
      
        
        // Buscar el tratamiento por ID
        Tratamiento tratamiento = tratamientoRepository.findById(tratamientoId)
            .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado"));
        
        cita.setTratamiento(tratamiento);
        
        // Guardar la cita
        citaRepository.save(cita);
        return "redirect:/cita";
    }
    
    @GetMapping("/editar/{id}")
    public String editarCita(@PathVariable Long id, Model model) {
        model.addAttribute("cita", citaRepository.findById(id).orElse(null));
        model.addAttribute("pacientes", pacienteRepository.findAll());
        model.addAttribute("tratamientos", tratamientoRepository.findAll());
        return "cita/form";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable Long id) {
        citaRepository.deleteById(id);
        return "redirect:/cita";
    }
}