package com.springBajo8.springBajo8.service;

//import com.yoandypv.reactivestack.messages.domain.Message;
import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.domain.pacienteDTOReactiva;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface IcitasReactivaService {
    Mono<citasDTOReactiva> save(citasDTOReactiva citasDTOReactiva);

    Flux<citasDTOReactiva> findAll();

    Mono<citasDTOReactiva> findById(String id);

    Mono<pacienteDTOReactiva> savePaciente (pacienteDTOReactiva pacienteSave);

    Mono<citasDTOReactiva> delete(String id);

    Mono<citasDTOReactiva> update(String id, citasDTOReactiva citasDTOReactiva);


    //Cancelar cita
    Mono<citasDTOReactiva> updateCancelarCita(String id, citasDTOReactiva citasDTOReactiva);

    Flux<citasDTOReactiva> findByIdPaciente(String idPaciente);

    //Obtener todos los pacientes
    Flux<pacienteDTOReactiva> findAllPacientes();

    //Obtener padecimientos con pacientes
    Flux<pacienteDTOReactiva> findPadecimientoWithPaciente(String idPaciente);

    //Consultar cita por fecha y hora
    Flux<citasDTOReactiva> findAppointmentByHourAndDate(String fechaReservaCita);

    //Obtener medico
    Mono<citasDTOReactiva> findDoctor(String nombreMedico);







/*
    Flux<citasDTOReactiva> findAppointmentByHourAndDate(String fechaReservaCita, String horaReservaCita);




    Flux<pacienteDTOReactiva> findByIdPacienteTto(String idPaciente);*/


}
