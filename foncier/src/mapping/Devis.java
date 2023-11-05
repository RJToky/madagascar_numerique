package mapping;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.Query;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "devis")
public class Devis {
    @Id
    @JsonProperty("id_devis")
    @Column(name = "id_devis")
    private int idDevis;

    @JsonProperty("nom_devis")
    @Column(name = "nom_devis")
    private String nomDevis;

    @Transient
    @JsonProperty("taux_achat")
    private double tauxAchat;

    @Transient
    @JsonProperty("taux_vente")
    private double tauxVente;

    public Devis() {
    }

    public static ArrayList<Devis> getAll(EntityManager entityManager) {
        String req = "select * from devis";
        Query query = entityManager.createNativeQuery(req, Devis.class);
        ArrayList<Devis> res = (ArrayList<Devis>) query.getResultList();
        for (Devis item : res) {
            Cours cour = Cours.getRecent(entityManager, item.getIdDevis());
            item.setTauxAchat(cour.getTauxAchat());
            item.setTauxVente(cour.getTauxVente());
        }
        return res;
    }

    public static Devis getOne(EntityManager entityManager, int idDevis) {
        String req = "select * from devis where id_devis = " + idDevis;
        Query query = entityManager.createNativeQuery(req, Devis.class);
        ArrayList<Devis> res = (ArrayList<Devis>) query.getResultList();
        for (Devis item : res) {
            Cours cour = Cours.getRecent(entityManager, item.getIdDevis());
            item.setTauxAchat(cour.getTauxAchat());
            item.setTauxVente(cour.getTauxVente());
            return item;
        }
        return null;
    }

    public int getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(int idDevis) {
        this.idDevis = idDevis;
    }

    public String getNomDevis() {
        return nomDevis;
    }

    public void setNomDevis(String nomDevis) {
        this.nomDevis = nomDevis;
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
