INSERT into roles (name)values ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

INSERT into users (age, last_name, name, password, username)
values (25, 'admin', 'admin', '$2a$10$hjcQ7ip6yirh8hsTaCfsnur4PLmNYnUDYaDOjwX7F0AHMHod0zCxe', 'ADMIN');
INSERT into users (age, last_name, name, password, username)
values (35, 'user', 'user', '$2a$10$1isF37KTiZVjJohMCyRTEe9eWRVZDKeGbihmfQ5BuFuMqtJC9ElZG', 'user');

INSERT into users_roles (user_id, roles_id)values (1, 1);
INSERT into users_roles (user_id, roles_id)values (2, 2);
