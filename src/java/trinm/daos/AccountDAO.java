
package trinm.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import trinm.dtos.AccountDTO;
import trinm.utils.MyConnection;

/**
 *
 * @author TNM
 */
public class AccountDAO implements Serializable {
    private Connection con;
    private PreparedStatement preStm;
    private ResultSet rs;

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

    public AccountDAO() {
    }

    public AccountDTO getAccount(String id, String password) throws Exception {
        AccountDTO dto = null;
        int roleId = 0, statusId = 0;
        try {
            String sql = "Select Name, RoleId,StatusId From Users Where Id = ? And Password = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, id);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            dto = new AccountDTO(id, password, "", "");
            if (rs.next()) {
                dto.setName(rs.getString("Name"));
                roleId = rs.getInt("RoleId");
                statusId = rs.getInt("StatusId");
                sql = "Select Role From Roles Where RoleId = ?";
                preStm = con.prepareStatement(sql);
                preStm.setInt(1, roleId);
                rs = preStm.executeQuery();
                if (rs.next()) {
                    dto.setRole(rs.getString("Role"));
                }
                sql = "Select Status From Status Where StatusId = ?";
                preStm = con.prepareStatement(sql);
                preStm.setInt(1, statusId);
                rs = preStm.executeQuery();
                if (rs.next()) {
                    dto.setStatus(rs.getString("Status"));
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
