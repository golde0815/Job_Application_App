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
VALUES ('tombrown@gmail.com', 5, 5);

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
VALUES ('janedoe@gmail.com', 1, 'Accountant', '2010-08-03', '2012-01-01');
INSERT INTO WORKED_AT (EMAIL, COMPANY_ID, POSITION, START_DATE, END_DATE)
VALUES ('janedoe@gmail.com', 2, 'Accountant', '2012-02-15', '2018-06-10');
INSERT INTO WORKED_AT (EMAIL, COMPANY_ID, POSITION, START_DATE, END_DATE)
VALUES ('bobjohnson@gmail.com', 4, 'Software Engineer', '2013-10-23', '2017-03-07');
INSERT INTO WORKED_AT (EMAIL, COMPANY_ID, POSITION, START_DATE, END_DATE)
VALUES ('tombrown@gmail.com', 4, 'Janitor', '2012-05-25', '2016-09-01');
INSERT INTO WORKED_AT (EMAIL, COMPANY_ID, POSITION, START_DATE, END_DATE)
VALUES ('tombrown@gmail.com', 5, 'Janitor', '2017-01-02', '2019-08-08');

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
VALUES (1, 2, '2019-01-01', 'Accountant', 'Vancouver',
        'We are seeking a highly motivated and experienced accountant to join our finance team.', 700000,
        'jasonmiller@gmail.com');
INSERT INTO POSTED_JOB (JOB_ID, COMPANY_ID, POSTED_DATE, POSITION, LOCATION, DESCRIPTION, SALARY, RECRUITER_EMAIL)
VALUES (2, 3, '2019-01-02', 'Investment Banker', 'Burnaby',
        'The investment banker will be responsible for providing strategic financial advice to clients.', 200000,
        'lisawilson@gmail.com');
INSERT INTO POSTED_JOB (JOB_ID, COMPANY_ID, POSTED_DATE, POSITION, LOCATION, DESCRIPTION, SALARY, RECRUITER_EMAIL)
VALUES (3, 3, '2019-01-03', 'Software Engineer', 'Burnaby', 'The ideal candidate will have strong programming skills.',
        93000, 'lisawilson@gmail.com');
INSERT INTO POSTED_JOB (JOB_ID, COMPANY_ID, POSTED_DATE, POSITION, LOCATION, DESCRIPTION, SALARY, RECRUITER_EMAIL)
VALUES (4, 4, '2019-01-04', 'Software Engineer', 'Richmond',
        'The software engineer will be responsible for designing and developing software.', 65000,
        'mikedavis@gmail.com');
INSERT INTO POSTED_JOB (JOB_ID, COMPANY_ID, POSTED_DATE, POSITION, LOCATION, DESCRIPTION, SALARY, RECRUITER_EMAIL)
VALUES (5, 5, '2019-01-05', 'Software Engineer', 'Vancouver',
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
VALUES ('janedoe@gmail.com', 1, '2019-01-03');
INSERT INTO USER_APPLIES_TO (EMAIL, JOB_ID, APPLIED_DATE)
VALUES ('tombrown@gmail.com', 2, '2019-01-03');
INSERT INTO USER_APPLIES_TO (EMAIL, JOB_ID, APPLIED_DATE)
VALUES ('bobjohnson@gmail.com', 3, '2019-01-07');
INSERT INTO USER_APPLIES_TO (EMAIL, JOB_ID, APPLIED_DATE)
VALUES ('bobjohnson@gmail.com', 4, '2019-01-07');
INSERT INTO USER_APPLIES_TO (EMAIL, JOB_ID, APPLIED_DATE)
VALUES ('bobjohnson@gmail.com', 5, '2019-01-07');

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
