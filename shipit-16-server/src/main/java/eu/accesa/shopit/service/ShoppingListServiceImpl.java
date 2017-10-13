package eu.accesa.shopit.service;

import eu.accesa.shopit.model.CreatePurchaseRequest;
import eu.accesa.shopit.model.entity.Product;
import eu.accesa.shopit.model.entity.ShoppingList;
import eu.accesa.shopit.repository.ComputationMapper;
import eu.accesa.shopit.repository.ShoppingListRepository;
import eu.accesa.shopit.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Optional;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingListServiceImpl.class);

    @Autowired
    private ShoppingListRepository shoppingListRepository;
    @Autowired
    private ComputationMapper computationMapper;

    @Override
    public ShoppingList getNextShoppingList() {
//        TODO get next shopping list as date
        return Optional
                .ofNullable(this.shoppingListRepository.findAll())
                .map(shoppingLists -> shoppingLists.get(0))
                .orElse(null);
    }

    @Override
    public void saveOrUpdate(CreatePurchaseRequest purchase) {
        ShoppingList shoppingList = getDbShoppingList(purchase);
        this.shoppingListRepository.save(shoppingList);
    }

    private ShoppingList getDbShoppingList(CreatePurchaseRequest purchase) {
        Date dateOfPurchase = purchase.getDate();
        if (ObjectUtils.isEmpty(dateOfPurchase)) {
            return new ShoppingList();
        } else {
            ShoppingList dbShoppingList = this.shoppingListRepository.findFirstByDate(DateUtil.convertUtilToSql(dateOfPurchase));
            if (ObjectUtils.isEmpty(dbShoppingList)) {
                return new ShoppingList(purchase);
            }
            dbShoppingList.add(purchase);
            return dbShoppingList;
        }
    }
}
