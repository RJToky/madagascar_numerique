INSERT INTO personnes (nom_personne, dtn_personne, num_cin) VALUES
    ('Safidy Mendrika', '2004-10-27', 'CINU1899'),
    ('Tokiniaina Judichael', '2004-12-14', 'CINU1822'),
    ('Mirija Marc', '2003-11-30', 'CINU1900'),
    ('Miora', '2004-09-09', 'CINU1790');

INSERT INTO allergies (nom_allergie) VALUES
    ('Allergie au pollen'),
    ('Allergie aux arachides'),
    ('Allergie aux chats');

INSERT INTO vaccins (nom_vaccin) VALUES
    ('Vaccin contre la grippe'),
    ('Vaccin contre la COVID-19'),
    ('Vaccin contre la rougeole');

INSERT INTO chirurgies (nom_chirurgie) VALUES
    ('Appendicectomie'),
    ('Chirurgie cardiaque'),
    ('Chirurgie de la cataracte');

INSERT INTO maladies (nom_maladie) VALUES
    ('Diabète de type 2'),
    ('Hypertension artérielle'),
    ('Asthme'),
    ('Mikohaka');

INSERT INTO personnes_allergies (id_personne, id_allergie) VALUES
    (1, 1),
    (1, 3),
    (2, 2);

INSERT INTO personnes_maladies (id_personne, id_maladie) VALUES
    (3, 3),
    (1, 1),
    (2, 2),
    (3, 4);

INSERT INTO personnes_chirurgies (id_personne, id_chirurgie) VALUES
    (4, 1),
    (2, 2),
    (3, 3);