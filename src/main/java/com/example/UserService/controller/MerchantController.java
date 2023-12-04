package com.example.UserService.controller;

import com.example.UserService.Dto.*;
import com.example.UserService.entity.Merchant;
import com.example.UserService.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    MerchantService merchantService;
   @PostMapping
   public Boolean addMerchant(@RequestBody MerchantDto merchantDto){
       return merchantService.addMercahntData(merchantDto);
   }
    @PostMapping("/Merchantlogin")
    public Boolean MerchantLogin(@RequestBody AuthenticateDto authenticateDto) {
        return merchantService.merchantLogin(authenticateDto);
    }
   @GetMapping
   private List<MerchantResponseDto> getAllMerchant(){
       Iterable<Merchant>data=merchantService.getAllMerchant();
       List<MerchantResponseDto> responseDtos=new ArrayList<>();
       for(Merchant merchant:data){
           MerchantResponseDto merchantResponseDto=new MerchantResponseDto();
           BeanUtils.copyProperties(merchant,merchantResponseDto);
           responseDtos.add(merchantResponseDto);
       }
       return responseDtos;

   }
    @GetMapping("/{merchant_Id}")
    public MerchantResponseDto getMerchantById(@RequestParam String merchant_Id) {
        return merchantService.getMerchantById(merchant_Id);
    }
    @GetMapping("/{merchant_email}")
    public MerchantResponseDto getMerchantByemail(@RequestParam String merchant_email) {
        return merchantService.getMerchantByEmail(merchant_email);
    }



}
