create database fomin_kiyko_6133;

\c fomin_kiyko_6133;

create sequence city_id_sequence;

create table city
(
  id bigint primary key default nextval('city_id_sequence'),
  name text not null
);

comment on table city is 'Сущность города';
comment on column city.id is 'Идентификатор города';
comment on column city.name is 'Название города';

create sequence school_id_sequence;

create table school
(
  id bigint primary key default nextval('school_id_sequence'),
  name text not null,
  city_id bigint not null references city(id)
);

comment on table school is 'Сущность школы';
comment on column school.id is 'Идентификатор школы';
comment on column school.name is 'Название школы';
comment on column school.city_id is 'Идентификатор города';

create sequence profile_type_id_sequence;

create table profile_type
(
  id smallint primary key default nextval('profile_type_id_sequence'),
  name text not null unique
);

comment on table profile_type is 'Сущность типа профиля';
comment on column profile_type.id is 'Идентификатор типа профиля';
comment on column profile_type.name is 'Название типа профиля';

create sequence profile_id_sequence;

create table profile
(
  id bigint primary key default nextval('profile_id_sequence'),
  name text not null,
  age smallint,
  type_id smallint not null references profile_type(id),
  school_id bigint not null references school(id),
  class_level text
);

comment on table profile is 'Сущность пользователя';
comment on column profile.id is 'Идентификатор профиля';
comment on column profile.name is 'Имя пользователя';
comment on column profile.age is 'Возраст пользователя';
comment on column profile.type_id is 'Идентификатор типа профиля';
comment on column profile.school_id is 'Идентификатор школы';
comment on column profile.class_level is 'Класс, если профиль ученика (костыль, в перспективе может нарушить целостность бд. Как поправить кроме как триггером без понятия)';

insert into city values
  (1, 'default city 1'),
  (2, 'default city 2');

insert into school values
  (1, 'Школа №46', 1),
  (2, 'Лицей №51', 1),
  (3, 'Школа №-1', 2);

insert into profile_type values
  (1, 'Студент'),
  (2, 'Учитель');

insert into profile (name, age, type_id, school_id, class_level) values
  ('Миша', 22, 1, 1, 11),
  ('Не Миша', 99, 2, 1, null),
  ('Таня', 22, 1, 2, 11);