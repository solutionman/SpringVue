INSERT INTO roles VALUES ( 1, 'USER' );
INSERT INTO roles VALUES ( 2, 'ADMIN' );
INSERT INTO roles VALUES ( 3, 'ROLE_GUEST' );
INSERT INTO roles VALUES ( 4, 'ROLE_PERFORMER' );

INSERT INTO users (id, username, password) VALUES (1,'admin','$2a$11$vwr8793lnCpvfD5T6/K16e0PXvTXGa0xE37Uez7WQJWoT4Wg5FN6K'); --12345678
INSERT INTO user_roles (user_id, role_id) values (1,2);

INSERT INTO users (id, username, password) VALUES (2,'user','$2a$11$vwr8793lnCpvfD5T6/K16e0PXvTXGa0xE37Uez7WQJWoT4Wg5FN6K'); --12345678
INSERT INTO user_roles (user_id, role_id) values (2,1);
