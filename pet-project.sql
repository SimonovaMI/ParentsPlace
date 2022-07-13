drop table edu_groups;
drop table statuses;
drop table participants;


create table edu_groups(
id number(6,0) constraint edu_groups_id_primary_key primary key,
group_name varchar2(50) constraint group_name_not_null not null,
constraint group_name_unique unique(group_name)
);

create table statuses(
status_name varchar2(20) constraint statuses_name_primary_key primary key
);

create table participants(
id number(6,0) constraint participants_id_primary_key primary key,
name varchar2(20) constraint name_not_null not null,
surname varchar2(25) constraint surname_not_null not null,
birthdate date,
phone_number varchar2(20),
email varchar2(25),
status varchar2(20) constraint status_not_null not null,
edu_group_id number(6,0) constraint edu_group_id_not_null not null,
parent1_id number(6,0),
parent2_id number(6,0),
constraint status_foreign_key foreign key (status) references statuses(status_name),
constraint edu_group_id_foreign_key foreign key (edu_group_id) references edu_groups(id) on delete cascade,
constraint email_check check(email like '_%@_%._%')
);
alter table participants add constraint parent1_id_foreign_key foreign key (parent1_id) references participants(id) on delete set null;
alter table participants add constraint parent2_id_foreign_key foreign key (parent2_id) references participants(id) on delete set null;
alter table participants add constraint phone_number_check check(phone_number like '+7__________' or phone_number like '8__________');

insert into statuses values ('CHILD');
insert into statuses values ('PARENT');
insert into statuses values ('TEACHER');

create sequence edu_groups_id_seq start with 1 increment by 1;
insert into edu_groups values (edu_groups_id_seq.nextval, '1 группа');



create sequence participants_id_seq start with 1 increment by 1;
drop sequence participants_id_seq;
insert into participants values (participants_id_seq.nextval, 'Маргарита', 'Симонова', null, null, null,'parent',1,null,null);
commit;
insert into participants values (participants_id_seq.nextval, 'Анна', 'Симонова', null, null, null,'CHILD',1,8,null);
select * from statuses;
select * from edu_groups;
select * from participants;
delete from participants where id > 23;

truncate table participants;
delete from statuses;



commit;






