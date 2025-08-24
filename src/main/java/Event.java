public class Event extends Task {
    private String from;
    private String to;

    public Event(String name, boolean marked, int id, String from, String to) {
        super(name, marked, id);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E][" + getMarked() + "] "
                + getName() + "(from:" + getFrom() + "to:" + getTo() + ")";
    }
}
