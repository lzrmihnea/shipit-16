package eu.accesa.shopit.service;

import eu.accesa.shopit.entity.ShoppingList;
import eu.accesa.shopit.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Override
    public ShoppingList getNextShoppingList() {
//        TODO get next shopping list as date
        return Optional
                .ofNullable(this.shoppingListRepository.findAll())
                .map(shoppingLists -> shoppingLists.get(0))
                .orElseGet(() -> null);
    }
}
