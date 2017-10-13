package eu.accesa.shopit.repository;

import eu.accesa.shopit.entity.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer> {

    List<ShoppingList> findByProductsIn(String product);
    List<ShoppingList> findByDate(Date product);

    ShoppingList findFirstByProductsInOOrderByDateDesc(String product);
}
