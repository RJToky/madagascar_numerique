-- POSTGRES

create database foncier;
\c foncier

create table proprietes(
    id_propriete serial primary key,
    id_personne int not null,
    adresse varchar(100) not null,
    prix_mcarre double precision not null,
    date_acquisation timestamp default now()
);

create table coord_proprietes(
    id_coord_propriete serial primary key,
    id_propriete int references proprietes(id_propriete),
    latlng geography(point, 4326) not null
);

create table devis(
    id_devis serial primary key,
    nom_devis varchar(50) not null
);

create table cours(
    id_cours serial primary key,
    id_devis int references devis(id_devis),
    taux_achat double precision not null,
    taux_vente double precision not null,
    date_cours timestamp default now()
);
