package trinm.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import trinm.dtos.DiscountDTO;
import trinm.dtos.TourDTO;
import trinm.utils.MyConnection;

/**
 *
 * @author TNM
 */
public class BookingDAO implements Serializable {

    private Connection con;
    private PreparedStatement preStm;
    private ResultSet rs;
    private static final DateFormat FORMAT_FOR_DB = new SimpleDateFormat("yyyy-MM-dd");

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public BookingDAO() {
    }

    public int getAllBooking() throws Exception {
        int result = 0;
        try {
            String sql = "Select COUNT(Id) as Total From Booking";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("Total");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<DiscountDTO> editUserDiscountList(List<DiscountDTO> list, String userId) throws Exception {
        try {
            String sql = "Select id From Booking Where UserId = ? and DiscountId = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, userId);
            for (int i = 0; i < list.size(); i++) {
                preStm.setString(2, list.get(i).getId());
                rs = preStm.executeQuery();
                if (rs.next()) {
                    list.remove(i);
                    i--;
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public int getBookedSlots(String tourId) throws Exception {
        int result = 0;
        try {
            String sql = "Select Amount From Booking Where TourId = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, tourId);
            rs = preStm.executeQuery();
            while (rs.next()) {
                result += rs.getInt("Amount");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertBooking(List<TourDTO> list, String userId, DiscountDTO dto) throws Exception {
        boolean bookingInserted = false;
        try {
            int bookingCount = getAllBooking();
            String sql = "Insert into Booking(Id,TourId,UserId,BookingDate,Amount,TotalPrice,DiscountId)"
                    + "values(?,?,?,?,?,?,?)";
            con = MyConnection.getConnection();
            con.setAutoCommit(false);
            for (int i = 0; i < list.size(); i++) {
                String tourId = list.get(i).getId();
                int slotBooked = list.get(i).getQuota();
                int number = bookingCount + i + 1;
                String id = "B" + number;
                String dateBook = FORMAT_FOR_DB.format(Calendar.getInstance().getTime());
                int tourPrice = list.get(i).getPrice();
                double discount = (100 - dto.getValue());
                double totalPrice = tourPrice * slotBooked * discount / 100;
                preStm = con.prepareStatement(sql);
                preStm.setString(1, id);
                preStm.setString(2, tourId);
                preStm.setString(3, userId);
                preStm.setString(4, dateBook);
                preStm.setInt(5, slotBooked);
                preStm.setFloat(6, (float) totalPrice);
                preStm.setString(7, dto.getId());
                preStm.executeUpdate();
            }
            con.commit();
            bookingInserted = true;
        } finally {
            closeConnection();
        }
        return bookingInserted;
    }
}
