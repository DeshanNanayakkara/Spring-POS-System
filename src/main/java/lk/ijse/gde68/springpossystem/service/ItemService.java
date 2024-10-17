package lk.ijse.gde68.springpossystem.service;


import lk.ijse.gde68.springpossystem.customObj.ItemResponse;
import lk.ijse.gde68.springpossystem.dto.ItemDto;

import java.util.List;

public interface ItemService {
    public void saveItem(ItemDto itemDto);
    public void updateItem(String itemCode, ItemDto itemDto);
    public ItemResponse getItem(String itemCode);
    public List<ItemDto> getAllItems();
    public void deleteItem(String itemCode);
}
