
package trinm.dtos;

import java.io.Serializable;

/**
 *
 * @author TNM
 */
public class DiscountDTO implements Serializable{
    private String id, discountCode, dateExpire;
    private int value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(String dateExpire) {
        this.dateExpire = dateExpire;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public DiscountDTO() {
    }

    public DiscountDTO(String id, String discountCode, String dateExpire, int value) {
        this.id = id;
        this.discountCode = discountCode;
        this.dateExpire = dateExpire;
        this.value = value;
    }
    
}
