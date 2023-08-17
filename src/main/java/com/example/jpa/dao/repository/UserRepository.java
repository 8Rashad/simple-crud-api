package com.example.jpa.dao.repository;

import com.example.jpa.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    @Query(nativeQuery = true, value = "SELECT count(*) FROM users WHERE  birth_place =:birthPlace")
    Integer findCountByBirthPlace(@Param("birthPlace") String birthPlace);

    @Query(value = "SELECT birthPlace FROM UserEntity where id=:id")
    String findBirthPlaceById(Long id);


}
