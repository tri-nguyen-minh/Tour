
package trinm.dtos;

import java.io.Serializable;

/**
 *
 * @author TNM
 */
public class BookingDTO implements Serializable{
    private String id, tourId, tourName, userId, bookingDate;
    private int amount, tourPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(int tourPrice) {
        this.tourPrice = tourPrice;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public BookingDTO() {
    }

    public BookingDTO(String id, String tourId, String userId, String bookingDate, int amount) {
        this.id = id;
        this.tourId = tourId;
        this.userId = userId;
        this.bookingDate = bookingDate;
        this.amount = amount;
    }
    
}
