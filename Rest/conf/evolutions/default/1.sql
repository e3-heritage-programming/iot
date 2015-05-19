# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table weather_info (
  id                        bigint auto_increment not null,
  location                  integer,
  date                      datetime,
  data                      TEXT,
  constraint pk_weather_info primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table weather_info;

SET FOREIGN_KEY_CHECKS=1;

