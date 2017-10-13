package eu.accesa.shopit.entity;

import eu.accesa.shopit.base.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

import static eu.accesa.shopit.entity.PredictedShoppingList.PREDICTED_SHOPPING_LIST_TABLE_NAME;

@Entity
@Table(name = PREDICTED_SHOPPING_LIST_TABLE_NAME)
public class PredictedShoppingList implements BaseEntity {
    static final String PREDICTED_SHOPPING_LIST_TABLE_NAME = "PREDICTED_SHOPPING_LIST";
    private static final String PREDICTED_SHOPPING_LIST_COLUMN_ID = "ID";
    private static final String PREDICTED_SHOPPING_LIST_COLUMN_DATE = "DATE";
    private static final String PREDICTED_SHOPPING_LIST_PRODUCTS_TABLE_NAME = "PREDICTED_SHOPPING_LIST_PRODUCTS";
    private static final String PREDICTED_SHOPPING_LIST_PRODUCTS_COLUMN_SHOPPING_LIST_ID = "PREDICTED_SHOPPING_LIST_ID";
    private static final String PREDICTED_SHOPPING_LIST_PRODUCTS_COLUMN_PRODUCT_ID = "PRODUCT_ID";

    @Id
    @Column(name = PREDICTED_SHOPPING_LIST_COLUMN_ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Access(AccessType.PROPERTY)
    private Integer id;

    @Column(name = PREDICTED_SHOPPING_LIST_COLUMN_DATE)
    private Date date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = PREDICTED_SHOPPING_LIST_PRODUCTS_TABLE_NAME,
            joinColumns = {@JoinColumn(name = PREDICTED_SHOPPING_LIST_PRODUCTS_COLUMN_SHOPPING_LIST_ID, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = PREDICTED_SHOPPING_LIST_PRODUCTS_COLUMN_PRODUCT_ID, updatable = false)})
    private List<Product> products;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "products");
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, "products");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
