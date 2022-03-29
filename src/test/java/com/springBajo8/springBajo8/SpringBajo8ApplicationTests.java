package com.springBajo8.springBajo8;

import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.domain.pacienteDTOReactiva;
import com.springBajo8.springBajo8.service.impl.citasReactivaServiceImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class SpringBajo8ApplicationTests {

	@Autowired
	citasReactivaServiceImpl citasReactivaServiceImpl;
	private static final Logger log = LoggerFactory.getLogger(SpringBajo8Application.class);

	@Test
	void contextLoads() {
	}


	@Test
	void testMono() {
		pacienteDTOReactiva paciente = new pacienteDTOReactiva();
		List<String> padecimientos = new ArrayList<>();
		padecimientos.add("Vomito");
		List<String> tratamientos = new ArrayList<>();
		tratamientos.add("Pastillas");


		paciente.setApellidosPaciente("Pava");
		paciente.setNombrePaciente("Veronica");
		paciente.setIdPaciente("1");
		paciente.setPadecimientos(padecimientos);
		paciente.setTratamientos(tratamientos);

		Mono<pacienteDTOReactiva> testMono = citasReactivaServiceImpl.savePaciente(paciente);
		StepVerifier.create(testMono).expectNext(paciente).verifyComplete();
	}

	@Test
	void testMono2() {
		citasDTOReactiva citaMedica = new citasDTOReactiva();
		citaMedica.setApellidosMedico("Aldana");
		citaMedica.setEstadoReservaCita("en espera");
		citaMedica.setNombreMedico("camilo");
		citaMedica.setFechaReservaCita("22-05-28");
		citaMedica.setHoraReservaCita("5:00");

		Mono<citasDTOReactiva> testoMono2 = citasReactivaServiceImpl.save(citaMedica);
		StepVerifier.create(testoMono2).expectNext(citaMedica).verifyComplete();
	}
	@Test
	void testFlux() {
		Flux<citasDTOReactiva> testFlux = (Flux<citasDTOReactiva>) citasReactivaServiceImpl.findAll();
		StepVerifier.create(testFlux).expectComplete();
	}

	@Test
	void testFlux2() {
		Flux<citasDTOReactiva> testFlux2 = citasReactivaServiceImpl.findByIdPaciente("3");
		StepVerifier.create(testFlux2).expectComplete();
	}
}