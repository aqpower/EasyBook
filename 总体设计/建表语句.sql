-- auto-generated definition
create table admin
(
    id       int auto_increment
        primary key,
    name     varchar(255)      not null,
    password varchar(225)      not null,
    role     tinyint default 2 not null
)
    collate = utf8mb4_general_ci;


-- auto-generated definition
create table blacklist
(
    id            int auto_increment
        primary key,
    user_id       int null,
    black_user_id int null,
    constraint blacklist_ibfk_1
        foreign key (user_id) references user (id),
    constraint blacklist_ibfk_2
        foreign key (black_user_id) references user (id)
)
    collate = utf8mb4_general_ci;

create index black_user_id
    on blacklist (black_user_id);

create index user_id
    on blacklist (user_id);
-- auto-generated definition


create table care
(
    id            int auto_increment
        primary key,
    care_user_id  int null,
    cared_user_id int not null,
    constraint care_user_id
        foreign key (care_user_id) references user (id),
    constraint cared_user_id
        foreign key (cared_user_id) references user (id)
)
    collate = utf8mb4_general_ci;
-- auto-generated definition


create table collection
(
    id              int auto_increment
        primary key,
    user_id         int       null,
    post_id         int       null,
    collection_time timestamp not null,
    constraint collection_ibfk_1
        foreign key (user_id) references user (id),
    constraint collection_ibfk_2
        foreign key (post_id) references post (id)
)
    collate = utf8mb4_general_ci;

create index post_id
    on collection (post_id);

create index user_id
    on collection (user_id);
-- auto-generated definition


create table comment
(
    id           int auto_increment
        primary key,
    user_id      int       not null,
    post_id      int       not null,
    content      text      not null,
    comment_time timestamp not null,
    constraint comment_ibfk_1
        foreign key (user_id) references user (id),
    constraint comment_ibfk_2
        foreign key (post_id) references post (id)
)
    collate = utf8mb4_general_ci;

create index commented_id
    on comment (post_id);

create index user_id
    on comment (user_id);
-- auto-generated definition


create table easylike
(
    id        int auto_increment
        primary key,
    user_id   int       null,
    post_id   int       null,
    like_time timestamp not null,
    constraint easylike_ibfk_1
        foreign key (user_id) references user (id),
    constraint easylike_ibfk_2
        foreign key (post_id) references post (id)
)
    collate = utf8mb4_general_ci;

create index post_id
    on easylike (post_id);

create index user_id
    on easylike (user_id);
-- auto-generated definition


create table image
(
    id      int auto_increment
        primary key,
    post_id int          null,
    url     varchar(255) not null,
    constraint image_ibfk_2
        foreign key (post_id) references post (id)
)
    collate = utf8mb4_general_ci;

create index post_id
    on image (post_id);
-- auto-generated definition


create table notify
(
    id      int auto_increment
        primary key,
    post_id int          not null,
    user_id int(8)       not null,
    content varchar(255) not null,
    constraint notify_ibfk_1
        foreign key (post_id) references post (id),
    constraint notify_ibfk_2
        foreign key (user_id) references user (id)
)
    collate = utf8mb4_general_ci;

create index post_id
    on notify (post_id);

create index user_id
    on notify (user_id);
-- auto-generated definition


create table post
(
    id             int auto_increment
        primary key,
    type           tinyint(11)                            not null,
    title          varchar(255)                           not null,
    content_text   text                                   not null,
    color          tinyint(6)                             not null,
    lyrics         varchar(255) default ''                null,
    like_num       int          default 0                 null,
    collection_num int          default 0                 null,
    violation_num  int          default 0                 null,
    user_id        int                                    not null,
    exist          int          default 1                 not null,
    create_time    timestamp    default CURRENT_TIMESTAMP not null,
    visit_count    int          default 0                 not null,
    constraint user_id
        foreign key (user_id) references user (id)
)
    collate = utf8mb4_general_ci;
-- auto-generated definition


create table review
(
    id            int auto_increment
        primary key,
    admin_id      int       null,
    violation_id  int       null,
    review_time   timestamp not null,
    review_result tinyint   not null,
    constraint review_ibfk_1
        foreign key (admin_id) references admin (id),
    constraint review_ibfk_2
        foreign key (violation_id) references violation (id)
)
    collate = utf8mb4_general_ci;

create index admin_id
    on review (admin_id);

create index violation_id
    on review (violation_id);
-- auto-generated definition


create table user
(
    id       int auto_increment
        primary key,
    name     varchar(25)       not null,
    email    varchar(50)       not null,
    password varchar(225)      not null,
    avatar   tinyint           not null,
    role     tinyint default 3 not null
)
    collate = utf8mb4_general_ci;
-- auto-generated definition


create table violation
(
    id               int auto_increment
        primary key,
    user_id          int       not null,
    post_id          int       not null,
    violation_time   timestamp not null,
    violation_reason text      not null,
    constraint violation_ibfk_1
        foreign key (user_id) references user (id),
    constraint violation_ibfk_2
        foreign key (post_id) references post (id)
)
    collate = utf8mb4_general_ci;

create index post_id
    on violation (post_id);

create index user_id
    on violation (user_id);
