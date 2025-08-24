public class Todo extends Task {
    public Todo(String name, boolean marked, int id) {
        super(name, marked, id);
    }

    @Override
    public String toString() {
        return "[T] [" + getMarked() + "] " + getName();
    }
}
