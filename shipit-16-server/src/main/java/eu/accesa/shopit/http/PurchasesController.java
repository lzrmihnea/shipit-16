package eu.accesa.shopit.http;

import eu.accesa.shopit.model.CreatePurchaseListRequest;
import eu.accesa.shopit.model.CreatePurchaseRequest;
import eu.accesa.shopit.model.entity.Product;
import eu.accesa.shopit.service.ShoppingListService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PurchasesController {

    public static final String API_SAVE_PURCHASE = "/purchase";
    public static final String API_SAVE_PURCHASE_LIST = "/purchase-list";
    public static final String API_GET_SHOPPING_LIST = "/shopping-list";

    private final ShoppingListService shoppingListService;

    public PurchasesController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @RequestMapping(value = API_SAVE_PURCHASE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public void purchaseDone(@RequestBody @Valid CreatePurchaseRequest purchase) {
        this.shoppingListService.saveOrUpdate(purchase);
    }

    @RequestMapping(value = API_SAVE_PURCHASE_LIST, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public void purchaseDone(@RequestBody @Valid CreatePurchaseListRequest purchaseList) {
        this.shoppingListService.saveOrUpdate(purchaseList);
    }

    @RequestMapping(value = API_GET_SHOPPING_LIST, method = RequestMethod.GET)
    public List<String> shoppingList() {
        // todo recalculate shopping list
        return shoppingListService.getNextShoppingList()
                .getProducts()
                .stream()
                .map(Product::toString)
                .collect(Collectors.toList());
    }

}
