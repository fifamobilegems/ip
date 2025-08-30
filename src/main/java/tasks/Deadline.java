package tasks;

public class Deadline extends Task {
    private String by;

    public Deadline(String name, boolean marked, int id, String by) {
        super(name, marked, id);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D][" + getMarked() + "] " + getName() + "(by:" + getBy() + ")";
    }

    @Override
    public String toDataFormat() {
        return "D|" + getMarked() + "|" + getName() + "|" + getBy();
    }
}
