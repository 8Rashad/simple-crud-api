package com.example.jpa.dao.repository;

import com.example.jpa.dao.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
}
