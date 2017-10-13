package eu.accesa.shopit.http;

import eu.accesa.shopit.entity.Product;
import eu.accesa.shopit.service.ShoppingListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Purchases {

    private final ShoppingListService shoppingListService;

    public Purchases(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @RequestMapping(
            value = "/purchase",
            method = RequestMethod.POST
    )
    public ResponseEntity<Void> purchaseDone(
            String productName
    ){
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


    @RequestMapping(
            value = "/shopping-list",
            method = RequestMethod.GET
    )
    public List<String> shoppingList(){
                // todo recalculate shopping list
                return shoppingListService.getNextShoppingList()
                        .getProducts()
                        .stream()
                        .map(Product::toString)
                        .collect(Collectors.toList());
    }
}
