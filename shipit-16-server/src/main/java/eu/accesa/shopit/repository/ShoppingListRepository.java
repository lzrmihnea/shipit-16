package eu.accesa.shopit.repository;

import eu.accesa.shopit.model.entity.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer> {

    ShoppingList findFirstByDate(Date date);
}
