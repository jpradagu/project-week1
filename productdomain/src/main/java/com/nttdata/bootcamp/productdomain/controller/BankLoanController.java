package com.nttdata.bootcamp.productdomain.controller;

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

import com.nttdata.bootcamp.productdomain.model.BankLoan;
import com.nttdata.bootcamp.productdomain.service.BankLoanService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/product/bank-loan")
public class BankLoanController {

	@Autowired
	private BankLoanService loanService;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<BankLoan>>> findAll() {
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(loanService.findAll()));
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<BankLoan>> findById(@PathVariable String id) {
		return loanService.findById(id)
				.map(ce -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ce))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Mono<ResponseEntity<Map<String, Object>>> create(@Valid @RequestBody Mono<BankLoan> monoAccount) {
		Map<String, Object> result = new HashMap<>();
		return monoAccount.flatMap(a -> {
			a.setId(null);
			return loanService.save(a).map(account -> ResponseEntity.created(URI.create("/api/product/bank-loan/".concat(account.getId())))
					.contentType(MediaType.APPLICATION_JSON).body(result));
		}).onErrorResume(t -> Mono.just(t).cast(WebExchangeBindException.class)
				.flatMap(e -> Mono.just(e.getFieldErrors()))
				.flatMapMany(Flux::fromIterable)
				.map(fieldError -> "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
				.collectList().flatMap(list -> {
					result.put("errors", list);
					result.put("timestamp", new Date());
					result.put("status", HttpStatus.BAD_REQUEST.value());
					return Mono.just(ResponseEntity.badRequest().body(result));
				}));
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<BankLoan>> update(@RequestBody BankLoan account, @PathVariable String id){
		return loanService.findById(id).flatMap(c-> loanService.save(c)).map(p->ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(p))
		.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> eliminar(@PathVariable String id){
		return loanService.findById(id).flatMap(e -> loanService.delete(e).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
