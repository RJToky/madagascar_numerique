package remote;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mapping.Cours;
import mapping.Devis;
import mapping.Proprietes;

@Stateless
public class SessionBean implements RemoteFoncier {

    @PersistenceContext(name = "persistenceUnitFoncier")
    private EntityManager entityManager;

    @Override
    public String getAllProprietes() {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";
        try {
            res = mapper.writeValueAsString(Proprietes.getAll(entityManager));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public String getProprieteByIdPersonne(int idPersonne) {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";
        try {
            res = mapper.writeValueAsString(Proprietes.getByIdPersonne(entityManager, idPersonne));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public String getAllDevis() {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";
        try {
            res = mapper.writeValueAsString(Devis.getAll(entityManager));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public String getOneDevis(int idDevis) {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";
        try {
            res = mapper.writeValueAsString(Devis.getOne(entityManager, idDevis));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public String getCoursRecent(int idDevis) {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";
        try {
            res = mapper.writeValueAsString(Cours.getRecent(entityManager, idDevis));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void addPropriete(int idPersonne, String adresse, double prixMCarre, double[] lats, double[] lngs) {
        Proprietes.add(entityManager, idPersonne, adresse, prixMCarre, lats, lngs);
    }

}
