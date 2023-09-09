package com.example.jpa.service;

import com.example.jpa.dao.repository.AccountRepository;
import com.example.jpa.model.cache.PersonDto;
import com.example.jpa.util.CacheUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheService  {

    private final CacheUtil cacheUtil;
    private final AccountRepository accountRepository;

    @PostConstruct
    public void test(){
        var pin = "123";
        cacheUtil.saveToCache("person-" + pin,
                new PersonDto(pin, "mock-add"), 1L, ChronoUnit.HOURS);
    }

    @PostConstruct
    public void test1(){
        var pin = "123";
        var person = cacheUtil.getBucket("person-" + pin);
        log.info("PERSON_DATA:{}", person);
    }

    @PostConstruct
    public void test2(){
        var account1 = accountRepository.findById(7L).get();
        account1.setAmount(account1.getAmount().add(BigDecimal.valueOf(10)));

        var account2 = accountRepository.findById(7L).get();
        account2.setAmount(account2.getAmount().subtract(BigDecimal.valueOf(20)));

        accountRepository.save(account1);
        try {
            accountRepository.save(account2);
        }catch (ObjectOptimisticLockingFailureException ex){
            log.error("LOCK WORK");
        }
    }

}
