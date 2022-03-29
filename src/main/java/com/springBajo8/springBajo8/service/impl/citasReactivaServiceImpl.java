package com.springBajo8.springBajo8.service.impl;

import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.domain.pacienteDTOReactiva;
import com.springBajo8.springBajo8.repository.IcitasReactivaRepository;
import com.springBajo8.springBajo8.repository.PacientesReactivosRepository;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class citasReactivaServiceImpl implements IcitasReactivaService {

    @Autowired
    private IcitasReactivaRepository IcitasReactivaRepository;

    @Autowired
    private PacientesReactivosRepository pacientesReactivosRepository;

    @Override
    public Mono<citasDTOReactiva> save(citasDTOReactiva citasDTOReactiva) {
        return this.IcitasReactivaRepository.save(citasDTOReactiva);
    }

    @Override
    public Mono<pacienteDTOReactiva> savePaciente(pacienteDTOReactiva paciente) {
        return this.pacientesReactivosRepository.save(paciente);
    }


    @Override
    public Mono<citasDTOReactiva> delete(String id) {
        return this.IcitasReactivaRepository
                .findById(id)
                .flatMap(p -> this.IcitasReactivaRepository.deleteById(p.getId()).thenReturn(p));
    }

    @Override
    public Mono<citasDTOReactiva> update(String id, citasDTOReactiva citasDTOReactiva) {
        return this.IcitasReactivaRepository.findById(id)
                .flatMap(citasDTOReactiva1 -> {
                    citasDTOReactiva.setId(id);
                    return save(citasDTOReactiva);
                })
                .switchIfEmpty(Mono.empty());
    }

    //Cancelar cita
    @Override
    public Mono<citasDTOReactiva> updateCancelarCita(String id, citasDTOReactiva citasDTOReactiva) {
        return this.IcitasReactivaRepository.findById(id)
                .flatMap(citasDTOReactiva1 -> {
                    citasDTOReactiva.setId(id);
                    citasDTOReactiva.setEstadoReservaCita("Cancelar");
                    return save(citasDTOReactiva);
                })
                .switchIfEmpty(Mono.empty());
    }



    @Override
    public Flux<citasDTOReactiva> findByIdPaciente(String idPaciente) {
        return this.IcitasReactivaRepository.findByIdPaciente(idPaciente);
    }


    //Obtener todos los pacientes
    @Override
    public Flux<pacienteDTOReactiva> findAllPacientes() {
        return this.pacientesReactivosRepository.findAll();
    }

    //Obtener padecimientos con pacientes
    @Override
    public Flux<pacienteDTOReactiva> findPadecimientoWithPaciente(String idPaciente) {
        return this.pacientesReactivosRepository.findByidPaciente(idPaciente);
    }

    @Override
    public Flux<citasDTOReactiva> findAll() {
        return this.IcitasReactivaRepository.findAll();
    }

    @Override
    public Mono<citasDTOReactiva> findById(String id) {
        return this.IcitasReactivaRepository.findById(id);
    }

    //Consultar cita por fecha y hora
    @Override
    public Flux<citasDTOReactiva> findAppointmentByHourAndDate(String fechaReservaCita){
        return this.IcitasReactivaRepository.findByFechaReservaCita(fechaReservaCita);
    }

    //Obtener medico por nombre
    @Override
    public Mono<citasDTOReactiva> findDoctor(String nombreMedico) {
        return this.IcitasReactivaRepository.findByNombreMedico(nombreMedico);
    }

}
