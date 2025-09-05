package commands;

import exceptions.TheseException;

/**
 * Represents a command to be executed by These
 *
 * Classes implementing this interface should provide a specific behavior
 * for running that command
 */
public interface Command {
    public boolean run(String input) throws TheseException;
}
