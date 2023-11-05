package foncier;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import remote.RemoteFoncier;
import util.Remote;

public class Devis {
    @JsonProperty("id_devis")
    private int idDevis;

    @JsonProperty("nom_devis")
    private String nomDevis;

    @JsonProperty("taux_achat")
    private double tauxAchat;

    @JsonProperty("taux_vente")
    private double tauxVente;

    public static ArrayList<Devis> getAll() {
        try {
            RemoteFoncier app = Remote.foncier();
            String data = app.getAllDevis();
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<Devis> res = mapper.readValue(data, new TypeReference<ArrayList<Devis>>() {
            });
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Devis.getAll().size());
    }

    public static Devis getOne(int idDevis) {
        try {
            RemoteFoncier app = Remote.foncier();
            String data = app.getOneDevis(idDevis);
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
