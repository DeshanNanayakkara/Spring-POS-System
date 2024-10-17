package lk.ijse.gde68.springpossystem.service;



import lk.ijse.gde68.springpossystem.customObj.CustomerResponse;
import lk.ijse.gde68.springpossystem.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    public void saveCustomer(CustomerDto customerDto);
    public CustomerResponse getCustomer(String customerId);
    public List<CustomerDto> getAllCustomers();
    public void updateCustomer(String customerId, CustomerDto customerDto);
    public void deleteCustomer(String customerId);
}
