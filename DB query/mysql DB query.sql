create table patients(
Pno int not null AUTo_INCREMENT PRIMARY KEY,
patientID varchar(20),
firstname varchar(50),
lastname varchar(70),
gender varchar(10),
phone varchar(12),
address varchar(200),
age varchar(10),
bloodGroup varchar(10),
nextOfKin varchar(50)
);

create table hospital (
hosID int auto_increment,
hosRegno varchar(10) not null,
hosName varchar(10) not null,
hostype varchar(30),
hosCharge decimal(5,2),
Address varchar(50),
city varchar(50),
Email varchar(50),

CONSTRAINT pk_appointment1
		PRIMARY KEY (hosID),
CONSTRAINT ck_hostype
		CHECK(hostype ="Acute care" OR 
hostype = "Psychiatric" OR hostype ="Surgical" OR hostype ="Dental"),
CONSTRAINT ck_hosCharge
		CHECK(hosCharge >0.00)
);


CREATE TABLE doctor (
	docId VARCHAR (12),
	docFname VARCHAR (15) NOT NULL,
	docLname VARCHAR (15) NOT NULL,
	specialization VARCHAR (30) NOT NULL,
	contact INT NOT NULL,
	email VARCHAR (30),
	address VARCHAR (30),
	gender CHAR (1),
	docCharge FLOAT NOT NULL,
	hospital INT,
	password VARCHAR (8),
	
	CONSTRAINT pk_doctor2
		PRIMARY KEY (docId)
);


CREATE TABLE schedule (
	docId VARCHAR (12),
	workingDay VARCHAR (10),
	max_num_of_patients INT,
	

	CONSTRAINT pk_schedule3
		PRIMARY KEY (docId, workingDay),

	CONSTRAINT fk_docId4
		FOREIGN KEY (docId)
		REFERENCES Doctor (docId)
);


CREATE TABLE appointment(
	appointmentID INT NOT NULL AUTO_INCREMENT,
	pADate varchar(10) NOT NULL,
	aDate varchar(10) NULL,
	aCause VARCHAR (100),
	aPatient INT NOT NULL,
	aDoctor VARCHAR(12) NOT NULL,
	aDay VARCHAR (10),
	
CONSTRAINT pk_appointment5
		PRIMARY KEY (appointmentID),

CONSTRAINT fk_appointment6
		FOREIGN KEY (aDoctor, aDay)
		REFERENCES schedule (docId,workingDay),
        
CONSTRAINT fk_appointment7
		FOREIGN KEY (aPatient)
		REFERENCES patients (Pno)
);

create table payments(
p_id INT NOT NULL PRIMARY KEY,
NameOnCard VARCHAR(15),
cardType VARCHAR(10),
bank VARCHAR(10),
totAmount decimal(10,2)
);

create table user(
first_name VARCHAR(25),
last_name VARCHAR(45),
e_mail VARCHAR(45),
mob_number VARCHAR(45),
address VARCHAR(45),
NIC_no VARCHAR(45) NOT NULL PRIMARY KEY,
password VARCHAR(45),
cn_password VARCHAR(45)

);