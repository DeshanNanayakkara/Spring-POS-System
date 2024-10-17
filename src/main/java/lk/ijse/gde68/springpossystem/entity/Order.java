package lk.ijse.gde68.springpossystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "orders")
@Entity
public class Order implements SuperEntity{
    @Id
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;
    private String date;
    private double subTotal;
    private double total;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetail> orderDetails;
}
