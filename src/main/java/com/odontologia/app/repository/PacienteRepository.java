package com.odontologia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.odontologia.app.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
