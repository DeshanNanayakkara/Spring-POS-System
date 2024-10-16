package lk.ijse.gde68.springpossystem.dao;

import lk.ijse.gde68.springpossystem.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;

@Repository
public interface CustomerDao extends JpaRepository<CustomerEntity, String> {
}
