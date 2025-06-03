package com.odontologia.app.config;

import com.odontologia.app.model.Rol;
import com.odontologia.app.repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CargaInicial {

    @Bean
    CommandLineRunner cargarRoles(RolRepository rolRepository) {
        return args -> {
            if (rolRepository.count() == 0) {
                rolRepository.save(new Rol(null, "Administrador"));
                rolRepository.save(new Rol(null, "Odontólogo"));
                rolRepository.save(new Rol(null, "Asistente"));
                rolRepository.save(new Rol(null, "Recepcionista"));
                
            }
        };
    }
}
