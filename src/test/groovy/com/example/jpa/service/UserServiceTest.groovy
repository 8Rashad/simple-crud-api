package com.example.jpa.service

import com.example.jpa.dao.entity.UserEntity
import com.example.jpa.dao.repository.UserRepository
import com.example.jpa.exception.NotFoundException
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification
import spock.lang.Unroll

class UserServiceTest extends Specification {

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private UserRepository userRepository
    private UserService userService
    private A a
    private B b

    def setup(){
        userRepository = Mock()
        a = Mock()
        b = Mock()
        userService = new UserService(userRepository, a, b)
    }

    def "TestGetUserById success case"(){

        given:
        def id = random.nextObject(Long)
        def entity = random.nextObject(UserEntity)

        when:
        def  userResponse = userService.getUser(id)

        then:
        1 * userRepository.findById(id)>> Optional.of(entity)
        1 * a.test()
        1 * b.testMock()
        userResponse.id == entity.id
        userResponse.age == entity.age
        userResponse.username == entity.username
        userResponse.birthPlace == entity.birthPlace
    }


    def "TestGetUserById user not found case"(){

        given:
        def id = random.nextObject(Long)

        when:
        userService.getUser(id)

        then:
        1 * userRepository.findById(id)>> Optional.empty()
        NotFoundException ex = thrown()
        ex.message == "USER_NOT_FOUND"
    }

    @Unroll
    def "Test"(){
        given:
        def num = number

        when:
        userService.testUnroll(num )

        then:
        callsCount * b.testMock()

        where:
        number  |callsCount
        1       |0
        2       |0
        3       |0
        4       |0
        5       |0
        6       |1
        78      |1
    }
}
