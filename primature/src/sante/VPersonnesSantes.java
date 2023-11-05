package sante;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import banque.Comptes;
import util.Api;

public class VPersonnesSantes {
    @JsonProperty("personne")
    private Personnes personne;

    @JsonProperty("allergies")
    private ArrayList<Allergies> allergies;

    @JsonProperty("vaccins")
    private ArrayList<Vaccins> vaccins;

    @JsonProperty("chirurgies")
    private ArrayList<Chirurgies> chirurgies;

    @JsonProperty("maladies")
    private ArrayList<Maladies> maladies;

    public VPersonnesSantes() {
    }

    public static VPersonnesSantes getByNumCin(String numCin) {
        try {
            String data = Api.fetch("http://localhost:5009/personnes_santes?num_cin=" + numCin, "GET");
            ObjectMapper mapper = new ObjectMapper();
            VPersonnesSantes res = mapper.readValue(data, VPersonnesSantes.class);
            res.getPersonne().setCompte(Comptes.getByIdPersonne(res.getPersonne().getIdPersonne()));
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Personnes getPersonne() {
        return personne;
    }

    public void setPersonne(Personnes personne) {
        this.personne = personne;
    }

    public ArrayList<Allergies> getAllergies() {
        return allergies;
    }

    public String getAllergiesToString() {
        StringBuilder out = new StringBuilder();
        if (this.getAllergies().size() == 0) {
            return "Neant";
        }
        for (int i = 0; i < this.getAllergies().size(); i++) {
            out.append(this.getAllergies().get(i).getNomAllergie()).append(", ");
        }
        out.delete(out.length() - 2, out.length());
        return out.toString();
    }

    public void setAllergies(ArrayList<Allergies> allergies) {
        this.allergies = allergies;
    }

    public ArrayList<Vaccins> getVaccins() {
        return vaccins;
    }

    public String getVaccinsToString() {
        StringBuilder out = new StringBuilder();
        if (this.getVaccins().size() == 0) {
            return "Neant";
        }
        for (int i = 0; i < this.getVaccins().size(); i++) {
            out.append(this.getVaccins().get(i).getNomVaccin()).append(", ");
        }
        out.delete(out.length() - 2, out.length());
        return out.toString();
    }

    public void setVaccins(ArrayList<Vaccins> vaccins) {
        this.vaccins = vaccins;
    }

    public ArrayList<Chirurgies> getChirurgies() {
        return chirurgies;
    }

    public String getChirurgiesToString() {
        StringBuilder out = new StringBuilder();
        if (this.getChirurgies().size() == 0) {
            return "Neant";
        }
        for (int i = 0; i < this.getChirurgies().size(); i++) {
            out.append(this.getChirurgies().get(i).getNomChirurgie()).append(", ");
        }
        out.delete(out.length() - 2, out.length());
        return out.toString();
    }

    public void setChirurgies(ArrayList<Chirurgies> chirurgies) {
        this.chirurgies = chirurgies;
    }

    public ArrayList<Maladies> getMaladies() {
        return maladies;
    }

    public String getMaladiesToString() {
        StringBuilder out = new StringBuilder();
        if (this.getMaladies().size() == 0) {
            return "Neant";
        }
        for (int i = 0; i < this.getMaladies().size(); i++) {
            out.append(this.getMaladies().get(i).getNomMaladie()).append(", ");
        }
        out.delete(out.length() - 2, out.length());
        return out.toString();
    }

    public void setMaladies(ArrayList<Maladies> maladies) {
        this.maladies = maladies;
    }

}
