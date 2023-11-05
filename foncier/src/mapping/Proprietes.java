package mapping;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.Query;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "proprietes")
public class Proprietes {
    @Id
    @JsonProperty("id_propriete")
    @Column(name = "id_propriete")
    private int idPropriete;

    @JsonProperty("id_personne")
    @Column(name = "id_personne")
    private int idPersonne;

    @JsonProperty("adresse")
    @Column(name = "adresse")
    private String adresse;

    @JsonProperty("prix_mcarre")
    @Column(name = "prix_mcarre")
    private double prixMCarre;

    @JsonProperty("date_acquisation")
    @Column(name = "date_acquisation")
    private String dateAcquisation;

    @Transient
    @JsonProperty("perimetre")
    private double perimetre;

    @Transient
    @JsonProperty("superficie")
    private double superficie;

    @Transient
    @JsonProperty("valeur")
    private double valeur;

    @Transient
    @JsonProperty("coordonnees")
    private ArrayList<VCoordProprietes> coordonnees;

    public Proprietes() {
    }

    public static ArrayList<Proprietes> getAll(EntityManager entityManager) {
        String req = "select * from proprietes";
        Query query = entityManager.createNativeQuery(req, Proprietes.class);
        ArrayList<Proprietes> res = (ArrayList<Proprietes>) query.getResultList();
        for (Proprietes propriete : res) {
            propriete.setCoordonnees(VCoordProprietes.getByIdProprietes(entityManager, propriete.getIdPropriete()));
            propriete.setPerimetre(entityManager);
            propriete.setSuperficie(entityManager);
            propriete.setValeur((propriete.getSuperficie() * 10000) * propriete.getPrixMCarre());
        }
        return res;
    }

    public static ArrayList<Proprietes> getByIdPersonne(EntityManager entityManager, int idPersonne) {
        String req = "select * from proprietes where id_personne = " + idPersonne + " order by date_acquisation desc";
        Query query = entityManager.createNativeQuery(req, Proprietes.class);
        ArrayList<Proprietes> res = (ArrayList<Proprietes>) query.getResultList();
        for (Proprietes propriete : res) {
            propriete.setCoordonnees(VCoordProprietes.getByIdProprietes(entityManager, propriete.getIdPropriete()));
            propriete.setPerimetre(entityManager);
            propriete.setSuperficie(entityManager);
            propriete.setValeur((propriete.getSuperficie() * 10000) * propriete.getPrixMCarre());
        }
        return res;
    }

    public static Proprietes getLastId(EntityManager entityManager) {
        String req = "select * from proprietes order by id_propriete desc";
        Query query = entityManager.createNativeQuery(req, Proprietes.class);
        ArrayList<Proprietes> res = (ArrayList<Proprietes>) query.getResultList();
        return res.isEmpty() ? null : res.get(0);
    }

    public static void add(EntityManager entityManager, int idPersonne, String adresse, double prixMCarre,
            double[] lats, double[] lngs) {
        String sql = "insert into proprietes(id_personne, adresse, prix_mcarre) values(" + idPersonne
                + ", '" + adresse + "', " + prixMCarre + ")";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();

        Proprietes propriete = Proprietes.getLastId(entityManager);

        for (int i = 0; i < lats.length; i++) {
            sql = "insert into coord_proprietes(id_propriete, latlng) values(" + propriete.getIdPropriete()
                    + ", st_geogfromtext('point(" + lats[i] + " " + lngs[i] + ")'))";
            query = entityManager.createNativeQuery(sql);
            query.executeUpdate();
        }
    }

    public void setSuperficie(EntityManager entityManager) {
        if (this.getCoordonnees().size() > 2) {
            StringBuilder builder = new StringBuilder();
            builder.append("select ")
                    .append("st_area(")
                    .append("st_geographyfromtext(")
                    .append("'polygon((");

            for (int i = 0; i < this.getCoordonnees().size(); i++) {
                builder.append(this.getCoordonnees().get(i).getLng()).append(" ")
                        .append(this.getCoordonnees().get(i).getLat()).append(",");
            }

            builder.append(this.getCoordonnees().get(0).getLng()).append(" ")
                    .append(this.getCoordonnees().get(0).getLat());

            builder.append("))'")
                    .append(")")
                    .append(") / 10000 as superficie");

            String sql = builder.toString();
            Query query = entityManager.createNativeQuery(sql);
            String res = query.getSingleResult().toString();
            this.setSuperficie(Double.parseDouble(res));

        } else {
            this.setSuperficie(0.0);
        }
    }

    public void setPerimetre(EntityManager entityManager) {
        if (this.getCoordonnees().size() > 2) {
            StringBuilder builder = new StringBuilder();
            builder.append("select ")
                    .append("st_perimeter(")
                    .append("st_geographyfromtext(")
                    .append("'polygon((");

            for (int i = 0; i < this.getCoordonnees().size(); i++) {
                builder.append(this.getCoordonnees().get(i).getLng()).append(" ")
                        .append(this.getCoordonnees().get(i).getLat()).append(",");
            }

            builder.append(this.getCoordonnees().get(0).getLng()).append(" ")
                    .append(this.getCoordonnees().get(0).getLat());

            builder.append("))'")
                    .append(")")
                    .append(") as perimetre");

            String sql = builder.toString();
            Query query = entityManager.createNativeQuery(sql);
            String res = query.getSingleResult().toString();
            this.setPerimetre(Double.parseDouble(res));

        } else {
            this.setPerimetre(0.0);
        }
    }

    public int getIdPropriete() {
        return idPropriete;
    }

    public void setIdPropriete(int idPropriete) {
        this.idPropriete = idPropriete;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getPrixMCarre() {
        return prixMCarre;
    }

    public void setPrixMCarre(double prixMCarre) {
        this.prixMCarre = prixMCarre;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getDateAcquisation() {
        return dateAcquisation;
    }

    public void setDateAcquisation(String dateAcquisation) {
        this.dateAcquisation = dateAcquisation;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public ArrayList<VCoordProprietes> getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(ArrayList<VCoordProprietes> coordonnees) {
        this.coordonnees = coordonnees;
    }

    public double getPerimetre() {
        return perimetre;
    }

    public void setPerimetre(double perimetre) {
        this.perimetre = perimetre;
    }

}