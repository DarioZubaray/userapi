package com.zubaray.userapi.service;

import com.zubaray.userapi.entity.UserApi;
import com.zubaray.userapi.exception.UserApiNotFoundException;
import com.zubaray.userapi.repository.UserApiRepository;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserApiService {

    private UserApiRepository userApiRepository;

    public UserApiService(UserApiRepository userApiRepository) {
        this.userApiRepository = userApiRepository;
    }

    public UserApi getUserApiById(Long id) {
        return userApiRepository.findByIdAndActive(id,true).orElseThrow(UserApiNotFoundException::new);
    }

    public List<UserApi> getAllUserApi() {
        var allUserApi = new ArrayList<UserApi>();
        userApiRepository.findByActiveTrue().forEach(allUserApi::add);
        return allUserApi;
    }

    public UserApi createUserApi(UserApi userApi) {
        String hashPass = BCrypt.hashpw(userApi.getPassword(), BCrypt.gensalt(11));
        userApi.setPassword(hashPass);
        return userApiRepository.save(userApi);
    }

    public UserApi putUserApi(Long id, UserApi userApi) {
        var userDB =userApiRepository.findByIdAndActive(id, true).orElseThrow(UserApiNotFoundException::new);
        userDB.setEmail(userApi.getEmail());
        String hashPass = BCrypt.hashpw(userApi.getPassword(), BCrypt.gensalt(11));
        userDB.setPassword(hashPass);
        userDB.setPhones(userApi.getPhones());
        return userApiRepository.save(userDB);
    }

    public UserApi updateUserApi(Long id, UserApi userApi) {
        log.debug("updateUserApi: {}, {}", id, userApi);
        var userDB = userApiRepository.findByIdAndActive(id, true).orElseThrow(UserApiNotFoundException::new);
        // solo se puede actualizar el email, password o los telefonos
        if (StringUtils.hasText(userApi.getEmail())) {
            userDB.setEmail(userApi.getEmail());
        }
        if(StringUtils.hasText(userApi.getPassword())) {
            String hashPass = BCrypt.hashpw(userApi.getPassword(), BCrypt.gensalt(11));
            userDB.setPassword(hashPass);
        }
        if(!ObjectUtils.isEmpty(userApi.getPhones())) {
            userDB.setPhones(userApi.getPhones());
        }
        return userApiRepository.save(userDB);
    }

    public void deleteUserApi(Long id) {
        var userDB = userApiRepository.findByIdAndActive(id, true).orElseThrow(UserApiNotFoundException::new);
        userDB.setActive(false);
        userApiRepository.save(userDB);
    }
}
