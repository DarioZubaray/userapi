package com.zubaray.userapi.repository;

import com.zubaray.userapi.entity.UserApi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserApiRepository extends CrudRepository<UserApi, Long> {

    Optional<UserApi> findByNameAndActiveTrue(String name);
    Optional<UserApi> findByEmailAndActiveTrue(String email);
    Optional<UserApi> findByIdAndActive(Long id, Boolean active);
    Iterable<UserApi> findByActiveTrue();
}
