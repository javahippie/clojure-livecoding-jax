CREATE TABLE rating (
    id varchar(36) primary key,
    name varchar(256) not null,
    rating integer not null,
    comment varchar(2048) not null
)