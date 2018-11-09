drop table recipe if exists;
create table recipe (
    id int not null auto_increment,
    title varchar(255) not null,
    primary key(id)
);

