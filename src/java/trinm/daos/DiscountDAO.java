
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
import trinm.dtos.DiscountDTO;
import trinm.utils.MyConnection;

/**
 *
 * @author TNM
 */
public class DiscountDAO implements Serializable{
    private Connection con;
    private PreparedStatement preStm;
    private ResultSet rs;
    private static final DateFormat FORMATFORPRINT = new SimpleDateFormat("MMM dd, yyyy");
    private static final DateFormat FORMATFORDB = new SimpleDateFormat("yyyy-MM-dd");
    
    public void closeConnection() throws Exception {
        if(rs != null) {
            rs.close();
        }
        if(preStm != null) {
            preStm.close();
        }
        if(con != null) {
            con.close();
        }
    }
    
    public List<DiscountDTO> getAllUserDiscount(String userId) throws Exception{
        List<DiscountDTO> result = null;
        try {
            String sql = "Select Id, DiscountCode, Value, ExpireDate From Discount Where ExpireDate > ? And Id != ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, FORMATFORDB.format(Calendar.getInstance().getTime()));
            preStm.setString(2, "NONE");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()) {
                String id = rs.getString("Id");
                String code = rs.getString("DiscountCode");
                String dateExpire = FORMATFORPRINT.format(rs.getDate("ExpireDate"));
                int value = rs.getInt("Value");
                result.add(new DiscountDTO(id, code, dateExpire, value));
            }
            result = (new BookingDAO()).editUserDiscountList(result, userId);
        } finally {
            closeConnection();
        }
        return result;
    }
}
