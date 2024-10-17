package lk.ijse.gde68.springpossystem.dao;

import lk.ijse.gde68.springpossystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository <Customer, String>{
    @Query()
    Customer getCustomerByCustomerId(String customerId);
}
