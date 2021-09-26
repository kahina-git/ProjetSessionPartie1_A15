/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.UtilisateurDAO.session;
import entities.Categorie;
import entities.Rencontre;
import entities.Utilisateur;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import net.sf.ehcache.hibernate.HibernateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import planificationrencontre.Planification;


public class RencontreDAO {
    static Session session = null;
    
    public static String insert(Rencontre rencontre) {
        JSONObject response = new JSONObject();
      
        if (findRencontreById(rencontre.getIdRencontre())) {
            
            response.accumulate("Statut", "erreur");
            response.accumulate("message", "Insertion échouée de la rencontre:  " + rencontre.getIdRencontre() + "  la rencotre existe déjà");
            
        } else {
            session = Planification.sessionFactory.openSession();
             Transaction tx = session.beginTransaction();
            session.save(rencontre);

            response.accumulate("Statut", "OK");
            response.accumulate("message", "Insertion réussie de la rencontre:  " + rencontre.getIdRencontre());
            tx.commit();
            session.close();
        }
        

        return response.toString();
    }
    
     
    public static String deleteById(int rencontreID) {

        session = Planification.sessionFactory.openSession();
        JSONObject response = new JSONObject();
        Transaction tx = session.beginTransaction();
      Rencontre rencontre = (Rencontre) session.get(Rencontre.class, rencontreID);
      
        if (rencontre == null) {
            response.accumulate("Statut", "erreur");
            response.accumulate("message", "la rencontre avec id: " + rencontreID + " n'existe pas");
        } else {
                session.delete(rencontre);
            response.accumulate("Statut", "OK");
            response.accumulate("message", "la rencontre avec id: " + rencontreID + " est bien supprimée");
        }

        tx.commit();
        session.close();
        return response.toString();
    }
         
 
    public static String updateTitre(int rencontreID, String titreRencontre) {
        JSONObject response = new JSONObject();
        session = Planification.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Rencontre rencontre = (Rencontre) session.get(Rencontre.class, rencontreID);

        if (rencontre == null) {
            response.accumulate("Statut", "erreur");
            response.accumulate("message", "la rencontre avec id: " + rencontreID + " n'existe pas");
        } else {
            rencontre.setTitreRencontre(titreRencontre);
            session.update(rencontre);
            response.accumulate("Statut", "OK");
            response.accumulate("message", "la rencontre avec id: " + rencontreID + " a était bien mise à jours");
        }

        tx.commit();
        session.close();
        return response.toString();
    }
     
  
    public static String allRencontres() {

        session = Planification.sessionFactory.openSession();

        Query query = session.createQuery("from Rencontre");
        List<Rencontre> results = query.list();
        JSONArray mainJSON = new JSONArray();
        JSONObject singleRencontre = new JSONObject();

        int idRencontre;
        String dateRencontre;
        String heure, duree;
        String titreRencontre;
        String etatRencontre;
        String U_N_Planificateur;
        int idCategorie;

        for (Rencontre line : results) {
            idRencontre = line.getIdRencontre();
            dateRencontre = line.getDateRencontre().toString();

            heure = heureRencontre(line);

            titreRencontre = line.getTitreRencontre();

            duree = dureeRencontre(line);

            etatRencontre = line.getEtatRencontre();

            U_N_Planificateur = line.getU_N_Planificateur().getUsername();

            idCategorie = line.getIdCategorie().getIdCategorie();

            singleRencontre.accumulate("idRencontre", idRencontre);
            singleRencontre.accumulate("dateRencontre", dateRencontre);
            singleRencontre.accumulate("heureRencontre", heure);
            singleRencontre.accumulate("titreRencontre", titreRencontre);
            singleRencontre.accumulate("dureeRencontre", duree);
            singleRencontre.accumulate("etatRencontre", etatRencontre);
            singleRencontre.accumulate("U_N_Planificateur", U_N_Planificateur);
            singleRencontre.accumulate("idCategorie", idCategorie);

            mainJSON.add(singleRencontre);
            singleRencontre.clear();

        }
        session.close();
        return mainJSON.toString();
    }

    public static String heureRencontre(Rencontre line) {
        Date heureRencontre;
        String heure;
        heureRencontre=line.getHeureRencontre();
        heure=new SimpleDateFormat("H:mm").format(heureRencontre);
        return heure;
    }

    public static String dureeRencontre(Rencontre line) {
        Date dureeRencontre;
        String duree;
        String dureeFormat;
        dureeRencontre=line.getDureeRencontre();
        duree=new SimpleDateFormat("H:mm").format(dureeRencontre);
        dureeFormat=duree.substring(0, 1).concat("h").concat(duree.substring(2, 4));
        return dureeFormat;
    }
     
     
    public static String singleRencontre(int rencontreID) {

        session = Planification.sessionFactory.openSession();

        
        JSONObject singleRencontre = new JSONObject();
        Rencontre rencontre = (Rencontre) session.get(Rencontre.class, rencontreID);
            
            int idRencontre;
            String dateRencontre;
            String heure, duree;
            String titreRencontre;
            String etatRencontre;
            String U_N_Planificateur;
            Categorie idCategorie;

            idRencontre = rencontre.getIdRencontre();
            dateRencontre = rencontre.getDateRencontre().toString();
            heure = heureRencontre(rencontre);
            titreRencontre = rencontre.getTitreRencontre();
            duree = dureeRencontre(rencontre);
            etatRencontre = rencontre.getEtatRencontre();
            U_N_Planificateur = rencontre.getU_N_Planificateur().getUsername();
            idCategorie=rencontre.getIdCategorie();
             
            singleRencontre.accumulate("idRencontre", idRencontre);
            singleRencontre.accumulate("dateRencontre", dateRencontre);
            singleRencontre.accumulate("heureRencontre", heure);
            singleRencontre.accumulate("titreRencontre", titreRencontre);
            singleRencontre.accumulate("dureeRencontre", duree);
            singleRencontre.accumulate("etatRencontre", etatRencontre);
            singleRencontre.accumulate("Catégrie", idCategorie.getIdCategorie());
            singleRencontre.accumulate("U_N_Planificateur", U_N_Planificateur);

        session.close();
        return singleRencontre.toString();
    }
    
       public static String singleRencontre1(int rencontreID) {

        session = Planification.sessionFactory.openSession();

        Query query = session.createQuery("from Rencontre R where R.idRencontre= :RencontreID");
        query.setParameter("RencontreID", rencontreID);
        List<Rencontre> results = query.list();
        JSONObject singleRencontre = new JSONObject();
        for (Rencontre line : results) {
 
            int idRencontre;
            String dateRencontre;
            String heure, duree;
            String titreRencontre;
            String etatRencontre;
            String U_N_Planificateur;
            Categorie idCategorie;
            idRencontre = line.getIdRencontre();
            dateRencontre = line.getDateRencontre().toString();
            heure = heureRencontre(line);
            titreRencontre = line.getTitreRencontre();
            duree = dureeRencontre(line);
            etatRencontre = line.getEtatRencontre();
            U_N_Planificateur = line.getU_N_Planificateur().getUsername();
            
            idCategorie=line.getIdCategorie();
            singleRencontre.accumulate("idRencontre", idRencontre);
            singleRencontre.accumulate("dateRencontre", dateRencontre);
            singleRencontre.accumulate("heureRencontre", heure);
            singleRencontre.accumulate("titreRencontre", titreRencontre);
            singleRencontre.accumulate("dureeRencontre", duree);
            singleRencontre.accumulate("etatRencontre", etatRencontre);
            singleRencontre.accumulate("Catégrie", idCategorie.getIdCategorie());
            singleRencontre.accumulate("U_N_Planificateur", U_N_Planificateur);

        }
        session.close();
        return singleRencontre.toString();
    }
     
      
       public static boolean findRencontreById(int id){
     
    session = Planification.sessionFactory.openSession();
        boolean trouve=true;
     Query query = session.createQuery("from Rencontre R where R.idRencontre= :RencontreID");
        query.setParameter("RencontreID", id);
        List<Rencontre> results = query.list();
      
        if (results.size()==0){
            trouve=false;
            }
        session.close();
        return trouve;
        
             
    
        
       
}
       
       
            public static String  PlanifierRencontre(Rencontre rencontre) {
        
                JSONObject response = new JSONObject();
                session = Planification.sessionFactory.openSession();
                session.beginTransaction();

                rencontre.getU_N_Planificateur().getRencontres().add(rencontre);

                int result=(int) session.save(rencontre);
                session.getTransaction().commit();
                if(result!=0){
                    response.accumulate("Statut", "OK");
                    response.accumulate("message", "la rencontre est bien inséré");
                    response.accumulate("idRencontre", rencontre.getIdRencontre());
                    response.accumulate("TitreRencontre", rencontre.getTitreRencontre());
                    response.accumulate("Le plannificateur", rencontre.getU_N_Planificateur().getUsername());
                
                
                }
                session.close();
                return response.toString();
        
    }
            
     
      
       
     
      public static void AnnulerRencontre(Utilisateur user, Rencontre rencontre) {

    session = Planification.sessionFactory.openSession();
    session.beginTransaction();

    user.getRencontresAnnules().add(rencontre);
     rencontre.setEtatRencontre("Annulée");
     
        session.update(rencontre);
    
    session.getTransaction().commit();
     session.close();
}

    public static String listRencontreByUser(String username) {
        
         session = Planification.sessionFactory.openSession();

        
        JSONObject singleRencontre = new JSONObject();
        JSONObject userJson = new JSONObject();
        JSONArray listeRencontre=new JSONArray();
        Rencontre rencontre=null;
      
        Utilisateur user = (Utilisateur) session.get(Utilisateur.class, username);
        userJson.accumulate("userName:", user.getUsername());
        userJson.accumulate("type: ", user.getTypeuser());
        
             int idRencontre;
            String dateRencontre;
            String heure, duree;
            String titreRencontre;
            String etatRencontre;
            String U_N_Planificateur;
            Categorie idCategorie;  
            Iterator it = user.getRencontres().iterator();
        
        while (  it.hasNext()) {
            
            rencontre=(Rencontre) it.next();
            idRencontre = rencontre.getIdRencontre();
            dateRencontre = rencontre.getDateRencontre().toString();
            heure = heureRencontre(rencontre);
            titreRencontre = rencontre.getTitreRencontre();
            duree = dureeRencontre(rencontre);
            etatRencontre = rencontre.getEtatRencontre();
            U_N_Planificateur = rencontre.getU_N_Planificateur().getUsername();
            idCategorie=rencontre.getIdCategorie();
            singleRencontre.accumulate("idRencontre", idRencontre);
            singleRencontre.accumulate("dateRencontre", dateRencontre);
            singleRencontre.accumulate("heureRencontre", heure);
            singleRencontre.accumulate("titreRencontre", titreRencontre);
            singleRencontre.accumulate("dureeRencontre", duree);
            singleRencontre.accumulate("etatRencontre", etatRencontre);
            singleRencontre.accumulate("Catégrie", idCategorie.getIdCategorie());
            singleRencontre.accumulate("U_N_Planificateur", U_N_Planificateur);
            listeRencontre.add(singleRencontre);
            singleRencontre.clear();
        }
        userJson.accumulate("liste rencontre", listeRencontre);
            
            
            

           

        session.close();
        return userJson.toString();
    }

    static void supprimerRencontresPlanifierPar(Utilisateur utilisateur) {
        UtilisateurDAO.session = Planification.sessionFactory.openSession();
        Transaction tx = UtilisateurDAO.session.beginTransaction();
        
        
        
        
        
        Rencontre rencontre = null;
        Iterator it = utilisateur.getRencontres().iterator();
        while (it.hasNext()) {
            rencontre = (Rencontre) it.next();
          session.delete(rencontre);
            tx.commit();
           session.close();
        }
    }
    
}
