DROP TABLE transcript_contains;
DROP TABLE grade;
DROP TABLE course;
DROP TABLE transcript;
DROP TABLE school;
DROP TABLE resume;
DROP TABLE user_applies_to;
DROP TABLE job_requires;
DROP TABLE skill;
DROP TABLE job_belongs_in;
DROP TABLE category;
DROP TABLE posted_job;
DROP TABLE recruiter;
DROP TABLE worked_at;
DROP TABLE operates_in;
DROP TABLE industry;
DROP TABLE rates;
DROP TABLE score;
DROP TABLE company;
DROP TABLE user_account;

CREATE TABLE user_account
(
    email        VARCHAR(255),
    address      VARCHAR(255),
    phone_number VARCHAR(20),
    PRIMARY KEY (email),
    UNIQUE (phone_number)
);

CREATE TABLE company
(
    company_id INTEGER,
    name       VARCHAR(255),
    PRIMARY KEY (company_id)
);

CREATE TABLE score
(
    value      INTEGER,
    recommends INTEGER NOT NULL,
    PRIMARY KEY (value)
);

CREATE TABLE rates
(
    email      VARCHAR(255),
    company_id INTEGER,
    value      INTEGER NOT NULL,
    r_comment  VARCHAR(255),
    PRIMARY KEY (email, company_id),
    FOREIGN KEY (email) REFERENCES user_account (email)
        ON DELETE CASCADE,
    FOREIGN KEY (company_id) REFERENCES company (company_id)
        ON DELETE CASCADE,
    FOREIGN KEY (value) REFERENCES score (value)
        ON DELETE CASCADE
);

CREATE TABLE industry
(
    name VARCHAR(255) Primary key
);

CREATE TABLE operates_in
(
    industry_name VARCHAR(255),
    company_id    INTEGER,
    PRIMARY KEY (industry_name, company_id),
    FOREIGN KEY (industry_name) REFERENCES industry (name)
        ON DELETE CASCADE,
    FOREIGN KEY (company_id) REFERENCES company (company_id)
        ON DELETE CASCADE
);

CREATE TABLE worked_at
(
    email      VARCHAR(255),
    company_id INTEGER,
    position   VARCHAR(255),
    start_date  DATE NOT NULL,
    end_date    DATE NOT NULL,
    PRIMARY KEY (email, company_id),
    FOREIGN KEY (email) REFERENCES user_account (email)
        ON DELETE CASCADE,
    FOREIGN KEY (company_id) REFERENCES company (company_id)
        ON DELETE CASCADE
);

CREATE TABLE recruiter
(
    email VARCHAR(255) PRIMARY KEY,
    name  VARCHAR(255) NOT NULL
);

CREATE TABLE posted_job
(
    job_id          INTEGER,
    company_id      INTEGER       NOT NULL,
    posted_date     DATE          NOT NULL,
    position        VARCHAR(255)  NOT NULL,
    location        VARCHAR(255)  NOT NULL,
    description     VARCHAR(2000) NOT NULL,
    salary          INTEGER,
    recruiter_email VARCHAR(255),
    PRIMARY KEY (job_id),
    FOREIGN KEY (company_id) REFERENCES company (company_id)
        ON DELETE CASCADE,
    FOREIGN KEY (recruiter_email) REFERENCES recruiter (email)
        ON DELETE SET NULL
);

CREATE TABLE category
(
    name VARCHAR(255) PRIMARY KEY
);

CREATE TABLE job_belongs_in
(
    job_id        INTEGER,
    category_name VARCHAR(255),
    PRIMARY KEY (job_id, category_name),
    FOREIGN KEY (job_id) REFERENCES posted_job (job_id)
        ON DELETE CASCADE,
    FOREIGN KEY (category_name) REFERENCES category (name)
        ON DELETE CASCADE
);

CREATE TABLE skill
(
    name VARCHAR(255) PRIMARY KEY
);

CREATE TABLE job_requires
(
    job_id      INTEGER,
    skill_name  VARCHAR(255),
    proficiency INTEGER,
    PRIMARY KEY (job_id, skill_name),
    FOREIGN KEY (job_id) REFERENCES posted_job (job_id)
        ON DELETE CASCADE,
    FOREIGN KEY (skill_name) REFERENCES skill (name)
        ON DELETE CASCADE
);

CREATE TABLE user_applies_to
(
    email       VARCHAR(255),
    job_id      INTEGER,
    applied_date DATE,
    PRIMARY KEY (email, job_id),
    FOREIGN KEY (email) REFERENCES user_account (email)
        ON DELETE CASCADE,
    FOREIGN KEY (job_id) REFERENCES posted_job (job_id)
        ON DELETE CASCADE
);

CREATE TABLE resume
(
    email       VARCHAR(255),
    document_id INTEGER,
    url         VARCHAR(255),
    PRIMARY KEY (email, document_id),
    UNIQUE (url),
    FOREIGN KEY (email) REFERENCES user_account (email)
        ON DELETE CASCADE
);

CREATE TABLE school
(
    name    VARCHAR(255),
    address VARCHAR(255),
    PRIMARY KEY (name)
);

CREATE TABLE transcript
(
    email       VARCHAR(255),
    document_id INTEGER,
    school_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (email, document_id),
    FOREIGN KEY (email) REFERENCES user_account (email)
        ON DELETE CASCADE,
    FOREIGN KEY (school_name) REFERENCES school (name)
        ON DELETE CASCADE
);

CREATE TABLE course
(
    course_id   INTEGER,
    course_name VARCHAR(255),
    department  VARCHAR(255),
    PRIMARY KEY (course_id),
    UNIQUE (course_name)
);

CREATE TABLE grade
(
    percentage   INTEGER,
    letter_grade VARCHAR(2) NOT NULL,
    PRIMARY KEY (percentage)
);

CREATE TABLE transcript_contains
(
    email        VARCHAR(255),
    document_id  INTEGER,
    course_id    INTEGER,
    percentage   INTEGER NOT NULL,
    year_session VARCHAR(255),
    PRIMARY KEY (email, document_id, course_id),
    FOREIGN KEY (email, document_id) REFERENCES transcript (email, document_id)
        ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES course (course_id)
        ON DELETE CASCADE,
    FOREIGN KEY (percentage) REFERENCES grade (percentage)
        ON DELETE CASCADE
);

INSERT INTO USER_ACCOUNT (EMAIL, ADDRESS, PHONE_NUMBER)
VALUES ('janedoe@gmail.com', 'address1', '1234560001');
INSERT INTO USER_ACCOUNT (EMAIL, ADDRESS, PHONE_NUMBER)
VALUES ('johnsmith@gmail.com', 'address2', '1234560002');
INSERT INTO USER_ACCOUNT (EMAIL, ADDRESS, PHONE_NUMBER)
VALUES ('bobjohnson@gmail.com', 'address3', '1234560003');
INSERT INTO USER_ACCOUNT (EMAIL, ADDRESS, PHONE_NUMBER)
VALUES ('tombrown@gmail.com', 'address4', '1234560004');
INSERT INTO USER_ACCOUNT (EMAIL, ADDRESS, PHONE_NUMBER)
VALUES ('alexlee@gmail.com', 'address5', '1234560005');

INSERT INTO COMPANY (COMPANY_ID, NAME)
VALUES (1, 'Acme Corporation');
INSERT INTO COMPANY (COMPANY_ID, NAME)
VALUES (2, 'Widget Industries');
INSERT INTO COMPANY (COMPANY_ID, NAME)
VALUES (3, 'Global Enterprises');
INSERT INTO COMPANY (COMPANY_ID, NAME)
VALUES (4, 'Premier Partners');
INSERT INTO COMPANY (COMPANY_ID, NAME)
VALUES (5, 'Future Technologies');

INSERT INTO SCORE (VALUE, RECOMMENDS)
VALUES (1, 0);
INSERT INTO SCORE (VALUE, RECOMMENDS)
VALUES (2, 0);
INSERT INTO SCORE (VALUE, RECOMMENDS)
VALUES (3, 0);
INSERT INTO SCORE (VALUE, RECOMMENDS)
VALUES (4, 0);
INSERT INTO SCORE (VALUE, RECOMMENDS)
VALUES (5, 0);
INSERT INTO SCORE (VALUE, RECOMMENDS)
VALUES (6, 1);
INSERT INTO SCORE (VALUE, RECOMMENDS)
VALUES (7, 1);
INSERT INTO SCORE (VALUE, RECOMMENDS)
VALUES (8, 1);
INSERT INTO SCORE (VALUE, RECOMMENDS)
VALUES (9, 1);
INSERT INTO SCORE (VALUE, RECOMMENDS)
VALUES (10, 1);

INSERT INTO RATES (EMAIL, COMPANY_ID, VALUE, R_COMMENT)
VALUES ('janedoe@gmail.com', 1, 1, 'This company is terrible');
INSERT INTO RATES (EMAIL, COMPANY_ID, VALUE, R_COMMENT)
VALUES ('janedoe@gmail.com', 2, 10, 'Fantastic company with great culture!');
INSERT INTO RATES (EMAIL, COMPANY_ID, VALUE, R_COMMENT)
VALUES ('bobjohnson@gmail.com', 4, 7, 'I enjoyed working here');
INSERT INTO RATES (EMAIL, COMPANY_ID, VALUE, R_COMMENT)
VALUES ('tombrown@gmail.com', 4, 8, 'Good company');
INSERT INTO RATES (EMAIL, COMPANY_ID, VALUE)
VALUES ('tombrown@gmail.com', 5, 7);

INSERT INTO INDUSTRY (NAME)
VALUES ('Technology');
INSERT INTO INDUSTRY (NAME)
VALUES ('Manufacturing');
INSERT INTO INDUSTRY (NAME)
VALUES ('Construction');
INSERT INTO INDUSTRY (NAME)
VALUES ('Finance');
INSERT INTO INDUSTRY (NAME)
VALUES ('Food Service');

INSERT INTO OPERATES_IN (INDUSTRY_NAME, COMPANY_ID)
VALUES ('Manufacturing', 2);
INSERT INTO OPERATES_IN (INDUSTRY_NAME, COMPANY_ID)
VALUES ('Finance', 4);
INSERT INTO OPERATES_IN (INDUSTRY_NAME, COMPANY_ID)
VALUES ('Finance', 3);
INSERT INTO OPERATES_IN (INDUSTRY_NAME, COMPANY_ID)
VALUES ('Technology', 3);
INSERT INTO OPERATES_IN (INDUSTRY_NAME, COMPANY_ID)
VALUES ('Technology', 5);

INSERT INTO WORKED_AT (EMAIL, COMPANY_ID, POSITION, START_DATE, END_DATE)
VALUES ('janedoe@gmail.com', 1, 'Accountant', TO_DATE('2010-08-03', 'YYYY-MM-DD'), TO_DATE('2012-01-01', 'YYYY-MM-DD'));
INSERT INTO WORKED_AT (EMAIL, COMPANY_ID, POSITION, START_DATE, END_DATE)
VALUES ('janedoe@gmail.com', 2, 'Accountant', TO_DATE('2012-02-15', 'YYYY-MM-DD'), TO_DATE('2018-06-10', 'YYYY-MM-DD'));
INSERT INTO WORKED_AT (EMAIL, COMPANY_ID, POSITION, START_DATE, END_DATE)
VALUES ('bobjohnson@gmail.com', 4, 'Software Engineer', TO_DATE('2013-10-23', 'YYYY-MM-DD'), TO_DATE('2017-03-07', 'YYYY-MM-DD'));
INSERT INTO WORKED_AT (EMAIL, COMPANY_ID, POSITION, START_DATE, END_DATE)
VALUES ('tombrown@gmail.com', 4, 'Janitor', TO_DATE('2012-05-25', 'YYYY-MM-DD'), TO_DATE('2016-09-01', 'YYYY-MM-DD'));
INSERT INTO WORKED_AT (EMAIL, COMPANY_ID, POSITION, START_DATE, END_DATE)
VALUES ('tombrown@gmail.com', 5, 'Janitor', TO_DATE('2017-01-02', 'YYYY-MM-DD'), TO_DATE('2019-08-08', 'YYYY-MM-DD'));

INSERT INTO RECRUITER (EMAIL, NAME)
VALUES ('jasonmiller@gmail.com', 'Jason Miller');
INSERT INTO RECRUITER (EMAIL, NAME)
VALUES ('lisawilson@gmail.com', 'Lisa Wilson');
INSERT INTO RECRUITER (EMAIL, NAME)
VALUES ('kevinjones@gmail.com', 'Kevin Jones');
INSERT INTO RECRUITER (EMAIL, NAME)
VALUES ('mikedavis@gmail.com', 'Mike Davis');
INSERT INTO RECRUITER (EMAIL, NAME)
VALUES ('andrewwilliams@gmail.com', 'Andrew Williams');

INSERT INTO POSTED_JOB (JOB_ID, COMPANY_ID, POSTED_DATE, POSITION, LOCATION, DESCRIPTION, SALARY, RECRUITER_EMAIL)
VALUES (1, 2, TO_DATE('2019-01-01', 'YYYY-MM-DD'), 'Accountant', 'Vancouver',
        'We are seeking a highly motivated and experienced accountant to join our finance team.', 130000,
        'jasonmiller@gmail.com');
INSERT INTO POSTED_JOB (JOB_ID, COMPANY_ID, POSTED_DATE, POSITION, LOCATION, DESCRIPTION, SALARY, RECRUITER_EMAIL)
VALUES (2, 3, TO_DATE('2019-01-02', 'YYYY-MM-DD'), 'Investment Banker', 'Burnaby',
        'The investment banker will be responsible for providing strategic financial advice to clients.', 200000,
        'lisawilson@gmail.com');
INSERT INTO POSTED_JOB (JOB_ID, COMPANY_ID, POSTED_DATE, POSITION, LOCATION, DESCRIPTION, SALARY, RECRUITER_EMAIL)
VALUES (3, 3, TO_DATE('2019-01-03', 'YYYY-MM-DD'), 'Software Engineer', 'Burnaby', 'The ideal candidate will have strong programming skills.',
        93000, 'lisawilson@gmail.com');
INSERT INTO POSTED_JOB (JOB_ID, COMPANY_ID, POSTED_DATE, POSITION, LOCATION, DESCRIPTION, SALARY, RECRUITER_EMAIL)
VALUES (4, 4, TO_DATE('2019-01-04', 'YYYY-MM-DD'), 'Software Engineer', 'Richmond',
        'The software engineer will be responsible for designing and developing software.', 65000,
        'mikedavis@gmail.com');
INSERT INTO POSTED_JOB (JOB_ID, COMPANY_ID, POSTED_DATE, POSITION, LOCATION, DESCRIPTION, SALARY, RECRUITER_EMAIL)
VALUES (5, 5, TO_DATE('2019-01-05', 'YYYY-MM-DD'), 'Software Engineer', 'Vancouver',
        'We are looking for a talented and passionate software engineer to join our development team.', 150000,
        'andrewwilliams@gmail.com');

INSERT INTO CATEGORY (NAME)
VALUES ('Full time');
INSERT INTO CATEGORY (NAME)
VALUES ('Part time');
INSERT INTO CATEGORY (NAME)
VALUES ('Contract');
INSERT INTO CATEGORY (NAME)
VALUES ('Internship');
INSERT INTO CATEGORY (NAME)
VALUES ('Freelance');

INSERT INTO JOB_BELONGS_IN (JOB_ID, CATEGORY_NAME)
VALUES (1, 'Full time');
INSERT INTO JOB_BELONGS_IN (JOB_ID, CATEGORY_NAME)
VALUES (2, 'Full time');
INSERT INTO JOB_BELONGS_IN (JOB_ID, CATEGORY_NAME)
VALUES (3, 'Full time');
INSERT INTO JOB_BELONGS_IN (JOB_ID, CATEGORY_NAME)
VALUES (4, 'Full time');
INSERT INTO JOB_BELONGS_IN (JOB_ID, CATEGORY_NAME)
VALUES (5, 'Full time');

INSERT INTO SKILL (NAME)
VALUES ('Microsoft Office');
INSERT INTO SKILL (NAME)
VALUES ('Java');
INSERT INTO SKILL (NAME)
VALUES ('JavaScript');
INSERT INTO SKILL (NAME)
VALUES ('SQL');
INSERT INTO SKILL (NAME)
VALUES ('Python');

INSERT INTO JOB_REQUIRES (JOB_ID, SKILL_NAME, PROFICIENCY)
VALUES (1, 'Microsoft Office', 8);
INSERT INTO JOB_REQUIRES (JOB_ID, SKILL_NAME, PROFICIENCY)
VALUES (3, 'Java', 9);
INSERT INTO JOB_REQUIRES (JOB_ID, SKILL_NAME, PROFICIENCY)
VALUES (3, 'SQL', 5);
INSERT INTO JOB_REQUIRES (JOB_ID, SKILL_NAME, PROFICIENCY)
VALUES (4, 'JavaScript', 10);
INSERT INTO JOB_REQUIRES (JOB_ID, SKILL_NAME, PROFICIENCY)
VALUES (5, 'Python', 8);

INSERT INTO USER_APPLIES_TO (EMAIL, JOB_ID, APPLIED_DATE)
VALUES ('janedoe@gmail.com', 1, TO_DATE('2019-01-03', 'YYYY-MM-DD'));
INSERT INTO USER_APPLIES_TO (EMAIL, JOB_ID, APPLIED_DATE)
VALUES ('tombrown@gmail.com', 2, TO_DATE('2019-01-03', 'YYYY-MM-DD'));
INSERT INTO USER_APPLIES_TO (EMAIL, JOB_ID, APPLIED_DATE)
VALUES ('bobjohnson@gmail.com', 3, TO_DATE('2019-01-07', 'YYYY-MM-DD'));
INSERT INTO USER_APPLIES_TO (EMAIL, JOB_ID, APPLIED_DATE)
VALUES ('bobjohnson@gmail.com', 4, TO_DATE('2019-01-07', 'YYYY-MM-DD'));
INSERT INTO USER_APPLIES_TO (EMAIL, JOB_ID, APPLIED_DATE)
VALUES ('bobjohnson@gmail.com', 5, TO_DATE('2019-01-07', 'YYYY-MM-DD'));
INSERT INTO USER_APPLIES_TO (EMAIL, JOB_ID, APPLIED_DATE)
VALUES ('janedoe@gmail.com', 5, TO_DATE('2019-01-08', 'YYYY-MM-DD'));

INSERT INTO RESUME (EMAIL, DOCUMENT_ID, URL)
VALUES ('janedoe@gmail.com', 1, 'https://www.example.com/1');
INSERT INTO RESUME (EMAIL, DOCUMENT_ID, URL)
VALUES ('johnsmith@gmail.com', 2, 'https://www.example.com/2');
INSERT INTO RESUME (EMAIL, DOCUMENT_ID, URL)
VALUES ('bobjohnson@gmail.com', 3, 'https://www.example.com/3');
INSERT INTO RESUME (EMAIL, DOCUMENT_ID, URL)
VALUES ('tombrown@gmail.com', 4, 'https://www.example.com/4');
INSERT INTO RESUME (EMAIL, DOCUMENT_ID, URL)
VALUES ('alexlee@gmail.com', 5, 'https://www.example.com/5');

INSERT INTO SCHOOL (NAME, ADDRESS)
VALUES ('UBC', 'Vancouver');
INSERT INTO SCHOOL (NAME, ADDRESS)
VALUES ('SFU', 'Burnaby');
INSERT INTO SCHOOL (NAME, ADDRESS)
VALUES ('UofT', 'Toronto');
INSERT INTO SCHOOL (NAME, ADDRESS)
VALUES ('UCalgary', 'Calgary');
INSERT INTO SCHOOL (NAME, ADDRESS)
VALUES ('UWaterloo', 'Waterloo');

INSERT INTO TRANSCRIPT (EMAIL, DOCUMENT_ID, SCHOOL_NAME)
VALUES ('janedoe@gmail.com', 6, 'UWaterloo');
INSERT INTO TRANSCRIPT (EMAIL, DOCUMENT_ID, SCHOOL_NAME)
VALUES ('johnsmith@gmail.com', 7, 'UBC');
INSERT INTO TRANSCRIPT (EMAIL, DOCUMENT_ID, SCHOOL_NAME)
VALUES ('bobjohnson@gmail.com', 8, 'UBC');
INSERT INTO TRANSCRIPT (EMAIL, DOCUMENT_ID, SCHOOL_NAME)
VALUES ('tombrown@gmail.com', 9, 'UofT');
INSERT INTO TRANSCRIPT (EMAIL, DOCUMENT_ID, SCHOOL_NAME)
VALUES ('alexlee@gmail.com', 10, 'SFU');

INSERT INTO COURSE (COURSE_ID, COURSE_NAME, DEPARTMENT)
VALUES (1, 'CPSC304', 'Computer Science');
INSERT INTO COURSE (COURSE_ID, COURSE_NAME, DEPARTMENT)
VALUES (2, 'COMM101', 'Commerce');
INSERT INTO COURSE (COURSE_ID, COURSE_NAME, DEPARTMENT)
VALUES (3, 'CPSC310', 'Computer Science');
INSERT INTO COURSE (COURSE_ID, COURSE_NAME, DEPARTMENT)
VALUES (4, 'ECON101', 'Economics');
INSERT INTO COURSE (COURSE_ID, COURSE_NAME, DEPARTMENT)
VALUES (5, 'ENGL112', 'English');

INSERT INTO GRADE (PERCENTAGE, LETTER_GRADE)
VALUES (95, 'A+');
INSERT INTO GRADE (PERCENTAGE, LETTER_GRADE)
VALUES (80, 'A-');
INSERT INTO GRADE (PERCENTAGE, LETTER_GRADE)
VALUES (73, 'B');
INSERT INTO GRADE (PERCENTAGE, LETTER_GRADE)
VALUES (65, 'C+');
INSERT INTO GRADE (PERCENTAGE, LETTER_GRADE)
VALUES (52, 'D');

INSERT INTO TRANSCRIPT_CONTAINS (EMAIL, DOCUMENT_ID, COURSE_ID, PERCENTAGE, YEAR_SESSION)
VALUES ('bobjohnson@gmail.com', 8, 1, 95, '2015W');
INSERT INTO TRANSCRIPT_CONTAINS (EMAIL, DOCUMENT_ID, COURSE_ID, PERCENTAGE, YEAR_SESSION)
VALUES ('bobjohnson@gmail.com', 8, 2, 80, '2014W');
INSERT INTO TRANSCRIPT_CONTAINS (EMAIL, DOCUMENT_ID, COURSE_ID, PERCENTAGE, YEAR_SESSION)
VALUES ('bobjohnson@gmail.com', 8, 3, 95, '2015W');
INSERT INTO TRANSCRIPT_CONTAINS (EMAIL, DOCUMENT_ID, COURSE_ID, PERCENTAGE, YEAR_SESSION)
VALUES ('bobjohnson@gmail.com', 8, 4, 73, '2013W');
INSERT INTO TRANSCRIPT_CONTAINS (EMAIL, DOCUMENT_ID, COURSE_ID, PERCENTAGE, YEAR_SESSION)
VALUES ('bobjohnson@gmail.com', 8, 5, 80, '2013W');
