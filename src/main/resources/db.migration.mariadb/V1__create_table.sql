DROP TABLE IF EXISTS books;

create table books (
    id bigint not null auto_increment,
    title varchar(255) not null,
    author varchar(255) not null,
    isbn varchar(255) not null,
    publishDate date(255) not null,
    price Integer
) engine=InnoDB;

create table bookdetail (
    id bigint not null auto_increment,
    description varchar(255) not null,
    language_ varchar(255) not null,
    pageCount Integer not null,
    publisher varchar(255) not null,
    coverImageUrl varchar(255) not null,
    edition varchar(255) not null,
    primary key (id)
) engine=InnoDB;

alter table IF EXISTS books add constraint UK_id unique (id);
alter table IF EXISTS bookdetail add constraint FK_id foreign key (book_id) references books(id);