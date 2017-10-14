package eu.accesa.shopit.repository;

import eu.accesa.shopit.model.entity.Product;
import eu.accesa.shopit.model.entity.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer> {

    ShoppingList findFirstByDate(Date date);

    ShoppingList findFirstByProductsInOrderByDateDesc(Product item);

    List<ShoppingList> findByProductsIn(Product item);
}
