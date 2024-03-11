package com.zubaray.userapi.service;

import com.zubaray.userapi.entity.UserApi;
import com.zubaray.userapi.exception.UserApiNotFoundException;
import com.zubaray.userapi.repository.UserApiRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserApiServiceTest {

    @Mock
    private UserApiRepository userApiRepository;

    private UserApiService servie;

    @BeforeEach
    void setUp() {
        this.servie = new UserApiService(this.userApiRepository);
    }

    @Test
    void getUserApiById_success() {
        Long id = 1L;
        var expectedUser = UserApi.builder()
                .id(id)
                .name("Jhon Doe")
                .email("john@example.com")
                .build();

        Mockito.when(userApiRepository.findByIdAndActive(Mockito.anyLong(), Mockito.anyBoolean()))
                .thenReturn(Optional.of(expectedUser));

        var actualUser = this.servie.getUserApiById(id);

        Assertions.assertEquals(1L, actualUser.getId());
        Assertions.assertEquals("Jhon Doe", actualUser.getName());
        Assertions.assertEquals("john@example.com", actualUser.getEmail());
    }

    @Test()
    void getUserApiById_fail() {
        Mockito.when(userApiRepository.findByIdAndActive(Mockito.anyLong(), Mockito.anyBoolean()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(UserApiNotFoundException.class, () -> this.servie.getUserApiById(1L));
    }

    @Test()
    void getAllUserApi_success() {
        var expectedUser = UserApi.builder()
                .id(1L)
                .name("Jhon Doe")
                .email("john@example.com")
                .build();
        Mockito.when(userApiRepository.findByActiveTrue())
                .thenReturn(Arrays.asList(expectedUser));

        var actualUserList = this.servie.getAllUserApi();

        Assertions.assertEquals(1L, actualUserList.size());
        Assertions.assertEquals(1L, actualUserList.get(0).getId());
        Assertions.assertEquals("Jhon Doe", actualUserList.get(0).getName());
        Assertions.assertEquals("john@example.com", actualUserList.get(0).getEmail());
    }

    @Test()
    void getAllUserApi_noUsers() {
        Mockito.when(userApiRepository.findByActiveTrue())
                .thenReturn(Arrays.asList());

        var actualUserList = this.servie.getAllUserApi();

        Assertions.assertEquals(0L, actualUserList.size());
    }

}