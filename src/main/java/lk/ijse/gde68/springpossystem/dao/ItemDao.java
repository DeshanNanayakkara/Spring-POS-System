package lk.ijse.gde68.springpossystem.dao;


import lk.ijse.gde68.springpossystem.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends JpaRepository <Item, String> {
    Item getItemByItemCode(String itemCode);
}
