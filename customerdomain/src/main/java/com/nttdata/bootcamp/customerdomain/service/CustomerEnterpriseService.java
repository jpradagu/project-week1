package com.nttdata.bootcamp.customerdomain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.customerdomain.model.CustomerEnterprise;
import com.nttdata.bootcamp.customerdomain.model.StatusType;
import com.nttdata.bootcamp.customerdomain.repository.CustomerEnterpriseRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerEnterpriseService {

	@Autowired
	private CustomerEnterpriseRepository enterpriseRepository;

	public Flux<CustomerEnterprise> findAll() {
		return enterpriseRepository.findAll().filter(p -> p.getStatus().equals(StatusType.ENABLED));
	}

	public Mono<CustomerEnterprise> findById(String id) {
		return enterpriseRepository.findById(id);
	}

	public Mono<CustomerEnterprise> save(CustomerEnterprise customer) {
		return enterpriseRepository.save(customer);
	}

}
