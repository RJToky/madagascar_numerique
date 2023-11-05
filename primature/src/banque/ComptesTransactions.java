package banque;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import remote.RemoteBanque;
import util.Remote;

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

    public static ArrayList<ComptesTransactions> getAll() {
        return null;
    }

    public static ComptesTransactions getOne(int idCompte) {
        return null;
    }

    public static ComptesTransactions getByIdPersonne(int idPersonne) {
        try {
            RemoteBanque app = Remote.banque();
            String data = app.getComptesTransactionsByIdPersonne(idPersonne);
            ObjectMapper mapper = new ObjectMapper();
            ComptesTransactions res = mapper.readValue(data, new TypeReference<ComptesTransactions>() {
            });
            return res;
        } catch (Exception e) {
            e.printStackTrace();
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
