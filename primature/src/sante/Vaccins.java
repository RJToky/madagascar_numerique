package sante;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vaccins {
    @JsonProperty("id_vaccin")
    private int idVaccin;

    @JsonProperty("nom_vaccin")
    private String nomVaccin;

    public Vaccins() {
    }

    public int getIdVaccin() {
        return idVaccin;
    }

    public void setIdVaccin(int idVaccin) {
        this.idVaccin = idVaccin;
    }

    public String getNomVaccin() {
        return nomVaccin;
    }

    public void setNomVaccin(String nomVaccin) {
        this.nomVaccin = nomVaccin;
    }

}
