package com.interview.repository.entities;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
@SuppressWarnings("serial")
@Entity(name="price")
public class Price implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(name="carton_size", length=20)
    private String cartonSize;
    @Column(name="carton_price", precision=22)
    private double cartonPrice;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    /** Default constructor. */
    public Price() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(int aId) {
        id = aId;
    }

    /**
     * Access method for cartonSize.
     *
     * @return the current value of cartonSize
     */
    public String getCartonSize() {
        return cartonSize;
    }

    /**
     * Setter method for cartonSize.
     *
     * @param aCartonSize the new value for cartonSize
     */
    public void setCartonSize(String aCartonSize) {
        cartonSize = aCartonSize;
    }

    /**
     * Access method for cartonPrice.
     *
     * @return the current value of cartonPrice
     */
    public double getCartonPrice() {
        return cartonPrice;
    }

    /**
     * Setter method for cartonPrice.
     *
     * @param aCartonPrice the new value for cartonPrice
     */
    public void setCartonPrice(double aCartonPrice) {
        cartonPrice = aCartonPrice;
    }

    /**
     * Access method for product.
     *
     * @return the current value of product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Setter method for product.
     *
     * @param aProduct the new value for product
     */
    public void setProduct(Product aProduct) {
        product = aProduct;
    }

    /**
     * Compares the key for this instance with another Price.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Price and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Price)) {
            return false;
        }
        Price that = (Price) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Price.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Price)) return false;
        return this.equalKeys(other) && ((Price)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = getId();
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[Price |");
        sb.append(" id=").append(getId());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("id", Integer.valueOf(getId()));
        return ret;
    }

}


