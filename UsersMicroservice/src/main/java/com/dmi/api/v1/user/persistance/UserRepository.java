package com.dmi.api.v1.user.persistance;

import com.dmi.api.v1.user.persistance.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
