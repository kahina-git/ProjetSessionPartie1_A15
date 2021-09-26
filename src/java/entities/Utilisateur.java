/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Karima
 */
public class Utilisateur {
    @QueryParam("username")
    private String username;
    @QueryParam("motpasse")
    private String motpasse;
    @QueryParam("nom")
    private String nom;
    @QueryParam("prenom")
    private String prenom;
    @QueryParam("email")
    private String email;
    @QueryParam("numtel")
    private String numtel;
    @QueryParam("adresse")
    private String adresse;
    @QueryParam("typeuser")
    private String typeuser;
    @QueryParam("etatvalidation")
    private String etatvalidation;
    @QueryParam("etatactivation")
    private String etatactivation;
    @QueryParam("adminValidateur")
    private Utilisateur adminValidateur ;
    @QueryParam("adminDesactivateur")
    private Utilisateur adminDesactivateur; 
    @QueryParam("disponibilite")
    private Set<DetailDisponibilite> disponibilite = new HashSet<DetailDisponibilite>(0);
    @QueryParam("compteValide")
    private Set<Utilisateur> compteValide = new HashSet<Utilisateur>(0);
    @QueryParam("compteDesactive")
    private Set<Utilisateur> compteDesactive = new HashSet<Utilisateur>(0);
    @QueryParam("rencontres")
    private Set<Rencontre> rencontres = new HashSet<Rencontre>(0);
    @QueryParam("categories")
    private Set<Categorie> categories = new HashSet<Categorie>(0);
    @QueryParam("rencontresAnnules")
    private Set<Rencontre> rencontresAnnules = new HashSet<Rencontre>(0);
    @QueryParam("evaluationUser")
    private Set<Evaluation> evaluationUser = new HashSet<Evaluation>(0);

    public Utilisateur() {
      
    }

    public Utilisateur(String username, String motpasse) {
        this.username = username;
        this.motpasse = motpasse;
    }

    
    public Utilisateur(String username, String motpasse, String nom, String prenom, String email, String numtel, String adresse, String typeuser, String etatvalidation, String etatactivation, Utilisateur adminValidateur) {
        this.username = username;
        this.motpasse = motpasse;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.adresse = adresse;
        this.typeuser = typeuser;
        this.etatvalidation = etatvalidation;
        this.etatactivation = etatactivation;
        this.adminValidateur = adminValidateur;
        
      }
    
     public Utilisateur(String username, String motpasse, String nom, String prenom, String email, String numtel, String adresse, String typeuser, String etatvalidation, String etatactivation,Utilisateur adminValidateur,Set compteValide,Set compteDesactive,Set disponibilite,Set rencontres) {
        this.username = username;
        this.motpasse = motpasse;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.adresse = adresse;
        this.typeuser = typeuser;
        this.etatvalidation = etatvalidation;
        this.etatactivation = etatactivation;
        this.adminValidateur = adminValidateur;
        this.compteValide = compteValide;
        this.compteDesactive = compteDesactive;
        this.disponibilite = disponibilite;
        this.rencontres = rencontres;
       }

    
    public Utilisateur(String username, String motpasse, String nom, String prenom, String email, String numtel, String adresse, String typeuser, String etatvalidation, String etatactivation) {
        this.username = username;
        this.motpasse = motpasse;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.adresse = adresse;
        this.typeuser = typeuser;
        this.etatvalidation = etatvalidation; 
        this.etatactivation = etatactivation;
    }

    public Utilisateur(String username, String motpasse, String nom, String prenom, String email, String numtel, String adresse, String typeuser, String etatvalidation, String etatactivation, Utilisateur adminValidateur, Utilisateur adminDesactivateur) {
        this.username = username;
        this.motpasse = motpasse;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.adresse = adresse;
        this.typeuser = typeuser;
        this.etatvalidation = etatvalidation;
        this.etatactivation = etatactivation;
        this.adminValidateur = adminValidateur;
        this.adminDesactivateur = adminDesactivateur;
    }

    public Set<Utilisateur> getCompteValide() {
        return compteValide;
    }

    public void setCompteValide(Set<Utilisateur> compteValide) {
        this.compteValide = compteValide;
    }

    public Set<Utilisateur> getCompteDesactive() {
        return compteDesactive;
    }

    public void setCompteDesactive(Set<Utilisateur> compteDesactive) {
        this.compteDesactive = compteDesactive;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMotpasse() {
        return motpasse;
    }

    public void setMotpasse(String motpasse) {
        this.motpasse = motpasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTypeuser() {
        return typeuser;
    }

    public void setTypeuser(String typeuser) {
        this.typeuser = typeuser;
    }

    public String getEtatvalidation() {
        return etatvalidation;
    }

    public void setEtatvalidation(String etatvalidation) {
        this.etatvalidation = etatvalidation;
    }

    public String getEtatactivation() {
        return etatactivation;
    }

    public void setEtatactivation(String etatactivation) {
        this.etatactivation = etatactivation;
    }

    public Utilisateur getAdminValidateur() {
        return adminValidateur ;
    }

    public void setAdminValidateur(Utilisateur adminValidateur) {
        this.adminValidateur = adminValidateur;
    }

    public Utilisateur getAdminDesactivateur() {
        return adminDesactivateur ;
    }

    public void setAdminDesactivateur(Utilisateur adminDesactivateur) {
        this.adminDesactivateur = adminDesactivateur;
    }

    public Set<DetailDisponibilite> getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Set disponibilite) {
        this.disponibilite = disponibilite;
    }

    public Set<Rencontre> getRencontres() {
        return rencontres;
    }

    public void setRencontres(Set rencontres) {
        this.rencontres = rencontres;
    }

    public Set<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(Set categories) {
        this.categories = categories;
    }

    public Set<Rencontre> getRencontresAnnules() {
        return rencontresAnnules;
    }

    public void setRencontresAnnules(Set rencontresAnnules) {
        this.rencontresAnnules = rencontresAnnules;
    }

    public Set<Evaluation> getEvaluationUser() {
        return evaluationUser;
    }

    public void setEvaluationUser(Set evaluationUser) {
        this.evaluationUser = evaluationUser;
    }

    /*@Override
    public String toString() {
        return "Utilisateur{" + "username=" + username + ", motpasse=" + motpasse + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", numtel=" + numtel + ", adresse=" + adresse + ", typeuser=" + typeuser + ", etatvalidation=" + etatvalidation + ", etatactivation=" + etatactivation + ", adminValidateur=" + adminValidateur + ", adminDesactivateur=" + adminDesactivateur + ", disponibilite=" + disponibilite + ", compteValide=" + compteValide + ", compteDesactive=" + compteDesactive + ", rencontres=" + rencontres + ", categories=" + categories + ", rencontresAnnules=" + rencontresAnnules + ", evaluationUser=" + evaluationUser + '}';
    }*/
    
    /*@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
      sb.append("username:").append(username).append(";");
      sb.append("motpasse:").append(motpasse).append(";");
     return sb.toString();
       
    }*/
    
    @Override
    public String toString() {
        return "Utilisateur{" +
                "username:'" + username + '\'' +
                ", motpasse:" + motpasse + '\'' +
                '}';
    }

     public Boolean isNotAdmin(){
         
         boolean admin = true; 
         if (this.getTypeuser().equals("Administrateur")){
             admin = false;
         }
         
         return admin;
     }
    
}
