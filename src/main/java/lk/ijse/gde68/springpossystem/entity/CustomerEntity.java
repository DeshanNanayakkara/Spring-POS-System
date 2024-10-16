package lk.ijse.gde68.springpossystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table (name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerEntity implements SuperEntity{
    @Id
    private String customerId;
    private String customerName;
    private String customerAddress;
    private double customerSalary;
    }
