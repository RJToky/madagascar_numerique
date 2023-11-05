package foncier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import remote.RemoteFoncier;
import util.Remote;

public class Cours {
    @JsonProperty("id_cours")
    private int idCours;

    @JsonProperty("id_devis")
    private int idDevis;

    @JsonProperty("solde_cours")
    private double soldeCours;

    @JsonProperty("date_cours")
    private String dateCours;

    public Cours() {
    }

    public static Cours getRecent(int idDevis) {
        try {
            RemoteFoncier app = Remote.foncier();
            String data = app.getCoursRecent(idDevis);
            ObjectMapper mapper = new ObjectMapper();
            Cours res = mapper.readValue(data, new TypeReference<Cours>() {
            });
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    public double getSoldeCours() {
        return soldeCours;
    }

    public void setSoldeCours(double soldeCours) {
        this.soldeCours = soldeCours;
    }

    public String getDateCours() {
        return dateCours;
    }

    public void setDateCours(String dateCours) {
        this.dateCours = dateCours;
    }

}