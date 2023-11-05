package mapping;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import util.Api;

public class Devis {
    @JsonProperty("id_devis")
    private int idDevis;

    @JsonProperty("nom_devis")
    private String nomDevis;

    @JsonProperty("taux_achat")
    private double tauxAchat;

    @JsonProperty("taux_vente")
    private double tauxVente;

    public Devis() {
    }

    public static Devis getOne(int idDevis) {
        try {
            String data = Api.fetch("http://localhost:8080/foncier/app/devis/" + idDevis, "GET");
            ObjectMapper mapper = new ObjectMapper();
            Devis res = mapper.readValue(data, new TypeReference<Devis>() {
            });
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(int idDevis) {
        this.idDevis = idDevis;
    }

    public String getNomDevis() {
        return nomDevis;
    }

    public void setNomDevis(String nomDevis) {
        this.nomDevis = nomDevis;
    }

    public double getTauxAchat() {
        return tauxAchat;
    }

    public void setTauxAchat(double tauxAchat) {
        this.tauxAchat = tauxAchat;
    }

    public double getTauxVente() {
        return tauxVente;
    }

    public void setTauxVente(double tauxVente) {
        this.tauxVente = tauxVente;
    }

}
