INSERT INTO usuario VALUES
                        (1, 'Usuário de Teste', 'userteste','$2a$10$5PiyN0MsG0y886d8xWXtwuLXK0Y7zZwcN5xm82b4oDSVr7yF0O6em'),
                        (2, 'Usuário ADM', 'admin','$2a$10$gqHrslMttQWSsDSVRTK1OehkkBiXsJ/a4z2OURU./dizwOQu5Lovu');

INSERT INTO roles VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');

INSERT INTO roles_usuario VALUES (2,1),(1,2);
