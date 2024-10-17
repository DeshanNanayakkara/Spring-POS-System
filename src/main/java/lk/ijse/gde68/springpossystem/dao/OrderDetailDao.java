package lk.ijse.gde68.springpossystem.dao;


import lk.ijse.gde68.springpossystem.entity.OrderDetail;
import lk.ijse.gde68.springpossystem.entity.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDao extends JpaRepository <OrderDetail, OrderDetailPK> {
}
