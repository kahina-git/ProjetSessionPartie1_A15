
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.RencontreDAO.session;
import entities.Rencontre;
import entities.Utilisateur;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import planificationrencontre.Planification;

/**
 *
 * @author Karima
 */
public class UtilisateurDAO {

    static Session session = null;

    public static JSONObject insert() {
        session = Planification.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Utilisateur adminvalidateur = (Utilisateur) session.get(Utilisateur.class, "SKarima");
       
        Utilisateur utilisateur = new Utilisateur("Robert", "test", "test", "test", "test@gmail.com", "1111111", "1478 Rue jarry", "Etudiant", "Non Validé", "Non Activé", adminvalidateur, null);
        session.save(utilisateur);
        //session.flush();
        //session.refresh(utilisateur);
         JSONObject response = new JSONObject();
         response.accumulate("Statut", "OK");
         response.accumulate("message", "Un nouveau utilisateur ajouté");
        tx.commit();
        session.close();
        return response;
                
    }

    public static JSONObject deleteUser(String id) {

        session = Planification.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Utilisateur user = (Utilisateur) session.get(Utilisateur.class, id);
        JSONObject response = new JSONObject();
        if (user == null) {
            response.accumulate("Statut", "erreur");
            response.accumulate("message", "l'utilisateur  avec id: " + id + " n'existe pas");
        } else {
            session.delete(user);
           response.accumulate("Statut", "OK");
           response.accumulate("message", "l'utilisateur avec id: " + id + " est supprimé");
        }
        
        tx.commit();
        session.close();
        return response;
    }
    
    public static JSONObject delete(String id) {
        JSONObject response = new JSONObject();

        session = Planification.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Utilisateur utilisateur = (Utilisateur) session.get(Utilisateur.class, id);
        session.close();
        if (utilisateur.isNotAdmin()) {
            if (!utilisateur.getRencontres().isEmpty()) {

                Iterator it = utilisateur.getRencontres().iterator();
                Rencontre rencontre = null;

                while (it.hasNext()) {
                    rencontre = (Rencontre) it.next();
                    session.delete(rencontre);
                }

                session.delete(utilisateur);
                tx.commit();
            }

            response.accumulate("Statut", "OK");
            response.accumulate("message", "l'utilisateur avec id: " + id + " est bien supprimé");

            session.delete(utilisateur);
        } else {
            response.accumulate("Statut", "Non autoriser");
            response.accumulate("message", "l'utilisateur avec id: " + id + " est un administrateur");

        }
        session.close();
        return response;
    }

       public static JSONObject updateName(String id,String name){
     
         session = Planification.sessionFactory.openSession();
         Transaction tx=session.beginTransaction();
         Utilisateur user = (Utilisateur) session.get(Utilisateur.class, id);
         JSONObject response = new JSONObject();
        
        if (user == null) {
            response.accumulate("Statut", "erreur");
            response.accumulate("message", "l'utilisateur  avec id: " + id + " n'existe pas");
        } else {
            user.setNom(name);
            session.update(user);
           response.accumulate("Statut", "OK");
           response.accumulate("message", "l'utilisateur avec id: " + id + " est mis à jour");
        }
        
        tx.commit();
        session.close();
        return response;
         
}
   
    public static JSONArray allUsers() {

        session = Planification.sessionFactory.openSession();

        Query query = session.createQuery("from Utilisateur");
        List<Utilisateur> results = query.list();
        JSONObject user = new JSONObject();

        JSONArray users = new JSONArray();
        for (Utilisateur line : results) {
            user.accumulate("username", line.getUsername());
            user.accumulate("Nom", line.getNom());
            user.accumulate("Prenom", line.getPrenom());
            user.accumulate("Mail", line.getEmail());
            user.accumulate("numerotelephone", line.getNumtel());
            user.accumulate("Adresse", line.getAdresse());
            user.accumulate("TypeUtilisateur", line.getTypeuser());
            user.accumulate("etatvalidation", line.getEtatvalidation());
            user.accumulate("etatActivation", line.getEtatactivation());
            
            users.add(user);
            user.clear();
        }
        session.close();
        return users;

    }

    public static JSONObject singleUser(String id) {

        session = Planification.sessionFactory.openSession();

        JSONObject user = new JSONObject();
        Utilisateur utilisateur = (Utilisateur) session.get(Utilisateur.class, id);

        user.accumulate("NomUtilisateur", utilisateur.getUsername());
        user.accumulate("Nom", utilisateur.getNom());
        user.accumulate("Prenom", utilisateur.getPrenom());
        user.accumulate("Mail", utilisateur.getEmail());
        user.accumulate("NumeroTelephone", utilisateur.getNumtel());
        user.accumulate("Adresse", utilisateur.getAdresse());
        user.accumulate("Type utilisateur", utilisateur.getTypeuser());
        user.accumulate("Etat validation", utilisateur.getEtatvalidation());
        user.accumulate("Etat activation", utilisateur.getEtatactivation());
        

        session.close();
        return user;
    }

    public static Utilisateur singleUserById(String id) {

        session = Planification.sessionFactory.openSession();

        Query query = session.createQuery("from Utilisateur U where U.username= :UserID");
        query.setParameter("UserID", id);
        List<Utilisateur> results = query.list();
        Utilisateur planificateur = new Utilisateur();
        for (Utilisateur line : results) {

            planificateur = line;

        }
        session.close();
        return planificateur;
    }

    public static JSONObject ValiderCompte(String id) {

        
        session = Planification.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        JSONObject response = new JSONObject();
        
        Utilisateur user = (Utilisateur) session.get(Utilisateur.class, id);
        
        if (user == null) {
            response.accumulate("Statut", "erreur");
            response.accumulate("message", "l'utilisateur  avec id: " + id + " n'existe pas");
        } else {
        user.setEtatvalidation("Validé");
        /* avec une interface graphique je recupere le id de l'administrateur validateur
           adminvalidateur.getCompteValide().add(user);
        */
          session.update(user);
           response.accumulate("Statut", "OK");
           response.accumulate("message", "le compte avec id: " + id + " est validé");
        }
         
        tx.commit();
        session.close();
        return response;
    }
}
