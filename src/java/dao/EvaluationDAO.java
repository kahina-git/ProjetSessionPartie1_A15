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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import planificationrencontre.Planification;

/**
 *
 * @author Karima
 */
public class EvaluationDAO {

    static Session session = null;

    public static String insert(Evaluation evaluation) {
        //initialiser ma session
        session = Planification.sessionFactory.openSession();

        JSONObject evaluationObject = new JSONObject();

        //commencer la transaction
        Transaction tx = session.beginTransaction();

        //Sauvegarde
        session.save(evaluationObject);
        evaluationObject.accumulate("Statut", "OK");
        evaluationObject.accumulate("message", "Insertion réussie de l'evaluation:  " + evaluation.getIdRencontre());

        //commit  
        tx.commit();
        session.close();
        return evaluationObject.toString();
    }

    public static String delete(int idRencontre) {

        session = Planification.sessionFactory.openSession();
        JSONObject evaluation = new JSONObject();
        Transaction tx = session.beginTransaction();
        Evaluation eval = (Evaluation) session.get(Evaluation.class, idRencontre);

        if (eval == null) {
            evaluation.accumulate("status", "erreur");
            evaluation.accumulate("message", "l'evaluation avec un id de rencontre: " + idRencontre + "n'existe pas");
        } else {
            session.delete(eval);
            evaluation.accumulate("status", "OK");
            evaluation.accumulate("message", "l'evaluation avec un id de rencontre: " + idRencontre + "est bien supprimee");
        }

        tx.commit();
        session.close();
        return evaluation.toString();
    }

    public static JSONObject updateEvaluation(int idRencontre, String evaluation) {

        session = Planification.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Evaluation eval = (Evaluation) session.get(Evaluation.class, idRencontre);
        JSONObject evaluationObject = new JSONObject();

        if (eval == null) {
            evaluationObject.accumulate("Statut", "erreur");
            evaluationObject.accumulate("message", "l'evalution  avec idRencontre: " + idRencontre + " n'existe pas");
        } else {
            eval.setEvaluation(evaluation);
            session.update(eval);
            evaluationObject.accumulate("Statut", "OK");
            evaluationObject.accumulate("message", "l'evalution  avec idRencontre: " + idRencontre + " est mise à jour");
        }
        tx.commit();
        session.close();
        return evaluationObject;
    }

    public static JSONArray allEvaluations() {

        session = Planification.sessionFactory.openSession();

        Query query = session.createQuery("from Evaluation");
        List<Evaluation> results = query.list();
        JSONObject evaluation = new JSONObject();
        JSONArray evaluations = new JSONArray();
        for (Evaluation line : results) {
            evaluation.accumulate("username", line.getUserName());
            evaluation.accumulate("idrencontre", line.getIdRencontre());
            evaluation.accumulate("evaluation", line.getEvaluation());
            evaluation.accumulate("commentaire", line.getCommentaire());

            evaluations.add(evaluation);
            evaluation.clear();
        }
        session.close();
        return evaluations;
    }

    public static JSONObject singleEvaluation(int idRencontre) {

        session = Planification.sessionFactory.openSession();

        JSONObject evaluation = new JSONObject();
        Evaluation eval = (Evaluation) session.get(Evaluation.class, idRencontre);

        evaluation.accumulate("username", eval.getUserName());
        evaluation.accumulate("idrencontre", eval.getIdRencontre());
        evaluation.accumulate("evaluation", eval.getEvaluation());
        evaluation.accumulate("commentaire", eval.getCommentaire());

        session.close();
        return evaluation;
    }

    public static void EvaluationRencontre(Utilisateur user, Rencontre rencontre, String evaluation, String commentaire) {

        session = Planification.sessionFactory.openSession();
        session.beginTransaction();
        Evaluation eval = new Evaluation(user, rencontre, evaluation, commentaire);
        user.getEvaluationUser().add(eval);
        rencontre.getEvaluationRencontre().add(eval);

        session.save(eval);

        session.getTransaction().commit();
        session.close();
    }

}
