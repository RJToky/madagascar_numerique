package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import mapping.Comptes;
import mapping.ComptesTransactions;

@Stateless
@Path("/")
public class WebService {
    @PersistenceContext(name = "persistenceUnitBanque")
    private EntityManager entityManager;

    @GET
    @Path("/comptes/{id}")
    public String getOneCompte(@PathParam("id") int id) {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";
        try {
            res = mapper.writeValueAsString(Comptes.getOne(entityManager, id));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    @GET
    @Path("/comptes")
    public String getComptesByIdPersonne(@QueryParam("id_personne") int idPersonne) {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";

        if (idPersonne != 0) {
            try {
                res = mapper.writeValueAsString(Comptes.getByIdPersonne(entityManager, idPersonne));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                res = mapper.writeValueAsString(Comptes.getAll(entityManager));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return res;
    }

    @GET
    @Path("/comptes_transactions/{id}")
    public String getOneCompteTransaction(@PathParam("id") int id) {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";
        try {
            res = mapper.writeValueAsString(ComptesTransactions.getOne(entityManager, id));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    @GET
    @Path("/comptes_transactions")
    public String getComptesTransactionsByIdPersonne(@QueryParam("id_personne") int idPersonne) {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";

        if (idPersonne != 0) {
            try {
                res = mapper.writeValueAsString(ComptesTransactions.getByIdPersonne(entityManager, idPersonne));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                res = mapper.writeValueAsString(ComptesTransactions.getAll(entityManager));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return res;
    }
}
