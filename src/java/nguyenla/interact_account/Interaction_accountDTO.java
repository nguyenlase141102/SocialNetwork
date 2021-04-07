/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.interact_account;

/**
 *
 * @author ANH NGUYEN
 */
public class Interaction_accountDTO {
    private int id_interaction_account;
    private int id_interaction;
    private int id_article;
    private String time_interaction;

    public Interaction_accountDTO() {
    }

    public Interaction_accountDTO(int id_interaction, int id_article, String time_interaction) {
        this.id_interaction = id_interaction;
        this.id_article = id_article;
        this.time_interaction = time_interaction;
    }

    public int getId_interaction_account() {
        return id_interaction_account;
    }

    public void setId_interaction_account(int id_interaction_account) {
        this.id_interaction_account = id_interaction_account;
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

    public String getTime_interaction() {
        return time_interaction;
    }

    public void setTime_interaction(String time_interaction) {
        this.time_interaction = time_interaction;
    }
    
    
    
    
}
