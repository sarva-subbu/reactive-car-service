package com.sarva.springbootcarapptdd.reactive;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest
public class ReactiveCarControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private ReactiveCarRepository reactiveCarRepository;

	@Test
	public void shouldReturnCarByMake() {
		given(reactiveCarRepository.findByMake(anyString()))
				.willReturn(Mono.just(new ReactiveCar("toyota", "prius", 2018)));

		webTestClient.get().uri("/reactive-cars/{make}", "prius").exchange().expectStatus().isOk();
	}

}
