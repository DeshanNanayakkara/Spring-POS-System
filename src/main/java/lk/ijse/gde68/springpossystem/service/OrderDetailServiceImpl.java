package lk.ijse.gde68.springpossystem.service;

import jakarta.transaction.Transactional;
import lk.ijse.gde68.springpossystem.dao.OrderDetailDao;
import lk.ijse.gde68.springpossystem.dto.OrderDetailDto;
import lk.ijse.gde68.springpossystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveOrderDetails(OrderDetailDto orderDetailDto) {
        orderDetailDao.save(mapping.convertToEntity(orderDetailDto));
    }
}
