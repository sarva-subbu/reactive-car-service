package com.sarva.springbootcarapptdd.reactive;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveCarController {

	private ReactiveCarRepository reactiveCarRepository;

	public ReactiveCarController(ReactiveCarRepository reactiveCarRepository) {
		this.reactiveCarRepository = reactiveCarRepository;
	}

	@GetMapping("/reactive-cars/{make}")
	public Mono<ReactiveCar> getReactiveCars(@PathVariable String make) {
		return reactiveCarRepository.findByMake(make);
	}

	@PostMapping("/reactive-cars")
	public Mono<ReactiveCar> addReactiveCar(@RequestBody ReactiveCar reactiveCar) {
		return reactiveCarRepository.save(reactiveCar);
	}
	
	
	@GetMapping("/reactive-cars")
	public Flux<ReactiveCar> getAllReactiveCars() {
		return reactiveCarRepository.findAll();
	}

}
