package com.springBajo8.springBajo8.repository;

import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.domain.pacienteDTOReactiva;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PacientesReactivosRepository extends ReactiveMongoRepository<pacienteDTOReactiva, String> {
    Flux<pacienteDTOReactiva> findByidPaciente(String idPaciente);
}
