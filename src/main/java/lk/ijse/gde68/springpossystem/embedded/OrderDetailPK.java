package lk.ijse.gde68.springpossystem.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class OrderDetailPK implements Serializable {
    @Column
    private String orderId;
    @Column
    private String itemId;
}
