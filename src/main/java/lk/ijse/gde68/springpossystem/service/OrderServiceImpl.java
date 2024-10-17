package lk.ijse.gde68.springpossystem.service;

import jakarta.transaction.Transactional;
import lk.ijse.gde68.springpossystem.dao.OrderDao;
import lk.ijse.gde68.springpossystem.dto.OrderDto;
import lk.ijse.gde68.springpossystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveOrder(OrderDto orderDto) {
        orderDao.save(mapping.convertToEntity(orderDto));
    }
}
