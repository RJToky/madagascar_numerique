package banque;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import foncier.Devis;
import remote.RemoteBanque;
import util.Remote;

public class Transactions {
    @JsonProperty("id_transaction")
    private int idTransaction;

    @JsonIgnore
    @JsonProperty("id_compte_sender")
    private int idCompteSender;

    @JsonProperty("compte_sender")
    private Comptes compteSender;

    @JsonIgnore
    @JsonProperty("id_compte_receiver")
    private int idCompteReceiver;

    @JsonProperty("compte_receiver")
    private Comptes compteReceiver;

    @JsonProperty("montant_transaction")
    private double montantTransaction;

    @JsonIgnore
    @JsonProperty("id_devis")
    private int idDevis;

    @JsonProperty("devis")
    private Devis devis;

    @JsonProperty("date_heure_transaction")
    private String dateHeureTransaction;

    public Transactions() {
    }

    public static ArrayList<Transactions> getAll() {
        return null;
    }

    public static void makeTransaction(int idCompteSender, int idCompteReceiver,
            double montant, int idDevis) throws Exception {
        try {
            RemoteBanque app = Remote.banque();
            app.makeTransaction(idCompteSender, idCompteReceiver, montant, idDevis);
        } catch (Exception e) {
            throw e;
        }
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public int getIdCompteSender() {
        return idCompteSender;
    }

    public void setIdCompteSender(int idCompteSender) {
        this.idCompteSender = idCompteSender;
    }

    public int getIdCompteReceiver() {
        return idCompteReceiver;
    }

    public void setIdCompteReceiver(int idCompteReceiver) {
        this.idCompteReceiver = idCompteReceiver;
    }

    public double getMontantTransaction() {
        return montantTransaction;
    }

    public void setMontantTransaction(double montantTransaction) {
        this.montantTransaction = montantTransaction;
    }

    public int getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(int idDevis) {
        this.idDevis = idDevis;
    }

    public String getDateHeureTransaction() {
        return dateHeureTransaction;
    }

    public void setDateHeureTransaction(String dateHeureTransaction) {
        this.dateHeureTransaction = dateHeureTransaction;
    }

    public Comptes getCompteSender() {
        return compteSender;
    }

    public void setCompteSender(Comptes compteSender) {
        this.compteSender = compteSender;
    }

    public Comptes getCompteReceiver() {
        return compteReceiver;
    }

    public void setCompteReceiver(Comptes compteReceiver) {
        this.compteReceiver = compteReceiver;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

}