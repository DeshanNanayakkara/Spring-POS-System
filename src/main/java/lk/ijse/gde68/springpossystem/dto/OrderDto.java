package lk.ijse.gde68.springpossystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto implements Serializable {
    private String orderId;
    private String customerId;
    private String date;
    private double subTotal;
    private double total;
}
