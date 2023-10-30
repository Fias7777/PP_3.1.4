DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

create table roles
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);

create table users
(
    id        int auto_increment
        primary key,
    age       int          null,
    last_name varchar(255) null,
    name      varchar(255) null,
    password  varchar(255) null,
    username  varchar(255) null,
    constraint UK_r43af9ap4edm43mmtq01oddj6
        unique (username)
);

create table users_roles
(
    user_id  int not null,
    roles_id int not null,
    primary key (user_id, roles_id),
    constraint FK2o0jvgh89lemvvo17cbqvdxaa
        foreign key (user_id) references users (id),
    constraint FKa62j07k5mhgifpp955h37ponj
        foreign key (roles_id) references roles (id)
);