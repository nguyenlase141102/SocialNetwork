/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.interaction_article;

import java.io.Serializable;

/**
 *
 * @author ANH NGUYEN
 */
public class InteractionDTO implements Serializable{
    private int id_interaction;
    private int id_article;
    private String comments;
    private int likes;
    private int dislikes;
    private String date;
    private String poster;
    private int numberComments;
    private String email_user;
    private String typeEmotions;
    private String colorEmotions;
    public InteractionDTO() {
    }

    public InteractionDTO(int id_article, String comments, int likes, int dislikes, String date, String poster,int id) {
        this.id_article = id_article;
        this.comments = comments;
        this.likes = likes;
        this.dislikes = dislikes;
        this.date = date;
        this.poster = poster;
        this.id_interaction = id;
    }

    public InteractionDTO(int id_article, String comments, int likes, int dislikes, String date, String poster,String email,String color) {
        this.id_article = id_article;
        this.comments = comments;
        this.likes = likes;
        this.dislikes = dislikes;
        this.date = date;
        this.poster = poster;
        this.email_user = email;
        this.colorEmotions = color;
    }

    public InteractionDTO(int id_article, String comments, int likes, int dislikes, String date, String poster, String email_user) {
        this.id_article = id_article;
        this.comments = comments;
        this.likes = likes;
        this.dislikes = dislikes;
        this.date = date;
        this.poster = poster;
        this.email_user = email_user;
    }

    
    
    
    
    public InteractionDTO(int id_article, String typeEmotions) {
        this.id_article = id_article;
        this.typeEmotions = typeEmotions;
    }

    public InteractionDTO(int id_article, String poster, String colorEmotions) {
        this.id_article = id_article;
        this.poster = poster;
        this.colorEmotions = colorEmotions;
    }

    
    
    
    
    public String getColorEmotions() {
        return colorEmotions;
    }

    public void setColorEmotions(String colorEmotions) {
        this.colorEmotions = colorEmotions;
    }

    
    
    
    
    public String getTypeEmotions() {
        return typeEmotions;
    }

    public void setTypeEmotions(String typeEmotions) {
        this.typeEmotions = typeEmotions;
    }

    public InteractionDTO(int id_article, int numberComments) {
        this.id_article = id_article;
        this.numberComments = numberComments;
    }

    public InteractionDTO(int id_article, int likes, int dislikes,String color) {
        this.id_article = id_article;
        this.likes = likes;
        this.dislikes = dislikes;
        this.colorEmotions = color;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

                                                                                  

    public int getId_interaction() {
        return id_interaction;
    }

    public void setId_interaction(int id_interaction) {
        this.id_interaction = id_interaction;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getNumberComments() {
        return numberComments;
    }

    public void setNumberComments(int numberComments) {
        this.numberComments = numberComments;
    }
    
    
    
}
