package com.odontologia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.odontologia.app.model.Tratamiento;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
}

