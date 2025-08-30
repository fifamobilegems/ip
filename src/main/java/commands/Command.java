package commands;

import exceptions.TheseException;

public interface Command {
    public boolean run(String input) throws TheseException;
}
