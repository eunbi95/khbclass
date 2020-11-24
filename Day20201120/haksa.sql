
select * from manager

CREATE TABLE manager
(
	no                    NUMBER(3)  NOT NULL ,
	irum                  VARCHAR2(100)  NULL ,
	age                   NUMBER(3)  NULL ,
	part                  VARCHAR2(100)  NULL 
);

CREATE SEQUENCE manager_no

CREATE UNIQUE INDEX XPK������ ON manager
(no  ASC);



ALTER TABLE manager
	ADD CONSTRAINT  XPK������ PRIMARY KEY (no);


select * from professor

CREATE TABLE professor
(
	no                    NUMBER(3)  NOT NULL ,
	subject               VARCHAR2(100)  NULL ,
	age                   NUMBER(3)  NULL ,
	irum                   VARCHAR2(100)  NULL 
);

CREATE SEQUENCE professor_no

CREATE UNIQUE INDEX XPK���� ON professor
(no  ASC);



ALTER TABLE professor
	ADD CONSTRAINT  XPK���� PRIMARY KEY (no);


select * from student
CREATE TABLE student
(
	no                    NUMBER(3)  NOT NULL ,
	age                   NUMBER(3)  NULL ,
	irum                   VARCHAR2(3)  NULL ,
	hakbun                NUMBER(5)  NULL 
);

DROP TABLE student

CREATE SEQUENCE student_no

CREATE UNIQUE INDEX XPK�л� ON student
(no  ASC);



ALTER TABLE student
	ADD CONSTRAINT  XPK�л� PRIMARY KEY (no);

