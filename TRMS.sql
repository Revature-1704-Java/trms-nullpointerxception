-- Grade Format Create Table
CREATE TABLE gradeformat (
    gradeformatid INTEGER PRIMARY KEY,
    format VARCHAR2(30) NOT NULL,
    defaultpassinggrade VARCHAR2(30) NOT NULL
);

-- Event Type Create Table
CREATE TABLE eventtype(
    eventtypeid INTEGER PRIMARY KEY,
    eventtype VARCHAR2(35) NOT NULL,
    coverage NUMERIC(*,2)
);

-- Approval Create Table
CREATE TABLE approval (
    approvalid INTEGER PRIMARY KEY,
    status VARCHAR2(50) NOT NULL
);
--Reimbursement Location Create Table
CREATE TABLE reimbursementlocation (
    reimbursementlocationid INTEGER PRIMARY KEY,
    startdate DATE NOT NULL,
    address VARCHAR2(30) NOT NULL,
    city VARCHAR2(20) NOT NULL,
    zip VARCHAR(20),
    country VARCHAR2(20) NOT NULL
);

--Employee type Create table
CREATE TABLE employeetype (
    employeetypeid INTEGER PRIMARY KEY,
    employeetype VARCHAR2(50) NOT NULL
);
--Employee Create Table
CREATE TABLE employee (
    employeeid INTEGER PRIMARY KEY,
    email VARCHAR2(15) NOT NULL,
    password VARCHAR2(26) NOT NULL,
    firstname VARCHAR2(15) NOT NULL,
    lastname VARCHAR2(15) NOT NULL,
    reportsto INTEGER,
    CONSTRAINT fk_reportsto FOREIGN KEY (reportsto) REFERENCES employee(employeeid)
);

--Employee Role Create Table
CREATE TABLE employeerole(
    employeeroleid INTEGER PRIMARY KEY,
    employeeid INTEGER NOT NULL,
    employeetypeid INTEGER NOT NULL,
    CONSTRAINT fk_roleemployeeid FOREIGN KEY (employeeid) REFERENCES employee(employeeid),
    CONSTRAINT fk_employeetypeid FOREIGN KEY (employeetypeid) REFERENCES employeetype(employeetypeid)
);

--Approval Process create table
CREATE TABLE approvalprocess (
    approvalprocessid INTEGER PRIMARY KEY,
    employeecreationdate DATE NOT NULL,
    employeecreationtime TIMESTAMP WITH LOCAL TIME ZONE NOT NULL,
    supervisorapprovedate DATE,
    departmentheadapprovedate DATE
);

-- Reimbursement Create Table
CREATE TABLE reimbursement (
    reimbursementid INTEGER PRIMARY KEY,
    employeeid INTEGER NOT NULL,
    supervisorid INTEGER,
    departmentheadid INTEGER,
    bencoid INTEGER,
    approvalprocessid INTEGER NOT NULL,
    reimbursementlocationid INTEGER NOT NULL,
    description VARCHAR2(500) NOT NULL,
    cost NUMERIC(9,2) NOT NULL,
    adjustedcost NUMERIC(9,2),
    gradeformatid INTEGER NOT NULL,
    eventtypeid INTEGER NOT NULL,
    workjustification VARCHAR2(500) NOT NULL,
    attachment BLOB,
    approvaldocument BLOB,
    approvalid INTEGER NOT NULL,
    timemissed INTEGER,
    denyreason VARCHAR2(500),
    inflatedreimbursementreason VARCHAR2(500),
    CONSTRAINT fk_employeeid FOREIGN KEY (employeeid) REFERENCES employee(employeeid),
    CONSTRAINT fk_supervisorid FOREIGN KEY (supervisorid) REFERENCES employee(employeeid),
    CONSTRAINT fk_rdepartmentheadid FOREIGN KEY (departmentheadid) REFERENCES employee(employeeid),
    CONSTRAINT fk_bencoid FOREIGN KEY (bencoid) REFERENCES employee(employeeid),
    CONSTRAINT fk_reimbursementlocationid FOREIGN KEY (reimbursementlocationid) REFERENCES reimbursementlocation(reimbursementlocationid),
    CONSTRAINT fk_gradeformatid FOREIGN KEY (gradeformatid) REFERENCES gradeformat(gradeformatid),
    CONSTRAINT fk_eventtypeid FOREIGN KEY (eventtypeid) REFERENCES eventtype(eventtypeid),
    CONSTRAINT fk_approvalid FOREIGN KEY (approvalid) REFERENCES approval(approvalid),
    CONSTRAINT fk_approvalprocessid FOREIGN KEY (approvalprocessid) REFERENCES approvalprocess(approvalprocessid)
);


-- Populate Event Type table
INSERT INTO eventtype VALUES (1, 'University Course', 0.80);
INSERT INTO eventtype VALUES (2, 'Seminar', 0.60);
INSERT INTO eventtype VALUES (3, 'Certification Preparation Class', 0.75);
INSERT INTO eventtype VALUES (4, 'Certification', 1.00);
INSERT INTO eventtype VALUES (5, 'Technical Training', 0.90);
INSERT INTO eventtype VALUES (6, 'Other', 0.30);

INSERT INTO approval VALUES (1, 'PENDING APPROVAL FROM SUPERVISOR');
INSERT INTO approval VALUES (2, 'PENDING APPROVAL FROM DEPARTMENT HEAD');
INSERT INTO approval VALUES (3, 'PENDING APPROVAL FROM BENEFITS COORDINATOR');
INSERT INTO approval VALUES (4, 'NEED ADDITIONAL INFORMATION');
INSERT INTO approval VALUES (5, 'REIMBURSEMENT AMOUNT ALTERED');
INSERT INTO approval VALUES (6, 'EMPLOYEE CANCELED');
INSERT INTO approval VALUES (7, 'DENIED');
INSERT INTO approval VALUES (8, 'APPROVED');

INSERT INTO gradeformat VALUES (1, 'PASS/FAIL', 'PASS');
INSERT INTO gradeformat VALUES (2, 'LETTER GRADES', 'C');
INSERT INTO gradeformat VALUES (3, 'PERCENTAGE', '70');
INSERT INTO gradeformat VALUES (4, 'PRESENTATION', 'NONE');

INSERT INTO employeetype VALUES (1, 'Employee');
INSERT INTO employeetype VALUES (2, 'Supervisor');
INSERT INTO employeetype VALUES (3, 'Department Head');
INSERT INTO employeetype VALUES (4, 'Benefits Coordinator');
--Sequence for Employee table
CREATE SEQUENCE employee_sequence
START WITH 1
INCREMENT BY 1;
/
--Sequence for Reimbursement table
CREATE SEQUENCE reimbursement_sequence
START WITH 1
INCREMENT BY 1;
/
--Sequence for Employee Role table
CREATE SEQUENCE employeerole_sequence
START WITH 1
INCREMENT BY 1;
/
--Sequence for Approval Process table
CREATE SEQUENCE approvalprocess_sequence
START WITH 1
INCREMENT BY 1;
--Sequence for Reimbursement Location table
CREATE SEQUENCE reimbursementlocation_sequence
START WITH 1
INCREMENT BY 1;
/
--Trigger for Employee Insert
CREATE OR REPLACE TRIGGER employee_insert_trigger
BEFORE INSERT ON employee
FOR EACH ROW
BEGIN
    SELECT employee_sequence.NEXTVAL INTO :NEW.employeeid FROM DUAL;
END;
/
--Trigger for Reimbursement Insert
CREATE OR REPLACE TRIGGER reimbursement_insert_trigger
BEFORE INSERT ON reimbursement
FOR EACH ROW
BEGIN
    SELECT reimbursement_sequence.NEXTVAL INTO :NEW.reimbursementid FROM DUAL;
END;
/
--Trigger for Employee Role Insert
CREATE OR REPLACE TRIGGER employeerole_insert_trigger
BEFORE INSERT ON employeerole
FOR EACH ROW
BEGIN
    SELECT employeerole_sequence.NEXTVAL INTO :NEW.employeeroleid FROM DUAL;
END;
/
--Trigger for Approval Process Insert
CREATE OR REPLACE TRIGGER approvalprocess_insert_trigger
BEFORE INSERT ON approvalprocess
FOR EACH ROW
BEGIN
    SELECT approvalprocess_sequence.NEXTVAL INTO :NEW.approvalprocessid FROM DUAL;
END;
/
--Trigger for Reimbursement Location Insert
CREATE OR REPLACE TRIGGER reimlocation_insert_trigger
BEFORE INSERT ON reimbursementlocation
FOR EACH ROW
BEGIN
    SELECT reimbursementlocation_sequence.NEXTVAL INTO :NEW.reimbursementlocationid FROM DUAL;
END;
/


--Stored Procudure for inserting Employee
CREATE OR REPLACE PROCEDURE sp_insert_employee(inputemail IN employee.email%TYPE, inputpassword IN employee.password%TYPE, inputfirstname IN employee.firstname%TYPE, inputlastname IN employee.lastname%TYPE, inputreportsto IN employee.reportsto%TYPE, issuccessful OUT VARCHAR2)
AS
BEGIN
    SAVEPOINT savepoint;
    INSERT INTO employee (email,password,firstname,lastname,reportsto)VALUES(inputemail,inputpassword,inputfirstname,inputlastname,inputreportsto);
    isSuccessful := 1;
    COMMIT;
    
    EXCEPTION
    WHEN OTHERS THEN
        isSuccessful := 0;
    ROLLBACK TO SAVEPOINT savepoint;
END;
/


--Stored Procedure for creating an Approval Process for a reimbursement
CREATE OR REPLACE PROCEDURE sp_insert_approvalprocess(inputdate IN DATE, inputtimestamp IN TIMESTAMP WITH LOCAL TIME ZONE, pk OUT INTEGER)
AS
BEGIN
    INSERT INTO approvalprocess (employeecreationdate, employeecreationtime, supervisorapprovedate, departmentheadapprovedate) VALUES (inputdate, inputtimestamp, null, null) RETURNING approvalprocessid INTO pk;
END;
/
--Stored Procedure for creating a reimbursement location record for a reimbursement;
CREATE OR REPLACE PROCEDURE sp_insert_reimlocation(inputdate IN DATE, inputaddress IN VARCHAR, inputcity IN VARCHAR, inputzip IN VARCHAR, inputcountry IN VARCHAR,pk OUT INTEGER)
AS
BEGIN
    INSERT INTO reimbursementlocation (startdate, address, city, zip, country) VALUES (inputdate,inputaddress,inputcity,inputzip,inputcountry) RETURNING reimbursementlocationid INTO pk;
END;
/
--Stored Procedure for creating a reimbursement
CREATE OR REPLACE PROCEDURE sp_insert_reimbursement (inputemployeeid IN INTEGER, inputapprovalprocessid IN INTEGER, inputreimbursementlocationid IN INTEGER, inputdescription IN VARCHAR, inputcost IN NUMBER, inputgradeformat IN VARCHAR, inputeventtype IN VARCHAR, inputworkjustification IN VARCHAR, inputattachment IN BLOB, inputapprovaldocument IN BLOB, inputtimemissed IN INTEGER, pk OUT INTEGER )
AS pk_gradeformat INTEGER; pk_eventtype INTEGER; pk_approval INTEGER;
BEGIN
    SELECT gradeformatid INTO pk_gradeformat FROM gradeformat WHERE format=inputgradeformat;
    SELECT eventtypeid INTO pk_eventtype FROM eventtype WHERE eventtype=inputeventtype;
    SELECT approvalid INTO pk_approval FROM approval WHERE status='PENDING APPROVAL FROM SUPERVISOR';
    INSERT INTO reimbursement (employeeid, approvalprocessid, reimbursementlocationid, description, cost, gradeformatid, eventtypeid, workjustification, attachment, approvaldocument, approvalid, timemissed) VALUES (inputemployeeid, inputapprovalprocessid, inputreimbursementlocationid, inputdescription, inputcost, pk_gradeformat, pk_eventtype, inputworkjustification, inputattachment, inputapprovaldocument, pk_approval, inputtimemissed) RETURNING reimbursementid INTO pk;
END;
/