package com.nttdata.bootcamp.customerdomain.controller;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.nttdata.bootcamp.customerdomain.model.CustomerEnterprise;
import com.nttdata.bootcamp.customerdomain.model.StatusType;
import com.nttdata.bootcamp.customerdomain.service.CustomerEnterpriseService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/account/enterprise")
public class CustomerEnterpriseController {

	@Autowired
	private CustomerEnterpriseService enterpriseService;

	@GetMapping
	public Mono<ResponseEntity<Flux<CustomerEnterprise>>> findAll() {
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(enterpriseService.findAll()));
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<CustomerEnterprise>> findById(@PathVariable String id) {
		return enterpriseService.findById(id)
				.map(ce -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ce))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Mono<ResponseEntity<Map<String, Object>>> create(@Valid @RequestBody Mono<CustomerEnterprise> monoCustomer) {
		Map<String, Object> result = new HashMap<String, Object>();
		return monoCustomer.flatMap(c -> {
			c.setStatus(StatusType.ENABLED);
			return enterpriseService.save(c).map(enterprise -> {
				return ResponseEntity.created(URI.create("/api/account/enterprise/".concat(enterprise.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(result);
			});
		}).onErrorResume(t -> {
			return Mono.just(t).cast(WebExchangeBindException.class).flatMap(e -> Mono.just(e.getFieldErrors()))
					.flatMapMany(Flux::fromIterable)
					.map(fieldError -> "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
					.collectList().flatMap(list -> {
						result.put("errors", list);
						result.put("timestamp", new Date());
						result.put("status", HttpStatus.BAD_REQUEST.value());
						return Mono.just(ResponseEntity.badRequest().body(result));
					});
		});
	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<CustomerEnterprise>> update(@RequestBody CustomerEnterprise enterprise, @PathVariable String id){
		return enterpriseService.findById(id).flatMap(c->{
			c.setAddress(enterprise.getAddress());
			c.setOwner(enterprise.getOwner());
			c.setReasonSocial(enterprise.getReasonSocial());
			c.setRuc(enterprise.getRuc());
		return enterpriseService.save(c);
		}).map(p->ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(p))
		.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> eliminar(@PathVariable String id){
		return enterpriseService.findById(id).flatMap(p ->{
			p.setStatus(StatusType.DISABLED);
			return enterpriseService.save(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
}