package foncier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VCoordProprietes {
    @JsonIgnore
    private int idCoordPropriete;

    @JsonIgnore
    private int idPropriete;

    @JsonIgnore
    private String latlng;

    @JsonProperty("lat")
    private double lat;

    @JsonProperty("lng")
    private double lng;

    public VCoordProprietes() {
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

}
