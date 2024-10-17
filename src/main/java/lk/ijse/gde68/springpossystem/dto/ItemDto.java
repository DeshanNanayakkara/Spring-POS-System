package lk.ijse.gde68.springpossystem.dto;


import lk.ijse.gde68.springpossystem.customObj.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDto implements SuperDto, ItemResponse {
    private String itemCode;
    private String itemName;
    private int itemQty;
    private double itemPrice;
}
