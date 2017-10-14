package eu.accesa.shopit.service;

import eu.accesa.shopit.model.CreatePurchaseListRequest;
import eu.accesa.shopit.model.CreatePurchaseRequest;
import io.vavr.Tuple2;

import java.util.List;
import java.util.Map;

public interface ShoppingListService {
    List<Tuple2<Double,String>> getNextShoppingList();

    void saveOrUpdate(CreatePurchaseRequest purchase);

    void saveOrUpdate(CreatePurchaseListRequest purchaseList);

    void setProfile(Map<String,Integer> profileMap);
}
