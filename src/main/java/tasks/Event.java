package tasks;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String name, boolean marked, int id, LocalDate from, LocalDate to) {
        super(name, marked, id);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E][" + getMarked() + "] "
                + getName() + "(from: " + getFrom() + " to: " + getTo() + ")";
    }

    @Override
    public String toDataFormat() {
        return "E|" + getMarked() + "|" + getName() + "|" + getFrom() + "|" + getTo();
    }
}
