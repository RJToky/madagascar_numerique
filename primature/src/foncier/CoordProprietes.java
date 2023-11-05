package foncier;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CoordProprietes implements Serializable {
    @JsonIgnore
    private int idCoordPropriete;

    @JsonIgnore
    private int idPropriete;

    @JsonProperty("latlng")
    private String latlng;

    public CoordProprietes() {
    }

    public int getIdCoordPropriete() {
        return idCoordPropriete;
    }

    public void setIdCoordPropriete(int idCoordPropriete) {
        this.idCoordPropriete = idCoordPropriete;
    }

    public int getIdPropriete() {
        return idPropriete;
    }

    public void setIdPropriete(int idPropriete) {
        this.idPropriete = idPropriete;
    }

    public String getLatlng() {
        return latlng;
    }

    public void setLatlng(String latlng) {
        this.latlng = latlng;
    }
}
