package com.sarva.springbootcarapptdd;

import java.time.Duration;
import java.util.stream.Stream;

import org.springframework.web.reactive.function.client.WebClient;

import cern.jet.random.Normal;
import cern.jet.random.engine.MersenneTwister64;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Signal;
import reactor.core.scheduler.Schedulers;

public class LoadTesterApplication {

	private static final Normal RANDOM_DISTRIBUTION = new Normal(4, 6, new MersenneTwister64(0));

	private static final Duration ONE_SECOND = Duration.ofSeconds(1);

	private final WebClient webClient;

	public LoadTesterApplication() {
		this.webClient = WebClient.create("http://localhost:8081/restaurants");
	}

	public static void main(String args[]) {
		new LoadTesterApplication().run();
	}

	private void run() {
		Flux
		.fromStream(randomDistributionStream())
		.delayElements(ONE_SECOND)
		.parallel()
		.runOn(Schedulers.parallel())
		.doOnEach(this::sendRequests)
		.subscribe();
		
		Flux.never().blockLast();
	}

	private void sendRequests(Signal<Integer> number) {
		System.out.println("sending " + number.get() + " requests...");
		for (int i = 0; i < number.get(); i++) {
			webClient.get().exchange().block().bodyToMono(String.class).block();
		}
	}

	private Stream<Integer> randomDistributionStream() {
		int nextVal = (int) RANDOM_DISTRIBUTION.nextDouble();
		System.out.println("nextVal " + nextVal);
		return Stream.iterate(0, n -> nextVal);
	}

}
