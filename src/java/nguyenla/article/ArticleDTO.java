/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.article;

import java.io.Serializable;

/**
 *
 * @author ANH NGUYEN
 */
public class ArticleDTO implements Serializable{
    private String title_article;
    private String description_article;
    private String image_article;
    private String date_article;
    private String path;
    private String poster;
    private int id_article;
    private String status_article;
    public ArticleDTO() {
    }

    public ArticleDTO(String title_article, String description_article, String image_article, String date_article, String path, String poster,String status) {
        this.title_article = title_article;
        this.description_article = description_article;
        this.image_article = image_article;
        this.date_article = date_article;
        this.path = path;
        this.poster = poster;
        this.status_article = status;
    }

    public ArticleDTO(String title_article, String description_article, String image_article, String date_article, String poster,int id) {
        this.title_article = title_article;
        this.description_article = description_article;
        this.image_article = image_article;
        this.date_article = date_article;
        this.poster = poster;
        this.id_article = id;
    }

    public ArticleDTO(String title_article, int id_article) {
        this.title_article = title_article;
        this.id_article = id_article;
    }

   

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getStatus_article() {
        return status_article;
    }

    public void setStatus_article(String status_article) {
        this.status_article = status_article;
    }

   

    public String getTitle_article() {
        return title_article;
    }

    public void setTitle_article(String title_article) {
        this.title_article = title_article;
    }

    public String getDescription_article() {
        return description_article;
    }

    public void setDescription_article(String description_article) {
        this.description_article = description_article;
    }

    public String getImage_article() {
        return image_article;
    }

    public void setImage_article(String image_article) {
        this.image_article = image_article;
    }

    public String getDate_article() {
        return date_article;
    }

    public void setDate_article(String date_article) {
        this.date_article = date_article;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
}
