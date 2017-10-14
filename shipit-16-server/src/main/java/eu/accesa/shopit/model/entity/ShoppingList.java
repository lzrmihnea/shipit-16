package eu.accesa.shopit.model.entity;

import eu.accesa.shopit.base.BaseEntity;
import eu.accesa.shopit.model.CreatePurchaseRequest;
import eu.accesa.shopit.util.DateUtil;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static eu.accesa.shopit.model.entity.ShoppingList.SHOPPING_LIST_TABLE_NAME;

@Entity
@Table(name = SHOPPING_LIST_TABLE_NAME)
public class ShoppingList implements BaseEntity {
    static final String SHOPPING_LIST_TABLE_NAME = "SHOPPING_LIST";
    private static final String SHOPPING_LIST_COLUMN_ID = "ID";
    private static final String SHOPPING_LIST_COLUMN_DATE = "DATE";
    private static final String SHOPPING_LIST_PRODUCTS_TABLE_NAME = "SHOPPING_LIST_PRODUCTS";
    private static final String SHOPPING_LIST_PRODUCTS_COLUMN_SHOPPING_LIST_ID = "SHOPPING_LIST_ID";
    private static final String SHOPPING_LIST_PRODUCTS_COLUMN_PRODUCT_ID = "PRODUCT_ID";

    @Id
    @Column(name = SHOPPING_LIST_COLUMN_ID)
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Access(AccessType.PROPERTY)
    private Integer id;

    @Column(name = SHOPPING_LIST_COLUMN_DATE)
    private Date date;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinTable(name = SHOPPING_LIST_PRODUCTS_TABLE_NAME,
            joinColumns = {@JoinColumn(name = SHOPPING_LIST_PRODUCTS_COLUMN_SHOPPING_LIST_ID, updatable = false, insertable = false)},
            inverseJoinColumns = {@JoinColumn(name = SHOPPING_LIST_PRODUCTS_COLUMN_PRODUCT_ID, updatable = false, insertable = false)})
    private List<Product> products;

    public ShoppingList() {
        this.date = new Date(Instant.now().toEpochMilli());
    }

    public ShoppingList(CreatePurchaseRequest purchase) {
        this.date = DateUtil.convertUtilToSql(purchase.getDate());
        this.getProducts().add(new Product(purchase.getProductName()));
    }

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
        if (this.products == null) {
            this.products = new ArrayList<>();
        }
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void add(CreatePurchaseRequest purchase) {
        this.getProducts().add(new Product(purchase.getProductName()));
    }

    public void add(Product product) {
        this.getProducts().add(product);
    }
}
