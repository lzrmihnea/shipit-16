package eu.accesa.shopit.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.accesa.shopit.entity.Product;
import eu.accesa.shopit.service.ShoppingListService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
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
            consumes = "application/json",
            method = RequestMethod.POST
    )
    public void purchaseDone(
            @RequestBody @Valid Purchase purchase
    ) {
    }

    @RequestMapping(
            value = "/shopping-list",
            method = RequestMethod.GET
    )
    public List<String> shoppingList() {
        // todo recalculate shopping list
        return shoppingListService.getNextShoppingList()
                .getProducts()
                .stream()
                .map(Product::toString)
                .collect(Collectors.toList());
    }

    private class Purchase {
        final String productName;
        final Date date;

        private Purchase(
                @JsonProperty(value = "productName", required = true) String productName,
                @JsonProperty(value = "date", required = true) Date date
        ) {
            this.productName = productName;
            this.date = date;
        }

        public String getProductName() {
            return productName;
        }

        public Date getDate() {
            return date;
        }
    }
}
