package eu.accesa.shopit.repository;

import eu.accesa.shopit.model.entity.ShoppingList;
import org.apache.ibatis.annotations.*;

public interface ComputationMapper {

    @Select("SELECT sl.id AS sl_id, sl.date AS sl_date " +
            " FROM shopping_list sl " +
            "   JOIN shopping_list_products slp ON slp.shopping_list_id = sl.id " +
            "   JOIN product p ON p.id = slp.product_id " +
            " WHERE sl.date=#{date} ")
    @ResultType(ShoppingList.class)
    @Results({
            @Result(property = "id", column = "sl_id"),
            @Result(property = "date", column = "sl_date")
    })
    ShoppingList findShoppingListBy(@Param("date") java.util.Date date);

}
