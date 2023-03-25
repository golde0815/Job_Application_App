package ca.ubc.cs304.database;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MigrationHandler {
    private final Connection connection;

    public MigrationHandler(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    private void createUserTable() {
        //language=SQL
        String createUserTableSQL =
                "CREATE TABLE user_accounts (" +
                        "email VARCHAR(255), " +
                        "address VARCHAR(255), " +
                        "phone_number VARCHAR(20), " +
                        "PRIMARY KEY (email), " +
                        "UNIQUE(phone_number))";
        createTable(createUserTableSQL);
    }

    private void createCompanyTable() {
        //language=SQL
        String createCompanyTableSQL =
                "CREATE TABLE company (" +
                        "company_id INTEGER, " +
                        "name VARCHAR(255), " +
                        "PRIMARY KEY (company_id))";
        createTable(createCompanyTableSQL);
    }

    private void createScoreTable() {
        //language=SQL
        String createScoreTableSQL =
                "CREATE TABLE score (" +
                        "value INTEGER, " +
                        "recommends INTEGER NOT NULL, " +
                        "PRIMARY KEY (value))";
        createTable(createScoreTableSQL);
    }

    private void createRatesTable() {
        //language=SQL
        String createRatesTableSQL =
                "CREATE TABLE rates (" +
                        "email VARCHAR(255), " +
                        "company_id INTEGER, " +
                        "value INTEGER NOT NULL, " +
                        "r_comment VARCHAR(255), " +
                        "PRIMARY KEY (email, company_id), " +
                        "FOREIGN KEY (email) REFERENCES user_accounts(email) " +
                        "ON DELETE CASCADE, " +
                        "FOREIGN KEY (company_id) REFERENCES company(company_id) " +
                        "ON DELETE CASCADE, " +
                        "FOREIGN KEY (value) REFERENCES score(value) " +
                        "ON DELETE CASCADE)";
        createTable(createRatesTableSQL);
    }

    private void createIndustryTable() {
        //language=SQL
        String createIndustryTableSQL = "CREATE TABLE industry (name VARCHAR(255) Primary key)";
        createTable(createIndustryTableSQL);
    }

    private void createOperatesInTable() {
        //language=SQL
        String createOperatesInTableSQL =
                "CREATE TABLE operates_in (" +
                        "industry_name VARCHAR(255), " +
                        "company_id INTEGER, " +
                        "PRIMARY KEY (industry_name, company_id), " +
                        "FOREIGN KEY (industry_name) REFERENCES industry (name) " +
                        "ON DELETE CASCADE, " +
                        "FOREIGN KEY (company_id) REFERENCES company (company_id) " +
                        "ON DELETE CASCADE)";
        createTable(createOperatesInTableSQL);
    }

    private void createWorkedAtTable() {
        //language=SQL
        String createWorkedAtTableSQL =
                "CREATE TABLE worked_at (" +
                        "email VARCHAR(255), " +
                        "company_id INTEGER, " +
                        "position VARCHAR(255), " +
                        "startDATE DATE NOT NULL, " +
                        "endDate DATE NOT NULL, " +
                        "PRIMARY KEY (email, company_id), " +
                        "FOREIGN KEY (email) REFERENCES user_accounts (email) " +
                        "ON DELETE CASCADE, " +
                        "FOREIGN KEY (company_id) REFERENCES company (company_id) " +
                        "ON DELETE CASCADE)";
        createTable(createWorkedAtTableSQL);
    }

    private void createRecruiterTable() {
        //language=SQL
        String createRecruiterTableSQL =
                "CREATE TABLE recruiter (" +
                        "email VARCHAR(255) PRIMARY KEY, " +
                        "name VARCHAR(255) NOT NULL)";
        createTable(createRecruiterTableSQL);
    }

    private void createPostedJobTable() {
        //language=SQL
        String createPostedJobTableSQL =
                "CREATE TABLE posted_job (" +
                        "job_id INTEGER, " +
                        "company_id INTEGER NOT NULL, " +
                        "posted_date DATE, " +
                        "location VARCHAR(255) NOT NULL, " +
                        "description VARCHAR(2000) NOT NULL, " +
                        "salary INTEGER, " +
                        "recruiter_email VARCHAR(255), " +
                        "PRIMARY KEY (job_id), " +
                        "FOREIGN KEY (company_id) REFERENCES company (company_id) " +
                        "ON DELETE CASCADE, " +
                        "FOREIGN KEY (recruiter_email) REFERENCES recruiter (email) " +
                        "ON DELETE SET NULL)";
        createTable(createPostedJobTableSQL);
    }

    private void createCategoryTable() {
        //language=SQL
        String createCategoryTableSQL = "CREATE TABLE category (name VARCHAR(255) PRIMARY KEY)";
        createTable(createCategoryTableSQL);
    }

    private void createJobBelongsInTable() {
        //language=SQL
        String createJobBelongsInTableSQL =
                "CREATE TABLE job_belongs_in (" +
                        "job_id INTEGER, " +
                        "category_name VARCHAR(255), " +
                        "PRIMARY KEY (job_id, category_name), " +
                        "FOREIGN KEY (job_id) REFERENCES posted_job (job_id) " +
                        "ON DELETE CASCADE, " +
                        "FOREIGN KEY (category_name) REFERENCES category (name) " +
                        "ON DELETE CASCADE)";
        createTable(createJobBelongsInTableSQL);
    }

    private void createSkillTable() {
        //language=SQL
        String createSkillTableSQL = "CREATE TABLE skill (name VARCHAR(255) PRIMARY KEY)";
        createTable(createSkillTableSQL);
    }

    private void createJobRequiresTable() {
        //language=SQL
        String createJobRequiresTableSQL =
                "CREATE TABLE job_requires (" +
                        "job_id INTEGER, " +
                        "skill_name VARCHAR(255), " +
                        "proficiency INTEGER, " +
                        "PRIMARY KEY (job_id, skill_name), " +
                        "FOREIGN KEY (job_id) REFERENCES posted_job (job_id) " +
                        "ON DELETE CASCADE, " +
                        "FOREIGN KEY (skill_name) REFERENCES skill (name) " +
                        "ON DELETE CASCADE)";
        createTable(createJobRequiresTableSQL);
    }

    private void createUserAppliesToTable() {
        //language=SQL
        String createUserAppliesToTableSQL =
                "CREATE TABLE user_applies_to (" +
                        "email VARCHAR(255), " +
                        "job_id INTEGER, " +
                        "appliedDate DATE, " +
                        "PRIMARY KEY (email, job_id), " +
                        "FOREIGN KEY (email) REFERENCES user_accounts (email) " +
                        "ON DELETE CASCADE, " +
                        "FOREIGN KEY (job_id) REFERENCES posted_job (job_id) " +
                        "ON DELETE CASCADE)";
        createTable(createUserAppliesToTableSQL);
    }

    private void createResumeTable() {
        //language=SQL
        String createResumeTableSQL =
                "CREATE TABLE resume (" +
                        "email VARCHAR(255), " +
                        "document_id INTEGER, " +
                        "url VARCHAR(255), " +
                        "PRIMARY KEY (email, document_id), " +
                        "UNIQUE (url), " +
                        "FOREIGN KEY (email) REFERENCES user_accounts (email) " +
                        "ON DELETE CASCADE)";
        createTable(createResumeTableSQL);
    }

    private void createSchoolTable() {
        //language=SQL
        String createSchoolTableSQL =
                "CREATE TABLE school (" +
                        "name VARCHAR(255), " +
                        "address VARCHAR(255), " +
                        "PRIMARY KEY (name))";
        createTable(createSchoolTableSQL);
    }

    private void createTranscriptTable() {
        //language=SQL
        String createTranscriptTableSQL =
                "CREATE TABLE transcript (" +
                        "email VARCHAR(255), " +
                        "document_id INTEGER, " +
                        "school_name VARCHAR(255) NOT NULL, " +
                        "PRIMARY KEY (email, document_id), " +
                        "FOREIGN KEY (email) REFERENCES user_accounts (email) " +
                        "ON DELETE CASCADE, " +
                        "FOREIGN KEY (school_name) REFERENCES school (name) " +
                        "ON DELETE CASCADE)";
        createTable(createTranscriptTableSQL);
    }

    private void createCourseTable() {
        //language=SQL
        String createCourseTableSQL =
                "CREATE TABLE course (" +
                        "course_id INTEGER, " +
                        "course_name VARCHAR(255), " +
                        "department VARCHAR(255), " +
                        "PRIMARY KEY (course_id), " +
                        "UNIQUE(course_name))";
        createTable(createCourseTableSQL);
    }

    private void createGradeTable() {
        //language=SQL
        String createGradeTableSQL =
                "CREATE TABLE grade (" +
                        "percentage INTEGER, " +
                        "letter_grade CHAR(1) NOT NULL, " +
                        "PRIMARY KEY (percentage))";
        createTable(createGradeTableSQL);
    }

    private void createTranscriptContainsTable() {
        //language=SQL
        String createTranscriptContainsTableSQL =
                "CREATE TABLE transcript_contains (" +
                        "email VARCHAR(255), " +
                        "document_id INTEGER, " +
                        "course_id INTEGER, " +
                        "percentage INTEGER NOT NULL, " +
                        "year_session VARCHAR(255), " +
                        "PRIMARY KEY (email, document_id, course_id), " +
                        "FOREIGN KEY (email, document_id) REFERENCES transcript (email, document_id) " +
                        "ON DELETE CASCADE, " +
                        "FOREIGN KEY (course_id) REFERENCES course (course_id) " +
                        "ON DELETE CASCADE, " +
                        "FOREIGN KEY (percentage) REFERENCES grade (percentage) " +
                        "ON DELETE CASCADE)";
        createTable(createTranscriptContainsTableSQL);
    }

    private void createTable(String createTableSQL) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableSQL);
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public void createTables() {
        createUserTable();
        createCompanyTable();
        createScoreTable();
        createRatesTable();
        createIndustryTable();
        createOperatesInTable();
        createWorkedAtTable();
        createRecruiterTable();
        createPostedJobTable();
        createCategoryTable();
        createJobBelongsInTable();
        createSkillTable();
        createJobRequiresTable();
        createUserAppliesToTable();
        createResumeTable();
        createSchoolTable();
        createTranscriptTable();
        createCourseTable();
        createGradeTable();
        createTranscriptContainsTable();
    }

    public void deleteTables() {
        String getAllTablesSQL = "SELECT table_name FROM user_tables";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllTablesSQL);
            Set<String> tableNames = new HashSet<>();
            while (resultSet.next()) {
                String tableName = resultSet.getString("table_name");
                tableNames.add(tableName.toLowerCase());
            }

            List<String> toDelete = new ArrayList<>();
            toDelete.add("transcript_contains");
            toDelete.add("grade");
            toDelete.add("course");
            toDelete.add("transcript");
            toDelete.add("school");
            toDelete.add("resume");
            toDelete.add("user_applies_to");
            toDelete.add("job_requires");
            toDelete.add("skill");
            toDelete.add("job_belongs_in");
            toDelete.add("category");
            toDelete.add("posted_job");
            toDelete.add("recruiter");
            toDelete.add("worked_at");
            toDelete.add("operates_in");
            toDelete.add("industry");
            toDelete.add("rates");
            toDelete.add("score");
            toDelete.add("company");
            toDelete.add("user_accounts");

            for (String tableName : toDelete) {
                if (tableNames.contains(tableName)) {
                    String deleteTableSQL = "DROP TABLE " + tableName;
                    statement.executeUpdate(deleteTableSQL);
                }
            }
        } catch (SQLException e) {
            // TODO: throw custom exception here and in createTables()
            System.err.println("Error deleting tables: " + e.getMessage());
        }
    }
}
