package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.migration.MigrationHandler;
import ca.ubc.cs304.database.model.ResponseMessage;
import ca.ubc.cs304.exception.GenericSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/tables")
    private List<String> getTables() {
        try {
            return migrationHandler.getTables();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry something went wrong");
        }
    }

    @GetMapping("/tables/{tableName}")
    private List<String> getTableColumns(@PathVariable String tableName) {
        try {
            return migrationHandler.getTableColumns(tableName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry something went wrong");
        }
    }

    // 5. Projection
    @GetMapping("/project/{tableName}")
    private List<Map<String, String>> projectColumns(@PathVariable String tableName, @RequestParam("column") List<String> columns) {
        try {
            return migrationHandler.projectColumns(tableName, columns);
        } catch (GenericSQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getCause().getMessage());
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
