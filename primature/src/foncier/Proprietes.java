package foncier;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import remote.RemoteFoncier;
import util.Remote;

public class Proprietes {
    @JsonProperty("id_propriete")
    private int idPropriete;

    @JsonProperty("id_personne")
    private int idPersonne;

    @JsonProperty("adresse")
    private String adresse;

    @JsonProperty("prix_mcarre")
    private double prixMCarre;

    @JsonProperty("date_acquisation")
    private String dateAcquisation;

    @JsonProperty("perimetre")
    private double perimetre;

    @JsonProperty("superficie")
    private double superficie;

    @JsonProperty("valeur")
    private double valeur;

    @JsonProperty("coordonnees")
    private ArrayList<VCoordProprietes> coordonnees;

    public Proprietes() {
    }

    public static ArrayList<Proprietes> getByIdPersonne(int idPersonne) {
        try {
            RemoteFoncier app = Remote.foncier();
            String data = app.getProprieteByIdPersonne(idPersonne);
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<Proprietes> res = mapper.readValue(data, new TypeReference<ArrayList<Proprietes>>() {
            });
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getValeurFormatted() {
        DecimalFormat decimalFormat = new DecimalFormat("########");
        return decimalFormat.format(this.getValeur());
    }

    public static void add(int idPersonne, String adresse, double prixMCarre, double[] lats, double[] lngs) {
        RemoteFoncier app = Remote.foncier();
        app.addPropriete(idPersonne, adresse, prixMCarre, lats, lngs);
    }

    public int getIdPropriete() {
        return idPropriete;
    }

    public void setIdPropriete(int idPropriete) {
        this.idPropriete = idPropriete;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getPrixMCarre() {
        return prixMCarre;
    }

    public void setPrixMCarre(double prixMCarre) {
        this.prixMCarre = prixMCarre;
    }

    public String getDateAcquisation() {
        return dateAcquisation;
    }

    public void setDateAcquisation(String dateAcquisation) {
        this.dateAcquisation = dateAcquisation;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public ArrayList<VCoordProprietes> getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(ArrayList<VCoordProprietes> coordonnees) {
        this.coordonnees = coordonnees;
    }

    public double getPerimetre() {
        return perimetre;
    }

    public void setPerimetre(double perimetre) {
        this.perimetre = perimetre;
    }

}
