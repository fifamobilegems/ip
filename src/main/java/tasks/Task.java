package tasks;

public class Task {
    public String name;
    public boolean marked;
    public int id;

    public Task(String name, boolean marked, int id) {
        this.name = name;
        this.marked = marked;
        this.id = id;
    }

    @Override
    public String toString() {
            return getId() + ". [" + getMarked() + "] " + getName();
    }

    public String getName() {
        return this.name;
    }

    public void dropId() {
        this.id--;
    }

    public String getId() {
        return String.valueOf(this.id);
    }

    public String getMarked() {
        return (marked) ? "X" : " ";
    }

    public void mark() {
        if (!marked) {
            this.marked = true;
        }
    }

    public void unmark() {
        if (marked) {
            this.marked = false;
        }
    }
}
