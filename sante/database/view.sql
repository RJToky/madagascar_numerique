create or replace view v_personnes_santes as (
    select
        personnes.*,
        allergies.id_allergie, allergies.nom_allergie,
        vaccins.id_vaccin, vaccins.nom_vaccin,
        chirurgies.id_chirurgie, chirurgies.nom_chirurgie,
        maladies.id_maladie, maladies.nom_maladie
    from personnes
    left join personnes_allergies on personnes.id_personne = personnes_allergies.id_personne
    left join personnes_vaccins on personnes.id_personne = personnes_vaccins.id_personne
    left join personnes_chirurgies on personnes.id_personne = personnes_chirurgies.id_personne
    left join personnes_maladies on personnes.id_personne = personnes_maladies.id_personne

    left join allergies on allergies.id_allergie = personnes_allergies.id_allergie
    left join vaccins on vaccins.id_vaccin = personnes_vaccins.id_vaccin
    left join chirurgies on chirurgies.id_chirurgie = personnes_chirurgies.id_chirurgie
    left join maladies on maladies.id_maladie = personnes_maladies.id_maladie
);