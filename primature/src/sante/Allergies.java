package sante;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Allergies {
    @JsonProperty("id_allergie")
    private int idAllergie;

    @JsonProperty("nom_allergie")
    private String nomAllergie;

    public Allergies() {
    }

    public int getIdAllergie() {
        return idAllergie;
    }

    public void setIdAllergie(int idAllergie) {
        this.idAllergie = idAllergie;
    }

    public String getNomAllergie() {
        return nomAllergie;
    }

    public void setNomAllergie(String nomAllergie) {
        this.nomAllergie = nomAllergie;
    }

}
