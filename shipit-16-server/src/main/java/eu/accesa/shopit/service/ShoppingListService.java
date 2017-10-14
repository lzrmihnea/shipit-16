package eu.accesa.shopit.service;

import eu.accesa.shopit.model.CreatePurchaseListRequest;
import eu.accesa.shopit.model.CreatePurchaseRequest;
import eu.accesa.shopit.model.entity.ShoppingList;

public interface ShoppingListService {
    ShoppingList getNextShoppingList();

    void saveOrUpdate(CreatePurchaseRequest purchase);

    void saveOrUpdate(CreatePurchaseListRequest purchaseList);
}
