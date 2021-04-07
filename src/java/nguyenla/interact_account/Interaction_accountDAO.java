/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.interact_account;

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
public class Interaction_accountDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean insertInteractAccount(Interaction_accountDTO i) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Insert into interaction_accountTBL values(?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, i.getId_interaction());
                ps.setInt(2, i.getId_article());
                ps.setString(3, i.getTime_interaction());
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

    public boolean deleteCommentAccount(int id) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Delete from interaction_accountTBL where id_interaction = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
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

    public boolean deleteArticle(int id) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Delete from interaction_accountTBL where id_article= ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
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
    
    
}
