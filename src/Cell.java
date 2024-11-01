public class Cell {

    private State state;

    private final int x;

    private final int y;

    public Cell(State state, int x, int y) {
        this.state = state;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return state == State.Alive ? "*" : ".";
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
