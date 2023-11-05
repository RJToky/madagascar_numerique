-- POSTGRES

create database sante;
\c sante

create table personnes(
    id_personne serial primary key,
    nom_personne varchar(100) not null,
    dtn_personne date not null,
    num_cin varchar(50) unique
);

create table allergies(
    id_allergie serial primary key,
    nom_allergie varchar(50) not null
);

create table vaccins(
    id_vaccin serial primary key,
    nom_vaccin varchar(50) not null
);

create table chirurgies(
    id_chirurgie serial primary key,
    nom_chirurgie varchar(50) not null
);

create table maladies(
    id_maladie serial primary key,
    nom_maladie varchar(50) not null
);

create table personnes_allergies(
    id_personne_allergie serial primary key,
    id_personne int references personnes(id_personne),
    id_allergie int references allergies(id_allergie)
);

create table personnes_vaccins(
    id_personne_vaccin serial primary key,
    id_personne int references personnes(id_personne),
    id_vaccin int references vaccins(id_vaccin)
);

create table personnes_chirurgies(
    id_personne_chirurgie serial primary key,
    id_personne int references personnes(id_personne),
    id_chirurgie int references chirurgies(id_chirurgie)
);

create table personnes_maladies(
    id_personne_maladie serial primary key,
    id_personne int references personnes(id_personne),
    id_maladie int references maladies(id_maladie)
);
