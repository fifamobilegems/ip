package commands;

import app.These;
import exceptions.TheseException;

/**
 * Represents a command that displays a help page with a list of commands and input format to guide the user
 */
public class HelpCommand implements Command {
    private These these;
    private String FULL_HELP = String.join("\n",
            "These — Help",
            "----------------------------------------",
            "General:",
            "  help                    Show this page",
            "  help <cmd>              Show details for a command",
            "  list                    List all tasks",
            "  todo <desc>             Add a Todo",
            "  deadline <desc> /by yyyy-mm-dd",
            "                          Add a Deadline",
            "  event <desc> /from yyyy-mm-dd /to yyyy-mm-dd",
            "                          Add an Event",
            "  mark <n>                Mark task n as done",
            "  unmark <n>              Unmark task n",
            "  delete <n>              Delete task n",
            "  clear                   Remove all tasks",
            "  find <keywords>         Search tasks (if implemented)",
            "  bye                     Exit",
            "----------------------------------------",
            "Tip: use ISO dates e.g., 2025-03-17."
            );

    public HelpCommand(These these) {
        this.these = these;
    }

    /**
     * Prints in-app guide for users, either for specific command or full list of commands
     *
     * If the user invokes {@code help <command>}, this method prints the
     * usage text for that specific command. If the user invokes just
     * {@code help}, it prints the full help overview.
     *
     * @param input the raw user input routed to the help command
     * @return true to continue the main loop, this command should never fail
     * @throws TheseException not thrown in this method, but declared to
     *      * align with the implementation in the {@link Command} interface
     */
    @Override
    public boolean run(String input) throws TheseException {

        String[] parsedInput = input.split(" ", 2);

        String helpMsg = (parsedInput.length < 2) ? FULL_HELP : helpFor(parsedInput[1]);

        these.getUi().showMessage(helpMsg);
        return true;
    }


    private String helpFor(String cmd) {
        return switch (cmd) {
            case "list"     -> "list\n  List all tasks in order.";
            case "todo"     -> "todo <description>\n  Add a Todo task.";
            case "deadline" -> "deadline <description> /by yyyy-mm-dd\n  Add a Deadline with a due date.";
            case "event"    -> "event <description> /from yyyy-mm-dd /to yyyy-mm-dd\n  Add an Event with a date range.";
            case "mark"     -> "mark <n>\n  Mark task n as done.";
            case "unmark"   -> "unmark <n>\n  Mark task n as not done.";
            case "delete"   -> "delete <n>\n  Delete task n (1-based index).";
            case "clear"    -> "clear\n  Remove all tasks permanently.";
            case "find"     -> "find <keywords>\n  Show tasks whose text contains the keywords.";
            case "bye"      -> "bye\n  Save (if applicable) and exit the app.";
            default         -> "Unknown command: " + cmd + "\n\n" + FULL_HELP;
        };
    }
}
