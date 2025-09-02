import org.junit.jupiter.api.Test;
import parser.Parser;
import app.These;
import commands.*;
import exceptions.TheseException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private final These these = new These();

    @Test
    void testTodoInputReturnsTodoCommand() {
        try {
            assertInstanceOf(TodoCommand.class, Parser.parse("todo read book", these));
        } catch (TheseException e) {
            these.getUi().showError("Error in parsing Todo Command");
        }
    }

    @Test
    void testMarkInputReturnsMarkCommand() {
        try {
            assertInstanceOf(MarkCommand.class, Parser.parse("mark 1", these));
        } catch (TheseException e) {
            these.getUi().showError("Error in parsing Mark Command");
        }
    }

    @Test
    void testListInputReturnsListCommand() {
        try {
            assertInstanceOf(ListCommand.class, Parser.parse("list", these));
        } catch (TheseException e) {
            these.getUi().showError("Error in parsing List Command");
        }
    }

    @Test
    void testParseUnknownThrowsException() {
        assertThrows(TheseException.class, () -> Parser.parse("nonsense", these));
    }
}
