/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.notification;

/**
 *
 * @author ANH NGUYEN
 */
public class Notification_accountDTO {
    private int id_notification;
    private String name_notification;
    private String date_notification;
    private String type_notification;
    private String content_notification;
    private int id_article;
    private String email_user;
    public Notification_accountDTO() {
    }

    public Notification_accountDTO(int id_notification, String name_notification, String date_notification, String type_notification, String content_notification, int id_article) {
        this.id_notification = id_notification;
        this.name_notification = name_notification;
        this.date_notification = date_notification;
        this.type_notification = type_notification;
        this.content_notification = content_notification;
        this.id_article = id_article;
    }

    public Notification_accountDTO(String name_notification, String date_notification, String type_notification, String content_notification, int id_article) {
        this.name_notification = name_notification;
        this.date_notification = date_notification;
        this.type_notification = type_notification;
        this.content_notification = content_notification;
        this.id_article = id_article;
    }

    public Notification_accountDTO(String name_notification, String date_notification, String type_notification, String content_notification, int id_article, String email_user) {
        this.name_notification = name_notification;
        this.date_notification = date_notification;
        this.type_notification = type_notification;
        this.content_notification = content_notification;
        this.id_article = id_article;
        this.email_user = email_user;
    }

    public Notification_accountDTO(String name_notification, String date_notification, String type_notification, int id_article, String email_user) {
        this.name_notification = name_notification;
        this.date_notification = date_notification;
        this.type_notification = type_notification;
        this.id_article = id_article;
        this.email_user = email_user;
    }

    
    
    
    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    
    
    
    
    
    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

   
    
    
    
    public int getId_notification() {
        return id_notification;
    }

    public void setId_notification(int id_notification) {
        this.id_notification = id_notification;
    }

    public String getName_notification() {
        return name_notification;
    }

    public void setName_notification(String name_notification) {
        this.name_notification = name_notification;
    }

    public String getDate_notification() {
        return date_notification;
    }

    public void setDate_notification(String date_notification) {
        this.date_notification = date_notification;
    }

    public String getType_notification() {
        return type_notification;
    }

    public void setType_notification(String type_notification) {
        this.type_notification = type_notification;
    }

    public String getContent_notification() {
        return content_notification;
    }

    public void setContent_notification(String content_notification) {
        this.content_notification = content_notification;
    }
    
    
}
