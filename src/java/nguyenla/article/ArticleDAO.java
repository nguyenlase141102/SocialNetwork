/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.article;

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
public class ArticleDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean postArticle(ArticleDTO a) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Insert into articleTBL values(?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, a.getTitle_article());
                ps.setString(2, a.getDescription_article());
                ps.setString(3, a.getImage_article());
                ps.setString(4, a.getDate_article());
                ps.setString(5, a.getPath());
                ps.setString(6, a.getPoster());
                ps.setString(7, a.getStatus_article());
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

    public int countAllArticle() throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select count(*) from articleTBL where status_article = 'Active'";
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
        }

        return 0;
    }

    public List<ArticleDTO> getAllArticle(int index) throws SQLException, NamingException {
        List<ArticleDTO> listAllArticle = new ArrayList<>();
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "with x as (select ROW_NUMBER() over(order by date_article desc) as r, * from articleTBL where status_article = 'Active')\n"
                        + "select title_article,description_article,image_article,date_article,poster,id_article  from x where r between ?*5-4 and ?*5";
                ps = con.prepareStatement(sql);
                ps.setInt(1, index);
                ps.setInt(2, index);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ArticleDTO article = new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3).trim(), rs.getString(4), rs.getString(5).trim(), rs.getInt(6));
                    listAllArticle.add(article);
                }
                System.out.println(listAllArticle.size());
                return listAllArticle;
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

    public List<ArticleDTO> getAllArticleNoIndex() throws SQLException, NamingException {
        List<ArticleDTO> listAllArticle = new ArrayList<>();
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select title_article,description_article,image_article,date_article,poster,id_article  from articleTBL where status_article = 'Active'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ArticleDTO article = new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3).trim(), rs.getString(4), rs.getString(5), rs.getInt(6));
                    listAllArticle.add(article);
                }
                return listAllArticle;
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

    public ArticleDTO getNotification(int id) throws SQLException, NamingException {
       
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select title_article,description_article,image_article,date_article,poster,id_article  from articleTBL where status_article = 'Active' and id_article = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ArticleDTO article = new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5).trim(), rs.getInt(6));
                    return article;
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

    public int countArticleByContent(String content) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select count(*) from articleTBL where description_article like ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + content + "%");
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
        }

        return 0;
    }

    public List<ArticleDTO> getArticleByContent(int index, String content) throws SQLException, NamingException {
        List<ArticleDTO> listSearchArticle = new ArrayList<>();
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "with x as (select ROW_NUMBER() over(order by date_article desc) as r, * from articleTBL where status_article = 'Active'"
                        + "and description_article like ?)\n"
                        + "select title_article,description_article,image_article,date_article,poster,id_article  from x where r between ?*5-4 and ?*5";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + content + "%");
                ps.setInt(2, index);
                ps.setInt(3, index);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ArticleDTO article = new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3).trim(), rs.getString(4), rs.getString(5).trim(), rs.getInt(6));
                    listSearchArticle.add(article);
                }
                return listSearchArticle;
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

    public boolean deletePost(int id) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Delete from articleTBL where id_article = ?";
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

    public boolean adminDelete(int id) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Update articleTBL set status_article = 'Delete' where id_article = ?";
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
