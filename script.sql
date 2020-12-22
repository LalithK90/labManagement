create table compound
(
    id         int auto_increment
        primary key,
    created_at datetime(6)    not null,
    created_by varchar(255)   not null,
    updated_at datetime(6)    not null,
    updated_by varchar(255)   not null,
    code       varchar(255)   null,
    name       varchar(255)   null,
    price      decimal(10, 2) not null
)
    engine = InnoDB;

create table customer
(
    id            int auto_increment
        primary key,
    created_at    datetime(6)                   not null,
    created_by    varchar(255)                  not null,
    updated_at    datetime(6)                   not null,
    updated_by    varchar(255)                  not null,
    address       varchar(255) collate utf8_bin null,
    code          varchar(255)                  null,
    company_name  varchar(255)                  null,
    customer_type varchar(255)                  null,
    email         varchar(255)                  null,
    mobile        varchar(10)                   null,
    name          varchar(255)                  null,
    nic           varchar(12)                   null,
    title         varchar(255)                  null,
    constraint UK_4h6a5iro7ibjn1v8g2n7pktiw
        unique (mobile),
    constraint UK_9st6x9trhf0s27g0vgpcaeu3m
        unique (nic),
    constraint UK_dwk6cx0afu8bs9o4t536v1j5v
        unique (email),
    constraint UK_oh5nd82pf3lh0synvtqcoo4kr
        unique (company_name),
    constraint UK_rm1bp9bhtiih5foj17t8l500j
        unique (code)
)
    engine = InnoDB;

create table discount_ratio
(
    id                    int auto_increment
        primary key,
    amount                int          not null,
    discount_ratio_status int          null,
    name                  varchar(255) null
)
    engine = InnoDB;

create table employee
(
    id                 int auto_increment
        primary key,
    created_at         datetime(6)                   not null,
    created_by         varchar(255)                  not null,
    updated_at         datetime(6)                   not null,
    updated_by         varchar(255)                  not null,
    address            varchar(255) collate utf8_bin null,
    calling_name       varchar(255)                  null,
    civil_status       varchar(255)                  null,
    code               varchar(255)                  null,
    date_of_assignment date                          null,
    date_of_birth      date                          null,
    designation        varchar(255)                  null,
    employee_status    varchar(255)                  null,
    gender             varchar(255)                  null,
    land               varchar(10)                   null,
    mobile_one         varchar(10)                   null,
    mobile_two         varchar(10)                   null,
    name               varchar(255)                  null,
    nic                varchar(12)                   null,
    office_email       varchar(255)                  null,
    title              varchar(255)                  null,
    constraint UK_dihajhqd7lkqn3lhsawly3t9r
        unique (nic),
    constraint UK_ldqrk7j96ef2tqxsonr4dqf0r
        unique (office_email),
    constraint UK_nbyivu8qgmx0r7wtbplf01gf8
        unique (code)
)
    engine = InnoDB;

create table employee_files
(
    id          int auto_increment
        primary key,
    created_at  datetime(6)  not null,
    created_by  varchar(255) not null,
    updated_at  datetime(6)  not null,
    updated_by  varchar(255) not null,
    mime_type   varchar(255) null,
    name        varchar(255) null,
    new_id      varchar(255) null,
    new_name    varchar(255) null,
    pic         longblob     null,
    employee_id int          null,
    constraint UK_5eob6je5op1e4m7v20v5i01p2
        unique (new_id),
    constraint FKolualpa5dydj5r06txltc1y6d
        foreign key (employee_id) references employee (id)
)
    engine = InnoDB;

create table role
(
    id         int auto_increment
        primary key,
    created_at datetime(6)  not null,
    created_by varchar(255) not null,
    updated_at datetime(6)  not null,
    updated_by varchar(255) not null,
    role_name  varchar(255) not null,
    constraint UK_iubw515ff0ugtm28p8g3myt0h
        unique (role_name)
)
    engine = InnoDB;

create table sample_receiving
(
    id                      int auto_increment
        primary key,
    created_at              datetime(6)    not null,
    created_by              varchar(255)   not null,
    updated_at              datetime(6)    not null,
    updated_by              varchar(255)   not null,
    amount                  decimal(10, 2) not null,
    batch_no                varchar(255)   null,
    number                  varchar(255)   null,
    sample_code             varchar(255)   null,
    sample_receiving_status varchar(255)   null,
    compound_id             int            null,
    customer_id             int            null,
    discount_ratio_id       int            null,
    constraint FK8x0822y3hdlctbsvffntygivh
        foreign key (discount_ratio_id) references discount_ratio (id),
    constraint FKabotpy92dim50i8xar6dxct27
        foreign key (customer_id) references customer (id),
    constraint FKnx9yk0isw7j2qrdrlmnqfkvw7
        foreign key (compound_id) references compound (id)
)
    engine = InnoDB;

create table payment
(
    id                  int auto_increment
        primary key,
    created_at          datetime(6)    not null,
    created_by          varchar(255)   not null,
    updated_at          datetime(6)    not null,
    updated_by          varchar(255)   not null,
    amount              decimal(10, 2) not null,
    number              varchar(255)   null,
    payment_date        date           null,
    payment_status      varchar(255)   null,
    sample_receiving_id int            null,
    constraint FKjrgj4j0fxoolb4qsx9jjkr5y5
        foreign key (sample_receiving_id) references sample_receiving (id)
)
    engine = InnoDB;

create table sample_receiving_lab_test
(
    id                               int auto_increment
        primary key,
    created_at                       datetime(6)  not null,
    created_by                       varchar(255) not null,
    updated_at                       datetime(6)  not null,
    updated_by                       varchar(255) not null,
    acceptability                    varchar(255) null,
    lab_test_name                    varchar(255) null,
    remarks                          varchar(255) null,
    sample_receiving_lab_test_status varchar(255) null,
    sample_receiving_id              int          null,
    constraint FK8j57592pe714f474p7uyl6o61
        foreign key (sample_receiving_id) references sample_receiving (id)
)
    engine = InnoDB;

create table specification
(
    id                 int auto_increment
        primary key,
    created_at         datetime(6)  not null,
    created_by         varchar(255) not null,
    updated_at         datetime(6)  not null,
    updated_by         varchar(255) not null,
    lab_test_name      varchar(255) null,
    max                varchar(255) null,
    min                varchar(255) not null,
    specification_name varchar(255) null,
    compound_id        int          null,
    constraint FKt2958kvke8lbutj8f93snap0f
        foreign key (compound_id) references compound (id)
)
    engine = InnoDB;

create table sample_receiving_lab_test_result
(
    id                           int auto_increment
        primary key,
    created_at                   datetime(6)  not null,
    created_by                   varchar(255) not null,
    updated_at                   datetime(6)  not null,
    updated_by                   varchar(255) not null,
    result                       float        not null,
    sample_receiving_lab_test_id int          null,
    specification_id             int          null,
    constraint FK69g6rqxksfvbqkdasu077vw2p
        foreign key (specification_id) references specification (id),
    constraint FKtirgedd4r2hfnw8rgjd6qfepo
        foreign key (sample_receiving_lab_test_id) references sample_receiving_lab_test (id)
)
    engine = InnoDB;

create table user
(
    id          int auto_increment
        primary key,
    created_at  datetime(6)  not null,
    created_by  varchar(255) not null,
    updated_at  datetime(6)  not null,
    updated_by  varchar(255) not null,
    enabled     bit          not null,
    password    varchar(255) not null,
    username    varchar(255) not null,
    employee_id int          not null,
    constraint UK_sb8bbouer5wak8vyiiy4pf2bx
        unique (username),
    constraint FK211dk0pe7l3aibwce8yy61ota
        foreign key (employee_id) references employee (id)
)
    engine = InnoDB;

create table user_role
(
    user_id int not null,
    role_id int not null,
    constraint FK859n2jvi8ivhui0rl0esws6o
        foreign key (user_id) references user (id),
    constraint FKa68196081fvovjhkek5m97n3y
        foreign key (role_id) references role (id)
)
    engine = InnoDB;

create table user_session_log
(
    id                      int auto_increment
        primary key,
    created_at              datetime(6)  not null,
    failure_attempts        int          not null,
    user_session_log_status varchar(255) null,
    user_id                 int          null,
    constraint FKrhb4wune1hnnhdsbiah2ojo5l
        foreign key (user_id) references user (id)
)
    engine = InnoDB;


