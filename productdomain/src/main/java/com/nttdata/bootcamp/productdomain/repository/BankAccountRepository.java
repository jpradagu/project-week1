package com.nttdata.bootcamp.productdomain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.productdomain.model.BankAccount;

public interface BankAccountRepository extends ReactiveMongoRepository<BankAccount, String>{

}
