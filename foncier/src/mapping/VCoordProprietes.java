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

@Entity
@Table(name = "v_coord_proprietes")
public class VCoordProprietes {
    @Id
    @JsonIgnore
    @Column(name = "id_coord_propriete")
    private int idCoordPropriete;

    @JsonIgnore
    @Column(name = "id_propriete")
    private int idPropriete;

    @JsonIgnore
    @Column(name = "latlng")
    private String latlng;

    @JsonProperty("lat")
    @Column(name = "lat")
    private double lat;

    @JsonProperty("lng")
    @Column(name = "lng")
    private double lng;

    public VCoordProprietes() {
    }

    public static ArrayList<VCoordProprietes> getByIdProprietes(EntityManager entityManager, int idPropriete) {
        String req = "select * from v_coord_proprietes where id_propriete = " + idPropriete;
        Query query = entityManager.createNativeQuery(req, VCoordProprietes.class);

        ArrayList<VCoordProprietes> res = (ArrayList<VCoordProprietes>) query.getResultList();
        return res;
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
