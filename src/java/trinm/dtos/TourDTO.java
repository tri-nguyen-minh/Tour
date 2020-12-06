
package trinm.dtos;

import java.io.Serializable;

/**
 *
 * @author TNM
 */
public class TourDTO implements Serializable{
    private String id, tourName, destination, imageLink, status, dateStart, dateEnd, dateCreate;
    private int price, quota, slotAvailable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getSlotAvailable() {
        return slotAvailable;
    }

    public void setSlotAvailable(int slotAvailable) {
        this.slotAvailable = slotAvailable;
    }

    public TourDTO() {
    }

    public TourDTO(String id, String tourName, String destination, String dateStart, String dateEnd, String dateCreate, int price, int quota, String imageLink, String status) {
        this.id = id;
        this.tourName = tourName;
        this.destination = destination;
        this.imageLink = imageLink;
        this.status = status;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dateCreate = dateCreate;
        this.price = price;
        this.quota = quota;
    }

    
}
