INSERT INTO personnes (nom_personne, dtn_personne, num_cin) VALUES
    ('John Doe', '1990-05-15', '1822'),
    ('Jane Smith', '1985-08-20', '1900'),
    ('Bob Johnson', '1977-03-10', '1899');

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
    ('Asthme');

INSERT INTO personnes_allergies (id_personne, id_allergie) VALUES
    (1, 1), -- John Doe - Allergie au pollen
    (1, 3); -- John Doe - Allergie aux chats

INSERT INTO personnes_allergies (id_personne, id_allergie) VALUES
    (2, 2); -- Jane Smith - Allergie aux arachides


