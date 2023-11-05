-- MYSQL

create database banque;
use banque;

create table comptes(
    id_compte int primary key auto_increment,
    id_personne int not null,
    num_compte varchar(100) not null,
    solde double precision not null
);

create table transactions(
    id_transaction int primary key auto_increment,
    id_compte_sender int references comptes(id_compte),
    id_compte_receiver int references comptes(id_compte),
    montant_transaction double precision not null,
    id_devis int,
    date_heure_transaction datetime not null
);