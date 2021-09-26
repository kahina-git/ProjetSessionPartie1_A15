
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

    public static void insert(Utilisateur utilisateur) {

        session = Planification.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(utilisateur);

        tx.commit();
        session.close();
    }

    
    
    
    public static String delete(String id) {
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
        }
        else{
            response.accumulate("Statut", "Non autoriser");
            response.accumulate("message", "l'utilisateur avec id: " + id + " est un administrateur");
        
        }
        session.close();
        return response.toString();
    }
    
    /*public static void delete(Utilisateur utilisateur) {

        session = Planification.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.delete(utilisateur);

        tx.commit();
        session.close();
    }*/
//    public static String delete(String id) {
//        JSONObject response = new JSONObject();
//        session = Planification.sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//
//       Utilisateur utilisateur = (Utilisateur) session.get(Utilisateur.class, id);
//        
//       if (utilisateur == null) {
//            response.accumulate("Statut", "erreur");
//            response.accumulate("message", "l'utilisateur  avec id: " + id + " n'existe pas");
//        } else {
//           utilisateur.setNom("Hello");
//           session.update(utilisateur);
//              //  session.delete(utilisateur);
//            response.accumulate("Statut", "OK");
//            response.accumulate("message", "l'utilisateur avec id: " + id + " est bien maj");
//        }
//
//        //session.delete(utilisateur);
//        tx.commit();
//        session.close();
//        return response.toString();
//    }

    /* public static void updateName(String id,String name){
     
         Session session = Planification.sessionFactory.openSession();
         Transaction tx=session.beginTransaction();
        Query query = session.createQuery("update Utilisateur U set U.nom =:name where U.username= :UserID");
        query.setParameter("UserID", id);
        query.setParameter("name",name);
        query.executeUpdate();
        //Utilisateur utilisateur= FindUserById(id);
        
          
        tx.commit();
        session.close();
}*/
    public static JSONObject updateName(JSONObject user) {

        Session session = Planification.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
         //utilisateur = new Utilisateur();
        ///utilisateur.setMotpasse(user.getString("motpasse"));
        
        Utilisateur utilisateur = (Utilisateur) session.get(Utilisateur.class, user.getString("username"));
       
        
            user.accumulate("NomUtilisateur", utilisateur.getUsername());
            user.accumulate("Nom", utilisateur.getNom());
            user.accumulate("Prenom", utilisateur.getPrenom());
            user.accumulate("Mail", utilisateur.getEmail());
            user.accumulate("NumeroTelephone", utilisateur.getNumtel());
            user.accumulate("Adresse", utilisateur.getAdresse());
            user.accumulate("Type utilisateur", utilisateur.getTypeuser());
            user.accumulate("Etat validation", utilisateur.getEtatvalidation());
            user.accumulate("Etat activation", utilisateur.getEtatactivation());
            user.accumulate("Admin validateur", utilisateur.getAdminValidateur().getNom());
            user.accumulate("Admin désactivateur", utilisateur.getAdminDesactivateur().getNom());
        
        tx.commit();
        session.close();
        return user;
    }

    
            
       /*     public static void updateName(Utilisateur user) {

        Session session = Planification.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Utilisateur utilisateur = (Utilisateur) session.load(Utilisateur.class, user.getUsername());
        utilisateur.setNom("Yacine");
        session.update(utilisateur);
        
        tx.commit();
        session.close();
    }*/
    public static void updateTypeUser(Utilisateur utilisateur, String typeUser) {

        session = Planification.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        utilisateur.setTypeuser(typeUser);
        session.update(utilisateur);

        tx.commit();
        session.close();
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
            user.accumulate("adminValidateur", line.getAdminValidateur().getNom());
            user.accumulate("adminDesactivateur", line.getAdminDesactivateur().getNom());

            users.add(user);
            user.clear();
        }
        session.close();
        return users;

    }

    public static JSONObject singleUser(String id) {

        session = Planification.sessionFactory.openSession();

        /*Query query = session.createQuery("from Utilisateur U where U.username= :UserID");
        query.setParameter("UserID", id);
        List<Utilisateur> results = query.list();*/
        JSONObject user = new JSONObject();
        Utilisateur utilisateur = (Utilisateur)session.get(Utilisateur.class, id);
        
            user.accumulate("NomUtilisateur", utilisateur.getUsername());
            user.accumulate("Nom", utilisateur.getNom());
            user.accumulate("Prenom", utilisateur.getPrenom());
            user.accumulate("Mail", utilisateur.getEmail());
            user.accumulate("NumeroTelephone", utilisateur.getNumtel());
            user.accumulate("Adresse", utilisateur.getAdresse());
            user.accumulate("Type utilisateur", utilisateur.getTypeuser());
            user.accumulate("Etat validation", utilisateur.getEtatvalidation());
            user.accumulate("Etat activation", utilisateur.getEtatactivation());
            user.accumulate("Admin validateur", utilisateur.getAdminValidateur().getNom());
            user.accumulate("Admin désactivateur", utilisateur.getAdminDesactivateur().getNom());
        
        
        session.close();
        return user;
    }

    public static Utilisateur FindUserById(String id) {

        session = Planification.sessionFactory.openSession();

        Query query = session.createQuery("from Utilisateur U where U.username= :UserID");
        query.setParameter("UserID", id);
        List<Utilisateur> results = query.list();
        Utilisateur user = new Utilisateur();
        for (Utilisateur line : results) {
            user.setUsername(line.getUsername());
            user.setNom(line.getNom());
            user.setPrenom(line.getPrenom());
            user.setEmail(line.getEmail());
            user.setNumtel(line.getNumtel());
            user.setAdresse(line.getAdresse());
            user.setTypeuser(line.getTypeuser());
        }
        session.close();
        return user;
    }
     public static Utilisateur singleUserById(String id){
     
    session = Planification.sessionFactory.openSession();
        
     Query query = session.createQuery("from Utilisateur U where U.username= :UserID");
        query.setParameter("UserID", id);
        List<Utilisateur> results = query.list();
         Utilisateur planificateur =new Utilisateur();
    for(Utilisateur line:results){
        
        planificateur=line;
      
    }
        session.close();
       return planificateur;
}

}
