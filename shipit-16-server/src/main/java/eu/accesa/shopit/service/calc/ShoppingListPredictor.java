package eu.accesa.shopit.service.calc;

import io.vavr.Tuple2;

import java.util.List;

public interface ShoppingListPredictor {

    List<Tuple2<Double,String>> predictedList();
}
