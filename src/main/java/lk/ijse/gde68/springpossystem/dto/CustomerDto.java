package lk.ijse.gde68.springpossystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto implements Serializable {
    private String customerId;
    private String customerName;
    private String customerAddress;
    private double customerSalary;
}
