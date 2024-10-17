package lk.ijse.gde68.springpossystem.controller;


import lk.ijse.gde68.springpossystem.dto.OrderDetailDto;
import lk.ijse.gde68.springpossystem.dto.OrderDto;
import lk.ijse.gde68.springpossystem.exception.DataPersistFailedException;
import lk.ijse.gde68.springpossystem.service.OrderDetailService;
import lk.ijse.gde68.springpossystem.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/placeorder")
public class PlaceOrderFormController {
    private static final Logger logger = LoggerFactory.getLogger(PlaceOrderFormController.class);
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping(value = "order")
    public ResponseEntity<Void> saveOrder(@RequestBody OrderDto orderDto){
        logger.info("Request to save order: {}", orderDto);
        if (orderDto == null) {
            logger.warn("Received null orderDto for saving: {}", orderDto);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try{
                orderService.saveOrder(orderDto);
                logger.info("Successfully saved order: {}", orderDto);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistFailedException e) {
                logger.error("Failed to persist order data: {}", orderDto, e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.error("Unexpected error while saving order: {}", orderDto, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

    }

    @PostMapping(value = "order_detail")
    public ResponseEntity<Void> saveOrderDetail(@RequestBody OrderDetailDto orderDetailDto){
        if (orderDetailDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try {
                orderDetailService.saveOrderDetails(orderDetailDto);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistFailedException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

}
