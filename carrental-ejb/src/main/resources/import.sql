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
insert into Registrant(id, name, email, phone_number) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212'); 
insert into CrDetail(mietNr, jahr) values (-1, '2015');
insert into CrMessage(id, msgType, msg,crDetail_mietNr) values (-1, 'Warning','This is a server warning.',-1);
insert into CrPeriod(id,periodFrom,periodTo,crDetail_mietNr) values (-1,PARSEDATETIME('01012015','ddMMyyyy'),PARSEDATETIME('31122015','ddMMyyyy'),-1); 
insert into CrPortfolio(id,bezeichnung,status,grund,anzahlPkw,anzahlLkw,anzahlTotal,mieteGeplantPkw,mieteGeplantLkw,mieteGeplantTotal,mieteAbgerechnetPkw,mieteAbgerechnetLkw,mieteAbgerechnetTotal,crPeriod_id) values (-1, 'Bezeichnung','Status1','Grund1',10,10,20,1000000,1000000,1000000,1000000,500000,2000000,-1);

insert into CrDetail(mietNr, jahr) values (-2, '2016');
insert into CrMessage(id, msgType, msg,crDetail_mietNr) values (-2, 'Error','This is a server error.',-2);
insert into CrPeriod(id,periodFrom,periodTo,crDetail_mietNr) values (-2,PARSEDATETIME('01012016','ddMMyyyy'),PARSEDATETIME('31122016','ddMMyyyy'),-2); 
insert into CrPortfolio(id,bezeichnung,status,grund,anzahlPkw,anzahlLkw,anzahlTotal,mieteGeplantPkw,mieteGeplantLkw,mieteGeplantTotal,mieteAbgerechnetPkw,mieteAbgerechnetLkw,mieteAbgerechnetTotal,crPeriod_id) values (-2, 'Bezeichnung','Status2','Grund2',null,null,null,1000000,1000000,1000000,null,null,null,-2);