package com.odontologia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.odontologia.app.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
}

