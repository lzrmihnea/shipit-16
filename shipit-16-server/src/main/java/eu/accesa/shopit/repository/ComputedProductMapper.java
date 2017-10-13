package eu.accesa.shopit.repository;

import eu.accesa.shopit.entity.Product;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ComputedProductMapper {


//    @Select("SELECT c.id, c.rank, c.top_view_calculation,c.exclude_superior_levels, " +
//            "  CASE WHEN (cds.percent_value IS NOT NULL AND cds.percent_value <> 0) THEN cds.percent_value " +
//            "       ELSE cds.amount " +
//            "  END as value, " +
//            "  CASE WHEN (cds.percent_value IS NOT NULL AND cds.percent_value <> 0) THEN true " +
//            "    ELSE false " +
//            "  END as is_percent " +
//            "FROM condition c " +
//            "  inner join condition_deduction_scaling cds on cds.condition_id = c.id " +
//            "  inner join condition_supplier cs on c.id = cs.condition_id " +
//            "  left join condition_manufacturer cm on c.id = cm.condition_id " +
//            "  left join condition_product cp on c.id = cp.condition_id " +
//            "  left join condition_marketing_channel cmc on c.id = cmc.condition_id " +
//            "  left join condition_subsidiary c_subsid on c.id = c_subsid.condition_id " +
//            "WHERE  " +
//            "   cds.md_condition_deduction_scaling_id = 1 AND " +
//            "   (cds.deleted IS NULL OR cds.deleted = FALSE) AND " +
//            "   c.release_flag = TRUE AND " +
//            "   c.invoice_cond = #{isECondition} AND " +
//            "   cs.supplier_id = #{supplierId} AND " +
//            "   (c.valid_from IS NULL OR c.valid_from <= now()) AND " +
//            "   (c.valid_until IS NULL OR c.valid_until >= now()) AND " +
//            "   c.cancel_flag = FALSE AND " +
//            "   c.sight_code IN (2, 4) AND " +
//            "   (cm.manufacturer_id IS NULL OR cm.manufacturer_id = #{productInfo.manufacturerTechnicalId}) AND " +
//            "   (cmc.marketing_channel_id IS NULL OR cmc.marketing_channel_id = #{marketingChannelId}) AND " +
//            "   (c_subsid.subsidiary_id IS NULL OR c_subsid.subsidiary_id = #{subsidiaryId}) AND " +
//            "   c.pricecodegroup_id IS NULL AND " +
//            "   (cp.product_id IS NULL OR cp.product_id = #{productInfo.productTechnicalId}) AND " +
//            "   (c.hierarchy_domain_id IS NULL OR c.hierarchy_domain_id = #{productInfo.domainTechnicalId}) AND " +
//            "   (c.hierarchy_department_id IS NULL OR c.hierarchy_department_id= #{productInfo.departmentTechnicalId}) AND " +
//            "   (c.hierarchy_main_product_group_id IS NULL OR c.hierarchy_main_product_group_id = #{productInfo.mainProductGroupTechnicalId}) AND " +
//            "   (c.hierarchy_product_group_id IS NULL OR c.hierarchy_product_group_id = #{productInfo.productGroupTechnicalId}) ")
//    @ResultType(ConditionInfo.class)
//    @Results({
//            @Result(property = "conditionTechnicalId", column = "id"),
//            @Result(property = "rank", column = "rank"),
//            @Result(property = "topViewCalculation", column = "top_view_calculation"),
//            @Result(property = "value", column = "value"),
//            @Result(property = "isPercent", column = "is_percent"),
//            @Result(property = "exclusive", column = "exclude_superior_levels")
//    })
//    List<ConditionInfo> findConditionsForProduct(@Param("productInfo") ProductInfo productInfo,
//                                                 @Param("supplierId") Long supplierId,
//                                                 @Param("marketingChannelId") Long marketingChannelId,
//                                                 @Param("subsidiaryId") Long subsidiaryId,
//                                                 @Param("isECondition") boolean isECondition);

    @Select("SELECT p.id, p.md_product_id, p.subsidiary " +
            " FROM ShoppingList " +
            "  INNER JOIN Condition_Product pc ON pc.product_id = p.id " +
            "  INNER JOIN Condition c ON c.id = pc.condition_id " +
            " WHERE c.md_condition_id=#{condition.conditionId} " +
            "  AND c.subsidiary=#{condition.subsidiary} ")
    @ResultType(String.class)
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "productId", column = "md_product_id"),
//            @Result(property = "subsidiary", column = "subsidiary")
//    })
    List<Product> findProductsConnectedToCondition();


}
