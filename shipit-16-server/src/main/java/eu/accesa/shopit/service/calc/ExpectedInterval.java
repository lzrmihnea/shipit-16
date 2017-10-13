package eu.accesa.shopit.service.calc;


import eu.accesa.shopit.entity.ShoppingList;
import eu.accesa.shopit.repository.ShoppingListRepository;
import io.vavr.collection.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ExpectedInterval implements ShoppingListPredictor {


    private final Integer retroDays;
    private final ShoppingListRepository shoppingListRepository;

    public ExpectedInterval(
            @Value("${retrospective.days}") Integer retroDays,
            ShoppingListRepository shoppingListRepository
    ) {
        this.retroDays = retroDays;
        this.shoppingListRepository = shoppingListRepository;
    }

    public double expectedInterval(java.util.List<Long> purchaseTimes) {
        Double wi, interval;
        Double weightSum = 0.0;
        Double divident = 0.0;

        if (purchaseTimes.size() < 2) return 0;//todo use default value;
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
        return (-1 / retroDays) * interval + 1;
    }

    public Double probabilityOfNeed(String item) {

        return ((System.currentTimeMillis() / 1000L) - shoppingListRepository.findFirstByProductsInOOrderByDateDesc(item).getDate().toInstant().getEpochSecond())
                / expectedInterval(
                        List.ofAll(shoppingListRepository.findByProductsIn(item))
                .map(shoppingList -> (shoppingList.getDate().toInstant().getEpochSecond())).toJavaList());

    }

    @Override
    public java.util.List<String> predictedList() {
        return null;
    }
}
