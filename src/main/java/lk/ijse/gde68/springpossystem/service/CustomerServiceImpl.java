package lk.ijse.gde68.springpossystem.service;

import jakarta.transaction.Transactional;
import lk.ijse.gde68.springpossystem.dao.CustomerDao;
import lk.ijse.gde68.springpossystem.dto.CustomerDto;
import lk.ijse.gde68.springpossystem.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerDao customerDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveCustomer(CustomerDto customerDto) {
        customerDao.save(mapping.convertToEntity(customerDto));
    }
}
