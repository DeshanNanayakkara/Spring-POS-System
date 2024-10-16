package lk.ijse.gde68.springpossystem.util;

import lk.ijse.gde68.springpossystem.dto.CustomerDto;
import lk.ijse.gde68.springpossystem.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerEntity convertToEntity (CustomerDto customerDto){
        return modelMapper.map(customerDto,CustomerEntity.class);
    }
}
