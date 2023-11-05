package sante;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Chirurgies {
    @JsonProperty("id_chirurgie")
    private int idChirurgie;

    @JsonProperty("nom_chirurgie")
    private String nomChirurgie;

    public Chirurgies() {
    }

    public int getIdChirurgie() {
        return idChirurgie;
    }

    public void setIdChirurgie(int idChirurgie) {
        this.idChirurgie = idChirurgie;
    }

    public String getNomChirurgie() {
        return nomChirurgie;
    }

    public void setNomChirurgie(String nomChirurgie) {
        this.nomChirurgie = nomChirurgie;
    }

}
