package banque;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import remote.RemoteBanque;
import sante.Personnes;
import util.Remote;

public class Comptes {
    @JsonProperty("id_compte")
    private int idCompte;

    @JsonIgnore
    private int idPersonne;

    @JsonProperty("personne")
    private Personnes personne;

    @JsonProperty("num_compte")
    private String numCompte;

    @JsonProperty("solde")
    private double solde;

    public Comptes() {
    }

    public static Comptes getByIdPersonne(int idPersonne) {
        try {
            RemoteBanque app = Remote.banque();
            String data = app.getCompteByIdPersonne(idPersonne);
            ObjectMapper mapper = new ObjectMapper();
            Comptes res = mapper.readValue(data, new TypeReference<Comptes>() {
            });
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
