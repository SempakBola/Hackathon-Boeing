PRAGMA foreign_keys = OFF;
drop table if exists LGA;
drop table if exists HomlessGroup;
drop table if exists Population;
drop table if exists MedianIncome;
drop table if exists Homeless;
drop table if exists Homeless16;
drop table if exists Homeless18;
drop table if exists AtRisk16;
drop table if exists AtRisk18;
drop table if exists Group16;
drop table if exists Group18;
drop table if exists Student;
DROP TABLE IF EXISTS Persona;
DROP TABLE IF EXISTS PersonaAttribute;
PRAGMA foreign_keys = ON;

CREATE TABLE LGA (
    lga_code          INTEGER NOT NULL,
    lga_name          TEXT NOT NULL,
    lga_type          CHAR (2),
    area_sqkm         DOUBLE,
    latitude          DOUBLE,
    longitude         DOUBLE,
    PRIMARY KEY (lga_code)
);

CREATE TABLE HomlessGroup (
    lga_code          INTEGER NOT NULL,
    year              INTEGER NOT NULL,
    status            CHAR (10) NOT NULL,
    sex               CHAR (2),
    age_group         CHAR (10),
    count             INTEGER,
    PRIMARY KEY (lga_code, status, year, sex, age_group)
    FOREIGN KEY (lga_code) REFERENCES LGA(lga_code)
);

CREATE TABLE Population (
    lga_code          INTEGER NOT NULL,
    pop2016           INTEGER,
    pop2018           INTEGER,
    PRIMARY KEY (lga_code)
    FOREIGN KEY (lga_code) REFERENCES LGA(lga_code)
);

CREATE TABLE MedianIncome (
    lga_code                    INTEGER NOT NULL,
    household_weekly_income     INTEGER,
    age                         INTEGER,
    mortgage_repay_monthly      INTEGER,
    rent_weekly                 INTEGER,
    PRIMARY KEY (lga_code)
    FOREIGN KEY (lga_code) REFERENCES LGA(lga_code)
);

CREATE TABLE Homeless (
    lga_code                INTEGER   NOT NULL,
    year              INTEGER NOT NULL,
    f_0_9                   INTEGER,
    m_0_9                   INTEGER,
    f_10_19                   INTEGER,
    m_10_19                   INTEGER,
    f_20_29                   INTEGER,
    m_20_29                   INTEGER,
    f_30_39                   INTEGER,
    m_30_39                   INTEGER,
    f_40_49                   INTEGER,
    m_40_49                   INTEGER,
    f_50_59                   INTEGER,
    m_50_59                   INTEGER,
    f_60_plus                   INTEGER,
    m_60_plus                   INTEGER,
    PRIMARY KEY (lga_code, year)
    FOREIGN KEY (lga_code) REFERENCES LGA (lga_code) 
    FOREIGN KEY (year) REFERENCES HomlessGroup (year) 
);



/*test for change over time */

CREATE TABLE Homeless16 (
    lga_code                INTEGER   NOT NULL,
    f_0_9                   INTEGER,
    m_0_9                   INTEGER,
    f_10_19                   INTEGER,
    m_10_19                   INTEGER,
    f_20_29                   INTEGER,
    m_20_29                   INTEGER,
    f_30_39                   INTEGER,
    m_30_39                   INTEGER,
    f_40_49                   INTEGER,
    m_40_49                   INTEGER,
    f_50_59                   INTEGER,
    m_50_59                   INTEGER,
    f_60_plus                   INTEGER,
    m_60_plus                   INTEGER,
    PRIMARY KEY (lga_code)
    FOREIGN KEY (lga_code) REFERENCES LGA (lga_code) 
);

CREATE TABLE Homeless18 (
    lga_code                INTEGER   NOT NULL,
    f_0_9                   INTEGER,
    m_0_9                   INTEGER,
    f_10_19                   INTEGER,
    m_10_19                   INTEGER,
    f_20_29                   INTEGER,
    m_20_29                   INTEGER,
    f_30_39                   INTEGER,
    m_30_39                   INTEGER,
    f_40_49                   INTEGER,
    m_40_49                   INTEGER,
    f_50_59                   INTEGER,
    m_50_59                   INTEGER,
    f_60_plus                   INTEGER,
    m_60_plus                   INTEGER,
    PRIMARY KEY (lga_code)
    FOREIGN KEY (lga_code) REFERENCES LGA (lga_code) 
);

CREATE TABLE AtRisk16 (
    lga_code                INTEGER   NOT NULL,
    f_0_9                   INTEGER,
    m_0_9                   INTEGER,
    f_10_19                   INTEGER,
    m_10_19                   INTEGER,
    f_20_29                   INTEGER,
    m_20_29                   INTEGER,
    f_30_39                   INTEGER,
    m_30_39                   INTEGER,
    f_40_49                   INTEGER,
    m_40_49                   INTEGER,
    f_50_59                   INTEGER,
    m_50_59                   INTEGER,
    f_60_plus                   INTEGER,
    m_60_plus                   INTEGER,
    PRIMARY KEY (lga_code)
    FOREIGN KEY (lga_code) REFERENCES LGA (lga_code) 
);

CREATE TABLE AtRisk18 (
    lga_code                INTEGER   NOT NULL,
    f_0_9                   INTEGER,
    m_0_9                   INTEGER,
    f_10_19                   INTEGER,
    m_10_19                   INTEGER,
    f_20_29                   INTEGER,
    m_20_29                   INTEGER,
    f_30_39                   INTEGER,
    m_30_39                   INTEGER,
    f_40_49                   INTEGER,
    m_40_49                   INTEGER,
    f_50_59                   INTEGER,
    m_50_59                   INTEGER,
    f_60_plus                   INTEGER,
    m_60_plus                   INTEGER,
    PRIMARY KEY (lga_code)
    FOREIGN KEY (lga_code) REFERENCES LGA (lga_code) 
);



CREATE TABLE Group16 (
    lga_code          INTEGER NOT NULL,
    year              INTEGER NOT NULL,
    status            CHAR (10) NOT NULL,
    sex               CHAR (2),
    age_group         CHAR (10),
    count             INTEGER,
    PRIMARY KEY (lga_code, status, year, sex, age_group)
    FOREIGN KEY (lga_code) REFERENCES LGA(lga_code)
    FOREIGN KEY (status) REFERENCES HomlessGroup(status)
    FOREIGN KEY (year) REFERENCES HomlessGroup(year)
    FOREIGN KEY (sex) REFERENCES HomlessGroup(sex)
    FOREIGN KEY (age_group) REFERENCES HomlessGroup(age_group)
);
CREATE TABLE Group18 (
    lga_code          INTEGER NOT NULL,
    year              INTEGER NOT NULL,
    status            CHAR (10) NOT NULL,
    sex               CHAR (2),
    age_group         CHAR (10),
    count             INTEGER,
    PRIMARY KEY (lga_code, status, year, sex, age_group)
    FOREIGN KEY (lga_code) REFERENCES LGA(lga_code)
    FOREIGN KEY (status) REFERENCES HomlessGroup(status)
    FOREIGN KEY (year) REFERENCES HomlessGroup(year)
    FOREIGN KEY (sex) REFERENCES HomlessGroup(sex)
    FOREIGN KEY (age_group) REFERENCES HomlessGroup(age_group)
);






CREATE TABLE Student (
    SID     CHAR(20),
    FName   TEXT,
    LName   TEXT,
    Email   TEXT,
    PRIMARY KEY (SID)  
);
CREATE TABLE Persona (
    PID             INTEGER NOT NULL,
    FName           TEXT,
    LName           TEXT,
    Image_Filepath   TEXT,
    PRIMARY KEY (PID)  
);
CREATE TABLE PersonaAttribute (
    PID                 INTEGER NOT NULL,
    Age                 SMALLINT,
    Background          TEXT,
    Need                TEXT,
    Goal                TEXT,
    Skill               TEXT,
    Experience          TEXT,
    PRIMARY KEY (PID)  
    FOREIGN KEY (PID) REFERENCES Persona(PID)
);



/*
because it's hard to get the statuses separately imo so far
i thought about having two separate tables to separate the statuses

CREATE TABLE Homeless(
    lga_code          INTEGER NOT NULL,
    year              INTEGER NOT NULL,
    status            CHAR (10) NOT NULL,
    sex               CHAR (2),
    age_group         CHAR (10),
    count             INTEGER,
    PRIMARY KEY (lga_code, status, year, sex, age_group)
    FOREIGN KEY (lga_code) REFERENCES LGA(lga_code)
);

CREATE TABLE AtRisk(
    lga_code          INTEGER NOT NULL,
    year              INTEGER NOT NULL,
    status            CHAR (10) NOT NULL,
    sex               CHAR (2),
    age_group         CHAR (10),
    count             INTEGER,
    PRIMARY KEY (lga_code, status, year, sex, age_group)
    FOREIGN KEY (lga_code) REFERENCES LGA(lga_code)
);

*/

