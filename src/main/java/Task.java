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
        if (marked) {
            return id + ". [X] " + name;
        } else {
            return id + ". [ ] " + name;
        }
    }

    public String getName() {
        return this.name;
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
