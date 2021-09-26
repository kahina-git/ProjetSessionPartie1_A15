
package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DetailDisponibilite {
    private String idDisponibilite;
    private Date jour;
    private Date heureDebut;
    private Date duree;
    private Utilisateur username;

    public DetailDisponibilite() {
    }

    public DetailDisponibilite(String idDisponibilite, Date jour, Date heureDebut, Date duree, Utilisateur username) {
        this.idDisponibilite = idDisponibilite;
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.duree = duree;
        this.username = username;
    }

    public DetailDisponibilite(String idDisponibilite, String jour, String heureDebut, String duree, Utilisateur username) {
        this.idDisponibilite = idDisponibilite;
        try {
            this.jour = new SimpleDateFormat("yyyy-MM-dd").parse(jour);
        } catch (ParseException ex) {
            Logger.getLogger(DetailDisponibilite.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.heureDebut =new SimpleDateFormat("HH:mm").parse( heureDebut);
        } catch (ParseException ex) {
            Logger.getLogger(DetailDisponibilite.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.duree =  new SimpleDateFormat("HH:mm").parse( duree);
        } catch (ParseException ex) {
            Logger.getLogger(DetailDisponibilite.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.username = username;
    }
    public String getIdDisponibilite() {
        return idDisponibilite;
    }

    public void setIdDisponibilite(String idDisponibilite) {
        this.idDisponibilite = idDisponibilite;
    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Date getDuree() {
        return duree;
    }

    public void setDuree(Date duree) {
        this.duree = duree;
    }

    public Utilisateur getUsername() {
        return username;
    }

    public void setUsername(Utilisateur username) {
        this.username = username;
    }

    
}
