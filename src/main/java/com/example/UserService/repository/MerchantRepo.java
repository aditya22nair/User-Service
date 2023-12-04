package com.example.UserService.repository;

import com.example.UserService.entity.Merchant;
import com.example.UserService.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepo extends CrudRepository<Merchant, String> {

    @Query("SELECT m FROM Merchant m WHERE m.merchant_email = :email")
    Merchant findMerchantByEmail(@Param("email") String email);

}
