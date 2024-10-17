package lk.ijse.gde68.springpossystem.controller;

import lk.ijse.gde68.springpossystem.customObj.CustomerResponse;
import lk.ijse.gde68.springpossystem.dto.CustomerDto;
import lk.ijse.gde68.springpossystem.exception.CustomerNotFoundException;
import lk.ijse.gde68.springpossystem.exception.DataPersistFailedException;
import lk.ijse.gde68.springpossystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private final CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDto customerDto){
        logger.info("Request to save customer: {}", customerDto);
        if (customerDto == null) {
            logger.warn("Received null customerDto for saving");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try {
                customerService.saveCustomer(customerDto);
                logger.info("Successfully saved customer: {}", customerDto);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistFailedException e) {
                logger.error("Failed to persist customer data: {}", customerDto, e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e) {
                logger.error("Unexpected error while saving customer data: {}", customerDto, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/{customerId}")
    public CustomerResponse getCustomer(@PathVariable ("customerId") String customerId){
        logger.info("Request to get customer with customerId: {}", customerId);
        CustomerResponse response = customerService.getCustomer(customerId);
        logger.info("Successfully retrieved customer: {}", response);
        return response;
    }

    @GetMapping
    public List<CustomerDto> getAllCustomers(){
        logger.info("Request to get all customers");
        List<CustomerDto> allCustomers = customerService.getAllCustomers();
        logger.info("Successfully retrieved all customers, count: {}", allCustomers.size());
        return allCustomers;
    }

    @PatchMapping("/{customerId}")
    public ResponseEntity<Void> updateCustomer(@PathVariable ("customerId") String customerId, @RequestBody CustomerDto customerDto){
        logger.info("Request to update customer with customerId: {}", customerId);
        customerService.updateCustomer(customerId, customerDto);
        logger.info("Succcessfully updated customer with id: {}", customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable ("customerId") String customerId){
        logger.info("Request to delete customer with customerId: {}", customerId);
        try {
            customerService.deleteCustomer(customerId);
            logger.info("Successfully deleted customer with customerId: {}", customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            logger.warn("Customer not found with customerId: {}", customerId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unexpected error while deleting customer with customerId: {}", customerId);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
