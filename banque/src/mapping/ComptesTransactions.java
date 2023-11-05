package mapping;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.EntityManager;

public class ComptesTransactions {
    @JsonProperty("compte")
    private Comptes compte;

    @JsonProperty("transactions")
    private ArrayList<Transactions> transactions;

    public ComptesTransactions() {
    }

    public ComptesTransactions(Comptes compte, ArrayList<Transactions> transactions) {
        this.compte = compte;
        this.transactions = transactions;
    }

    public static ArrayList<ComptesTransactions> getAll(EntityManager entityManager) {
        ArrayList<ComptesTransactions> comptesTransactions = new ArrayList<ComptesTransactions>();
        ArrayList<Comptes> comptes = Comptes.getAll(entityManager);
        ArrayList<Transactions> transactions = Transactions.getAll(entityManager);

        for (Comptes compte : comptes) {
            ArrayList<Transactions> tempTransaction = new ArrayList<Transactions>();

            for (Transactions transaction : transactions) {
                if (compte.getIdCompte() == transaction.getIdCompteSender()
                        || compte.getIdCompte() == transaction.getIdCompteReceiver()) {
                    tempTransaction.add(transaction);
                }
            }
            comptesTransactions.add(new ComptesTransactions(compte, tempTransaction));
        }

        return comptesTransactions;
    }

    public static ComptesTransactions getOne(EntityManager entityManager, int idCompte) {
        ArrayList<ComptesTransactions> comptesTransactions = ComptesTransactions.getAll(entityManager);
        for (ComptesTransactions compteTransaction : comptesTransactions) {
            if (compteTransaction.getCompte().getIdCompte() == idCompte) {
                return compteTransaction;
            }
        }
        return null;
    }

    public static ComptesTransactions getByIdPersonne(EntityManager entityManager, int idPersonne) {
        ArrayList<ComptesTransactions> comptesTransactions = ComptesTransactions.getAll(entityManager);
        for (ComptesTransactions compteTransaction : comptesTransactions) {
            if (compteTransaction.getCompte().getIdPersonne() == idPersonne) {
                return compteTransaction;
            }
        }
        return null;
    }

    public ArrayList<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transactions> transactions) {
        this.transactions = transactions;
    }

    public Comptes getCompte() {
        return compte;
    }

    public void setCompte(Comptes compte) {
        this.compte = compte;
    }

}
