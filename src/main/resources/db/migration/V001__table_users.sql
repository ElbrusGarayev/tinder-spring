create table users
(
    id       serial    not null
        constraint users_pk
            primary key,
    name     varchar   not null,
    surname  varchar   not null,
    status   varchar   not null,
    email    varchar   not null,
    password varchar   not null,
    url      varchar   not null,
    lastseen timestamp not null
);

alter table users
    owner to vfqqrouexuyann;

create unique index users_id_uindex
    on users (id);

