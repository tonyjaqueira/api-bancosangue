ALTER TABLE pessoa
    ADD FOREIGN KEY (endereco_id) REFERENCES endereco (endereco_id),
    ADD FOREIGN KEY (contato_id) REFERENCES contato (contato_id);
