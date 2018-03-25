package com.sarva.springbootcarapptdd.reactive;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ReactiveCarRepositoryTest {

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
	public void shouldFindCarByMake() {
		StepVerifier.create(reactiveCarRepository.findByMake("toyota")).consumeNextWith(reactiveCar -> {
			assertThat(reactiveCar).isNotNull();
			assertThat(reactiveCar.getMake()).isEqualTo("toyota");
		}).verifyComplete();
	}

}
