drop table T_MOTORCYCLES if exists;
drop table T_USERS if exists;
drop table T_ROLES if exists;

create table T_MOTORCYCLES(ID integer identity primary key, CHASSIS_NUMBER integer, MAKER varchar(25) not null, MODEL varchar(40) not null, DISPLACEMENT float not null, STYLE varchar(20), UNIQUE(CHASSIS_NUMBER));
create table T_USERS(ID integer identity primary key, USERNAME varchar(25) not null, PASSWORD varchar(100) not null, UNIQUE(USERNAME));
create table T_ROLES(ID integer identity primary key, NAME varchar(25) not null, UNIQUE(NAME));
create table T_USERS_ROLES(ID integer identity, USER_ID integer not null, ROLE_ID integer not null);

alter table T_USERS_ROLES add constraint FK_USER_ID foreign key (USER_ID) references T_USERS(ID);
alter table T_USERS_ROLES add constraint FK_ROLE_ID foreign key (ROLE_ID) references T_ROLES(ID);
