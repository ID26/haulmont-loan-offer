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




select client0_.id as id1_1_, client0_.bank_id as bank_id6_1_, client0_.email as email2_1_, client0_.full_name as
    full_nam3_1_, client0_.passport_number as passport4_1_, client0_.phone as phone5_1_ from client client0_;
select client0_.id as id1_1_, client0_.bank_id as bank_id6_1_, client0_.email as email2_1_, client0_.full_name as
    full_nam3_1_, client0_.passport_number as passport4_1_, client0_.phone as phone5_1_ from client client0_;
select bank0_.id as id1_0_, bank0_.bank_name as bank_nam2_0_ from bank bank0_;
insert into client (bank_id, email, full_name, passport_number, phone, id) values (?, ?, ?, ?, ?, ?);
select client0_.id as id1_1_0_, client0_.bank_id as bank_id6_1_0_, client0_.email as email2_1_0_, client0_.full_name
    as full_nam3_1_0_, client0_.passport_number as passport4_1_0_, client0_.phone as phone5_1_0_, bank1_.id as id1_0_1_, bank1_.bank_name as bank_nam2_0_1_ from client client0_ left outer join bank bank1_ on client0_.bank_id=bank1_.id where client0_.id=?;

select bank0_.id as id1_0_, bank0_.bank_name as bank_nam2_0_ from bank bank0_;
insert into credit (bank_id, credit_limit, name, percent, id) values (?, ?, ?, ?, ?);
select credit0_.id as id1_2_, credit0_.bank_id as bank_id5_2_, credit0_.credit_limit as credit_l2_2_, credit0_.name
    as name3_2_, credit0_.percent as percent4_2_ from credit credit0_;
select bank0_.id as id1_0_0_, bank0_.bank_name as bank_nam2_0_0_ from bank bank0_ where bank0_.id=?;


select * from bank
                  inner join client c on bank.id = c.bank_id
                  inner join credit c2 on bank.id = c2.bank_id;

select * from client inner join bank b on b.id = client.bank_id;

select * from bank where bank_name like 'Haulmont Bank';

select * from bank
                  inner join client c on bank.id = c.bank_id
                  inner join credit c2 on bank.id = c2.bank_id
where bank.id = 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11';

select * from bank;







insert into bank (bank_name, id) values ('Haulmont Bank', 'A0EEBC99-9C0B-4EF8-BB6D-6BB9BD380A11');

insert into client (bank_id, email, full_name, passport_number, phone, id) values
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '1111@1111', 'Iliya', '1111111', '1111111',
     'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12');
insert into client (bank_id, email, full_name, passport_number, phone, id) values
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '1111@1111', 'Anna', '1111111', '1111111',
     'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13');
insert into client (bank_id, email, full_name, passport_number, phone, id) values
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '1111@1111', 'Nina', '1111111', '1111111',
     'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14');
insert into client (bank_id, email, full_name, passport_number, phone, id) values
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '1111@1111', 'Tanya', '1111111', '1111111',
     'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15');
insert into client (bank_id, email, full_name, passport_number, phone, id) values
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '1111@1111', 'Sveta', '1111111', '1111111',
     'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a16');
insert into client (bank_id, email, full_name, passport_number, phone, id) values
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '1111@1111', 'Olya', '1111111', '1111111',
     'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a17');
insert into client (bank_id, email, full_name, passport_number, phone, id) values
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '1111@1111', 'Olesya', '1111111', '1111111',
     'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a18');



insert into credit (bank_id, credit_limit, name, percent, id) values ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 20000,
                                                                      'some1',
                                                                      4.9, 'a1eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');
insert into credit (bank_id, credit_limit, name, percent, id) values ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 30000,
                                                                      'some2',
                                                                      4.9, 'a2eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');
insert into credit (bank_id, credit_limit, name, percent, id) values ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 40000,
                                                                      'some3',
                                                                      4.9, 'a3eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');
insert into credit (bank_id, credit_limit, name, percent, id) values ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 52000,
                                                                      'some4',
                                                                      4.9, 'a4eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');
insert into credit (bank_id, credit_limit, name, percent, id) values ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 620000,
                                                                      'some5',
                                                                      5.1, 'a5eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');
insert into credit (bank_id, credit_limit, name, percent, id) values ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 720000,
                                                                      'some6',
                                                                      5.1, 'a6eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');
insert into credit (bank_id, credit_limit, name, percent, id) values ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 2000000,
                                                                      'some7',
                                                                      5.1, 'a7eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');


select * from bank
                  inner join client c on bank.id = c.bank_id
                  inner join credit c2 on bank.id = c2.bank_id
where bank.id = 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11';