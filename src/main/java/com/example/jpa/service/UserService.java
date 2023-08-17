package com.example.jpa.service;

import com.example.jpa.dao.entity.UserEntity;
import com.example.jpa.model.criteria.PageCriteria;
import com.example.jpa.model.criteria.UserCriteria;
import com.example.jpa.model.response.PageableUserResponse;
import com.example.jpa.model.response.UserResponse;
import com.example.jpa.dao.repository.UserRepository;
import com.example.jpa.exception.NotFoundException;
import com.example.jpa.model.request.UserRequest;
import com.example.jpa.service.specification.UserSpecification;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.example.jpa.mapper.UserMapper.*;
import static com.example.jpa.model.constants.CriteriaConstants.COUNT_DEFAULT_VALUE;
import static com.example.jpa.model.constants.CriteriaConstants.PAGE_DEFAULT_VALUE;
import static com.example.jpa.model.enums.ExceptionNotifiers.USER_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse getUser(Long id) {
        log.info("ActionLog.getUser.start id:{}", id);

        var user = fetchUserIfExist(id);
        log.info("ActionLog.getUser.end id:{}", id);
        return mapEntityToResponse(user);
    }

//    public List<UserResponse> getUsers() {
//        return List.of(new UserResponse(1L, "mock_user", 23));
//    }

    public void saveUser(UserRequest user) {
        userRepository.save(mapRequestToEntity(user));
    }

    public void setBirthPlace(Long id, String birthPlace) {
        var user = fetchUserIfExist(id);
        user.setBirthPlace(birthPlace);
        userRepository.save(user);
    }

//    @PostConstruct
//    public void test() {
//        System.out.println("-*-*-*-*-*-");
//        System.out.println(userRepository.findCountByBirthPlace("Ankara"));
//    }

//    @PostConstruct
//    public void testNative() {
//        System.out.println("------------------------");
//        System.out.println(userRepository.findBirthPlaceById(4L));
//    }

    public PageableUserResponse getUsers(PageCriteria pageCriteria, UserCriteria userCriteria) {

        var pageNumber = pageCriteria.getPage() == null ? PAGE_DEFAULT_VALUE : pageCriteria.getPage();
        var count = pageCriteria.getCount() == null ? COUNT_DEFAULT_VALUE : pageCriteria.getCount();

        var usersPage = userRepository.findAll(new UserSpecification(userCriteria), PageRequest.of(pageNumber, count));

        return mapToPageableResponse(usersPage);
    }


    private UserEntity fetchUserIfExist(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ActionLog.getUser.error id:{}",id);
                    throw new NotFoundException(USER_NOT_FOUND.getMessage());
                });
    }
}
