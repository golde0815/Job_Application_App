CREATE TABLE user_accounts
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
    FOREIGN KEY (email) REFERENCES user_accounts (email)
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
    startDATE  DATE NOT NULL,
    endDate    DATE NOT NULL,
    PRIMARY KEY (email, company_id),
    FOREIGN KEY (email) REFERENCES user_accounts (email)
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
    posted_date     DATE,
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
    appliedDate DATE,
    PRIMARY KEY (email, job_id),
    FOREIGN KEY (email) REFERENCES user_accounts (email)
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
    FOREIGN KEY (email) REFERENCES user_accounts (email)
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
    FOREIGN KEY (email) REFERENCES user_accounts (email)
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
    letter_grade CHAR(1) NOT NULL,
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
