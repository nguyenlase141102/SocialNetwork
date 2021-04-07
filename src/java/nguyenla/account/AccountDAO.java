/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import nguyenla.dbutil.Dbutil;

/**
 *
 * @author ANH NGUYEN
 */
public class AccountDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean insertAccount(AccountDTO a) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Insert into accountTBL values(?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, a.getEmail_user());
                ps.setString(2, a.getName_user());
                ps.setString(3, a.getPassword_user());
                ps.setString(4, a.getRole_user());
                ps.setString(5, a.getStatus_user());
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return false;
    }

    public AccountDTO checkAvailableLogin(String userEmail, String userPassword) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select name_user,role_user,status_user from accountTBL where email_user = ? and password_user = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userEmail);
                ps.setString(2, userPassword);
                rs = ps.executeQuery();
                while (rs.next()) {
                    AccountDTO accountDTO = new AccountDTO(rs.getString(1), rs.getString(2), rs.getString(3));
                    return accountDTO;
                }
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return null;
    }

    public boolean updateStatusUser(String userEmail) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Update accountTBL set status_user= 'Active' where email_user = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userEmail);
                int row = ps.executeUpdate();
                if(row > 0){
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return false;
    }
        public String getEmail(String userName) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select email_user from accountTBL where name_user = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userName);
                
                rs = ps.executeQuery();
                while(rs.next()){
                    return rs.getString(1);
                }
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return null;
    }
    
}
