package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.database.MigrationHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TestController {
    private final MigrationHandler migrationHandler;
    private final DatabaseConnectionHandler dbHandler;

    public TestController(MigrationHandler migrationHandler, DatabaseConnectionHandler dbHandler) {
        this.migrationHandler = migrationHandler;
        this.dbHandler = dbHandler;
    }

    @PostMapping("/tables")
    private String testEndpoint() {
        try {
            migrationHandler.createTables();
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry something went wrong");
        }
    }

    @DeleteMapping("/tables")
    private String test2Endpoint() {
        try {
            migrationHandler.deleteTables();
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry something went wrong");
        }
    }
}
