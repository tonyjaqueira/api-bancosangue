CREATE TABLE endereco (
                          endereco_id bigint NOT NULL AUTO_INCREMENT,
                          bairro varchar(255) DEFAULT NULL,
                          cep varchar(255) DEFAULT NULL,
                          endereco varchar(255) DEFAULT NULL,
                          numero int DEFAULT NULL,
                          cidade_id bigint DEFAULT NULL,
                          PRIMARY KEY (endereco_id));
