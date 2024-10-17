package lk.ijse.gde68.springpossystem.controller;

import lk.ijse.gde68.springpossystem.customObj.ItemResponse;
import lk.ijse.gde68.springpossystem.dto.ItemDto;
import lk.ijse.gde68.springpossystem.exception.DataPersistFailedException;
import lk.ijse.gde68.springpossystem.exception.ItemNotFoundException;
import lk.ijse.gde68.springpossystem.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@RequiredArgsConstructor
@CrossOrigin
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Void> saveItem(@RequestBody ItemDto itemDto){
        logger.info("Request to save item: {}", itemDto);
        if (itemDto == null) {
            logger.warn("Received null itemDto for saving");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            try {
                itemService.saveItem(itemDto);
                logger.info("Successfully saved item: {}", itemDto);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistFailedException e) {
                logger.error("Failed to persist item data: {}", itemDto, e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.error("Unexpected error while saving item: {}", itemDto, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PatchMapping("/{itemCode}")
    public ResponseEntity<Void> updateItem(@PathVariable ("itemCode") String itemCode, @RequestBody ItemDto itemDto){
        logger.info("Request to update item with ID: {}", itemCode);
        itemService.updateItem(itemCode, itemDto);
        logger.info("Successfully updated item with ID: {}", itemCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{itemCode}")
    public ItemResponse getItem(@PathVariable ("itemCode") String itemCode){
        logger.info("Request to get item with ID: {}", itemCode);
        ItemResponse response = itemService.getItem(itemCode);
        logger.info("Successfully retrieved item: {}", response);
        return response;
    }

    @GetMapping
    public List<ItemDto> getAllItems(){
        logger.info("Request to get all items");
        List<ItemDto> allItems = itemService.getAllItems();
        logger.info("Successfully retrieved all items, count: {}", allItems.size());
        return allItems;
    }

    @DeleteMapping("/{itemCode}")
    public ResponseEntity<Void> deleteItem(@PathVariable ("itemCode") String itemCode) {
        logger.info("Request to delete item with ID: {}", itemCode);
        try {
            itemService.deleteItem(itemCode);
            logger.info("Successfully deleted item with ID: {}", itemCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ItemNotFoundException e) {
            logger.warn("Item not found with ID: {}", itemCode);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unexpected error while deleting item with ID: {}", itemCode);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
