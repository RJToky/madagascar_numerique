package remote;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mapping.Comptes;
import mapping.ComptesTransactions;
import mapping.Transactions;

@Stateless
public class SessionBean implements RemoteBanque {

    @PersistenceContext(name = "persistenceUnitFoncier")
    private EntityManager entityManager;

    @Override
    public String getCompteByIdPersonne(int idPersonne) {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";
        try {
            res = mapper.writeValueAsString(Comptes.getByIdPersonne(entityManager, idPersonne));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public String getComptesTransactionsByIdPersonne(int idPersonne) {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";
        try {
            res = mapper.writeValueAsString(ComptesTransactions.getByIdPersonne(entityManager, idPersonne));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void makeTransaction(int idCompteSender, int idCompteReceiver, double montant, int idDevis)
            throws Exception {
        try {
            Transactions.makeTransaction(entityManager, idCompteSender, idCompteReceiver, montant, idDevis);
        } catch (Exception e) {
            throw e;
        }
    }

}
