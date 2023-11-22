import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;
public class ClientTests {
    @Test
    public void testClientConnectionWithNoServer(){
        assertDoesNotThrow(() -> {
            Client c = new Client(1234, "test");
        });
    }
}
