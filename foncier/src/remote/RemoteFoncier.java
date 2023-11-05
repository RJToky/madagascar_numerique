package remote;

import jakarta.ejb.Remote;

@Remote
public interface RemoteFoncier {
    String getAllProprietes();

    String getProprieteByIdPersonne(int idPersonne);

    String getAllDevis();

    String getOneDevis(int idDevis);

    String getCoursRecent(int idDevis);

    void addPropriete(int idPersonne, String adresse, double prixMCarre, double[] lats, double[] lngs);
}
