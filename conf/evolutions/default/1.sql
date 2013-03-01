# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table activity_part (
  PART_TYPE                 varchar(31) not null,
  id                        bigint not null,
  speech_therapy_activity_id bigint not null,
  text                      varchar(255),
  constraint pk_activity_part primary key (id))
;

create table category (
  name                      varchar(255))
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

create table text_to_images_item (
  id                        bigint not null,
  text_to_images_activity_part_id bigint not null,
  name                      varchar(255),
  picture_url               varchar(255),
  constraint pk_text_to_images_item primary key (id))
;

create sequence activity_part_seq;

create sequence speech_therapy_activity_seq;

create sequence text_to_images_item_seq;

alter table activity_part add constraint fk_activity_part_speech_therap_1 foreign key (speech_therapy_activity_id) references speech_therapy_activity (id) on delete restrict on update restrict;
create index ix_activity_part_speech_therap_1 on activity_part (speech_therapy_activity_id);
alter table text_to_images_item add constraint fk_text_to_images_item_activit_2 foreign key (text_to_images_activity_part_id) references activity_part (id) on delete restrict on update restrict;
create index ix_text_to_images_item_activit_2 on text_to_images_item (text_to_images_activity_part_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists activity_part;

drop table if exists category;

drop table if exists speech_therapy_activity;

drop table if exists text_to_images_item;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists activity_part_seq;

drop sequence if exists speech_therapy_activity_seq;

drop sequence if exists text_to_images_item_seq;

