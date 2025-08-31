package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String name, boolean marked, int id, LocalDate by) {
        super(name, marked, id);
        this.by = by;
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D][" + getMarked() + "] " + getName() + "(by: " + getBy() + ")";
    }

    @Override
    public String toDataFormat() {
        return "D|" + getMarked() + "|" + getName() + "|" + getBy();
    }
}
