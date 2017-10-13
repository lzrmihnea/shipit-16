package eu.accesa.shopit.model.entity;

import eu.accesa.shopit.base.BaseEntity;
import eu.accesa.shopit.model.CreatePurchaseRequest;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

import static eu.accesa.shopit.model.entity.Product.PRODUCT_TABLE_NAME;

@Entity
@Table(name = PRODUCT_TABLE_NAME)
public class Product implements BaseEntity {

    static final String PRODUCT_TABLE_NAME = "PRODUCT";
    private static final String PRODUCT_COLUMN_ID = "ID";
    private static final String PRODUCT_COLUMN_NAME = "NAME";

    @Id
    @Column(name = PRODUCT_COLUMN_ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Access(AccessType.PROPERTY)
    private Integer id;

    @Column(name = PRODUCT_COLUMN_NAME, unique = true)
    private String name;

    public Product() {
    }

    public Product(String productName) {
        this.name = productName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
