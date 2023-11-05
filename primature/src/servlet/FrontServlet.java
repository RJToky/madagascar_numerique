package servlet;

import java.io.IOException;
import java.util.ArrayList;

import banque.ComptesTransactions;
import banque.Transactions;
import foncier.Devis;
import foncier.Proprietes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sante.Personnes;
import sante.VPersonnesSantes;

@WebServlet("/front")
public class FrontServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String numCin = (req.getParameter("num_cin") == null) ? "CINU1899" : req.getParameter("num_cin");

        VPersonnesSantes vPersonneSante = VPersonnesSantes.getByNumCin(numCin);
        req.setAttribute("vPersonneSante", vPersonneSante);

        if (vPersonneSante != null) {
            if (vPersonneSante.getPersonne() != null) {
                int idPersonne = vPersonneSante.getPersonne().getIdPersonne();

                ArrayList<Proprietes> proprietes = Proprietes.getByIdPersonne(idPersonne);
                req.setAttribute("proprietes", proprietes);

                ComptesTransactions compteTransaction = ComptesTransactions.getByIdPersonne(idPersonne);
                req.setAttribute("compteTransaction", compteTransaction);

                ArrayList<Personnes> personnes = Personnes.getAll();
                req.setAttribute("personnes", personnes);

                ArrayList<Devis> deviss = Devis.getAll();
                req.setAttribute("deviss", deviss);
            }
        }
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String numCin = req.getParameter("num_cin");
        int idCompteSender = Integer.parseInt(req.getParameter("compte_sender"));
        int idCompteReceiver = Integer.parseInt(req.getParameter("compte_receiver"));
        double montant = Double.parseDouble(req.getParameter("montant"));
        int idDevis = Integer.parseInt(req.getParameter("devis"));

        String message = "";
        try {
            Transactions.makeTransaction(idCompteSender, idCompteReceiver, montant, idDevis);
            message = "Transaction avec succes.";
        } catch (Exception e) {
            message = e.getMessage();
        }
        req.setAttribute("message", message);
        req.setAttribute("num_cin", numCin);
        req.getRequestDispatcher("message.jsp").forward(req, resp);
    }

    public static void main(String[] args) {
        VPersonnesSantes vPersonneSante = VPersonnesSantes.getByNumCin("CINU1899");
        System.out.println(vPersonneSante.getMaladies().size());
    }

}
