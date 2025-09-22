package parser;

import commands.*;
import exceptions.TheseException;
import app.These;
import java.util.Map;
import java.util.function.Function;

public class Parser {

    // Map of known and supported commands
    private static final Map<String, Function<These, Command>> COMMANDS = Map.of(
            "bye",      ExitCommand::new,
            "list",     ListCommand::new,
            "delete",   DeleteCommand::new,
            "mark",     MarkCommand::new,
            "unmark",   UnmarkCommand::new,
            "todo",     TodoCommand::new,
            "deadline", DeadlineCommand::new,
            "event",    EventCommand::new,
            "clear",    ClearCommand::new,
            "help",     HelpCommand::new,
    );

    public static Command parse(String input, These these) throws TheseException {

        String[] parts = input.split(" ", 2);
        String cmd = parts[0];
      
        // Retrieves command (before applying null) and checks for unknown command
        Function<These, Command> factory = COMMANDS.get(cmd);
        if (factory == null) {
            throw new TheseException("Unknown command: " + cmd);
        }

        return factory.apply(these);

    }
}
