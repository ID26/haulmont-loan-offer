
alter table if exists client drop constraint if exists FKim95abd01ot21q2dn9mpxo7nc;
alter table if exists credit drop constraint if exists FK1x1iaf0dsp6s1tjhdihhedgdk;
alter table if exists loan_offer drop constraint if exists FKor6tsudo4v592s1fv0n0ao0mn;
alter table if exists loan_offer drop constraint if exists FKoawkn49784og5e9n12emtwwbx;
alter table if exists loan_offer drop constraint if exists FKtjq6y42pvds1vjocjju5c39n4;
drop table if exists bank cascade;
drop table if exists client cascade;
drop table if exists credit cascade;
drop table if exists loan_offer cascade;
drop table if exists payment_scheduler cascade;
create table bank (id uuid not null, bank_name varchar(255), primary key (id));
create table client (id uuid not null, email varchar(55), full_name varchar(255), passport_number varchar(20), phone
                        varchar(20), bank_id uuid, clients_scheduler_key uuid, primary key (id));
create table credit (id uuid not null, credit_limit int8, name varchar(255), percent float8, bank_id uuid,
                     credits_scheduler_key uuid, primary key (id));
create table loan_offer (id uuid not null, amount_credit int8, quantity_month int4, client_id uuid, credit_id uuid,
                         payment_scheduler_id uuid, primary key (id));
create table payment_scheduler (id uuid not null, amount_body int8, amount_pay int8, amount_percent int8, primary key
    (id));
alter table if exists client add constraint FKim95abd01ot21q2dn9mpxo7nc foreign key (bank_id) references bank;
alter table if exists credit add constraint FK1x1iaf0dsp6s1tjhdihhedgdk foreign key (bank_id) references bank;
alter table if exists loan_offer add constraint FKor6tsudo4v592s1fv0n0ao0mn foreign key (client_id) references client;
alter table if exists loan_offer add constraint FKoawkn49784og5e9n12emtwwbx foreign key (credit_id) references credit;
alter table if exists loan_offer add constraint FKtjq6y42pvds1vjocjju5c39n4 foreign key (payment_scheduler_id)
    references payment_scheduler;
insert into bank (bank_name, id) values (?, ?)