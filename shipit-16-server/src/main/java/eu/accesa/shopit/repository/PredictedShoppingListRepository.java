package eu.accesa.shopit.repository;

import eu.accesa.shopit.entity.PredictedShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictedShoppingListRepository extends JpaRepository<PredictedShoppingList, Integer> {
}
