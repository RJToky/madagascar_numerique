package service;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import mapping.Cours;
import mapping.Devis;
import mapping.Proprietes;

@Stateless
@Path("/")
public class WebService {
    @PersistenceContext(name = "persistenceUnitFoncier")
    private EntityManager entityManager;

    @GET
    @Path("/proprietes")
    public String getProprieteByIdPersonne(@QueryParam("id_personne") int idPersonne) {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";

        if (idPersonne != 0) {
            try {
                res = mapper.writeValueAsString(Proprietes.getByIdPersonne(entityManager,
                        idPersonne));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                res = mapper.writeValueAsString(Proprietes.getAll(entityManager));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return res;
    }

    @GET
    @Path("/devis")
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

    @GET
    @Path("/devis/{id}")
    public String getOneDevis(@PathParam("id") int id) {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";

        try {
            res = mapper.writeValueAsString(Devis.getOne(entityManager, id));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return res;
    }

    @GET
    @Path("/cours")
    public String getAllCours() {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";

        try {
            res = mapper.writeValueAsString(Cours.getAll(entityManager));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return res;
    }

    @GET
    @Path("/cours/{id_devis}")
    public String getCoursRecent(@PathParam("id_devis") int idDevis) {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";

        try {
            res = mapper.writeValueAsString(Cours.getRecent(entityManager, idDevis));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return res;
    }

}
