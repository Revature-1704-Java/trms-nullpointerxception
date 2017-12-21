-- Grade Format Create Table
CREATE TABLE gradeformat (
    gradeformatid INTEGER PRIMARY KEY,
    format VARCHAR2(10) NOT NULL
);

-- Event Type Create Table
CREATE TABLE eventtype(
    eventtypeid INTEGER PRIMARY KEY,
    eventtype VARCHAR2(25) NOT NULL,
    coverage NUMBER(*,2)
);

-- Approval Create Table
CREATE TABLE approval (
    approvalid INTEGER PRIMARY KEY,
    status VARCHAR2(10) NOT NULL
);

--Reimbursement Location Create Table
CREATE TABLE reimbursementlocation (
    reimbursementlocationid INTEGER PRIMARY KEY,
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
    address VARCHAR2(20) NOT NULL,
    city VARCHAR2(20) NOT NULL,
    zip VARCHAR2(20),
    country VARCHAR2(20) NOT NULL,
    reportsto INTEGER,
    isbenco VARCHAR(1) NOT NULL,
    CONSTRAINT fk_departmentid FOREIGN KEY (departmentid) REFERENCES department(departmentid),
    CONSTRAINT fk_reportsto FOREIGN KEY (reportsto) REFERENCES employee(employeeid)
);

-- Reimbursement Create Table
CREATE TABLE reimbursement (
    reimbursementid INTEGER PRIMARY KEY,
    employeeid INTEGER NOT NULL,
    supervisorid INTEGER,
    departmentheadid INTEGER,
    bencoid INTEGER,
    creationdate DATE NOT NULL,
    creationtime TIMESTAMP WITH LOCAL TIME ZONE NOT NULL,
    reimbursementlocationid INTEGER NOT NULL,
    description VARCHAR2(500) NOT NULL,
    cost NUMERIC(9,2) NOT NULL,
    gradeformatid INTEGER NOT NULL,
    eventtypeid INTEGER NOT NULL,
    workjustification VARCHAR2(500) NOT NULL,
    attachment BLOB,
    approvaldocument BLOB,
    approvalid INTEGER NOT NULL,
    timemissed INTEGER,
    CONSTRAINT fk_employeeid FOREIGN KEY (employeeid) REFERENCES employee(employeeid),
    CONSTRAINT fk_supervisorid FOREIGN KEY (supervisorid) REFERENCES employee(employeeid),
    CONSTRAINT fk_rdepartmentheadid FOREIGN KEY (departmentheadid) REFERENCES employee(employeeid),
    CONSTRAINT fk_bencoid FOREIGN KEY (bencoid) REFERENCES employee(employeeid),
    CONSTRAINT fk_reimbursementlocationid FOREIGN KEY (reimbursementlocationid) REFERENCES reimbursementlocation(reimbursementlocationid),
    CONSTRAINT fk_gradeformatid FOREIGN KEY (gradeformatid) REFERENCES gradeformat(gradeformatid),
    CONSTRAINT fk_eventtypeid FOREIGN KEY (eventtypeid) REFERENCES eventtype(eventtypeid),
    CONSTRAINT fk_approvalid FOREIGN KEY (approvalid) REFERENCES approval(approvalid)
);
ALTER TABLE department ADD CONSTRAINT fk_departmentheadid FOREIGN KEY (departmentheadid) REFERENCES employee(employeeid);