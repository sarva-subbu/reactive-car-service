package com.sarva.springbootcarapptdd.reactive;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ReactiveCarTest {

	@Test
	public void shouldReactiveCarExist() {
		ReactiveCar reactiveCar = new ReactiveCar("toyota", "prius", 2018);
		assertThat(reactiveCar.getMake()).isEqualTo("toyota");
		assertThat(reactiveCar.getModel()).isEqualTo("prius");
		assertThat(reactiveCar.getYear()).isEqualTo(2018);
	}
	
}
