/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Evaluation;
import entities.Rencontre;
import entities.Utilisateur;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import planificationrencontre.Planification;

/**
 *
 * @author Karima
 */
public class EvaluationDAO {
   static Session session=null;
    
     public static void insert(Evaluation ev)
    {
        //initialiser ma session
    session= Planification.sessionFactory.openSession();
    
//commencer la transaction
    Transaction tx=session.beginTransaction();
    //Sauvegarde
    session.save(ev);
    
      //commit  
    tx.commit();
    session.close();
    }
     
     public static void delete(Evaluation ev){
     
    session = Planification.sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        
        session.delete(ev);
        
        tx.commit();
        session.close();
}
     
      public static void updateEvaluation(Evaluation ev,String evaluation){
     
    session = Planification.sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        
        ev.setEvaluation(evaluation);
        session.update(evaluation);
        
        tx.commit();
        session.close();
}
      
      public static void allEvaluations(){
     
    session = Planification.sessionFactory.openSession();
        
     Query query = session.createQuery("from Evaluation");
        List<Evaluation> results = query.list();
        
    for(Evaluation line:results){
            System.out.println("L'evalustion est: " + line.getEvaluation());
            System.out.println("le commentaire est: " + line.getCommentaire());
            System.out.println(" Nom utilisateur : " + line.getUserName().getNom());
            System.out.println(" la rencontre " + line.getIdRencontre().getTitreRencontre());
    }
        session.close();
}
      
      public static void singleEvaluation(Rencontre rencontre,Utilisateur user){
     
    session = Planification.sessionFactory.openSession();
        
     Query query = session.createQuery("from Evaluation E where E.idRencontre = :RENCONTREID AND E.userName = :UserID");
       query.setParameter("UserID", user);
        query.setParameter("RENCONTREID", rencontre); 
        List<Evaluation> results = query.list();
        
    for(Evaluation line:results){
            System.out.println("L'evalustion est: " + line.getEvaluation());
            System.out.println("le commentaire est: " + line.getCommentaire());
            System.out.println(" Nom utilisateur : " + line.getUserName().getNom());
            System.out.println(" la rencontre " + line.getIdRencontre().getTitreRencontre());
    }
        session.close();
    }
     
      
    public static void EvaluationRencontre(Utilisateur user, Rencontre rencontre,String evaluation, String commentaire) {

    session = Planification.sessionFactory.openSession();
    session.beginTransaction();
    Evaluation eval =new Evaluation(user,rencontre,evaluation,commentaire);
    user.getEvaluationUser().add(eval);
    rencontre.getEvaluationRencontre().add(eval);
         
        session.save(eval);
    
    session.getTransaction().commit();
     session.close();
}


}
