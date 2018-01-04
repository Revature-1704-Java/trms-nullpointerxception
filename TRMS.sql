--------------------------------------------------------
--  File created - Thursday-January-04-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type ROLEARRAY
--------------------------------------------------------

  CREATE OR REPLACE TYPE "ADMIN"."ROLEARRAY" AS VARRAY(4) OF VARCHAR2(50);

/
--------------------------------------------------------
--  DDL for Table APPROVAL
--------------------------------------------------------

  CREATE TABLE "ADMIN"."APPROVAL" 
   (	"APPROVALID" NUMBER(*,0), 
	"STATUS" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table APPROVALPROCESS
--------------------------------------------------------

  CREATE TABLE "ADMIN"."APPROVALPROCESS" 
   (	"APPROVALPROCESSID" NUMBER(*,0), 
	"EMPLOYEECREATIONDATE" DATE, 
	"EMPLOYEECREATIONTIME" TIMESTAMP (6) WITH LOCAL TIME ZONE, 
	"SUPERVISORAPPROVEDATE" DATE, 
	"DEPARTMENTHEADAPPROVEDATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table DEPARTMENT
--------------------------------------------------------

  CREATE TABLE "ADMIN"."DEPARTMENT" 
   (	"DEPARTMENTID" NUMBER(*,0), 
	"DEPARTMENT" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table EMPLOYEE
--------------------------------------------------------

  CREATE TABLE "ADMIN"."EMPLOYEE" 
   (	"EMPLOYEEID" NUMBER(*,0), 
	"EMAIL" VARCHAR2(50 BYTE), 
	"FIRSTNAME" VARCHAR2(15 BYTE), 
	"LASTNAME" VARCHAR2(15 BYTE), 
	"REPORTSTO" NUMBER(*,0), 
	"DEPARTMENTID" NUMBER(*,0), 
	"SALT" VARCHAR2(20 BYTE), 
	"HASH" VARCHAR2(40 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table EMPLOYEEROLE
--------------------------------------------------------

  CREATE TABLE "ADMIN"."EMPLOYEEROLE" 
   (	"EMPLOYEEROLEID" NUMBER(*,0), 
	"EMPLOYEEID" NUMBER(*,0), 
	"EMPLOYEETYPEID" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table EMPLOYEETYPE
--------------------------------------------------------

  CREATE TABLE "ADMIN"."EMPLOYEETYPE" 
   (	"EMPLOYEETYPEID" NUMBER(*,0), 
	"EMPLOYEETYPE" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table EVENTTYPE
--------------------------------------------------------

  CREATE TABLE "ADMIN"."EVENTTYPE" 
   (	"EVENTTYPEID" NUMBER(*,0), 
	"EVENTTYPE" VARCHAR2(35 BYTE), 
	"COVERAGE" NUMBER(*,2)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table GRADEFORMAT
--------------------------------------------------------

  CREATE TABLE "ADMIN"."GRADEFORMAT" 
   (	"GRADEFORMATID" NUMBER(*,0), 
	"FORMAT" VARCHAR2(30 BYTE), 
	"DEFAULTPASSINGGRADE" VARCHAR2(30 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table REIMBURSEMENT
--------------------------------------------------------

  CREATE TABLE "ADMIN"."REIMBURSEMENT" 
   (	"REIMBURSEMENTID" NUMBER(*,0), 
	"EMPLOYEEID" NUMBER(*,0), 
	"SUPERVISORID" NUMBER(*,0), 
	"DEPARTMENTHEADID" NUMBER(*,0), 
	"BENCOID" NUMBER(*,0), 
	"APPROVALPROCESSID" NUMBER(*,0), 
	"REIMBURSEMENTLOCATIONID" NUMBER(*,0), 
	"DESCRIPTION" VARCHAR2(500 BYTE), 
	"COST" NUMBER(9,2), 
	"ADJUSTEDCOST" NUMBER(9,2), 
	"GRADEFORMATID" NUMBER(*,0), 
	"EVENTTYPEID" NUMBER(*,0), 
	"WORKJUSTIFICATION" VARCHAR2(500 BYTE), 
	"ATTACHMENT" BLOB, 
	"APPROVALDOCUMENT" BLOB, 
	"APPROVALID" NUMBER(*,0), 
	"TIMEMISSED" NUMBER(*,0), 
	"DENYREASON" VARCHAR2(500 BYTE), 
	"INFLATEDREIMBURSEMENTREASON" VARCHAR2(500 BYTE), 
	"CUSTOMPASSINGGRADE" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 
 LOB ("ATTACHMENT") STORE AS BASICFILE (
  TABLESPACE "USERS" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("APPROVALDOCUMENT") STORE AS BASICFILE (
  TABLESPACE "USERS" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;
--------------------------------------------------------
--  DDL for Table REIMBURSEMENTLOCATION
--------------------------------------------------------

  CREATE TABLE "ADMIN"."REIMBURSEMENTLOCATION" 
   (	"REIMBURSEMENTLOCATIONID" NUMBER(*,0), 
	"STARTDATE" DATE, 
	"ADDRESS" VARCHAR2(30 BYTE), 
	"CITY" VARCHAR2(20 BYTE), 
	"ZIP" VARCHAR2(20 BYTE), 
	"COUNTRY" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Sequence APPROVALPROCESS_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "ADMIN"."APPROVALPROCESS_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 361 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence EMPLOYEEROLE_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "ADMIN"."EMPLOYEEROLE_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 541 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence EMPLOYEE_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "ADMIN"."EMPLOYEE_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 581 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence REIMBURSEMENTLOCATION_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "ADMIN"."REIMBURSEMENTLOCATION_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 341 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence REIMBURSEMENT_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "ADMIN"."REIMBURSEMENT_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 301 CACHE 20 NOORDER  NOCYCLE ;
REM INSERTING into ADMIN.APPROVAL
SET DEFINE OFF;
Insert into ADMIN.APPROVAL (APPROVALID,STATUS) values (1,'PENDING APPROVAL FROM SUPERVISOR');
Insert into ADMIN.APPROVAL (APPROVALID,STATUS) values (2,'PENDING APPROVAL FROM DEPARTMENT HEAD');
Insert into ADMIN.APPROVAL (APPROVALID,STATUS) values (3,'PENDING APPROVAL FROM BENEFITS COORDINATOR');
Insert into ADMIN.APPROVAL (APPROVALID,STATUS) values (4,'NEED ADDITIONAL INFORMATION');
Insert into ADMIN.APPROVAL (APPROVALID,STATUS) values (5,'REIMBURSEMENT AMOUNT ALTERED');
Insert into ADMIN.APPROVAL (APPROVALID,STATUS) values (6,'EMPLOYEE CANCELED');
Insert into ADMIN.APPROVAL (APPROVALID,STATUS) values (7,'DENIED');
Insert into ADMIN.APPROVAL (APPROVALID,STATUS) values (8,'APPROVED');

REM INSERTING into ADMIN.DEPARTMENT
SET DEFINE OFF;
Insert into ADMIN.DEPARTMENT (DEPARTMENTID,DEPARTMENT) values (1,'Department 1');
Insert into ADMIN.DEPARTMENT (DEPARTMENTID,DEPARTMENT) values (2,'Department 2');
Insert into ADMIN.DEPARTMENT (DEPARTMENTID,DEPARTMENT) values (3,'Department 3');

REM INSERTING into ADMIN.EMPLOYEETYPE
SET DEFINE OFF;
Insert into ADMIN.EMPLOYEETYPE (EMPLOYEETYPEID,EMPLOYEETYPE) values (1,'Employee');
Insert into ADMIN.EMPLOYEETYPE (EMPLOYEETYPEID,EMPLOYEETYPE) values (2,'Supervisor');
Insert into ADMIN.EMPLOYEETYPE (EMPLOYEETYPEID,EMPLOYEETYPE) values (3,'Department Head');
Insert into ADMIN.EMPLOYEETYPE (EMPLOYEETYPEID,EMPLOYEETYPE) values (4,'Benefits Coordinator');
REM INSERTING into ADMIN.EVENTTYPE
SET DEFINE OFF;
Insert into ADMIN.EVENTTYPE (EVENTTYPEID,EVENTTYPE,COVERAGE) values (1,'University Course',0.8);
Insert into ADMIN.EVENTTYPE (EVENTTYPEID,EVENTTYPE,COVERAGE) values (2,'Seminar',0.6);
Insert into ADMIN.EVENTTYPE (EVENTTYPEID,EVENTTYPE,COVERAGE) values (3,'Certification Preparation Class',0.75);
Insert into ADMIN.EVENTTYPE (EVENTTYPEID,EVENTTYPE,COVERAGE) values (4,'Certification',1);
Insert into ADMIN.EVENTTYPE (EVENTTYPEID,EVENTTYPE,COVERAGE) values (5,'Technical Training',0.9);
Insert into ADMIN.EVENTTYPE (EVENTTYPEID,EVENTTYPE,COVERAGE) values (6,'Other',0.3);
REM INSERTING into ADMIN.GRADEFORMAT
SET DEFINE OFF;
Insert into ADMIN.GRADEFORMAT (GRADEFORMATID,FORMAT,DEFAULTPASSINGGRADE) values (1,'PASS/FAIL','PASS');
Insert into ADMIN.GRADEFORMAT (GRADEFORMATID,FORMAT,DEFAULTPASSINGGRADE) values (2,'LETTER GRADES','C');
Insert into ADMIN.GRADEFORMAT (GRADEFORMATID,FORMAT,DEFAULTPASSINGGRADE) values (3,'PERCENTAGE','70');
Insert into ADMIN.GRADEFORMAT (GRADEFORMATID,FORMAT,DEFAULTPASSINGGRADE) values (4,'PRESENTATION','NONE');

--------------------------------------------------------
--  DDL for Index UNIQUE_EMAIL
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."UNIQUE_EMAIL" ON "ADMIN"."EMPLOYEE" ("EMAIL") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger APPROVALPROCESS_INSERT_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "ADMIN"."APPROVALPROCESS_INSERT_TRIGGER" 
BEFORE INSERT ON approvalprocess
FOR EACH ROW
BEGIN
    SELECT approvalprocess_sequence.NEXTVAL INTO :NEW.approvalprocessid FROM DUAL;
END;

/
ALTER TRIGGER "ADMIN"."APPROVALPROCESS_INSERT_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger EMPLOYEEROLE_INSERT_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "ADMIN"."EMPLOYEEROLE_INSERT_TRIGGER" 
BEFORE INSERT ON employeerole
FOR EACH ROW
BEGIN
    SELECT employeerole_sequence.NEXTVAL INTO :NEW.employeeroleid FROM DUAL;
END;

/
ALTER TRIGGER "ADMIN"."EMPLOYEEROLE_INSERT_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger EMPLOYEE_INSERT_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "ADMIN"."EMPLOYEE_INSERT_TRIGGER" 
BEFORE INSERT ON employee
FOR EACH ROW
BEGIN
    SELECT employee_sequence.NEXTVAL INTO :NEW.employeeid FROM DUAL;
END;

/
ALTER TRIGGER "ADMIN"."EMPLOYEE_INSERT_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger REIMBURSEMENT_INSERT_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "ADMIN"."REIMBURSEMENT_INSERT_TRIGGER" 
BEFORE INSERT ON reimbursement
FOR EACH ROW
BEGIN
    SELECT reimbursement_sequence.NEXTVAL INTO :NEW.reimbursementid FROM DUAL;
END;

/
ALTER TRIGGER "ADMIN"."REIMBURSEMENT_INSERT_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger REIMLOCATION_INSERT_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "ADMIN"."REIMLOCATION_INSERT_TRIGGER" 
BEFORE INSERT ON reimbursementlocation
FOR EACH ROW
BEGIN
    SELECT reimbursementlocation_sequence.NEXTVAL INTO :NEW.reimbursementlocationid FROM DUAL;
END;

/
ALTER TRIGGER "ADMIN"."REIMLOCATION_INSERT_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Procedure SP_CANCEL_REIM
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "ADMIN"."SP_CANCEL_REIM" (p_id  IN INTEGER)
AS 
BEGIN
    UPDATE reimbursement SET approvalid=(SELECT approvalid FROM approval WHERE status='EMPLOYEE CANCELED') WHERE reimbursementid=p_id;
END;

/
--------------------------------------------------------
--  DDL for Procedure SP_INSERT_APPROVALPROCESS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "ADMIN"."SP_INSERT_APPROVALPROCESS" (inputdate IN DATE, inputtimestamp IN TIMESTAMP WITH LOCAL TIME ZONE, pk OUT INTEGER)
AS
BEGIN
    INSERT INTO approvalprocess (employeecreationdate, employeecreationtime, supervisorapprovedate, departmentheadapprovedate) VALUES (inputdate, inputtimestamp, null, null) RETURNING approvalprocessid INTO pk;
END;

/
--------------------------------------------------------
--  DDL for Procedure SP_INSERT_REIMBURSEMENT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "ADMIN"."SP_INSERT_REIMBURSEMENT" (inputemployeeid IN INTEGER, inputapprovalprocessid IN INTEGER, inputreimbursementlocationid IN INTEGER, inputdescription IN VARCHAR, inputcost IN NUMBER, inputgradeformat IN VARCHAR, inputeventtype IN VARCHAR, inputworkjustification IN VARCHAR, inputattachment IN BLOB, inputapprovaldocument IN BLOB, inputtimemissed IN INTEGER, inputcustompassinggrade IN VARCHAR,  pk OUT INTEGER )
AS pk_gradeformat INTEGER; pk_eventtype INTEGER; pk_approval INTEGER;
BEGIN
    SELECT gradeformatid INTO pk_gradeformat FROM gradeformat WHERE format=inputgradeformat;
    SELECT eventtypeid INTO pk_eventtype FROM eventtype WHERE eventtype=inputeventtype;
    SELECT approvalid INTO pk_approval FROM approval WHERE status='PENDING APPROVAL FROM SUPERVISOR';
    INSERT INTO reimbursement (employeeid, approvalprocessid, reimbursementlocationid, description, cost, gradeformatid, eventtypeid, workjustification, attachment, approvaldocument, approvalid, timemissed, custompassinggrade) VALUES (inputemployeeid, inputapprovalprocessid, inputreimbursementlocationid, inputdescription, inputcost, pk_gradeformat, pk_eventtype, inputworkjustification, inputattachment, inputapprovaldocument, pk_approval, inputtimemissed, inputcustompassinggrade) RETURNING reimbursementid INTO pk;
END;

/
--------------------------------------------------------
--  DDL for Procedure SP_INSERT_REIMLOCATION
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "ADMIN"."SP_INSERT_REIMLOCATION" (inputdate IN DATE, inputaddress IN VARCHAR, inputcity IN VARCHAR, inputzip IN VARCHAR, inputcountry IN VARCHAR,pk OUT INTEGER)
AS
BEGIN
    INSERT INTO reimbursementlocation (startdate, address, city, zip, country) VALUES (inputdate,inputaddress,inputcity,inputzip,inputcountry) RETURNING reimbursementlocationid INTO pk;
END;

/
--------------------------------------------------------
--  DDL for Procedure SP_SELECT_ALL_BENCO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "ADMIN"."SP_SELECT_ALL_BENCO" (rs OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN rs FOR
    SELECT reimbursement.*, approvalprocess.*, reimbursementlocation.*, gradeformat.*, eventtype.*, approval.*, employee.*, supervisor.employeeid AS supervisorid, supervisor.email AS supervisoremail, supervisor.firstname AS supervisorfirstname, supervisor.lastname AS supervisorlastname, departmenthead.employeeid AS departmentheadid, departmenthead.employeeid AS departmentid, departmenthead.email AS departmentheademail, departmenthead.firstname AS departmentheadfirstname, departmenthead.lastname AS departmentheadlastname FROM ((((((((reimbursement INNER JOIN approvalprocess ON approvalprocess.approvalprocessid=reimbursement.approvalprocessid) INNER JOIN reimbursementlocation ON reimbursementlocation.reimbursementlocationid=reimbursement.reimbursementlocationid) INNER JOIN gradeformat ON gradeformat.gradeformatid=reimbursement.gradeformatid) INNER JOIN eventtype ON eventtype.eventtypeid=reimbursement.eventtypeid) INNER JOIN approval ON approval.approvalid=reimbursement.approvalid) INNER JOIN employee ON reimbursement.employeeid=employee.employeeid) LEFT JOIN employee supervisor ON reimbursement.supervisorid=supervisor.employeeid) LEFT JOIN employee departmenthead ON reimbursement.departmentheadid=departmenthead.employeeid) WHERE reimbursement.approvalid IN (3, 5) ORDER BY employeecreationdate, employeecreationtime;
END;

/
--------------------------------------------------------
--  DDL for Procedure SP_SELECT_ALL_DEPARTMENT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "ADMIN"."SP_SELECT_ALL_DEPARTMENT" (p_departmentid IN INTEGER, rs OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN rs FOR
    SELECT reimbursement.*, approvalprocess.*, reimbursementlocation.*, gradeformat.*, eventtype.*, approval.*, employee.*, supervisor.employeeid AS supervisorid, supervisor.email AS supervisoremail, supervisor.firstname AS supervisorfirstname, supervisor.lastname AS supervisorlastname, departmenthead.employeeid AS departmentheadid, departmenthead.employeeid AS departmentid, departmenthead.email AS departmentheademail, departmenthead.firstname AS departmentheadfirstname, departmenthead.lastname AS departmentheadlastname FROM ((((((((reimbursement INNER JOIN approvalprocess ON approvalprocess.approvalprocessid=reimbursement.approvalprocessid) INNER JOIN reimbursementlocation ON reimbursementlocation.reimbursementlocationid=reimbursement.reimbursementlocationid) INNER JOIN gradeformat ON gradeformat.gradeformatid=reimbursement.gradeformatid) INNER JOIN eventtype ON eventtype.eventtypeid=reimbursement.eventtypeid) INNER JOIN approval ON approval.approvalid=reimbursement.approvalid) INNER JOIN employee ON reimbursement.employeeid=employee.employeeid) LEFT JOIN employee supervisor ON reimbursement.supervisorid=supervisor.employeeid) LEFT JOIN employee departmenthead ON reimbursement.departmentheadid=departmenthead.employeeid) WHERE employee.departmentid=p_departmentid AND status IN ('PENDING APPROVAL FROM DEPARTMENT HEAD', 'PENDING APPROVAL FROM BENEFITS COORDINATOR','REIMBURSEMENT AMOUNT ALTERED','APPROVED') ORDER BY employeecreationdate,employeecreationtime;
END;

/
--------------------------------------------------------
--  DDL for Procedure SP_SELECT_ALL_UNDERLING_REIM
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "ADMIN"."SP_SELECT_ALL_UNDERLING_REIM" (p_employeeid IN INTEGER, rs OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN rs FOR
    SELECT reimbursement.*, approvalprocess.*, reimbursementlocation.*, gradeformat.*, eventtype.*, approval.*, employee.*, supervisor.employeeid AS supervisorid, supervisor.email AS supervisoremail, supervisor.firstname AS supervisorfirstname, supervisor.lastname AS supervisorlastname, departmenthead.employeeid AS departmentheadid, departmenthead.employeeid AS departmentid, departmenthead.email AS departmentheademail, departmenthead.firstname AS departmentheadfirstname, departmenthead.lastname AS departmentheadlastname FROM ((((((((reimbursement INNER JOIN approvalprocess ON approvalprocess.approvalprocessid=reimbursement.approvalprocessid) INNER JOIN reimbursementlocation ON reimbursementlocation.reimbursementlocationid=reimbursement.reimbursementlocationid) INNER JOIN gradeformat ON gradeformat.gradeformatid=reimbursement.gradeformatid) INNER JOIN eventtype ON eventtype.eventtypeid=reimbursement.eventtypeid) INNER JOIN approval ON approval.approvalid=reimbursement.approvalid) INNER JOIN employee ON reimbursement.employeeid=employee.employeeid) LEFT JOIN employee supervisor ON reimbursement.supervisorid=supervisor.employeeid) LEFT JOIN employee departmenthead ON reimbursement.departmentheadid=departmenthead.employeeid) WHERE employee.reportsto=p_employeeid ORDER BY employeecreationdate, employeecreationtime;
END;

/
--------------------------------------------------------
--  DDL for Procedure SP_SELECT_ONE_REIMBURSEMENT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "ADMIN"."SP_SELECT_ONE_REIMBURSEMENT" (p_reimbursementid IN INTEGER, rs OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN rs FOR
    SELECT * FROM (((((reimbursement INNER JOIN approvalprocess ON approvalprocess.approvalprocessid=reimbursement.approvalprocessid) INNER JOIN reimbursementlocation ON reimbursementlocation.reimbursementlocationid=reimbursement.reimbursementlocationid) INNER JOIN gradeformat ON gradeformat.gradeformatid=reimbursement.gradeformatid) INNER JOIN eventtype ON eventtype.eventtypeid=reimbursement.eventtypeid) INNER JOIN approval ON approval.approvalid=reimbursement.approvalid) WHERE reimbursement.reimbursementid=p_reimbursementid ORDER BY employeecreationdate, employeecreationtime;
END;

/
--------------------------------------------------------
--  DDL for Procedure SP_SELECT_REIMBURSEMENT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "ADMIN"."SP_SELECT_REIMBURSEMENT" (inputemployeeid IN INTEGER, rs OUT SYS_REFCURSOR)
AS 
BEGIN
--SELECT * INTO rs FROM (((((reimbursement INNER JOIN approvalprocess ON approvalprocess.approvalprocessid=reimbursement.approvalprocessid) INNER JOIN reimbursementlocation ON reimbursementlocation.reimbursementlocationid=reimbursement.reimbursementlocationid) INNER JOIN gradeformat ON gradeformat.gradeformatid=reimbursement.gradeformatid) INNER JOIN eventtype ON eventtype.eventtypeid=reimbursement.eventtypeid) INNER JOIN approval ON approval.approvalid=reimbursement.approvalid) WHERE reimbursement.employeeid=inputemployeeid;
    OPEN rs FOR
    SELECT reimbursement.*, approvalprocess.*, reimbursementlocation.*, gradeformat.*, eventtype.*, approval.*, supervisor.employeeid AS supervisorid, supervisor.email AS supervisoremail, supervisor.firstname AS supervisorfirstname, supervisor.lastname AS supervisorlastname, departmenthead.employeeid AS departmentheadid, departmenthead.employeeid AS departmentid, departmenthead.email AS departmentheademail, departmenthead.firstname AS departmentheadfirstname, departmenthead.lastname AS departmentheadlastname FROM (((((((reimbursement INNER JOIN approvalprocess ON approvalprocess.approvalprocessid=reimbursement.approvalprocessid) INNER JOIN reimbursementlocation ON reimbursementlocation.reimbursementlocationid=reimbursement.reimbursementlocationid) INNER JOIN gradeformat ON gradeformat.gradeformatid=reimbursement.gradeformatid) INNER JOIN eventtype ON eventtype.eventtypeid=reimbursement.eventtypeid) INNER JOIN approval ON approval.approvalid=reimbursement.approvalid) LEFT JOIN employee supervisor ON reimbursement.supervisorid=supervisor.employeeid) LEFT JOIN employee departmenthead ON reimbursement.departmentheadid=departmenthead.employeeid) WHERE reimbursement.employeeid=inputemployeeid ORDER BY employeecreationdate, employeecreationtime;

END;

/
--------------------------------------------------------
--  DDL for Procedure SP_UPDATE_ALTER_REIM
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "ADMIN"."SP_UPDATE_ALTER_REIM" (p_id  IN INTEGER, p_alterAmount IN NUMERIC, p_reason IN VARCHAR2)
AS 
BEGIN
    UPDATE reimbursement SET adjustedcost=p_alterAmount, inflatedreimbursementreason=p_reason, approvalid=(SELECT approvalid FROM approval WHERE status='REIMBURSEMENT AMOUNT ALTERED') WHERE reimbursementid=p_id;
END;

/
--------------------------------------------------------
--  DDL for Procedure SP_UPDATE_APPROVE_BENCO_REIM
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "ADMIN"."SP_UPDATE_APPROVE_BENCO_REIM" (p_id IN INTEGER)
AS
BEGIN
    UPDATE reimbursement SET approvalid=(SELECT approvalid FROM approval WHERE status='APPROVED') WHERE reimbursementid=p_id;
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure SP_UPDATE_APPROVE_DEPART_REIM
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "ADMIN"."SP_UPDATE_APPROVE_DEPART_REIM" (p_id IN INTEGER , p_departmenthead IN INTEGER)
AS apid INTEGER;
BEGIN
    UPDATE reimbursement SET departmentheadid=p_departmenthead, approvalid=(SELECT approvalid FROM approval WHERE status='PENDING APPROVAL FROM BENEFITS COORDINATOR') WHERE reimbursementid=p_id RETURNING approvalprocessid INTO apid;
    UPDATE approvalprocess SET departmentheadapprovedate=CURRENT_DATE;
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure SP_UPDATE_APPROVE_SUPERV_REIM
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "ADMIN"."SP_UPDATE_APPROVE_SUPERV_REIM" (p_id IN INTEGER, p_supervisor IN INTEGER)
AS apid INTEGER;
BEGIN
    UPDATE reimbursement SET supervisorid=p_supervisor, approvalid=(SELECT approvalid FROM approval WHERE status='PENDING APPROVAL FROM DEPARTMENT HEAD') WHERE reimbursementid=p_id RETURNING approvalprocessid INTO apid;
    UPDATE approvalprocess SET supervisorapprovedate=CURRENT_DATE;
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure SP_UPDATE_DENY_BENCO_REIM
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "ADMIN"."SP_UPDATE_DENY_BENCO_REIM" (p_id IN INTEGER, p_bencoid IN INTEGER, p_approval IN VARCHAR, p_reason IN VARCHAR)
AS
BEGIN
    UPDATE reimbursement SET bencoid=p_bencoid, approvalid=(SELECT approvalid FROM approval WHERE status=p_approval), denyreason=p_reason WHERE reimbursementid=p_id;
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure SP_UPDATE_DENY_DEPARTMENT_REIM
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "ADMIN"."SP_UPDATE_DENY_DEPARTMENT_REIM" (p_id IN INTEGER, p_departmenthead IN INTEGER, p_approval IN VARCHAR, p_reason IN VARCHAR)
AS
BEGIN
    UPDATE reimbursement SET departmentheadid=p_departmenthead, approvalid=(SELECT approvalid FROM approval WHERE status=p_approval), denyreason=p_reason WHERE reimbursementid=p_id;
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure SP_UPDATE_DENY_SUPERVISOR_REIM
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "ADMIN"."SP_UPDATE_DENY_SUPERVISOR_REIM" (p_id IN INTEGER, p_supervisor IN INTEGER, p_approval IN VARCHAR, p_reason IN VARCHAR)
AS
BEGIN
    UPDATE reimbursement SET supervisorid=p_supervisor, approvalid=(SELECT approvalid FROM approval WHERE status=p_approval), denyreason=p_reason WHERE reimbursementid=p_id;
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Package APP_USER_SECURITY
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE "ADMIN"."APP_USER_SECURITY" AS

    FUNCTION get_hash (p_email  IN  VARCHAR2, p_password  IN  VARCHAR2)
        RETURN VARCHAR2;
        
    FUNCTION get_hash (p_email  IN  VARCHAR2, p_password  IN  VARCHAR2, p_salt IN VARCHAR2)
        RETURN VARCHAR2;

    PROCEDURE add_user (p_email IN VARCHAR2, p_password IN VARCHAR2, p_firstname IN employee.firstname%TYPE, p_lastname IN employee.lastname%TYPE, p_reportsto IN employee.reportsto%TYPE, p_departmentid IN employee.departmentid%TYPE, p_salt IN VARCHAR, pk OUT INTEGER);

    FUNCTION valid_user (p_email  IN  VARCHAR2, p_password  IN  VARCHAR2)
        RETURN SYS_REFCURSOR;

END;

/
--------------------------------------------------------
--  DDL for Package Body APP_USER_SECURITY
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE BODY "ADMIN"."APP_USER_SECURITY" AS

    FUNCTION get_hash(p_email IN VARCHAR2, p_password IN VARCHAR2)
    RETURN VARCHAR2 AS l_salt VARCHAR2(20);
    BEGIN
        SELECT salt INTO l_salt FROM employee WHERE email=p_email;
        RETURN DBMS_CRYPTO.HASH(UTL_I18N.STRING_TO_RAW(UPPER(p_email) || l_salt || UPPER(p_password)), DBMS_CRYPTO.HASH_SH1);
    END;
    
    FUNCTION get_hash(p_email IN VARCHAR2, p_password IN VARCHAR2, p_salt IN VARCHAR2)
    RETURN VARCHAR2 AS
    BEGIN
        RETURN DBMS_CRYPTO.HASH(UTL_I18N.STRING_TO_RAW(UPPER(p_email) || p_salt || UPPER(p_password)), DBMS_CRYPTO.HASH_SH1);
    END;

    PROCEDURE add_user (p_email IN VARCHAR2, p_password IN VARCHAR2, p_firstname IN employee.firstname%TYPE, p_lastname IN employee.lastname%TYPE, p_reportsto IN employee.reportsto%TYPE, p_departmentid IN employee.departmentid%TYPE, p_salt IN VARCHAR, pk OUT INTEGER) 
    AS
    BEGIN
        SAVEPOINT savepoint;
        INSERT INTO employee (email,firstname,lastname,reportsto,departmentid, salt, hash) VALUES (p_email, p_firstname, p_lastname, p_reportsto, p_departmentid, p_salt, app_user_security.get_hash(p_email,p_password, p_salt)) RETURNING employeeid INTO pk;
        COMMIT;

--        EXCEPTION
--        WHEN OTHERS THEN
--        ROLLBACK TO SAVEPOINT savepoint;
    END;

    FUNCTION valid_user (p_email IN VARCHAR2, p_password IN VARCHAR2)
    RETURN SYS_REFCURSOR AS c SYS_REFCURSOR;
    BEGIN
        OPEN c FOR
        SELECT * FROM employee WHERE email=p_email AND hash=app_user_security.get_hash(p_email, p_password);
        RETURN c;
    END;
END;

/
--------------------------------------------------------
--  Constraints for Table EMPLOYEEROLE
--------------------------------------------------------

  ALTER TABLE "ADMIN"."EMPLOYEEROLE" ADD PRIMARY KEY ("EMPLOYEEROLEID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ADMIN"."EMPLOYEEROLE" MODIFY ("EMPLOYEETYPEID" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."EMPLOYEEROLE" MODIFY ("EMPLOYEEID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table DEPARTMENT
--------------------------------------------------------

  ALTER TABLE "ADMIN"."DEPARTMENT" ADD PRIMARY KEY ("DEPARTMENTID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ADMIN"."DEPARTMENT" MODIFY ("DEPARTMENT" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table EMPLOYEETYPE
--------------------------------------------------------

  ALTER TABLE "ADMIN"."EMPLOYEETYPE" ADD PRIMARY KEY ("EMPLOYEETYPEID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ADMIN"."EMPLOYEETYPE" MODIFY ("EMPLOYEETYPE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table APPROVALPROCESS
--------------------------------------------------------

  ALTER TABLE "ADMIN"."APPROVALPROCESS" ADD PRIMARY KEY ("APPROVALPROCESSID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ADMIN"."APPROVALPROCESS" MODIFY ("EMPLOYEECREATIONTIME" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."APPROVALPROCESS" MODIFY ("EMPLOYEECREATIONDATE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table REIMBURSEMENTLOCATION
--------------------------------------------------------

  ALTER TABLE "ADMIN"."REIMBURSEMENTLOCATION" ADD PRIMARY KEY ("REIMBURSEMENTLOCATIONID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ADMIN"."REIMBURSEMENTLOCATION" MODIFY ("COUNTRY" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."REIMBURSEMENTLOCATION" MODIFY ("CITY" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."REIMBURSEMENTLOCATION" MODIFY ("ADDRESS" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."REIMBURSEMENTLOCATION" MODIFY ("STARTDATE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table REIMBURSEMENT
--------------------------------------------------------

  ALTER TABLE "ADMIN"."REIMBURSEMENT" ADD PRIMARY KEY ("REIMBURSEMENTID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ADMIN"."REIMBURSEMENT" MODIFY ("APPROVALID" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."REIMBURSEMENT" MODIFY ("WORKJUSTIFICATION" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."REIMBURSEMENT" MODIFY ("EVENTTYPEID" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."REIMBURSEMENT" MODIFY ("GRADEFORMATID" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."REIMBURSEMENT" MODIFY ("COST" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."REIMBURSEMENT" MODIFY ("DESCRIPTION" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."REIMBURSEMENT" MODIFY ("REIMBURSEMENTLOCATIONID" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."REIMBURSEMENT" MODIFY ("APPROVALPROCESSID" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."REIMBURSEMENT" MODIFY ("EMPLOYEEID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table EVENTTYPE
--------------------------------------------------------

  ALTER TABLE "ADMIN"."EVENTTYPE" ADD PRIMARY KEY ("EVENTTYPEID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ADMIN"."EVENTTYPE" MODIFY ("EVENTTYPE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table EMPLOYEE
--------------------------------------------------------

  ALTER TABLE "ADMIN"."EMPLOYEE" MODIFY ("HASH" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."EMPLOYEE" MODIFY ("SALT" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."EMPLOYEE" ADD CONSTRAINT "UNIQUE_EMAIL" UNIQUE ("EMAIL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ADMIN"."EMPLOYEE" MODIFY ("DEPARTMENTID" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."EMPLOYEE" ADD PRIMARY KEY ("EMPLOYEEID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ADMIN"."EMPLOYEE" MODIFY ("LASTNAME" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."EMPLOYEE" MODIFY ("FIRSTNAME" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."EMPLOYEE" MODIFY ("EMAIL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table APPROVAL
--------------------------------------------------------

  ALTER TABLE "ADMIN"."APPROVAL" ADD PRIMARY KEY ("APPROVALID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ADMIN"."APPROVAL" MODIFY ("STATUS" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table GRADEFORMAT
--------------------------------------------------------

  ALTER TABLE "ADMIN"."GRADEFORMAT" ADD PRIMARY KEY ("GRADEFORMATID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ADMIN"."GRADEFORMAT" MODIFY ("DEFAULTPASSINGGRADE" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."GRADEFORMAT" MODIFY ("FORMAT" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table EMPLOYEE
--------------------------------------------------------

  ALTER TABLE "ADMIN"."EMPLOYEE" ADD CONSTRAINT "FK_DEPARTMENTID" FOREIGN KEY ("DEPARTMENTID")
	  REFERENCES "ADMIN"."DEPARTMENT" ("DEPARTMENTID") ENABLE;
  ALTER TABLE "ADMIN"."EMPLOYEE" ADD CONSTRAINT "FK_REPORTSTO" FOREIGN KEY ("REPORTSTO")
	  REFERENCES "ADMIN"."EMPLOYEE" ("EMPLOYEEID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table EMPLOYEEROLE
--------------------------------------------------------

  ALTER TABLE "ADMIN"."EMPLOYEEROLE" ADD CONSTRAINT "FK_EMPLOYEETYPEID" FOREIGN KEY ("EMPLOYEETYPEID")
	  REFERENCES "ADMIN"."EMPLOYEETYPE" ("EMPLOYEETYPEID") ENABLE;
  ALTER TABLE "ADMIN"."EMPLOYEEROLE" ADD CONSTRAINT "FK_ROLEEMPLOYEEID" FOREIGN KEY ("EMPLOYEEID")
	  REFERENCES "ADMIN"."EMPLOYEE" ("EMPLOYEEID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table REIMBURSEMENT
--------------------------------------------------------

  ALTER TABLE "ADMIN"."REIMBURSEMENT" ADD CONSTRAINT "FK_APPROVALID" FOREIGN KEY ("APPROVALID")
	  REFERENCES "ADMIN"."APPROVAL" ("APPROVALID") ENABLE;
  ALTER TABLE "ADMIN"."REIMBURSEMENT" ADD CONSTRAINT "FK_APPROVALPROCESSID" FOREIGN KEY ("APPROVALPROCESSID")
	  REFERENCES "ADMIN"."APPROVALPROCESS" ("APPROVALPROCESSID") ENABLE;
  ALTER TABLE "ADMIN"."REIMBURSEMENT" ADD CONSTRAINT "FK_BENCOID" FOREIGN KEY ("BENCOID")
	  REFERENCES "ADMIN"."EMPLOYEE" ("EMPLOYEEID") ENABLE;
  ALTER TABLE "ADMIN"."REIMBURSEMENT" ADD CONSTRAINT "FK_EMPLOYEEID" FOREIGN KEY ("EMPLOYEEID")
	  REFERENCES "ADMIN"."EMPLOYEE" ("EMPLOYEEID") ENABLE;
  ALTER TABLE "ADMIN"."REIMBURSEMENT" ADD CONSTRAINT "FK_EVENTTYPEID" FOREIGN KEY ("EVENTTYPEID")
	  REFERENCES "ADMIN"."EVENTTYPE" ("EVENTTYPEID") ENABLE;
  ALTER TABLE "ADMIN"."REIMBURSEMENT" ADD CONSTRAINT "FK_GRADEFORMATID" FOREIGN KEY ("GRADEFORMATID")
	  REFERENCES "ADMIN"."GRADEFORMAT" ("GRADEFORMATID") ENABLE;
  ALTER TABLE "ADMIN"."REIMBURSEMENT" ADD CONSTRAINT "FK_RDEPARTMENTHEADID" FOREIGN KEY ("DEPARTMENTHEADID")
	  REFERENCES "ADMIN"."EMPLOYEE" ("EMPLOYEEID") ENABLE;
  ALTER TABLE "ADMIN"."REIMBURSEMENT" ADD CONSTRAINT "FK_REIMBURSEMENTLOCATIONID" FOREIGN KEY ("REIMBURSEMENTLOCATIONID")
	  REFERENCES "ADMIN"."REIMBURSEMENTLOCATION" ("REIMBURSEMENTLOCATIONID") ENABLE;
  ALTER TABLE "ADMIN"."REIMBURSEMENT" ADD CONSTRAINT "FK_SUPERVISORID" FOREIGN KEY ("SUPERVISORID")
	  REFERENCES "ADMIN"."EMPLOYEE" ("EMPLOYEEID") ENABLE;
