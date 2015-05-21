# --- !Ups

create table location (
  id                        bigint auto_increment not null,
  location_name             varchar(255),
  country_name              varchar(255),
  constraint pk_location primary key (id))
;

create table weather_info (
  id                        bigint auto_increment not null,
  location                  bigint,
  date                      datetime,
  data                      TEXT,
  constraint pk_weather_info primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table location;

drop table weather_info;

SET FOREIGN_KEY_CHECKS=1;

