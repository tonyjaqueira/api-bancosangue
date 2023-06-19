CREATE TABLE contato (
                         contato_id bigint NOT NULL AUTO_INCREMENT,
                         celular varchar(255) DEFAULT NULL,
                         email varchar(255) DEFAULT NULL,
                         telefone_fixo varchar(255) DEFAULT NULL,
                         PRIMARY KEY (contato_id));
