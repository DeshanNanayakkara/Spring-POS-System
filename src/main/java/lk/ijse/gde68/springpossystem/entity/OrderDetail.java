package lk.ijse.gde68.springpossystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@IdClass(OrderDetailPK.class)
@Table(name = "order_detail")
@Entity
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Order orders;

    @Id
    @ManyToOne
    @JoinColumn(name = "itemCode", nullable = false)
    private Item item;

    private double unitPrice;
    private int qty;
}
