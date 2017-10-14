package eu.accesa.shopit.service;

import eu.accesa.shopit.model.CreatePurchaseListRequest;
import eu.accesa.shopit.model.CreatePurchaseRequest;
import eu.accesa.shopit.model.entity.Product;
import eu.accesa.shopit.model.entity.ShoppingList;
import eu.accesa.shopit.repository.ComputationMapper;
import eu.accesa.shopit.repository.ProductRepository;
import eu.accesa.shopit.repository.ShoppingListRepository;
import eu.accesa.shopit.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingListServiceImpl.class);

    @Autowired
    private ShoppingListRepository shoppingListRepository;
    @Autowired
    private ProductRepository productRepository;
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

    @Override
    public void saveOrUpdate(CreatePurchaseListRequest purchaseList) {
        if (!ObjectUtils.isEmpty(purchaseList) && !CollectionUtils.isEmpty(purchaseList.getPurchaseList())) {
            purchaseList.getPurchaseList().forEach(purchaseReq -> this.saveOrUpdate(purchaseReq));
        }
    }

    private ShoppingList getDbShoppingList(CreatePurchaseRequest purchase) {
        ShoppingList dbShoppingList = this.shoppingListRepository.findFirstByDate(DateUtil.convertUtilToSql(purchase.getDate()));
        if (ObjectUtils.isEmpty(dbShoppingList)) {
            ShoppingList newShoppingList = new ShoppingList();
            newShoppingList.add(getProductToAdd(purchase));
            return newShoppingList;
        }

        dbShoppingList.add(getProductToAdd(purchase));
        return dbShoppingList;
    }

    private Product getProductToAdd(CreatePurchaseRequest purchase) {
        String prodName = purchase.getProductName();
        Product dbProd = this.productRepository.findByName(prodName);
        if (ObjectUtils.isEmpty(dbProd)) {
            return new Product(prodName);
        } else {
            return dbProd;
        }
    }
}
