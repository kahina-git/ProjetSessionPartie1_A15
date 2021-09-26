/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Karima
 */
public class Categorie {
    private int idCategorie;
    private String nomCategorie;
    private String etatCategorie;
    private Utilisateur adminUserName;
    private Set rencontresCategories = new HashSet(0);

    public Categorie() {
    }

    public Categorie(int idCategorie, String nomCategorie, String etatCategorie) {
        this.idCategorie = idCategorie;
        this.nomCategorie = nomCategorie;
        this.etatCategorie = etatCategorie;
    }

    public Categorie(int idCategorie, String nomCategorie, String etatCategorie, Utilisateur adminUserName) {
        this.idCategorie = idCategorie;
        this.nomCategorie = nomCategorie;
        this.etatCategorie = etatCategorie;
        this.adminUserName = adminUserName;
    }

   
    
    

    public Utilisateur getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(Utilisateur adminUserName) {
        this.adminUserName = adminUserName;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getEtatCategorie() {
        return etatCategorie;
    }

    public void setEtatCategorie(String etatCategorie) {
        this.etatCategorie = etatCategorie;
    }

    public Set getRencontresCategories() {
        return rencontresCategories;
    }

    public void setRencontresCategories(Set rencontresCategories) {
        this.rencontresCategories = rencontresCategories;
    }

   
    
    
}
