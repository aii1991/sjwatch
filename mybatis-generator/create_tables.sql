/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/12/27 16:57:24                          */
/*==============================================================*/


drop table if exists t_banner;

drop table if exists t_brand;

drop table if exists t_goods;

drop table if exists t_goods_type;

drop table if exists t_order;

drop table if exists t_role;

drop table if exists t_user;

drop table if exists t_user_acl;

drop table if exists t_user_log;

/*==============================================================*/
/* Table: t_banner                                              */
/*==============================================================*/
create table t_banner
(
   id                   bigint  not null auto_increment,
   g_id                 bigint not null,
   src                  varchar(100),
   create_time          timestamp,
   modify_time          timestamp,
   primary key (id)
);

/*==============================================================*/
/* Table: t_brand                                               */
/*==============================================================*/
create table t_brand
(
   id                   bigint not null auto_increment,
   name                 varchar(30),
   logo                 varchar(300),
   goods_type_id        bigint default NULL,
   create_time          timestamp default CURRENT_TIMESTAMP,
   modify_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   category_id          int,
   category_name        char(30),
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
   sources              text comment 'json�����ʽ',
   create_time          timestamp default CURRENT_TIMESTAMP,
   modify_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   is_hot               int(2),
   is_recommend         int(2),
   sex                  int(2),
   cover_src            varchar(100),
   primary key (id)
);

/*==============================================================*/
/* Table: t_goods_type                                          */
/*==============================================================*/
create table t_goods_type
(
   id                   bigint not null auto_increment,
   name                 varchar(30),
   create_time          timestamp default CURRENT_TIMESTAMP,
   modify_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (id)
);

/*==============================================================*/
/* Table: t_order                                               */
/*==============================================================*/
create table t_order
(
   id                   bigint not null auto_increment,
   no                   bigint not null,
   status               int(2),
   address              char(200),
   receiver_name        char(50),
   receiver_number      char(20),
   delivery_number      int,
   delivery_name        char(30),
   price                double,
   goods_id             bigint,
   create_time          timestamp,
   modify_time          timestamp,
   primary key (id),
   unique key AK_Key_2 (no)
);

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   id                   bigint not null,
   name                 varchar(30),
   create_time          timestamp default CURRENT_TIMESTAMP,
   modify_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
   sex                  int(1) comment '0=δ֪,1=��,2=Ů',
   age                  int(3),
   qq_token             varchar(50),
   wechat_token         varchar(50),
   role_id              bigint,
   create_time          timestamp default CURRENT_TIMESTAMP,
   modify_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (id)
);

/*==============================================================*/
/* Table: t_user_acl                                            */
/*==============================================================*/
create table t_user_acl
(
   id                   bigint not null,
   acl_url              varchar(20),
   name                 varchar(20),
   role_id              bigint,
   create_time          timestamp default CURRENT_TIMESTAMP,
   modify_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
   request_url          varchar(100),
   create_time          timestamp default CURRENT_TIMESTAMP,
   modify_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (id)
);

alter table t_brand add constraint FK_Reference_3 foreign key (goods_type_id)
      references t_goods_type (id) on delete restrict on update restrict;

alter table t_goods add constraint FK_Reference_1 foreign key (type)
      references t_goods_type (id) on delete restrict on update restrict;

alter table t_goods add constraint FK_Reference_2 foreign key (t_b_id)
      references t_brand (id) on delete restrict on update restrict;

alter table t_order add constraint FK_Reference_8 foreign key (goods_id)
      references t_goods (id) on delete restrict on update restrict;

alter table t_user add constraint FK_Reference_5 foreign key (role_id)
      references t_role (id) on delete restrict on update restrict;

alter table t_user_acl add constraint FK_Reference_6 foreign key (role_id)
      references t_role (id) on delete restrict on update restrict;

alter table t_user_log add constraint FK_Reference_4 foreign key (uid)
      references t_user (id) on delete restrict on update restrict;

