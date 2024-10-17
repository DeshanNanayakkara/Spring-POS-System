package lk.ijse.gde68.springpossystem.service;

import jakarta.transaction.Transactional;

import lk.ijse.gde68.springpossystem.customObj.CustomerErrorResponse;
import lk.ijse.gde68.springpossystem.customObj.CustomerResponse;
import lk.ijse.gde68.springpossystem.dao.CustomerDao;
import lk.ijse.gde68.springpossystem.dto.CustomerDto;
import lk.ijse.gde68.springpossystem.entity.Customer;
import lk.ijse.gde68.springpossystem.exception.CustomerNotFoundException;
import lk.ijse.gde68.springpossystem.exception.DataPersistFailedException;
import lk.ijse.gde68.springpossystem.util.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        logger.info("Attempt to save customer: {}", customerDto);
        if (customerDao.existsById(customerDto.getCustomerId())) {
            logger.warn("Customer Id already exists: {}", customerDto.getCustomerId());
            throw new DataPersistFailedException("This customer ID already exists!");
        }else {
            Customer savedCustomer = customerDao.save(mapping.convertToEntity(customerDto));
            if (savedCustomer == null && savedCustomer.getCustomerId() == null) {
                logger.error("Failed to save customer: {}", customerDto);
                throw new DataPersistFailedException("Can't save the customer!");
            }
            logger.info("Successfully saved customer: {}", customerDto);
        }

    }

    @Override
    public CustomerResponse getCustomer(String customerId) {
        logger.info("Fetching customer with customer id: {}", customerId);
        if (customerDao.existsById(customerId)) {
            CustomerResponse response = mapping.convertToDto(customerDao.getCustomerByCustomerId(customerId));
            logger.info("Successfully retrieved customer: {}", response);
            return response;
        } else {
            logger.warn("Customer not found with customerId: {}", customerId);
            return new CustomerErrorResponse(0, "Customer Not Found!");
        }

    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        logger.info("Fetching all customers");
        List<CustomerDto> customers = mapping.convertToDtos(customerDao.findAll());
        logger.info("Successfully retrieved all customers, count: {}", customers.size());
        return customers;
    }

    @Override
    public void updateCustomer(String customerId, CustomerDto incomeCustomerDto) {
        logger.info("Attempting to update customer with customerId: {}", customerId);
        Optional<Customer> tmpCustomer = customerDao.findById(customerId);
        if (!tmpCustomer.isPresent()) {
            logger.warn("Customer not found for update with customerId: {}", customerId);
            throw new CustomerNotFoundException("Customer Not Found!");
        }else {
            logger.info("Updating customer details for customerId: {}", customerId);
            tmpCustomer.get().setCustomerName(incomeCustomerDto.getCustomerName());
            tmpCustomer.get().setCustomerAddress(incomeCustomerDto.getCustomerAddress());
            tmpCustomer.get().setCustomerSalary(incomeCustomerDto.getCustomerSalary());
            logger.info("Successfully updated customer with customerId: {}", customerId);
        }
    }

    @Override
    public void deleteCustomer(String customerId) {
        logger.info("Attempting to delete customer with id: {}", customerId);
        Optional<Customer> existingCustomer = customerDao.findById(customerId);
        if (!existingCustomer.isPresent()) {
            logger.warn("Customer not found for delete with id: {}", customerId);
            throw new CustomerNotFoundException("Customer Not Found!");
        } else {
            customerDao.deleteById(customerId);
            logger.info("Successfully delete customer with id: {}", customerId);
        }
    }
}
