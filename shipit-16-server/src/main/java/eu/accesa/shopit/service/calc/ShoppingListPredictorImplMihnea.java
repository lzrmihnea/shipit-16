package eu.accesa.shopit.service.calc;

import eu.accesa.shopit.repository.ComputedProductMapper;
import eu.accesa.shopit.repository.PredictedShoppingListRepository;
import eu.accesa.shopit.repository.ProductRepository;
import eu.accesa.shopit.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShoppingListPredictorImplMihnea implements ShoppingListPredictor {

    @Autowired
    private ShoppingListRepository shoppingListRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PredictedShoppingListRepository predictedShoppingListRepository;
    @Autowired
    private ComputedProductMapper computedProductMapper;

    @Override
    public List<String> predictedList() {


        return null;
    }
}
