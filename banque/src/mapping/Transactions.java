package mapping;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.Query;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @JsonProperty("id_transaction")
    @Column(name = "id_transaction")
    private int idTransaction;

    @JsonIgnore
    @Column(name = "id_compte_sender")
    private int idCompteSender;

    @Transient
    @JsonProperty("compte_sender")
    private Comptes compteSender;

    @JsonIgnore
    @Column(name = "id_compte_receiver")
    private int idCompteReceiver;

    @Transient
    @JsonProperty("compte_receiver")
    private Comptes compteReceiver;

    @JsonProperty("montant_transaction")
    @Column(name = "montant_transaction")
    private double montantTransaction;

    @JsonIgnore
    @Column(name = "id_devis")
    private int idDevis;

    @Transient
    @JsonProperty("devis")
    private Devis devis;

    @JsonProperty("date_heure_transaction")
    @Column(name = "date_heure_transaction")
    private String dateHeureTransaction;

    public Transactions() {
    }

    public static ArrayList<Transactions> getAll(EntityManager entityManager) {
        String req = "select * from transactions order by date_heure_transaction desc";
        Query query = entityManager.createNativeQuery(req, Transactions.class);
        ArrayList<Transactions> res = (ArrayList<Transactions>) query.getResultList();
        for (Transactions item : res) {
            item.setCompteSender(Comptes.getOne(entityManager, item.getIdCompteSender()));
            item.setCompteReceiver(Comptes.getOne(entityManager, item.getIdCompteReceiver()));
            item.setDevis(Devis.getOne(item.getIdDevis()));
        }
        return res;
    }

    public static void insert(EntityManager entityManager, int idCompteSender, int idCompteReceiver,
            double montant, int idDevis) {
        Transactions transaction = new Transactions();
        transaction.setIdCompteSender(idCompteSender);
        transaction.setIdCompteReceiver(idCompteReceiver);
        transaction.setMontantTransaction(montant);
        transaction.setIdDevis(idDevis);
        transaction.setDateHeureTransaction(LocalDateTime.now().toString());
        entityManager.merge(transaction);
    }

    public static void makeTransaction(EntityManager entityManager, int idCompteSender, int idCompteReceiver,
            double montant, int idDevis) throws Exception {

        if (montant <= 0) {
            throw new Exception("La transaction n'est pas autorisée lorsque le montant est négatif ou nul.");
        }
        try {
            Devis devis = Devis.getOne(idDevis);
            Comptes.updateSolde(entityManager, idCompteSender, -(montant * devis.getTauxAchat()));
            Comptes.updateSolde(entityManager, idCompteReceiver, +(montant * devis.getTauxVente()));
            Transactions.insert(entityManager, idCompteSender, idCompteReceiver, montant, idDevis);
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