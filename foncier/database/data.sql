-- INSERT INTO proprietes (id_personne, adresse, prix_mcarre)
-- VALUES
--     (1, 'Masay', 200000.00);

-- INSERT INTO coord_proprietes (id_propriete, latlng)
-- VALUES
--     (1, st_geogfromtext('point(-18.87614230233831 47.52277803921712)')),
--     (1, st_geogfromtext('point(-18.875979872804955 47.54201465308118)')),
--     (1, st_geogfromtext('point(-18.886050206106102 47.54115587567651)'));

INSERT INTO devis (id_devis, nom_devis)
VALUES
    (1, 'Ariary'),
    (2, 'Dollar'),
    (3, 'Euro');

INSERT INTO cours (id_cours, id_devis, taux_achat, taux_vente)
VALUES
    (1, 1, 1, 1),
    (2, 2, 4930, 4450),
    (3, 3, 5150, 4890);

