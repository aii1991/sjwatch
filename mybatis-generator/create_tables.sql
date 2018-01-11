/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/1/11 16:26:14                           */
/*==============================================================*/


drop table if exists t_brand;

drop table if exists t_goods;

drop table if exists t_goods_type;

drop table if exists t_role;

drop table if exists t_user;

drop table if exists t_user_acl;

drop table if exists t_user_log;

/*==============================================================*/
/* Table: t_brand                                               */
/*==============================================================*/
create table t_brand
(
   id                   bigint not null auto_increment,
   name                 varchar(30),
   logo                 varchar(150),
   create_time          bigint,
   modify_time          bigint,
   goods_type_id        bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: t_goods                                               */
/*==============================================================*/
create table t_goods
(
   id                   bigint not null auto_increment,
   t_b_id               bigint,
   name                 varchar(100),
   number               varchar(50),
   type                 bigint,
   sale_price           double,
   wholesale            double,
   purchase_price       double,
   stock                int,
   descr                text,
   sources              varchar(300),
   brand                int,
   create_time          bigint,
   modify_time          bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: t_goods_type                                          */
/*==============================================================*/
create table t_goods_type
(
   id                   bigint not null auto_increment,
   name                 varchar(30),
   create_time          bigint,
   modify_time          bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   id                   bigint not null auto_increment,
   name                 varchar(30),
   primary key (id)
);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   bigint not null auto_increment,
   user_name            varchar(30),
   nick_name            varchar(30),
   password             varchar(100),
   token                varchar(50),
   sex                  int(1),
   age                  int(3),
   qq_token             varchar(50),
   wechat_token         varchar(50),
   create_time          bigint,
   modify_time          bigint,
   role_id              bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: t_user_acl                                            */
/*==============================================================*/
create table t_user_acl
(
   id                   bigint not null auto_increment,
   acl_url              varchar(20),
   name                 varchar(20),
   role_id              bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: t_user_log                                            */
/*==============================================================*/
create table t_user_log
(
   id                   bigint not null auto_increment,
   login_ip             varchar(20),
   uid                  bigint,
   primary key (id)
);

alter table t_brand add constraint FK_Reference_3 foreign key (goods_type_id)
      references t_goods_type (id) on delete restrict on update restrict;

alter table t_goods add constraint FK_Reference_1 foreign key (type)
      references t_goods_type (id) on delete restrict on update restrict;

alter table t_goods add constraint FK_Reference_2 foreign key (t_b_id)
      references t_brand (id) on delete restrict on update restrict;

alter table t_user add constraint FK_Reference_5 foreign key (role_id)
      references t_role (id) on delete restrict on update restrict;

alter table t_user_acl add constraint FK_Reference_6 foreign key (role_id)
      references t_role (id) on delete restrict on update restrict;

alter table t_user_log add constraint FK_Reference_4 foreign key (uid)
      references t_user (id) on delete restrict on update restrict;

