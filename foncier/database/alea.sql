INSERT INTO devis (id_devis, nom_devis)
VALUES
    (1, 'Ariary'),
    (2, 'Dollar'),
    (3, 'Euro');

INSERT INTO cours (id_cours, id_devis, taux_achat, taux_vente, date_cours)
VALUES
    (1, 1, 1, 1, now()),
    (2, 2, 4930, 4450, now()),
    (3, 3, 5150, 4890, now());