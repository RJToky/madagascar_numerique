package remote;

import jakarta.ejb.Remote;

@Remote
public interface RemoteBanque {
    String getCompteByIdPersonne(int idPersonne);

    String getComptesTransactionsByIdPersonne(int idPersonne);

    void makeTransaction(int idCompteSender, int idCompteReceiver,
            double montant, int idDevis) throws Exception;
}
