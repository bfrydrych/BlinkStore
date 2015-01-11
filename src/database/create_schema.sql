 drop table USERS;
 create table USERS(
      username varchar_ignorecase(50) not null primary key,
      password varchar_ignorecase(50) not null,
      enabled boolean not null);

drop table AUTHORITIES;
  create table AUTHORITIES (
      username varchar_ignorecase(50) not null,
      authority varchar_ignorecase(50) not null,
      constraint fk_authorities_users foreign key(username) references users(username));
      create unique index ix_auth_username on authorities (username,authority);
      
insert into USERS values ('ADMIN', 'ADMIN', true);
insert into AUTHORITIES values ('ADMIN', 'ROLE_USER');
insert into AUTHORITIES values ('ADMIN', 'ROLE_ADMIN');