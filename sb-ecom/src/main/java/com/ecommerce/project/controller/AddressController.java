package com.ecommerce.project.controller;

import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.service.AddressService;
import com.ecommerce.project.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    AuthUtil authUtil;
    @Autowired
    AddressService addressService;
    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO){
        User user = authUtil.loggedInUser();
        AddressDTO savedaddressDTO =addressService.createAddress(addressDTO,user);
        return new ResponseEntity<>(savedaddressDTO, HttpStatus.CREATED);
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAddresses(){
        List<AddressDTO> addressList=addressService.getAddresses();
        return new ResponseEntity<>(addressList,HttpStatus.OK);
    }
    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long addressId){
       AddressDTO addressDTO = addressService.getAddressById(addressId);
       return new ResponseEntity<>(addressDTO,HttpStatus.OK);
    }
    @GetMapping("/addresses/user")
    public ResponseEntity<List<AddressDTO>> getUserAddresses(){
        List<AddressDTO> userAddresses=addressService.getUserAddresses(authUtil.loggedInUser());
        return new ResponseEntity<>(userAddresses,HttpStatus.OK);
    }
}
