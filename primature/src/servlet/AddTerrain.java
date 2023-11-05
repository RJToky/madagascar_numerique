package servlet;

import java.io.IOException;

import foncier.Proprietes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add-terrain")
public class AddTerrain extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idPersonne = Integer.parseInt(req.getParameter("id_personne"));
        String adresse = req.getParameter("adresse");
        double prixMCarre = Double.parseDouble(req.getParameter("prixmcarre"));
        String[] latitudes = (String[]) req.getParameterValues("latitude[]");
        String[] longitudes = (String[]) req.getParameterValues("longitude[]");

        double[] lats = new double[latitudes.length];
        double[] lngs = new double[longitudes.length];
        for (int i = 0; i < latitudes.length; i++) {
            lats[i] = Double.parseDouble(latitudes[i]);
            lngs[i] = Double.parseDouble(longitudes[i]);
        }

        Proprietes.add(idPersonne, adresse, prixMCarre, lats, lngs);

        String numCin = req.getParameter("num_cin");
        resp.sendRedirect("http://localhost:8080/primature/front?num_cin=" + numCin);
    }

}
