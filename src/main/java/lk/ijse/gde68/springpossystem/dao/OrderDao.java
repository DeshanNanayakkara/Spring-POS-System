package lk.ijse.gde68.springpossystem.dao;

import lk.ijse.gde68.springpossystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository <Order, String> {
}
