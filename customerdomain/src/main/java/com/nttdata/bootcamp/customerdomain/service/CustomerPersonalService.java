package com.nttdata.bootcamp.customerdomain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.customerdomain.model.CustomerPersonal;
import com.nttdata.bootcamp.customerdomain.repository.CustomerPersonalRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerPersonalService {

	@Autowired
	private CustomerPersonalRepository customerRepository;
	
	public Flux<CustomerPersonal> findAll(){
		return customerRepository.findAll();
	}
	
	public Mono<CustomerPersonal> create(CustomerPersonal customer){
		return customerRepository.save(customer);
	}
	
	public Mono<CustomerPersonal> update(CustomerPersonal customer){
		return customerRepository.save(customer);
	}
	

}
