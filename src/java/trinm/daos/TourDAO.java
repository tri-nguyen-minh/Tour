
package trinm.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import trinm.dtos.TourDTO;
import trinm.utils.MyConnection;

/**
 *
 * @author TNM
 */
public class TourDAO implements Serializable {

    private Connection con;
    private PreparedStatement preStm;
    private ResultSet rs;
    private static final DateFormat FORMAT_FOR_PRINT = new SimpleDateFormat("MMM dd, yyyy");
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

    public TourDAO() {
    }
    
    public int countAllTour() throws Exception {
        int total = 0;
        try {
            String sql = "Select COUNT(TourId) as Total From Tours";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();
            if(rs.next()) {
                total = rs.getInt("Total");
            }
        } finally {
            closeConnection();
        }
        return total;
    }
    
    public boolean manageBookedTour(List<TourDTO> list) throws Exception {
        boolean tourManaged = false;
        try {
            String sql = "Update Tours Set StatusId = ? Where Quota = ? and tourId = ?";
            con = MyConnection.getConnection();
            for (int i = 0; i < list.size(); i++) {
                int slotBooked = (new BookingDAO()).getBookedSlots(list.get(i).getId());
                preStm = con.prepareStatement(sql);
                preStm.setInt(1, 2);
                preStm.setInt(2, slotBooked);
                preStm.setString(3, list.get(i).getId());
                preStm.executeUpdate();
            }
            tourManaged = true;
        } finally {
             closeConnection();
        }
        return tourManaged;
    }
    
    public void deactivateTour() throws Exception {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 2);
            String dateExpire = FORMAT_FOR_DB.format(calendar.getTime());
            String sql = "Update Tours Set StatusId = ? Where FromDate < ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, 2);
            preStm.setString(2, dateExpire);
            preStm.executeUpdate();
        } finally {
            closeConnection();
        }
    }
    public int getTotalSearchPage (String search, String startDate, String endDate,
            int priceLowLimit, int priceHighLimit) throws Exception {
        int result = 0;
            try {
            String sql = "Select COUNT(TourId) as Total From Tours Where Destination Like ? And (Price Between ? And ?)";
            if (!startDate.isEmpty()) {
                sql += " And FromDate >= ?";
            }
            if (!endDate.isEmpty()) {
                sql += " And FromDate <= ?";
            }
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            preStm.setInt(2, priceLowLimit);
            preStm.setInt(3, priceHighLimit);
            if (startDate.isEmpty() && endDate.isEmpty()) {
            } else if (endDate.isEmpty()) {
                preStm.setString(4, startDate);
            } else if (startDate.isEmpty()) {
                preStm.setString(4, endDate);
            } else {
                preStm.setString(4, startDate);
                preStm.setString(5, endDate);
            }
            rs = preStm.executeQuery();
            if(rs.next())
                result = rs.getInt("Total");
            if((result%5)==0)
                result = result/5;
            else result = (result/5) + 1;
        } finally {
                closeConnection();
        }
        return result;
    }
    
    public List<TourDTO> searchTour (String search, String startDate, String endDate,
            int priceLowLimit, int priceHighLimit, int page) throws Exception {
        List<TourDTO> result = null;
        try {
            String mainSQL = "Select top(5) TourId,TourName,Destination,FromDate,ToDate,Price,Quota,Image,StatusId From Tours "
                    + "Where Destination LIKE '%"+search+"%' And (Price Between "+priceLowLimit+" And "+priceHighLimit+") ";
            String subSQL = "Select top("+(page - 1) * 5+") TourId From Tours Where Destination Like '%"+search+"%' And (Price Between "+priceLowLimit+" And "+priceHighLimit+") ";
            String sortMethod = " Order by FromDate";
            if (!startDate.isEmpty()) {
                mainSQL += "And FromDate >= '"+startDate+"' ";
                subSQL += "And FromDate >= '"+startDate+"' ";
            }
            if (!endDate.isEmpty()) {
                mainSQL += "And FromDate <= '"+endDate+"' ";
                subSQL += "And FromDate <= '"+endDate+"' ";
            }
            String sql = mainSQL + "And TourId NOT IN (" + subSQL + sortMethod + ") " + sortMethod;
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("TourId");
                String tourName = rs.getString("TourName");
                String destination = rs.getString("Destination");
                String start = FORMAT_FOR_PRINT.format(rs.getDate("FromDate"));
                String end = FORMAT_FOR_PRINT.format(rs.getDate("ToDate"));
                int price = rs.getInt("Price");
                int quota = rs.getInt("Quota");
                String imageLink = rs.getString("Image");
                String status = rs.getInt("StatusId")+"";
                TourDTO dto = new TourDTO(id, tourName, destination, start, end, "", price, quota, imageLink, status);
                result.add(dto);
            }
            sql = "Select Status From Status Where StatusId = ?";
            for (int i = 0; i < result.size(); i++) {
                preStm = con.prepareStatement(sql);
                preStm.setInt(1, Integer.parseInt(result.get(i).getStatus()));
                rs = preStm.executeQuery();
                if(rs.next()) {
                    result.get(i).setStatus(rs.getString("Status"));
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    public boolean createTour(TourDTO dto) throws Exception {
        boolean tourCreated = false;
        try {
            String sql = "Insert Into Tours(TourId,TourName,Destination,"
                        + "FromDate,ToDate,Price,Quota,Image,DateImport,StatusId) "
                        + "values(?,?,?,?,?,?,?,?,?,?)";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, dto.getId());
            preStm.setString(2, dto.getTourName());
            preStm.setString(3, dto.getDestination());
            preStm.setString(4, dto.getDateStart());
            preStm.setString(5, dto.getDateEnd());
            preStm.setInt(6, dto.getPrice());
            preStm.setInt(7, dto.getQuota());
            preStm.setString(8, dto.getImageLink());
            Calendar calendar = Calendar.getInstance();
            String dateImport = FORMAT_FOR_DB.format(calendar.getTime());
            preStm.setString(9, dateImport);
            preStm.setInt(10, 1);
            tourCreated = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return tourCreated;
    }
    
    public TourDTO findBookingTour(String tourId) throws Exception {
        TourDTO tour = null;
        try {
            String sql = "Select TourName,Destination,FromDate,Quota,Price From Tours Where TourId = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, tourId);
            rs = preStm.executeQuery();
            tour = new TourDTO();
            if(rs.next()) {
                tour.setId(tourId);
                tour.setTourName(rs.getString("TourName"));
                tour.setDestination(rs.getString("Destination"));
                String dateStart = FORMAT_FOR_PRINT.format(rs.getDate("FromDate"));
                tour.setDateStart(dateStart);
                tour.setQuota(rs.getInt("Quota"));
                tour.setPrice(rs.getInt("Price"));
            }
        } finally {
            closeConnection();
        }
        return tour;
    }
}
