package com.nttdata.bootcamp.customerdomain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.customerdomain.model.CustomerEnterprise;

import reactor.core.publisher.Mono;

public interface CustomerEnterpriseRepository extends ReactiveMongoRepository<CustomerEnterprise, String> {

	Mono<CustomerEnterprise> findByRuc(String ruc);

}
