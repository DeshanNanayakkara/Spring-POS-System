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
@Table(name = "item")
@Entity
public class Item  implements SuperEntity{
    @Id
    private String itemCode;
    private String itemName;
    private int itemQty;
    private double itemPrice;

    @OneToMany(mappedBy = "item")
    private List<OrderDetail> orderDetails;
}
