package com.example.jpa.service

import spock.lang.Specification
import spock.lang.Unroll

class MathTest extends Specification{

    @Unroll
    def "TestSum"(){

        given:
        def a = firstNum
        def b = secondNum

        when:
        def actual = A.sum(a, b)

        then:
        actual == sum

        where:
        firstNum| secondNum |sum
        5       |3          |8
        1       |3          |4
        0       |-2         |-2
    }
}
