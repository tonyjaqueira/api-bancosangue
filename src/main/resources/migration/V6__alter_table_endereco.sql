ALTER TABLE endereco
    ADD FOREIGN KEY (cidade_id) REFERENCES cidade (cidade_id);
