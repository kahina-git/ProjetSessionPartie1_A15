
package planificationrencontre;

import dao.CategorieDAO;
import dao.RencontreDAO;
import dao.UtilisateurDAO;
import entities.Categorie;
import entities.Rencontre;
import entities.Utilisateur;
import javax.ws.rs.BeanParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;


@Path("planification")
public class Planification {

    public static SessionFactory sessionFactory
           = new AnnotationConfiguration().configure().buildSessionFactory();   
    @Context
    private UriInfo context;

    
    public Planification() {
    }

    
    @GET
    @Path("AllUsers")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String allUsers() {
        
        JSONArray users = UtilisateurDAO.allUsers();
        return users.toString();

    }

    @GET
    @Path("SingleUser&{id}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String singleUser(@PathParam("id") String username) {
      
        JSONObject OutPut = UtilisateurDAO.singleUser(username);
        
        return OutPut.toString();

    }

    @GET
    @Path("InsertUser")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String InsertUser() {
        
         JSONObject OutPut = UtilisateurDAO.insert();
        
        return OutPut.toString();
        
    }
    @GET
    @Path("DeleteUser&{id}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("id") String id) 
    {
       
        JSONObject OutPut = UtilisateurDAO.deleteUser(id);
        
        return OutPut.toString();
        
    }
    
    @GET
    @Path("UpdateUser&{id}&{name}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String updateName(@PathParam("id") String id,@PathParam("name") String name) 
    {
        JSONObject OutPut= UtilisateurDAO.updateName(id,name);
        
        return OutPut.toString();
        
    }
    
  @GET
    @Path("ValiderCompte&{id}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String ValiderCompte(@PathParam("id") String id) 
    {
        JSONObject OutPut = UtilisateurDAO.ValiderCompte(id);
                
        return OutPut.toString();
        
    }
    
    //les services concerant la rencontre
    
        @GET
    @Path("rencontreList")
    @Produces("application/json")
    public String allRencontre() {
      String mainJSON;
      mainJSON=RencontreDAO.allRencontres();
        
       return mainJSON;
    }
    
        @GET
    @Path("listRencontreByUser&{id}")
    @Produces("application/json")
    public String listRencontreByUser(@PathParam("id")String username) {
      String mainJSON;
      mainJSON=RencontreDAO.listRencontreByUser(username);
        
       return mainJSON;
    }
    
    @GET
    @Path("singleRencontre&{id}")
    @Produces("application/json")
    public String singleRencontre(@PathParam("id") int rencontreID) {
     String mainJSON;
      mainJSON=RencontreDAO.singleRencontre1(rencontreID);
        
       return mainJSON;
    }
    
     @GET
    @Path("deleteRencontre&{id}")
    @Produces("application/json")
    public String deleteRencontre(@PathParam("id") int rencontreID) {
     String mainJSON;
      mainJSON=RencontreDAO.deleteById(rencontreID);
        
       return mainJSON;
    }
    
    @GET
    @Path("updateRencontre&{id}&{titre}")
    @Produces("application/json")
    public String updateRencontre(@PathParam("id") int rencontreID,@PathParam("titre") String titireRencontre) {
     String mainJSON;
      mainJSON=RencontreDAO.updateTitre(rencontreID, titireRencontre);
        
       return mainJSON;
    }
    
     @GET
    @Path("insertRencontre&{id}&{dateR}&{heureR}&{titreR}&{dureeR}&{etatR}&{catID}&{planif}")
    @Produces("application/json")
    public String insertRencontre(@PathParam("id") int rencontreID, 
                           @PathParam("dateR") String dateRencontre,
                           @PathParam("heureR") String heureRencontre,
                           @PathParam("titreR") String titreRencontre,
                           @PathParam("dureeR") String dureeRencontre,
                           @PathParam("etatR") String etatRencontre,
                           @PathParam("catID") int catégorieID,
                           @PathParam("planif") String planificateur
            
            ) 
    {
        Session session = Planification.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
                    
     Rencontre rencontre= new Rencontre(rencontreID,dateRencontre,heureRencontre,titreRencontre,dureeRencontre, etatRencontre, catégorieID,planificateur) ;

       
     String mainJSON;
     
       
     mainJSON=RencontreDAO.insert(rencontre);
        
       
return mainJSON;
    } 
   
    
        @GET
    @Path("planifierRencontre&{id}&{dateR}&{heureR}&{titreR}&{dureeR}&{etatR}&{catID}&{planif}")
    @Produces("application/json")
    public String plannifierRencontre(@PathParam("id") int rencontreID, 
                           @PathParam("dateR") String dateRencontre,
                           @PathParam("heureR") String heureRencontre,
                           @PathParam("titreR") String titreRencontre,
                           @PathParam("dureeR") String dureeRencontre,
                           @PathParam("etatR") String etatRencontre,
                           @PathParam("catID") int catégorieID,
                           @PathParam("planif") String planificateur
            
            ) 
    {
        Session session = Planification.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
     
        Utilisateur utilisateur = (Utilisateur) session.get(Utilisateur.class, planificateur);
          Categorie categorie = (Categorie) session.get(Categorie.class, catégorieID);                
    Rencontre rencontre= new Rencontre(rencontreID,dateRencontre,heureRencontre,titreRencontre,dureeRencontre, etatRencontre, categorie,utilisateur) ;

       
     String mainJSON = null;
     
       
   mainJSON=RencontreDAO.PlanifierRencontre(rencontre);
        
       
        return mainJSON;
    } 
    
    
    
    
}
