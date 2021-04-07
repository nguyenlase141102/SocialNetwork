/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nguyenla.article.ArticleDTO;
import nguyenla.dbutil.Dbutil;

/**
 *
 * @author ANH NGUYEN
 */
public class Notification_accountDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean insertInteraction(Notification_accountDTO no) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Insert into notitication_accountTBL values(?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, no.getName_notification());
                ps.setString(2, no.getDate_notification());
                ps.setString(3, no.getType_notification());
                ps.setString(4, no.getContent_notification());
                ps.setInt(5, no.getId_article());
                ps.setString(6,no.getEmail_user());
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
    
    
        public boolean insertInteraction2(Notification_accountDTO no) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Insert into notitication_accountTBL values(?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, no.getName_notification());
                ps.setString(2, no.getDate_notification());
                ps.setString(3, no.getType_notification());
                ps.setInt(5, no.getId_article());
                ps.setString(6,no.getEmail_user());
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

    public int countAllNotification() throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select count(*) from notitication_accountTBL ";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return 0;
    }

    public List<Notification_accountDTO> getAllNotifi(String userName) throws SQLException, NamingException {
        try {
            List<Notification_accountDTO> listNotifi = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select name_notification,date_notification,type_interaction,content_notification,id_article from notitication_accountTBL\n"
                        + "where name_notification <> ?";
                ps = con.prepareStatement(sql);
                ps.setString(1,userName);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String name = rs.getString(1).trim();
                    String date = rs.getString(2);
                    String type = rs.getString(3);
                    String content = rs.getString(4);
                    int id = rs.getInt(5);
                    Notification_accountDTO noDTO = new Notification_accountDTO(name, date, type, content, id);
                    listNotifi.add(noDTO);
                }
                return listNotifi;
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public List<Notification_accountDTO> getNameNotifi(String nameUser) throws SQLException, NamingException {
        try {
            List<Notification_accountDTO> listNotifi = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select name_notification,date_notification,type_interaction,content_notification,id_article from notitication_accountTBL\n"
                        + "where name_notification <> ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, nameUser);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String name = rs.getString(1).trim();
                    String date = rs.getString(2);
                    String type = rs.getString(3);
                    String content = rs.getString(4);
                    int id = rs.getInt(5);
                    Notification_accountDTO noDTO = new Notification_accountDTO(name, date, type, content, id);
                    listNotifi.add(noDTO);
                }
                return listNotifi;
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public List<ArticleDTO> getIdArticle(String nameUser) throws SQLException, NamingException {
        try {
            List<ArticleDTO> listIdArticle = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select DISTINCT id_article,title_article from articleTBL where poster = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, nameUser);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String title = rs.getString(2);
                    ArticleDTO article = new ArticleDTO(title, id);
                    listIdArticle.add(article);
                }
                return listIdArticle;
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

}
