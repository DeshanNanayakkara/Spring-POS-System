package lk.ijse.gde68.springpossystem.service;

import jakarta.transaction.Transactional;

import lk.ijse.gde68.springpossystem.customObj.ItemErrorResponse;
import lk.ijse.gde68.springpossystem.customObj.ItemResponse;
import lk.ijse.gde68.springpossystem.dao.ItemDao;
import lk.ijse.gde68.springpossystem.dto.ItemDto;
import lk.ijse.gde68.springpossystem.entity.Item;
import lk.ijse.gde68.springpossystem.exception.DataPersistFailedException;
import lk.ijse.gde68.springpossystem.exception.ItemNotFoundException;
import lk.ijse.gde68.springpossystem.util.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveItem(ItemDto itemDto) {
        logger.info("Attempting toi save item: {}", itemDto);
        if (itemDao.existsById(itemDto.getItemCode())) {
            logger.warn("Item Code already exists: {}", itemDto.getItemCode());
            throw new DataPersistFailedException("This Item Code already exists!");
        }else {
            Item savedItem = itemDao.save(mapping.convertToEntity(itemDto));
            if (savedItem == null && savedItem.getItemCode() == null) {
                logger.error("Failed to save item: {}", itemDto);
                throw new DataPersistFailedException("Can't save the Item!");
            }
            logger.info("Successfully saved item: {}", itemDto);
        }

    }

    @Override
    public void updateItem(String itemCode, ItemDto incomingItem) {
        logger.info("Attempting to update customer with ID: {}", itemCode);
        Optional<Item> existItem = itemDao.findById(itemCode);
        if (existItem == null) {
            logger.warn("Item not found for update with ID: {}", itemCode);
            throw new ItemNotFoundException("Item not found!");
        } else {
            logger.info("Updating item details for ID: {}", itemCode);
            existItem.get().setItemName(incomingItem.getItemName());
            existItem.get().setItemQty(incomingItem.getItemQty());
            existItem.get().setItemPrice(incomingItem.getItemPrice());
            logger.info("Successfully updated item with ID: {}", itemCode);
        }
    }

    @Override
    public ItemResponse getItem(String itemCode) {
        logger.info("Fetching item with item code: {}", itemCode);
        if (itemDao.existsById(itemCode)) {
            ItemDto response = mapping.convertToDto(itemDao.getItemByItemCode(itemCode));
            logger.info("Successfully retrieved item: {}", response);
            return response;
        } else {
            logger.warn("Item not found with item code: {}", itemCode);
            return new ItemErrorResponse(0, "Item Not Found!");
        }
    }

    @Override
    public List<ItemDto> getAllItems() {
        logger.info("Fetching all customers");
        List<ItemDto> items = mapping.convertToItemDtos(itemDao.findAll());
        logger.info("Successfully retrieved all customers, count: {}", items.size());
        return items;
    }

    @Override
    public void deleteItem(String itemCode){
        logger.info("Attempting to delete item with item code: {}", itemCode);
        Optional<Item> existsItem = itemDao.findById(itemCode);
        if (!existsItem.isPresent()) {
            logger.warn("Item not found for deletion with item code: {}", itemCode);
            throw new ItemNotFoundException("Item not found!");
        } else {
            itemDao.deleteById(itemCode);
            logger.info("Successfully deleted item with item code: {}", itemCode);
        }
    }
}
