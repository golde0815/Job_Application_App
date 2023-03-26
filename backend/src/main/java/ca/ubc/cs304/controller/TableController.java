package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.MigrationHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TableController {
    private final MigrationHandler migrationHandler;

    public TableController(MigrationHandler migrationHandler) {
        this.migrationHandler = migrationHandler;
    }

    @PostMapping("/tables")
    private String createAndPopulateTables() {
        try {
            migrationHandler.createAndPopulateTables();
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry something went wrong");
        }
    }

    @DeleteMapping("/tables")
    private String dropTables() {
        try {
            migrationHandler.dropTables();
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry something went wrong");
        }
    }
}
