package com.odontologia.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.odontologia.app.model.Usuario;
import com.odontologia.app.repository.RolRepository;
import com.odontologia.app.repository.UsuarioRepository;

import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;
    
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuario/index";
    }
    
    @GetMapping("/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolRepository.findAll());
        return "usuario/form";
    }
    
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario, 
                                RedirectAttributes redirectAttributes) {
        try {
            if (usuario.getRol() != null && usuario.getRol().getId() != null) {
                Optional<com.odontologia.app.model.Rol> rol = rolRepository.findById(usuario.getRol().getId());
                if (rol.isPresent()) {
                    usuario.setRol(rol.get());
                } else {
                    redirectAttributes.addFlashAttribute("error", "Rol no encontrado");
                    return "redirect:/usuario/nuevo";
                }
            }
            usuarioRepository.save(usuario);
            redirectAttributes.addFlashAttribute("success", "Usuario guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el usuario: " + e.getMessage());
            return "redirect:/usuario/nuevo";
        }
        return "redirect:/usuario";
    }
    
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model, 
                               RedirectAttributes redirectAttributes) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
            return "redirect:/usuario";
        }
        
        model.addAttribute("usuario", usuarioOpt.get());
        model.addAttribute("roles", rolRepository.findAll());
        return "usuario/form";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id, 
                                 RedirectAttributes redirectAttributes) {
        try {
            if (usuarioRepository.existsById(id)) {
                usuarioRepository.deleteById(id);
                redirectAttributes.addFlashAttribute("success", "Usuario eliminado exitosamente");
            } else {
                redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el usuario: " + e.getMessage());
        }
        return "redirect:/usuario";
    }
}