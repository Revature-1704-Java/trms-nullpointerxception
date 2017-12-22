-- Grade Format Create Table
CREATE TABLE gradeformat (
    gradeformatid INTEGER PRIMARY KEY,
    format VARCHAR2(10) NOT NULL,
    defaultpassinggrade VARCHAR2(20) NOT NULL
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
    status VARCHAR2(10) NOT NULL
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

--Department Create Table
CREATE TABLE department (
    departmentid INTEGER PRIMARY KEY,
    department VARCHAR2(20) NOT NULL,
    departmentheadid INTEGER NOT NULL
);

--Employee Create Table
CREATE TABLE employee (
    employeeid INTEGER PRIMARY KEY,
    departmentid INTEGER NOT NULL,
    username VARCHAR2(15) NOT NULL,
    password VARCHAR2(26) NOT NULL,
    firstname VARCHAR2(15) NOT NULL,
    lastname VARCHAR2(15) NOT NULL,
    reportsto INTEGER,
    isbenco VARCHAR(1) NOT NULL,
    availablereimbursement NUMERIC(*,2) NOT NULL,
    CONSTRAINT fk_departmentid FOREIGN KEY (departmentid) REFERENCES department(departmentid),
    CONSTRAINT fk_reportsto FOREIGN KEY (reportsto) REFERENCES employee(employeeid)
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



-- Add Constraint to department
ALTER TABLE department ADD CONSTRAINT fk_departmentheadid FOREIGN KEY (departmentheadid) REFERENCES employee(employeeid);
ALTER TABLE gradeformat MODIFY format VARCHAR2(20);
-- Populate Event Type table
INSERT INTO eventtype VALUES (1, 'University Course', 0.80);
INSERT INTO eventtype VALUES (2, 'Seminar', 0.60);
INSERT INTO eventtype VALUES (3, 'Certification Preparation Class', 0.75);
INSERT INTO eventtype VALUES (4, 'Certification', 1.00);
INSERT INTO eventtype VALUES (5, 'Technical Training', 0.90);
INSERT INTO eventtype VALUES (6, 'Other', 0.30);

INSERT INTO approval VALUES (1, 'PENDING APPROVAL FROM SUPERVISOR');
INSERT INTO approval VALUES (2, 'PENDING APPROVAL FROM DEPARTMENT HEAD');
INSERT INTO approval VALUES (3, 'PENDING APPROVAL FROM BENEFIT COORDINATOR');
INSERT INTO approval VALUES (4, 'NEED ADDITIONAL INFORMATION');
INSERT INTO approval VALUES (5, 'REIMBURSEMENT AMOUNT ALTERED');
INSERT INTO approval VALUES (6, 'EMPLOYEE CANCELED');
INSERT INTO approval VALUES (7, 'DENIED');

INSERT INTO gradeformat VALUES (1, 'PASS/FAIL', 'PASS');
INSERT INTO gradeformat VALUES (2, 'LETTER GRADES', 'C');
INSERT INTO gradeformat VALUES (3, 'PERCENTAGE', '70');
INSERT INTO gradeformat VALUES (4, 'PRESENTATION', 'NONE');
