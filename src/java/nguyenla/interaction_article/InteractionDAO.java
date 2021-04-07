/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.interaction_article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nguyenla.dbutil.Dbutil;

/**
 *
 * @author ANH NGUYEN
 */
public class InteractionDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean insertInteraction(InteractionDTO i) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Insert into interaction_articleTBL values(?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, i.getId_article());
                ps.setString(2, i.getComments());
                ps.setInt(3, i.getLikes());
                ps.setInt(4, i.getDislikes());
                ps.setString(5, i.getDate());
                ps.setString(6, i.getPoster());
                ps.setString(7, i.getEmail_user()); 
                ps.setString(8,i.getColorEmotions());
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

    public List<InteractionDTO> getAllListInteract() throws SQLException, NamingException {
        try {
            List<InteractionDTO> listInteraction = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select id_article,comments,likes,dislikes,date,poster,id_interaction from interaction_articleTBL where "
                        + " email_user is not null ";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    InteractionDTO interDTO = new InteractionDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6).trim(), rs.getInt(7));
                    listInteraction.add(interDTO);
                }
                return listInteraction;
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

    public int getIdInteract(String comments) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select id_interaction from interaction_articleTBL where comments = ? ";

                ps = con.prepareStatement(sql);
                ps.setString(1, comments);
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

    public InteractionDTO countComments(int id) throws SQLException, NamingException {
        try {

            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select count(*) from interaction_articleTBL where id_article = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id_article = id;
                    int numberComment = rs.getInt(1);
                    InteractionDTO numberCommment = new InteractionDTO(id_article, numberComment);
                    return numberCommment;
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

    public InteractionDTO countLikesAndDislikes(int id) throws SQLException, NamingException {
        try {

            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select likes,dislikes,color from interaction_articleTBL where id_article = ?  ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id_article = id;
                    int numberLikes = rs.getInt(1);
                    int numberDislikes = rs.getInt(2);
                    String color = rs.getString(3);
                                   
                    InteractionDTO numberLikesAndDisLikes = new InteractionDTO(id_article, numberLikes, numberDislikes,color);
                    return numberLikesAndDisLikes;
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

    public boolean deleteComment(int id) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Delete from interaction_articleTBL where id_interaction = ?";
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

    public boolean deleteNull() throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Delete from interaction_articleTBL where date is null and poster is null";
                ps = con.prepareStatement(sql);
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

    public String getEmail(String userName) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select email_user from accountTBL where name_user = ? ";

                ps = con.prepareStatement(sql);
                ps.setString(1, userName);
                rs = ps.executeQuery();
                while (rs.next()) {
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
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public boolean updateLike(int numberLike, int id) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Update interaction_articleTBL set likes = ? where id_article = ? and date is not null";
                ps = con.prepareStatement(sql);
                ps.setInt(1, numberLike);
                ps.setInt(2, id);
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

    public boolean updateDisLike(int numberLike, int id) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Update interaction_articleTBL set dislikes = ? where id_article = ? and date is not null";
                ps = con.prepareStatement(sql);
                ps.setInt(1, numberLike);
                ps.setInt(2, id);
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

    public boolean updateColor(int id, String color, String poster) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Update interaction_articleTBL set color = ? where id_article = ? and poster = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, color);
                ps.setInt(2, id);
                ps.setString(3,poster);
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
       public int getLastId() throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "SELECT TOP 1 id_interaction FROM interaction_articleTBL ORDER BY id_interaction DESC";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
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

        }
        return 0;
    }
}
