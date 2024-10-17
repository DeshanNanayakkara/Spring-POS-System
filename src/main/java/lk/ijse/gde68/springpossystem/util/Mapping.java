package lk.ijse.gde68.springpossystem.util;


import lk.ijse.gde68.springpossystem.dto.CustomerDto;
import lk.ijse.gde68.springpossystem.dto.ItemDto;
import lk.ijse.gde68.springpossystem.dto.OrderDto;
import lk.ijse.gde68.springpossystem.entity.Customer;
import lk.ijse.gde68.springpossystem.entity.Item;
import lk.ijse.gde68.springpossystem.entity.Order;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public Customer convertToEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto, Customer.class);
    }

    public CustomerDto convertToDto(Customer customer){
        return modelMapper.map(customer, CustomerDto.class);
    }

    public List<CustomerDto> convertToDtos(List<Customer> customerList){
        return modelMapper.map(customerList, new TypeToken<List<CustomerDto>>(){}.getType());
    }

    public Item convertToEntity(ItemDto itemDto){
        return modelMapper.map(itemDto, Item.class);
    }

    public ItemDto convertToDto(Item item){
        return modelMapper.map(item, ItemDto.class);
    }

    public List<ItemDto> convertToItemDtos(List<Item> itemList){
        return modelMapper.map(itemList, new TypeToken<List<ItemDto>>(){}.getType());
    }

    public Order convertToEntity(OrderDto orderDto){
        return modelMapper.map(orderDto, Order.class);
    }

}
