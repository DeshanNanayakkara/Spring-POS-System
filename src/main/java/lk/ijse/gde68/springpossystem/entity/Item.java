package lk.ijse.gde68.springpossystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    @OneToMany(mappedBy = "item" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<OrderDetail> orderDetails = new ArrayList<>();
}
