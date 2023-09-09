package com.example.jpa.controller
import com.example.jpa.exception.ErrorHandler
import com.example.jpa.model.request.UserRequest
import com.example.jpa.model.response.UserResponse
import com.example.jpa.service.UserService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.http.HttpStatus.CREATED

class UserControllerTest extends Specification {

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private UserService userService
    private MockMvc mockMvc

    void setup(){
        userService = Mock()
        def userController = new UserController(userService)
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                                .setControllerAdvice(new ErrorHandler())
                                .build()
    }
    def "TestUserById"(){
        given:
        def id = random.nextObject(Long)
        def url = "https://example.com/v1/users/$id"

        def dataResponse = new UserResponse(
                1L, "Rashad", 22, "Baku")

        def expectedResponse = """
                                            {
                                              "id": 1,
                                              "username": "Rashad",
                                              "age": 22,
                                              "birthPlace": "Baku"
                                            }   
                                      """
        when:
        def result = mockMvc.perform(get(url)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn()

        then:
        1 * userService.getUser(id) >> dataResponse

        result.response.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(expectedResponse, result.response.contentAsString, true)
    }

    def "TestSaveUser"(){
        given:
        def url = "https://example.com/v1/users"
        def userRequest = new UserRequest("Rashad", 22)
        def jsonRequest = '''
                                {
                                "username": "Rashad",
                                "age": 22
                                }
                                 '''
        when:
        def mvcResult = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andReturn()


        then:
        1 * userService.saveUser(userRequest)
        mvcResult.response.status == CREATED.value()
    }
}