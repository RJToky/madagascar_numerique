INSERT INTO comptes (id_personne, num_compte, solde)
VALUES
    (1, '123456789', 125000.00),
    (2, '987654321', 540000.00),
    (3, '555555555', 475000.00);

INSERT INTO transactions (id_transaction, id_compte_sender, id_compte_receiver, montant_transaction, id_devis, date_heure_transaction)
VALUES
    (1, 1, 2, 50000.00, 1, '2023-10-10 08:00:00'),
    (2, 2, 3, 10000.00, 1, '2023-10-10 09:30:00'),
    (3, 3, 1, 25000.00, 1, '2023-10-10 12:15:00');

