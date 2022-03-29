package com.springBajo8.springBajo8.web;


import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.domain.pacienteDTOReactiva;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class citasReactivaResource {

    @Autowired
    private IcitasReactivaService icitasReactivaService;

    @PostMapping("/citasReactivas")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<citasDTOReactiva> save(@RequestBody citasDTOReactiva citasDTOReactiva) {
        return this.icitasReactivaService.save(citasDTOReactiva);
    }

    //Post paciente

    @PostMapping("/crearPaciente")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<pacienteDTOReactiva> savePaciente(@RequestBody pacienteDTOReactiva paciente) {
        return this.icitasReactivaService.savePaciente(paciente);
    }

    @DeleteMapping("/citasReactivas/{id}")
    private Mono<ResponseEntity<citasDTOReactiva>> delete(@PathVariable("id") String id) {
        return this.icitasReactivaService.delete(id)
                .flatMap(citasDTOReactiva -> Mono.just(ResponseEntity.ok(citasDTOReactiva)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @PutMapping("/citasReactivas/{id}")
    private Mono<ResponseEntity<citasDTOReactiva>> update(@PathVariable("id") String id, @RequestBody citasDTOReactiva citasDTOReactiva) {
        return this.icitasReactivaService.update(id, citasDTOReactiva)
                .flatMap(citasDTOReactiva1 -> Mono.just(ResponseEntity.ok(citasDTOReactiva1)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    //Cancelar una cita
    @PutMapping("/citasReactivas/cancelar/{id}")
    private Mono<ResponseEntity<citasDTOReactiva>> updateEstadoCita(@PathVariable("id") String id, @RequestBody citasDTOReactiva citasDTOReactiva) {
        return this.icitasReactivaService.update(id, citasDTOReactiva)
                .flatMap(citasDTOReactiva1 -> Mono.just(ResponseEntity.ok(citasDTOReactiva1)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }


    @GetMapping("/citasReactivas/{idPaciente}/byidPaciente")
    private Flux<citasDTOReactiva> findAllByidPaciente(@PathVariable("idPaciente") String idPaciente) {
        return this.icitasReactivaService.findByIdPaciente(idPaciente);
    }

    //Obtener todos los pacientes
    @GetMapping("/pacientes")
    private Flux<pacienteDTOReactiva> findAllPaciente() {
        return this.icitasReactivaService.findAllPacientes();
    }

    //obtener padecimientos con pacientes
    @GetMapping("/pacientes/{idPaciente}")
    private Flux<pacienteDTOReactiva> findPadecimiento(@PathVariable("idPaciente") String idPaciente) {
        return this.icitasReactivaService.findPadecimientoWithPaciente(idPaciente);
    }

    @GetMapping(value = "/citasReactivas")
    private Flux<citasDTOReactiva> findAll() {
        return this.icitasReactivaService.findAll();
    }

    //Obtener cita por fecha y hora
    @GetMapping(value = "/citasReactivas/fecha/{fecha}")
    private Flux<citasDTOReactiva> findAppointmentByHourAndDate(@PathVariable("fecha") String fechaCita) {
        return this.icitasReactivaService.findAppointmentByHourAndDate(fechaCita);
    }

    //Obtener medico
    @GetMapping(value = "/citasReactivas/doctor/{nombre}")
    private Mono<citasDTOReactiva> findDoctor(@PathVariable("nombre") String nombreMedico ) {
        return this.icitasReactivaService.findDoctor(nombreMedico);
    }


}
