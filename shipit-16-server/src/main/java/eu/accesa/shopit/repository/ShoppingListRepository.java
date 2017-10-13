package eu.accesa.shopit.repository;

import eu.accesa.shopit.entity.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer> {
}
