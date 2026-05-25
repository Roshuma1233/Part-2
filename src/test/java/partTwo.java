import com.mycompany.chat_app.message;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class partTwo {
Message msg= new message();

    // Test Message ID
    @Test
    public void testCheckMessageID() {

        assertTrue(message.checkMessageID("1234567890"));
        assertFalse(message.checkMessageID("12345"));
    }

    // Test recipient cell number
    @Test
    public void testCheckRecipientCell() {

        String valid =
                message.checkRecipientCell("+27831234567");

        String invalid =
                message.checkRecipientCell("0831234567");

        assertEquals(
                "Cell phone number successfully captured.",
                valid
        );

        assertEquals(
                "Cell phone number incorrectly formatted.",
                invalid
        );
    }

    // Test message hash creation
    @Test
    public void testCreateMessageHash() {

        String hash =
                message.createMessageHash(
                        "1234567890",
                        1,
                        "Hi Mike"
                );

        assertEquals("12:1:HIMIKE", hash);
    }

    // Test total messages
    @Test
    public void testReturnTotalMessages() {

        message.sentMessage();
        message.sentMessage();

        assertEquals(2,
                message.returnTotalMessages());
    }
}