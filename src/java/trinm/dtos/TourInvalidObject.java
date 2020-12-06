
package trinm.dtos;

import java.io.Serializable;

/**
 *
 * @author TNM
 */
public class TourInvalidObject implements Serializable{
    private String nameInvalid, destinationInvalid,
                   quotaInvalid, priceInvalid,
                   dateDepartInvalid, dateFinishInvalid;

    public String getNameInvalid() {
        return nameInvalid;
    }

    public void setNameInvalid(String nameInvalid) {
        this.nameInvalid = nameInvalid;
    }

    public String getDestinationInvalid() {
        return destinationInvalid;
    }

    public void setDestinationInvalid(String destinationInvalid) {
        this.destinationInvalid = destinationInvalid;
    }

    public String getQuotaInvalid() {
        return quotaInvalid;
    }

    public void setQuotaInvalid(String quotaInvalid) {
        this.quotaInvalid = quotaInvalid;
    }

    public String getPriceInvalid() {
        return priceInvalid;
    }

    public void setPriceInvalid(String priceInvalid) {
        this.priceInvalid = priceInvalid;
    }

    public String getDateDepartInvalid() {
        return dateDepartInvalid;
    }

    public void setDateDepartInvalid(String dateDepartInvalid) {
        this.dateDepartInvalid = dateDepartInvalid;
    }

    public String getDateFinishInvalid() {
        return dateFinishInvalid;
    }

    public void setDateFinishInvalid(String dateFinishInvalid) {
        this.dateFinishInvalid = dateFinishInvalid;
    }

    public TourInvalidObject() {
    }
    
}
