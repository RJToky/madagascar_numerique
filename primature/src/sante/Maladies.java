package sante;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Maladies {
    @JsonProperty("id_maladie")
    private int idMaladie;

    @JsonProperty("nom_maladie")
    private String nomMaladie;

    public Maladies() {
    }

    public int getIdMaladie() {
        return idMaladie;
    }

    public void setIdMaladie(int idMaladie) {
        this.idMaladie = idMaladie;
    }

    public String getNomMaladie() {
        return nomMaladie;
    }

    public void setNomMaladie(String nomMaladie) {
        this.nomMaladie = nomMaladie;
    }

}
