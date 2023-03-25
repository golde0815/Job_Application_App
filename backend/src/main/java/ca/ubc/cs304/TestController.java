package ca.ubc.cs304;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final DatabaseConnectionHandler dbHandler;

    public TestController(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @GetMapping("/test")
    private void testEndpoint() {
        try {
            dbHandler.exampleQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
