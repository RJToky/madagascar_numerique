package mapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import util.Api;

public class Personnes {
    @JsonProperty("id_personne")
    private int idPersonne;

    @JsonProperty("nom_personne")
    private String nomPersonne;

    @JsonProperty("dtn_personne")
    private String dtnPersonne;

    @JsonProperty("num_cin")
    private String numCin;

    @JsonIgnore
    private Comptes compte;

    public Personnes() {
    }

    public static Personnes getOne(int idPersonne) {
        try {
            String data = Api.fetch("http://localhost:5009/personnes/" + idPersonne, "GET");
            ObjectMapper mapper = new ObjectMapper();
            Personnes res = mapper.readValue(data, new TypeReference<Personnes>() {
            });
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Personnes> getAll(EntityManager entityManager) {
        try {
            String data = Api.fetch("http://localhost:5009/personnes", "GET");
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<Personnes> res = mapper.readValue(data, new TypeReference<ArrayList<Personnes>>() {
            });
            for (int i = 0; i < res.size(); i++) {
                res.get(i).setCompte(Comptes.getByIdPersonne(entityManager, res.get(i).getIdPersonne()));
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getAge() {
        try {
            // Créez un objet SimpleDateFormat pour analyser la date de naissance
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Analysez la date de naissance à partir de la chaîne de caractères
            Date dateOfBirth = dateFormat.parse(this.getDtnPersonne());

            // Obtenez la date actuelle
            Date currentDate = new Date();

            // Créez un objet de calendrier pour calculer la différence d'âge
            Calendar dob = Calendar.getInstance();
            dob.setTime(dateOfBirth);
            Calendar today = Calendar.getInstance();
            today.setTime(currentDate);

            // Calculez la différence d'âge
            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

            // Vérifiez si la date de naissance n'est pas encore passée cette année
            if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                age--;
            }

            return age;
        } catch (Exception e) {
            // En cas d'erreur lors de l'analyse de la date, renvoyez une valeur par défaut
            // (par exemple, -1)
            return -1;
        }
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNomPersonne() {
        return nomPersonne;
    }

    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;
    }

    public String getDtnPersonne() {
        return dtnPersonne;
    }

    public void setDtnPersonne(String dtnPersonne) {
        this.dtnPersonne = dtnPersonne;
    }

    public String getNumCin() {
        return numCin;
    }

    public void setNumCin(String numCin) {
        this.numCin = numCin;
    }

    public Comptes getCompte() {
        return compte;
    }

    public void setCompte(Comptes compte) {
        this.compte = compte;
    }

}