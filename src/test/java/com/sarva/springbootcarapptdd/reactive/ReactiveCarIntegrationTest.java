package com.sarva.springbootcarapptdd.reactive;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ReactiveCarIntegrationTest {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private ReactiveCarRepository reactiveCarRepository;

	@Before
	public void setUp() {
		reactiveCarRepository.deleteAll().then().block();
		reactiveCarRepository.save(new ReactiveCar("toyota", "prius", 2018)).then().block();
	}

	@After
	public void tearDown() {
		reactiveCarRepository.deleteAll().then().block();
	}

	@Test
	public void shouldReturnReactiveCar() {
		ReactiveCar reactiveCar = webTestClient.get().uri("/reactive-cars/{make}", "toyota").exchange().expectStatus()
				.isOk().expectBody(ReactiveCar.class).returnResult().getResponseBody();
		assertThat(reactiveCar).isNotNull();
	}

}
