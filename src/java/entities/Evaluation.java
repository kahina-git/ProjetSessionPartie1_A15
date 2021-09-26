/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;

/**
 *
 * @author Karima
 */
public class Evaluation implements Serializable{
    
     private Utilisateur userName;
     private Rencontre idRencontre;
     private String evaluation;
     private String commentaire;
  
  

    public Evaluation() {
    }

    public Evaluation(Utilisateur userName, Rencontre idRencontre, String evaluation, String commentaire) {
        this.userName = userName;
        this.idRencontre = idRencontre;
        this.evaluation = evaluation;
        this.commentaire = commentaire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Utilisateur getUserName() {
        return userName;
    }

    public void setUserName(Utilisateur userName) {
        this.userName = userName;
    }

    public Rencontre getIdRencontre() {
        return idRencontre;
    }

    public void setIdRencontre(Rencontre idRencontre) {
        this.idRencontre = idRencontre;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

   
    
}
