package com.sarva.springbootcarapptdd.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface ReactiveCarRepository extends ReactiveMongoRepository<ReactiveCar, String> {

	Mono<ReactiveCar> findByMake(String make);
	
}
