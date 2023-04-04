package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.migration.MigrationHandler;
import ca.ubc.cs304.database.model.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TableController {
    private final MigrationHandler migrationHandler;

    public TableController(MigrationHandler migrationHandler) {
        this.migrationHandler = migrationHandler;
    }

    @PostMapping("/tables")
    @ResponseBody
    private ResponseMessage createAndPopulateTables() {
        try {
            migrationHandler.createAndPopulateTables();
            return new ResponseMessage("OK");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry something went wrong");
        }
    }

    @DeleteMapping("/tables")
    @ResponseBody
    private ResponseMessage dropTables() {
        try {
            migrationHandler.dropTables();
            return new ResponseMessage("OK");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry something went wrong");
        }
    }
}
