package eu.accesa.shopit.service.calc;


import eu.accesa.shopit.model.entity.Product;
import eu.accesa.shopit.repository.DefaultProfileRepository;
import eu.accesa.shopit.repository.ProductRepository;
import eu.accesa.shopit.repository.ShoppingListRepository;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.Optional;

@Component
public class ExpectedInterval implements ShoppingListPredictor {


    private final Integer retroDays;
    private final ShoppingListRepository shoppingListRepository;
    private final ProductRepository productRepository;
    private final DefaultProfileRepository defaultProfileRepository;

    public ExpectedInterval(
            @Value("${retrospective.days}") Integer retroDays,
            ShoppingListRepository shoppingListRepository,
            ProductRepository productRepository, DefaultProfileRepository defaultProfileRepository) {
        this.retroDays = retroDays;
        this.shoppingListRepository = shoppingListRepository;
        this.productRepository = productRepository;
        this.defaultProfileRepository = defaultProfileRepository;
    }

    public double expectedInterval(java.util.List<Long> purchaseTimes, String itemName) {
        Double wi;
        Double weightSum = 0.0;
        Double divident = 0.0;

        if (purchaseTimes.size() < 2)
            return Optional.ofNullable(defaultProfileRepository.findByName(itemName)).orElse(7 * 24 * 60 * 60);
        for (int i = 0; i < purchaseTimes.size() - 1; i++) {
            wi = weightNormalization(
                    (purchaseTimes.get(i) +
                            purchaseTimes.get(i + 1))
                            / 2.0
            );
            divident += wi * purchaseTimes.get(i + 1) - purchaseTimes.get(i);
            weightSum += wi;
        }
        return divident / weightSum;
    }

    private Double weightNormalization(Double interval) {
        return (-1 / (retroDays * 24 * 60 * 60)) * interval + 1;
    }

    public Double probabilityOfNeed(Product item) {

        long curentEpochSeconds = (System.currentTimeMillis() / 1000L);
        long lastPurchaseSeconds = shoppingListRepository.findFirstByProductsInOrderByDateDesc(item).getDate().toLocalDate().atStartOfDay(ZoneOffset.UTC).toInstant().getEpochSecond();
        double calculatedInterval = expectedInterval(
                List.ofAll(shoppingListRepository.findByProductsIn(item))
                        .map(shoppingList ->
                                (shoppingList.getDate().toLocalDate().atStartOfDay(ZoneOffset.UTC).toInstant().getEpochSecond()))
                        .distinct()
                        .toJavaList(),
                item.getName()
        );
        return (curentEpochSeconds-lastPurchaseSeconds)/calculatedInterval;
    }

    @Override
    public java.util.List<Tuple2<Double, String>> predictedList() {
        return List.ofAll(productRepository.findAll())
                .map(product -> Tuple.of(probabilityOfNeed(product), product.getName()))
                .filter(item -> item._1 > 0.3)
                .sortBy((Double item1, Double item2) -> item1.compareTo(item1), Tuple2::_1)
                //  .map(item -> item._2)
                .collect(List.collector()).toJavaList();
    }
}
