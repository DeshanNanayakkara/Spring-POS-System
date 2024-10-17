package lk.ijse.gde68.springpossystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "customer")
@Entity
public class Customer implements SuperEntity {
    @Id
    private String customerId;
    private String customerName;
    private String customerAddress;
    private double customerSalary;
    @OneToMany(mappedBy = "orderId")
    private List<Order> orders;
}
