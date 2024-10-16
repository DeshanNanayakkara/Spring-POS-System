package lk.ijse.gde68.springpossystem.controller;

import lk.ijse.gde68.springpossystem.dto.CustomerDto;
import lk.ijse.gde68.springpossystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@RestController
public class CustomerController {
    @Autowired
    private final CustomerService customerService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDto customerDto){
        if (customerDto==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try{
                customerService.saveCustomer(customerDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (Exception e){
                throw e;
            }

        }

    }
}
