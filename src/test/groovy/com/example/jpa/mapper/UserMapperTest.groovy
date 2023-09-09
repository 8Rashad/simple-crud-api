package com.example.jpa.mapper

import com.example.jpa.dao.entity.UserEntity
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.jpa.mapper.UserMapper.mapEntityToResponse

class UserMapperTest extends Specification{
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "TestBuildUserResponse"(){
        given:
        def entity = random.nextObject(UserEntity)

        when:
        def actual = mapEntityToResponse(entity)

        then:
        actual.id == entity.id
        actual.age == entity.age
        actual.username == entity.username
        actual.birthPlace == entity.birthPlace
    }

}
