CREATE TABLE roles_usuario (
    usuario_id int NOT NULL,
    role_id bigint NOT NULL,
    KEY FK_role_id (role_id),
    KEY FK_usuario_id (usuario_id),
    CONSTRAINT FK_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuario (usuario_id),
    CONSTRAINT FK_role_id FOREIGN KEY (role_id) REFERENCES roles (role_id));
