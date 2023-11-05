<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="sante.*, foncier.*, banque.*, java.util.ArrayList" %>

<% 
  VPersonnesSantes vPersonneSante = (VPersonnesSantes) request.getAttribute("vPersonneSante"); 
  ArrayList<Proprietes> proprietes = (ArrayList<Proprietes>) request.getAttribute("proprietes"); 
  ComptesTransactions compteTransaction = (ComptesTransactions) request.getAttribute("compteTransaction");
  ArrayList<Personnes> personnes = (ArrayList<Personnes>) request.getAttribute("personnes");
  ArrayList<Devis> deviss = (ArrayList<Devis>) request.getAttribute("deviss");
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Primature</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/style.css">
    <link rel="stylesheet" href="./assets/lib/leaflet/leaflet.css">
  </head>
  <body>
    <div class="container py-5">
      <form class="row align-items-end mb-4" action="front" method="get">
        <div class="col-lg-3 col-8">
          <label for="num_cin" class="form-label">Inserez le numero CIN</label>
          <input
            type="text"
            class="form-control"
            id="num_cin"
            name="num_cin"
            placeholder="0000"
            required
          />
        </div>
        <div class="col-auto ps-0">
          <button type="submit" class="btn btn-outline-primary">Valider</button>
        </div>
      </form>
      <% if(vPersonneSante != null) {
          if(vPersonneSante.getPersonne() != null) { %>
          <div>
            <div id="apropos" class="pt-3 pb-5 border-bottom row">
              <div class="col-lg-7 col-12">
                <h3>A PROPOS</h3>
                <div>
                  <b>Nom : </b> <%= vPersonneSante.getPersonne().getNomPersonne() %>
                </div>
                <div>
                  <b>Age : </b> <%= vPersonneSante.getPersonne().getAge() + " ans" %>
                </div>
                <div>
                  <b>Numero CIN : </b> <%= vPersonneSante.getPersonne().getNumCin() %>
                </div>
              </div>
            </div>

            <div id="sante" class="pt-3 pb-5 border-bottom row">
              <div class="col-lg-7 col-12">
                <h3>SANTE</h3>
                <div><b>Allergies : </b> <%= vPersonneSante.getAllergiesToString() %></div>
                <div><b>Vaccins : </b> <%= vPersonneSante.getVaccinsToString() %></div>
                <div><b>Chirurgies : </b> <%= vPersonneSante.getChirurgiesToString() %></div>
                <div><b>Maladies : </b> <%= vPersonneSante.getMaladiesToString() %></div>
              </div>
            </div>

            <div id="foncier" class="pt-3 pb-5 border-bottom row">
              <div class="col-12">
                <h3 class="mb-3">FONCIER</h3>

                <button class="btn btn-primary mb-3" id="ajouter_terrain" data-bs-toggle="modal" data-bs-target="#staticBackdropAddTerrain">Ajouter terrain</button>

                <h5 class="mb-0 text-secondary" style="text-decoration: underline;">Liste terrains</h5>
                <div class="mb-4 col-lg-7 col-12 table-responsive">
                  <table class="table">
                    <thead>
                      <tr>
                        <th>Adresse</th>
                        <th>Perimetre</th>
                        <th>Superficie</th>
                        <th class="d-none">Prix par ha</th>
                        <th class="d-none">Valeur</th>
                      </tr>
                    </thead>
                    <tbody>
                      <% for(int i = 0; i < proprietes.size(); i++) { %>
                        <tr>
                          <td><%= proprietes.get(i).getAdresse() %></td>
                          <td><%= proprietes.get(i).getPerimetre() + " m" %></td>
                          <td><%= proprietes.get(i).getSuperficie() + " ha" %></td>
                          <td class="d-none"><%= proprietes.get(i).getPrixMCarre() + " Ar" %></td>
                          <td class="d-none"><%= proprietes.get(i).getValeurFormatted() + " Ar" %></td>
                        </tr>
                      <% } %>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>

            <div id="banque" class="pt-3 pb-5 border-bottom row">
              <div class="col-lg-7 col-12">
                <h3>BANQUE</h3>
                <div><b>Numero : </b> <%= compteTransaction.getCompte().getNumCompte() %></div>
                <div class="mb-3"><b>Solde : </b> <%= compteTransaction.getCompte().getSolde() + " Ar" %></div>

                <h5 class="mb-0 text-secondary" style="text-decoration: underline;">Faire un transfert</h5>
                <div class="mb-4 table-responsive">
                  <table class="table">
                    <thead>
                      <tr>
                        <th>Numero</th>
                        <th>Nom personne</th>
                        <th>Action</th>
                      </tr>
                    </thead>
                    <tbody>
                      <% for(int i = 0; i < personnes.size(); i++) {
                        if(personnes.get(i).getIdPersonne() != vPersonneSante.getPersonne().getIdPersonne()) { %>
                        <tr>
                          <td><%= personnes.get(i).getCompte().getNumCompte() %></td>
                          <td><%= personnes.get(i).getNomPersonne() %></td>
                          <td>
                            <button class="btn btn-transparent" data-bs-toggle="modal" data-bs-target="#staticBackdropTransfert<%= i %>">Envoyer</button>
                          </td>
                        </tr>
                      <% }
                      } %>
                    </tbody>
                  </table>
                </div>

                <h5 class="mb-0 text-secondary" style="text-decoration: underline;">Mes transactions</h5>
                <div class="table-responsive">
                  <table class="table">
                    <thead>
                      <tr>
                        <th>Type</th>
                        <th>Numero</th>
                        <th>Nom</th>
                        <th style="text-align: right;">Montant</th>
                        <th>Devis</th>
                        <th style="text-align: right;">MGA</th>
                        <th>Heure</th>
                      </tr>
                    </thead>
                    <tbody>
                      <% for(int i = 0; i < compteTransaction.getTransactions().size(); i++) { 
                        if(compteTransaction.getCompte().getIdCompte() == compteTransaction.getTransactions().get(i).getCompteSender().getIdCompte()) { %>
                        <tr>
                          <td class="text-primary fw-bold">Envoi</td>
                          <td><%= compteTransaction.getTransactions().get(i).getCompteReceiver().getNumCompte()  %></td>
                          <td><%= compteTransaction.getTransactions().get(i).getCompteReceiver().getPersonne().getNomPersonne() %></td>
                          <td style="text-align: right;"><%= compteTransaction.getTransactions().get(i).getMontantTransaction() %></td>
                          <td><%= compteTransaction.getTransactions().get(i).getDevis().getNomDevis() %></td>
                          <td style="text-align: right;">-<%= compteTransaction.getTransactions().get(i).getDevis().getTauxAchat() * compteTransaction.getTransactions().get(i).getMontantTransaction() %> Ar</td>
                          <td><%= compteTransaction.getTransactions().get(i).getDateHeureTransaction() %></td>
                        </tr>
                      <% } else { %>
                        <tr>
                          <td class="text-success fw-bold">Reçu</td>
                          <td><%= compteTransaction.getTransactions().get(i).getCompteSender().getNumCompte()  %></td>
                          <td><%= compteTransaction.getTransactions().get(i).getCompteSender().getPersonne().getNomPersonne() %></td>
                          <td style="text-align: right;"><%= compteTransaction.getTransactions().get(i).getMontantTransaction() %></td>
                          <td><%= compteTransaction.getTransactions().get(i).getDevis().getNomDevis() %></td>
                          <td style="text-align: right;">+<%= compteTransaction.getTransactions().get(i).getDevis().getTauxVente() * compteTransaction.getTransactions().get(i).getMontantTransaction() %> Ar</td>
                          <td><%= compteTransaction.getTransactions().get(i).getDateHeureTransaction() %></td>
                        </tr>
                      <% }
                      } %>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>

          <% for(int i = 0; i < personnes.size(); i++) {
            if(personnes.get(i).getIdPersonne() != vPersonneSante.getPersonne().getIdPersonne()) { %>
            <div class="modal fade" id="staticBackdropTransfert<%= i %>" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropTransfertLabel<%= i %>" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                      <h2 class="modal-title fs-4" id="staticBackdropTransfertLabel<%= i %>">Transfert</h2>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <form action="front" method="post" class="row needs-validation" novalidate>
                      <input type="hidden" name="num_cin" value="<%= vPersonneSante.getPersonne().getNumCin() %>">
                      <input type="hidden" name="compte_sender" value="<%= vPersonneSante.getPersonne().getCompte().getIdCompte() %>">
                      <input type="hidden" name="compte_receiver" value="<%= personnes.get(i).getCompte().getIdCompte() %>">
                      <div class="col-12 mb-3">
                        <label for="imontant">Montant (*)</label>
                        <input required type="number" id="imontant" class="form-control border-grey-2" name="montant"
                          placeholder="1000000">
                        <div class="invalid-feedback">
                          Veuillez entrer un montant.
                        </div>
                      </div>
                      <div class="col-12 mb-4">
                        <label for="idevis">Devis (*)</label>
                        <select required class="form-select border-grey-2" name="devis" id="idevis">
                          <option value="">Devis</option>
                          <% for(int j = 0; j < deviss.size(); j++) { %>
                            <option value="<%= deviss.get(j).getIdDevis() %>"><%= deviss.get(j).getNomDevis() %></option>
                          <% } %>
                        </select>
                        <div class="invalid-feedback">
                          Veuillez sélectionner un devis.
                        </div>
                      </div>
                      <div class="d-flex justify-content-center gap-3">
                        <button type="reset" class="btn btn-transparent py-2" data-bs-dismiss="modal" aria-label="Close">Annuler</button>
                        <button type="submit" class="btn btn-teal py-2">Valider</button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          <% }
          } %>

          <div class="modal fade" id="staticBackdropAddTerrain" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropAddTerrainLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl">
              <div class="modal-content">
                <div class="modal-header">
                    <h2 class="modal-title fs-4" id="staticBackdropAddTerrainLabel">Ajouter terrain</h2>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  <div class="row">
                    <div class="col-lg-8 mb-lg-0 mb-3">
                      <div id="map" style="width: 100%; height: 500px;"></div>
                    </div>
                    <div class="col-lg-4">
                      <form class="row needs-validation" action="add-terrain" method="post" novalidate>
                        <input type="hidden" name="id_personne" value="<%= vPersonneSante.getPersonne().getIdPersonne() %>">
                        <input type="hidden" name="num_cin" value="<%= vPersonneSante.getPersonne().getNumCin() %>">
                        <div class="col-12 mb-3">
                          <label for="iadresse" class="form-label">Adresse</label>
                          <input
                            type="text" class="form-control" id="iadresse" name="adresse" placeholder="Antananarivo" required
                          />
                        </div>
                        <div class="col-12 mb-3">
                          <label for="iprixmcarre" class="form-label">Prix par m²</label>
                          <input
                            type="number" class="form-control" id="iprixmcarre" name="prixmcarre" placeholder="100000" required
                          />
                        </div>
                        <div class="col-12 mb-3" id="points">
                          <div class="row mb-3">
                            <div class="col-6">
                              <label for="ilatitude0" class="form-label">Latitude</label>
                              <input
                                type="text" class="form-control" id="ilatitude0" name="latitude[]" placeholder="0" required
                              />
                            </div>
                            <div class="col-6">
                              <label for="ilongitude0" class="form-label">Longitude</label>
                              <input
                                type="text" class="form-control" id="ilongitude0" name="longitude[]" placeholder="0" required
                              />
                            </div>
                          </div>
                        </div>
                        <div class="col-12 d-flex justify-content-center" style="flex-wrap: wrap; row-gap: 1rem;">
                          <button type="button" class="btn btn-danger" id="supprimer_point">Supprimer</button>
                          <button type="button" class="btn btn-transparent ms-3" id="nouveau_point">Nouveau</button>
                          <button type="submit" class="btn btn-teal ms-3" id="valider_point" disabled>Valider</button>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

      <% }
      } %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="./assets/js/form_validation.js"></script>
    <script src="./assets/lib/leaflet/leaflet.js"></script>
    <script src="./assets/js/add_terrain.js"></script>
    <% if(vPersonneSante != null) {
        if(vPersonneSante.getPersonne() != null) { %>
      <script>
          drawTerrain(<%= vPersonneSante.getPersonne().getIdPersonne() %>);
      </script>
    <% }
    } %>
  </body>
</html>
