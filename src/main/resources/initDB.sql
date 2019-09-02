-- create database if not exists depo character set utf8;

create table depo.transport
(
    transport_id int(10)     not null auto_increment,
    number       varchar(50) not null unique ,
    created_at   datetime    not null,
    modified_at  datetime default null,
    kind_id      int(10)     not null,
    park_id      int(10)     not null,
    primary key (transport_id)
);

create table depo.kinds
(
    kind_id int(10)     not null auto_increment,
    kind    varchar(50) not null,
    primary key (kind_id)
);

create table depo.parks
(
    park_id     int(10)     not null auto_increment,
    park_number int(10)     not null unique ,
    park_type   varchar(50) not null,
    primary key (park_id)
);

create table depo.transport_type
(
    transport_id int(10) not null ,
    type_id      int(50) not null
#     primary key (transport_id, type_id)
);

create table depo.types
(
    type_id int(10)     not null auto_increment,
    type    varchar(50) not null,
    primary key (type_id)
);

create table depo.transport_details
(
    transport_id int(10) not null,
    capacity     int(10) default 0,
    tonnage      double(10,2) default 0,
    primary key (transport_id)
);

create table depo.location
(
    transport_id int(10) not null,
    length       decimal(9, 6) default 59.939095,
    width        decimal(9, 6) default 30.315868,
    primary key (transport_id)
);

alter table depo.transport
    add constraint transport_kind_fk foreign key (kind_id) references kinds (kind_id) on update cascade ,
    add constraint transport_park_fk foreign key (park_id) references parks (park_id) on update cascade ;

alter table depo.transport_type
    add constraint transport_type_transport_id foreign key (transport_id) references transport (transport_id)
        on delete cascade on update cascade ,
    add constraint transport_type_type_id foreign key (type_id) references types (type_id) on update cascade ;

alter table depo.location
    add constraint location_fk foreign key (transport_id) references transport (transport_id)
        on delete cascade on update cascade ;

alter table depo.transport_details
    add constraint transport_details_transport_id foreign key (transport_id) references transport (transport_id)
        on delete cascade on update cascade ;
