package com.example.jpa.service.specification;

import com.example.jpa.dao.entity.UserEntity;
import com.example.jpa.model.criteria.UserCriteria;
import com.example.jpa.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.example.jpa.model.constants.CriteriaConstants.AGE;
import static com.example.jpa.model.constants.CriteriaConstants.BIRTH_PLACE;
import static com.example.jpa.util.PredicateUtil.applyLikePattern;

@AllArgsConstructor
public class UserSpecification implements Specification<UserEntity> {

    private UserCriteria userCriteria;

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        var predicates = PredicateUtil.builder()
                .addNullSafety(userCriteria.getBirthPlace(),
                        birthPlace -> cb.like(root.get(BIRTH_PLACE), applyLikePattern(birthPlace))
                )
                .addNullSafety(userCriteria.getAgeFrom(), ageFrom ->  cb.greaterThanOrEqualTo(root.get(AGE), ageFrom))
                .addNullSafety(userCriteria.getAgeTo(), ageTo ->  cb.lessThanOrEqualTo(root.get(AGE), ageTo))
                .build();

       return cb.and(predicates);
    }
}
