/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.account;

import java.io.Serializable;

/**
 *
 * @author ANH NGUYEN
 */
public class AccountDTO implements Serializable{
     private String email_user;
     private String name_user;
     private String password_user;
     private String role_user;
     private String status_user;
     private String code;
    public AccountDTO() {
    }

    public AccountDTO(String name_user, String role_user, String status_user) {
        this.name_user = name_user;
        this.role_user = role_user;
        this.status_user = status_user;
    }

    public AccountDTO(String email_user, String name_user, String password_user, String role_user, String status_user) {
        this.email_user = email_user;
        this.name_user = name_user;
        this.password_user = password_user;
        this.role_user = role_user;
        this.status_user = status_user;
    }

    public AccountDTO(String email_user, String name_user, String role_user, String code) {
        this.email_user = email_user;
        this.name_user = name_user;
        this.role_user = role_user;
        this.code = code;
    }

    
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
    
    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }

    public String getRole_user() {
        return role_user;
    }

    public void setRole_user(String role_user) {
        this.role_user = role_user;
    }

    public String getStatus_user() {
        return status_user;
    }

    public void setStatus_user(String status_user) {
        this.status_user = status_user;
    }
     
     
     
}
