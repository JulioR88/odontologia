package com.odontologia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.odontologia.app.model.Citas;

public interface CitasRepository extends JpaRepository<Citas, Long> {
}
