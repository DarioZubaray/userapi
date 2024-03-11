package com.zubaray.userapi.controller;

import com.zubaray.userapi.config.JwtService;
import com.zubaray.userapi.dto.ResponseDto;
import com.zubaray.userapi.entity.JwtRequest;
import com.zubaray.userapi.entity.JwtResponse;
import com.zubaray.userapi.entity.UserApi;
import com.zubaray.userapi.exception.UserApiNotFoundException;
import com.zubaray.userapi.repository.UserApiRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private JwtService jwtTokenUtil;

    @Autowired
    private UserApiRepository repository;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        log.debug("createAuthenticationToken");
        log.debug(authenticationRequest.toString());

        try {
            String email = authenticationRequest.getEmail();
            UserDetails userDetails = repository.findByEmailAndActiveTrue(email).orElseThrow(UserApiNotFoundException::new);

            final String token = jwtTokenUtil.generateToken(userDetails);

            UserApi userApi = (UserApi) userDetails;
            userApi.setToken(token);
            repository.save(userApi);

            return ResponseEntity.ok(new JwtResponse(token));
        } catch(UserApiNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseDto.builder().message("usuario invalido").build());
        }
    }
}