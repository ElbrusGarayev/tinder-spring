create table likes
(
    id     serial  not null
        constraint likes_pk
            primary key,
    who    integer not null
        constraint likes_users_id_fk
            references users,
    whom   integer not null
        constraint likes_users_id_fk_2
            references users,
    status boolean not null
);

alter table likes
    owner to vfqqrouexuyann;

create unique index likes_id_uindex
    on likes (id);

