/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.RencontreDAO.session;
import entities.Categorie;
import java.util.List;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import planificationrencontre.Planification;

/**
 *
 * @author Karima
 */
public class CategorieDAO {
    static Session session = null;
    
    public static String insert(Categorie categorie){
        JSONObject response = new JSONObject();
        session = Planification.sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        
        int result=(int) session.save(categorie);
       // if(result!=0)
        {
        response.accumulate("Statut", "ok");
        response.accumulate("idCategorie", categorie.getIdCategorie());
        response.accumulate("nomCategorie", categorie.getNomCategorie());
        response.accumulate("etatCategorie", categorie.getEtatCategorie());
     //   response.accumulate("adminUser", categorie.getAdminUserName().getUsername());
        }
//        else{
//        response.accumulate("Statut", "erreur");
//        response.accumulate("message", "la categorie n'est pas  bien ajoutée");
//        }
        tx.commit();
        session.close();
        return response.toString();
    }
    
    public static void delete(Categorie categorie){
     
    session = Planification.sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        
        session.delete(categorie);
        
        tx.commit();
        session.close();
}
    
    public static void updateName(Categorie categorie,String name){
     
    session = Planification.sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        
        categorie.setNomCategorie(name);
        session.update(categorie);
        
        tx.commit();
        session.close();
}
 
    
     public static void allCategorie() {
        session = Planification.sessionFactory.openSession();

        Query query = session.createQuery("from Categorie");
        List<Categorie> results = query.list();

        for (Categorie line : results) {
            
            System.out.println("Id Categorie : " + line.getIdCategorie());
            System.out.println(" Nom categorie : " + line.getNomCategorie());
            System.out.println(" L'Etat de la categorie " + line.getEtatCategorie());
            System.out.println(" Nom administrateur" + line.getAdminUserName());
        }

        
    session.close();
    }
     
     public static void singleCategorie(int idCategorie){
     
    session = Planification.sessionFactory.openSession();
        
     Query query = session.createQuery("from Categorie C where C.idCategorie= :CategorieID");
        query.setParameter("CategorieID", idCategorie);
        List<Categorie> results = query.list();
        
    for(Categorie line:results){
        System.out.println("Id Categorie : " + line.getIdCategorie());
            System.out.println(" Nom categorie : " + line.getNomCategorie());
            System.out.println(" L'Etat de la categorie " + line.getEtatCategorie());
            //System.out.println(" Nom administrateur" + line.getAdminUserName());
        }

        
    session.close();
    }
        public static Categorie singleCategorieById(int idCategorie){
     
    session = Planification.sessionFactory.openSession();
        
     Query query = session.createQuery("from Categorie C where C.idCategorie= :CategorieID");
        query.setParameter("CategorieID", idCategorie);
        List<Categorie> results = query.list();
        Categorie categorie=new Categorie();
        
    for(Categorie line:results){
        categorie=line;

        }

    session.close();
    return categorie;
    }
}
