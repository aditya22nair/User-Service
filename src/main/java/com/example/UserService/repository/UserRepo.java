package com.example.UserService.repository;

import com.example.UserService.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User,String> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query("SELECT u.hashedPassword FROM User u WHERE u.email = :email")
    String findHashedPasswordByEmail(@Param("email") String email);


}
