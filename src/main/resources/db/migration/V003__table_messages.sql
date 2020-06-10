create table messages
(
    id       serial    not null
        constraint messages_pk
            primary key,
    sender   integer   not null
        constraint messages_users_id_fk
            references users,
    receiver integer   not null
        constraint messages_users_id_fk_2
            references users,
    datetime timestamp not null,
    content  varchar
);

alter table messages
    owner to vfqqrouexuyann;

create unique index messages_id_uindex
    on messages (id);

