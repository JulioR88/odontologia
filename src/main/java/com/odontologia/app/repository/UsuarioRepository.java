package com.odontologia.app.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.odontologia.app.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

