--
-- JBoss, Home of Professional Open Source
-- Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements
drop table CrDetail if exists;
drop table CrMessage if exists;
drop table CrPeriod if exists;
drop table CrPortfolio if exists;
drop table Registrant if exists;
drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start with 1 increment by 1;
create table CrDetail (id bigint not null, jahr varchar(255), mietNr varchar(255), primary key (id));
create table CrMessage (id bigint not null, msg varchar(255), msgType varchar(255), crDetail_id bigint, primary key (id));
create table CrPeriod (id bigint not null, periodFrom timestamp, periodTo timestamp, crDetail_id bigint, primary key (id));
create table CrPortfolio (id bigint not null, anzahlLkw integer, anzahlPkw integer, anzahlTotal integer, bezeichnung varchar(255), grund varchar(255), mieteAbgerechnetLkw decimal(19,2), mieteAbgerechnetPkw decimal(19,2), mieteAbgerechnetTotal decimal(19,2), mieteGeplantLkw decimal(19,2), mieteGeplantPkw decimal(19,2), mieteGeplantTotal decimal(19,2), status varchar(255), crPeriod_id bigint, primary key (id));
create table Registrant (id bigint not null, email varchar(255) not null, name varchar(25) not null, phone_number varchar(12) not null, primary key (id));
alter table Registrant add constraint UKit677o4jaydleb048j9oub9mk unique (email);
alter table CrMessage add constraint FK307mgtbg8pb57nnf58h76dtyh foreign key (crDetail_id) references CrDetail;
alter table CrPeriod add constraint FK3pujcxoaagpdqg16cqy4mdvce foreign key (crDetail_id) references CrDetail;
alter table CrPortfolio add constraint FK2839jdedxgua8gwoh95vil7kv foreign key (crPeriod_id) references CrPeriod;

insert into Registrant(id, name, email, phone_number) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212'); 
insert into CrDetail(id, mietNr, jahr) values (-1, '1', '2015');
insert into CrMessage(id, msgType, msg,crDetail_id) values (-1, 'Warning','This is a server warning.',-1);
insert into CrPeriod(id,periodFrom,periodTo,crDetail_id) values (-1,PARSEDATETIME('01012015','ddMMyyyy'),PARSEDATETIME('31122015','ddMMyyyy'),-1); 
insert into CrPortfolio(id,bezeichnung,status,grund,anzahlPkw,anzahlLkw,anzahlTotal,mieteGeplantPkw,mieteGeplantLkw,mieteGeplantTotal,mieteAbgerechnetPkw,mieteAbgerechnetLkw,mieteAbgerechnetTotal,crPeriod_id) values (-1, 'Bezeichnung','Status1','Grund1',10,10,20,1000000,1000000,1000000,1000000,500000,2000000,-1);

insert into CrDetail(id, mietNr, jahr) values (-2, '1', '2016');
insert into CrMessage(id, msgType, msg,crDetail_id) values (-2, 'Error','This is a server error.',-2);
insert into CrPeriod(id,periodFrom,periodTo,crDetail_id) values (-2,PARSEDATETIME('01012016','ddMMyyyy'),PARSEDATETIME('31122016','ddMMyyyy'),-2); 
insert into CrPortfolio(id,bezeichnung,status,grund,anzahlPkw,anzahlLkw,anzahlTotal,mieteGeplantPkw,mieteGeplantLkw,mieteGeplantTotal,mieteAbgerechnetPkw,mieteAbgerechnetLkw,mieteAbgerechnetTotal,crPeriod_id) values (-2, 'Bezeichnung','Status2','Grund2',null,null,null,1000000,1000000,1000000,null,null,null,-2);