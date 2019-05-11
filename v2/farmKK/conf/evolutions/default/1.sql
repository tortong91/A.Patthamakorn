# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table Addcow_planvacs (
  id                        varchar(255) not null,
  addcows_cow_id            varchar(255),
  addcow_id                 varchar(255),
  constraint pk_Addcow_planvacs primary key (id))
;

create table tbagent (
  agent_id                  varchar(255) not null,
  agent_name                varchar(255),
  agent_address             varchar(255),
  agent_tel                 varchar(255),
  picture                   varchar(255),
  constraint pk_tbagent primary key (agent_id))
;

create table tbaddcow (
  cow_id                    varchar(255) not null,
  sex                       varchar(255),
  color                     varchar(255),
  status                    varchar(255),
  picture                   varchar(255),
  date                      datetime,
  price                     integer,
  weight                    integer,
  age                       integer,
  eat_recip                 varchar(255),
  breed_id                  varchar(255),
  constraint pk_tbaddcow primary key (cow_id))
;

create table detailRe (
  did                       varchar(255) not null,
  amount                    integer,
  totalkg                   integer,
  inputfoods_id_ifoods      varchar(255),
  recipe_name_recip         varchar(255),
  savefood_id               varchar(255),
  constraint pk_detailRe primary key (did))
;

create table Detailsavefoods_cow (
  id                        varchar(255),
  name_cow                  varchar(255),
  savefoods_cows_cow_id     varchar(255),
  detailsavefoods_name_recip varchar(255))
;

create table tbFoods (
  id                        varchar(255) not null,
  animalfoods_name          varchar(255),
  animalfoods_type          varchar(255),
  constraint pk_tbFoods primary key (id))
;

create table tbinputcow (
  id                        varchar(255) not null,
  date                      varchar(255),
  confirm                   varchar(255),
  cow_cow_id                varchar(255),
  constraint pk_tbinputcow primary key (id))
;

create table tbInputfood (
  id_ifoods                 varchar(255) not null,
  unit                      varchar(255),
  picture                   varchar(255),
  price_ifoods              integer,
  amount_ifoods             integer,
  totalamount               integer,
  amount_kg                 integer,
  total                     integer,
  total_price               integer,
  date                      datetime,
  food_id                   varchar(255),
  inputfood_did             varchar(255),
  recip_name_recip          varchar(255),
  constraint pk_tbInputfood primary key (id_ifoods))
;

create table tbOrders (
  id                        varchar(255) not null,
  date                      datetime,
  ulogin_id                 varchar(255),
  status                    varchar(255),
  constraint pk_tbOrders primary key (id))
;

create table tbOrdersDetail (
  id                        varchar(255) not null,
  orders_id                 varchar(255),
  product_id_ifoods         varchar(255),
  amount                    integer,
  constraint pk_tbOrdersDetail primary key (id))
;

create table tbPlanVac (
  id                        varchar(255) not null,
  pvacdate                  datetime,
  pdate                     varchar(255),
  pdate1                    varchar(255),
  vac_id                    varchar(255),
  addcowpv_cow_id           varchar(255),
  constraint pk_tbPlanVac primary key (id))
;

create table tbRecipe (
  name_recip                varchar(255) not null,
  mount                     varchar(255),
  mount1                    varchar(255),
  amount_savefood           integer,
  date_time_recipe          varchar(255),
  date_recipe               datetime,
  inputfood_id_ifoods       varchar(255),
  recip_did                 varchar(255),
  constraint pk_tbRecipe primary key (name_recip))
;

create table saveFood (
  id                        varchar(255) not null,
  amount                    integer,
  datesavefood              datetime,
  cowdata_cow_id            varchar(255),
  recip_name_recip          varchar(255),
  ifoode_id_ifoods          varchar(255),
  constraint pk_saveFood primary key (id))
;

create table tbsellcow (
  id                        varchar(255) not null,
  name                      varchar(255),
  status                    varchar(255),
  sale_price                varchar(255),
  date                      datetime,
  sellcow_cow_id            varchar(255),
  coo_id                    varchar(255),
  constraint pk_tbsellcow primary key (id))
;

create table tbUser (
  id                        varchar(255) not null,
  user_name                 varchar(255),
  user_password             varchar(255),
  position                  varchar(255),
  name                      varchar(255),
  lastname                  varchar(255),
  address                   varchar(255),
  email                     varchar(255),
  tel                       integer,
  age                       integer,
  constraint pk_tbUser primary key (id))
;

create table tbVac (
  id                        varchar(255) not null,
  name                      varchar(255),
  pr                        varchar(255),
  picture                   varchar(255),
  constraint pk_tbVac primary key (id))
;

create table tbBreeds (
  id                        varchar(255) not null,
  name                      varchar(255),
  constraint pk_tbBreeds primary key (id))
;

create table db_coo (
  id                        varchar(255) not null,
  name                      varchar(255),
  address                   varchar(255),
  tel                       varchar(255),
  picture                   varchar(255),
  constraint pk_db_coo primary key (id))
;

create table tbFarmkk (
  farm_id                   varchar(255) not null,
  farm_details              longtext,
  fram_name                 longtext,
  farm_address              longtext,
  farm_tel                  longtext,
  picture                   longtext,
  constraint pk_tbFarmkk primary key (farm_id))
;

create table farmData (
  farm_id                   varchar(255) not null,
  fram_name                 varchar(255),
  farm_address              varchar(255),
  farm_tel                  varchar(255),
  picture                   varchar(255),
  constraint pk_farmData primary key (farm_id))
;

alter table Addcow_planvacs add constraint fk_Addcow_planvacs_addcows_1 foreign key (addcows_cow_id) references tbaddcow (cow_id) on delete restrict on update restrict;
create index ix_Addcow_planvacs_addcows_1 on Addcow_planvacs (addcows_cow_id);
alter table Addcow_planvacs add constraint fk_Addcow_planvacs_addcow_2 foreign key (addcow_id) references tbPlanVac (id) on delete restrict on update restrict;
create index ix_Addcow_planvacs_addcow_2 on Addcow_planvacs (addcow_id);
alter table tbaddcow add constraint fk_tbaddcow_breed_3 foreign key (breed_id) references tbBreeds (id) on delete restrict on update restrict;
create index ix_tbaddcow_breed_3 on tbaddcow (breed_id);
alter table detailRe add constraint fk_detailRe_inputfoods_4 foreign key (inputfoods_id_ifoods) references tbInputfood (id_ifoods) on delete restrict on update restrict;
create index ix_detailRe_inputfoods_4 on detailRe (inputfoods_id_ifoods);
alter table detailRe add constraint fk_detailRe_recipe_5 foreign key (recipe_name_recip) references tbRecipe (name_recip) on delete restrict on update restrict;
create index ix_detailRe_recipe_5 on detailRe (recipe_name_recip);
alter table detailRe add constraint fk_detailRe_savefood_6 foreign key (savefood_id) references saveFood (id) on delete restrict on update restrict;
create index ix_detailRe_savefood_6 on detailRe (savefood_id);
alter table Detailsavefoods_cow add constraint fk_Detailsavefoods_cow_SavefoodsCows_7 foreign key (savefoods_cows_cow_id) references tbaddcow (cow_id) on delete restrict on update restrict;
create index ix_Detailsavefoods_cow_SavefoodsCows_7 on Detailsavefoods_cow (savefoods_cows_cow_id);
alter table Detailsavefoods_cow add constraint fk_Detailsavefoods_cow_Detailsavefoods_8 foreign key (detailsavefoods_name_recip) references tbRecipe (name_recip) on delete restrict on update restrict;
create index ix_Detailsavefoods_cow_Detailsavefoods_8 on Detailsavefoods_cow (detailsavefoods_name_recip);
alter table tbinputcow add constraint fk_tbinputcow_cow_9 foreign key (cow_cow_id) references tbaddcow (cow_id) on delete restrict on update restrict;
create index ix_tbinputcow_cow_9 on tbinputcow (cow_cow_id);
alter table tbInputfood add constraint fk_tbInputfood_food_10 foreign key (food_id) references tbFoods (id) on delete restrict on update restrict;
create index ix_tbInputfood_food_10 on tbInputfood (food_id);
alter table tbInputfood add constraint fk_tbInputfood_inputfood_11 foreign key (inputfood_did) references detailRe (did) on delete restrict on update restrict;
create index ix_tbInputfood_inputfood_11 on tbInputfood (inputfood_did);
alter table tbInputfood add constraint fk_tbInputfood_recip_12 foreign key (recip_name_recip) references tbRecipe (name_recip) on delete restrict on update restrict;
create index ix_tbInputfood_recip_12 on tbInputfood (recip_name_recip);
alter table tbOrders add constraint fk_tbOrders_ulogin_13 foreign key (ulogin_id) references tbUser (id) on delete restrict on update restrict;
create index ix_tbOrders_ulogin_13 on tbOrders (ulogin_id);
alter table tbOrdersDetail add constraint fk_tbOrdersDetail_orders_14 foreign key (orders_id) references tbOrders (id) on delete restrict on update restrict;
create index ix_tbOrdersDetail_orders_14 on tbOrdersDetail (orders_id);
alter table tbOrdersDetail add constraint fk_tbOrdersDetail_product_15 foreign key (product_id_ifoods) references tbInputfood (id_ifoods) on delete restrict on update restrict;
create index ix_tbOrdersDetail_product_15 on tbOrdersDetail (product_id_ifoods);
alter table tbPlanVac add constraint fk_tbPlanVac_vac_16 foreign key (vac_id) references tbVac (id) on delete restrict on update restrict;
create index ix_tbPlanVac_vac_16 on tbPlanVac (vac_id);
alter table tbPlanVac add constraint fk_tbPlanVac_addcowpv_17 foreign key (addcowpv_cow_id) references tbaddcow (cow_id) on delete restrict on update restrict;
create index ix_tbPlanVac_addcowpv_17 on tbPlanVac (addcowpv_cow_id);
alter table tbRecipe add constraint fk_tbRecipe_inputfood_18 foreign key (inputfood_id_ifoods) references tbInputfood (id_ifoods) on delete restrict on update restrict;
create index ix_tbRecipe_inputfood_18 on tbRecipe (inputfood_id_ifoods);
alter table tbRecipe add constraint fk_tbRecipe_recip_19 foreign key (recip_did) references detailRe (did) on delete restrict on update restrict;
create index ix_tbRecipe_recip_19 on tbRecipe (recip_did);
alter table saveFood add constraint fk_saveFood_cowdata_20 foreign key (cowdata_cow_id) references tbaddcow (cow_id) on delete restrict on update restrict;
create index ix_saveFood_cowdata_20 on saveFood (cowdata_cow_id);
alter table saveFood add constraint fk_saveFood_recip_21 foreign key (recip_name_recip) references tbRecipe (name_recip) on delete restrict on update restrict;
create index ix_saveFood_recip_21 on saveFood (recip_name_recip);
alter table saveFood add constraint fk_saveFood_ifoode_22 foreign key (ifoode_id_ifoods) references tbInputfood (id_ifoods) on delete restrict on update restrict;
create index ix_saveFood_ifoode_22 on saveFood (ifoode_id_ifoods);
alter table tbsellcow add constraint fk_tbsellcow_sellcow_23 foreign key (sellcow_cow_id) references tbaddcow (cow_id) on delete restrict on update restrict;
create index ix_tbsellcow_sellcow_23 on tbsellcow (sellcow_cow_id);
alter table tbsellcow add constraint fk_tbsellcow_coo_24 foreign key (coo_id) references db_coo (id) on delete restrict on update restrict;
create index ix_tbsellcow_coo_24 on tbsellcow (coo_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table Addcow_planvacs;

drop table tbagent;

drop table tbaddcow;

drop table detailRe;

drop table Detailsavefoods_cow;

drop table tbFoods;

drop table tbinputcow;

drop table tbInputfood;

drop table tbOrders;

drop table tbOrdersDetail;

drop table tbPlanVac;

drop table tbRecipe;

drop table saveFood;

drop table tbsellcow;

drop table tbUser;

drop table tbVac;

drop table tbBreeds;

drop table db_coo;

drop table tbFarmkk;

drop table farmData;

SET FOREIGN_KEY_CHECKS=1;

