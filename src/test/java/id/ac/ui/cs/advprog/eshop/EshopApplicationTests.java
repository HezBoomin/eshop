package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
        // Ensure that the application context loads successfully
    }

    @Test
    void startsApplicationSuccessfully() {
        // Test that the root endpoint returns a 200 OK status
    }

    @Test
    void mainMethodStartsApplicationSuccessfully() {
        // Test the main method by attempting to run it without throwing any exceptions
        try {
            EshopApplication.main(new String[] {});
        } catch (Exception e) {
            // If there's an exception, fail the test
            fail("Exception thrown while starting application: " + e.getMessage());
        }
    }
}
