package parser;

import commands.*;
import exceptions.TheseException;
import app.These;

public class Parser {
    public static Command parse(String input, These these) throws TheseException {
        String[] parts = input.split(" ", 2);
        String cmd = parts[0];

        switch (cmd) {
            case "bye" -> {
                return new ExitCommand(these);
            } case "list" -> {
                return new ListCommand(these);
            } case "delete" -> {
                return new DeleteCommand(these);
            } case "mark" -> {
                return new MarkCommand(these);
            } case "unmark" -> {
                return new UnmarkCommand(these);
            } case "todo" -> {
                return new TodoCommand(these);
            } case "deadline" -> {
                return new DeadlineCommand(these);
            } case "event" -> {
                return new EventCommand(these);
            } case "clear" -> {
                return new ClearCommand(these);
            } case "find" -> {
                return new FindCommand(these);
            } default -> {
                return new UnknownCommand(these);
            }

        }
    }
}
