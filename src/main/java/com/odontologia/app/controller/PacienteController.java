package com.odontologia.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.odontologia.app.model.Paciente;
import com.odontologia.app.repository.PacienteRepository;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteRepository.findAll());
        return "paciente/index";
    }

    @GetMapping("/nuevo")
    public String nuevoPaciente(Model model) {
        model.addAttribute("paciente", new Paciente()); // Esto asegura que el modelo tiene un objeto paciente
        return "paciente/form";
    }



    @PostMapping("/guardar")
    public String guardarPaciente(@ModelAttribute Paciente pasiente) {
        pacienteRepository.save(pasiente);
        return "redirect:/paciente";
    }


    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable("id") Long id, Model model) {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        model.addAttribute("paciente", paciente);
        return "paciente/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable("id") Long id) {
        pacienteRepository.deleteById(id);
        return "redirect:/paciente";
    }
}

