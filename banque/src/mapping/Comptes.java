package mapping;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.Query;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "comptes")
public class Comptes {
    @Id
    @JsonProperty("id_compte")
    @Column(name = "id_compte")
    private int idCompte;

    @JsonIgnore
    @Column(name = "id_personne")
    private int idPersonne;

    @Transient
    @JsonProperty("personne")
    private Personnes personne;

    @JsonProperty("num_compte")
    @Column(name = "num_compte")
    private String numCompte;

    @JsonProperty("solde")
    @Column(name = "solde")
    private double solde;

    public Comptes() {
    }

    public Comptes(int idCompte, int idPersonne, String numCompte, double solde) {
        this.idCompte = idCompte;
        this.idPersonne = idPersonne;
        this.numCompte = numCompte;
        this.solde = solde;
    }

    public static ArrayList<Comptes> getAll(EntityManager entityManager) {
        String req = "select * from comptes";
        Query query = entityManager.createNativeQuery(req, Comptes.class);
        ArrayList<Comptes> res = (ArrayList<Comptes>) query.getResultList();

        for (Comptes item : res) {
            item.setPersonne(Personnes.getOne(item.getIdPersonne()));
        }

        return res;
    }

    public static Comptes getOne(EntityManager entityManager, int id) {
        String req = "select * from comptes where id_compte = " + id;
        Query query = entityManager.createNativeQuery(req, Comptes.class);
        ArrayList<Comptes> res = (ArrayList<Comptes>) query.getResultList();

        return res.isEmpty() ? null : res.get(0);
    }

    public static Comptes getByIdPersonne(EntityManager entityManager, int idPersonne) {
        String req = "select * from comptes where id_personne = " + idPersonne;
        Query query = entityManager.createNativeQuery(req, Comptes.class);
        ArrayList<Comptes> res = (ArrayList<Comptes>) query.getResultList();

        return res.isEmpty() ? null : res.get(0);
    }

    public static void updateSolde(EntityManager entityManager, int idCompte, double solde) throws Exception {
        Comptes compte = Comptes.getOne(entityManager, idCompte);

        double newSolde = compte.getSolde() + solde;
        if (newSolde >= 0) {
            compte.setSolde(newSolde);
        } else {
            throw new Exception("Solde insuffisant, votre solde est de : " + compte.getSolde() + " Ar.");
        }
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Personnes getPersonne() {
        return personne;
    }

    public void setPersonne(Personnes personne) {
        this.personne = personne;
    }

}
