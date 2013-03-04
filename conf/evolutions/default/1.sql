# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table activity_part (
  id                        bigint not null,
  speech_therapy_activity_id bigint not null,
  title                     varchar(255),
  extra                     varchar(255),
  constraint pk_activity_part primary key (id))
;

create table activity_resource (
  id                        bigint not null,
  activity_part_id          bigint not null,
  name                      varchar(255),
  resource_url              varchar(255),
  constraint pk_activity_resource primary key (id))
;

create table category (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_category primary key (id))
;

create table speech_therapy_activity (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  picture_url               varchar(255),
  type                      integer,
  creation_date             timestamp,
  last_modification_date    timestamp,
  constraint ck_speech_therapy_activity_type check (type in (0)),
  constraint pk_speech_therapy_activity primary key (id))
;

create sequence activity_part_seq;

create sequence activity_resource_seq;

create sequence category_seq;

create sequence speech_therapy_activity_seq;

alter table activity_part add constraint fk_activity_part_speech_therap_1 foreign key (speech_therapy_activity_id) references speech_therapy_activity (id) on delete restrict on update restrict;
create index ix_activity_part_speech_therap_1 on activity_part (speech_therapy_activity_id);
alter table activity_resource add constraint fk_activity_resource_activity__2 foreign key (activity_part_id) references activity_part (id) on delete restrict on update restrict;
create index ix_activity_resource_activity__2 on activity_resource (activity_part_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists activity_part;

drop table if exists activity_resource;

drop table if exists category;

drop table if exists speech_therapy_activity;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists activity_part_seq;

drop sequence if exists activity_resource_seq;

drop sequence if exists category_seq;

drop sequence if exists speech_therapy_activity_seq;

