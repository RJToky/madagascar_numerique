package mapping;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.Query;
import jakarta.persistence.Table;

@Entity
@Table(name = "cours")
public class Cours {
    @Id
    @JsonProperty("id_cours")
    @Column(name = "id_cours")
    private int idCours;

    @JsonProperty("id_devis")
    @Column(name = "id_devis")
    private int idDevis;

    @JsonProperty("taux_achat")
    @Column(name = "taux_achat")
    private double tauxAchat;

    @JsonProperty("taux_vente")
    @Column(name = "taux_vente")
    private double tauxVente;

    @JsonProperty("date_cours")
    @Column(name = "date_cours")
    private String dateCours;

    public Cours() {
    }

    public static ArrayList<Cours> getAll(EntityManager entityManager) {
        String req = "select * from cours order by date_cours desc";
        Query query = entityManager.createNativeQuery(req, Cours.class);
        ArrayList<Cours> res = (ArrayList<Cours>) query.getResultList();
        return res;
    }

    public static Cours getRecent(EntityManager entityManager, int idDevis) {
        String req = "select * from cours where id_devis = " + idDevis + " order by date_cours desc";
        Query query = entityManager.createNativeQuery(req, Cours.class);
        ArrayList<Cours> res = (ArrayList<Cours>) query.getResultList();
        return res.isEmpty() ? null : res.get(0);
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public int getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(int idDevis) {
        this.idDevis = idDevis;
    }

    public String getDateCours() {
        return dateCours;
    }

    public void setDateCours(String dateCours) {
        this.dateCours = dateCours;
    }

    public double getTauxAchat() {
        return tauxAchat;
    }

    public void setTauxAchat(double tauxAchat) {
        this.tauxAchat = tauxAchat;
    }

    public double getTauxVente() {
        return tauxVente;
    }

    public void setTauxVente(double tauxVente) {
        this.tauxVente = tauxVente;
    }

}
