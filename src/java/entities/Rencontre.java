/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import dao.CategorieDAO;
import dao.UtilisateurDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.PathParam;


public class Rencontre {
    @PathParam("idRencontre")
    private int idRencontre;
    
    @PathParam("dateR")
    private String dateR;
    
    @PathParam("dateRencontre")
    private Date dateRencontre;
    
   @PathParam("heureRencontre") 
   private String heureR;
    private Date heureRencontre;
   
   @PathParam("titreRencontre")
   private String titreRencontre;
    
   @PathParam("dureeRencontre")
   private String dureeR;
   
   private Date dureeRencontre;
   
   @PathParam("etatRencontre")
    private String etatRencontre;
   
  
    private Utilisateur U_N_Planificateur;
   
   
   @PathParam("planificateur")
   private String planificateur;
   
    private Utilisateur userName;
    
    @PathParam("catégorieID")
    private int catégorieID;
    
    private Categorie idCategorie;
    private Set evaluationRencontre = new HashSet(0);

    public Rencontre() {
    }

   
    
//constructeur utiliser pour web service inserer rencontre//    
public Rencontre(int idRencontre,String dateR, String heureR, String titreRencontre, String dureeR, String etatRencontre, int catégorieID, String planificateur) {
        this.idRencontre = idRencontre;
        this.dateR=dateR;
        this.dateRencontre =new Date(); //new SimpleDateFormat("yyyy-MM-dd").parse(dateR);
        try {
            this.heureRencontre =new SimpleDateFormat("HH:mm").parse(heureR);
        } catch (ParseException ex) {
            Logger.getLogger(Rencontre.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.titreRencontre = titreRencontre;
        try {
            this.dureeRencontre = new SimpleDateFormat("HH:mm").parse(dureeR);
        } catch (ParseException ex) {
            Logger.getLogger(Rencontre.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.etatRencontre = etatRencontre;
       this.idCategorie = CategorieDAO.singleCategorieById(catégorieID);
        this.U_N_Planificateur=UtilisateurDAO.singleUserById(planificateur);

    }

    public String getDateR() {
        return dateR;
    }

    public void setDateR(String dateR) {
        this.dateR = dateR;
    }

    public String getHeureR() {
        return heureR;
    }

    public void setHeureR(String heureR) {
        this.heureR = heureR;
    }

    public String getDureeR() {
        return dureeR;
    }

    public void setDureeR(String dureeR) {
        this.dureeR = dureeR;
    }
       
    public Rencontre(int idRencontre, Date dateRencontre, Date heureRencontre, String titreRencontre, Date dureeRencontre, String etatRencontre, Utilisateur U_N_Planificateur, Categorie idCategorie) {
        this.idRencontre = idRencontre;
        this.dateRencontre = dateRencontre;
        this.heureRencontre = heureRencontre;
        this.titreRencontre = titreRencontre;
        this.dureeRencontre = dureeRencontre;
        this.etatRencontre = etatRencontre;
        this.U_N_Planificateur = U_N_Planificateur;
        this.idCategorie = idCategorie;
    }
    
    public Rencontre(int idRencontre, String dateRencontre, String heureRencontre, String titreRencontre, String dureeRencontre, String etatRencontre, Categorie idCategorie, Utilisateur U_N_Planificateur) {
        this.idRencontre = idRencontre;
        try {
            this.dateRencontre = new SimpleDateFormat("yyyy-MM-dd").parse(dateRencontre);
        } catch (ParseException ex) {
            Logger.getLogger(Rencontre.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.heureRencontre =new SimpleDateFormat("HH:mm").parse(heureRencontre);
        } catch (ParseException ex) {
            Logger.getLogger(Rencontre.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.titreRencontre = titreRencontre;
        try {
            this.dureeRencontre = new SimpleDateFormat("HH:mm").parse(dureeRencontre);
        } catch (ParseException ex) {
            Logger.getLogger(Rencontre.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.etatRencontre = etatRencontre;
        this.idCategorie = idCategorie;
        this.U_N_Planificateur = U_N_Planificateur;
    }
    
     public String getPlanificateur() {
        return planificateur;
    }

    public void setPlanificateur(String planificateur) {
        this.planificateur = planificateur;
    }

    public int getCatégorieID() {
        return catégorieID;
    }

    public void setCatégorieID(int catégorieID) {
        this.catégorieID = catégorieID;
    }

    public Rencontre(int idRencontre, Date dateRencontre, String titreRencontre) {
        this.idRencontre = idRencontre;
        this.dateRencontre = dateRencontre;
        this.titreRencontre = titreRencontre;
    }
    
    
    public Categorie getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Categorie idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getIdRencontre() {
        return idRencontre;
    }

    public void setIdRencontre(int idRencontre) {
        this.idRencontre = idRencontre;
    }

    public Date getDateRencontre() {
        return dateRencontre;
    }

    public void setDateRencontre(Date dateRencontre) {
        this.dateRencontre = dateRencontre;
    }

    public Date getHeureRencontre() {
        return heureRencontre;
    }

    public void setHeureRencontre(Date heureRencontre) {
        this.heureRencontre = heureRencontre;
    }

    public String getTitreRencontre() {
        return titreRencontre;
    }

    public void setTitreRencontre(String titreRencontre) {
        this.titreRencontre = titreRencontre;
    }

    public Date getDureeRencontre() {
        return dureeRencontre;
    }

    public void setDureeRencontre(Date dureeRencontre) {
        this.dureeRencontre = dureeRencontre;
    }

    public String getEtatRencontre() {
        return etatRencontre;
    }

    public void setEtatRencontre(String etatRencontre) {
        this.etatRencontre = etatRencontre;
    }

    public Utilisateur getU_N_Planificateur() {
        return U_N_Planificateur;
    }

    public void setU_N_Planificateur(Utilisateur U_N_Planificateur) {
        this.U_N_Planificateur = U_N_Planificateur;
    }

   
    public Utilisateur getUserName() {
        return userName;
    }

    public void setUserName(Utilisateur userName) {
        this.userName = userName;
    }

    public Set getEvaluationRencontre() {
        return evaluationRencontre;
    }

    public void setEvaluationRencontre(Set evaluationRencontre) {
        this.evaluationRencontre = evaluationRencontre;
    }

    @Override
    public String toString() {
        return "Rencontre{" + "idRencontre=" + idRencontre + ", dateRencontre=" + dateRencontre + ", heureRencontre=" + heureRencontre.getHours()+"h"+ heureRencontre.getMinutes()+"mn" + ", titreRencontre=" + titreRencontre + ", dureeRencontre=" + dureeRencontre.getHours()+"h"+ heureRencontre.getMinutes()+"mn" + ", etatRencontre=" + etatRencontre + ", U_N_Planificateur=" + U_N_Planificateur.getNom() + ", userName=" + userName + ", idCategorie=" + idCategorie.getIdCategorie() + ", evaluationRencontre=" + evaluationRencontre + '}';
    }
    
    
    
}
