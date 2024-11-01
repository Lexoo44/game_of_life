import java.util.Random;

public class Board {

    private static final int BOARD_SIZE = 10;

    private static final Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];

    public Board() {
        fillBoard();
    }

    public void printBoard() {
        for (Cell[] cells : board) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(cells[j].toString() + " ");
            }
            System.out.print("\n");
        }
    }

    private void fillBoard() {
        Random random = new Random();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <  board.length; j++) {
                var rand = random.nextInt(2);
                board[i][j] = new Cell(rand == 0 ? State.Dead : State.Alive, i, j);
            }
        }
    }

    private int getNeighbourAlive(Cell cell) {
        int ret = 0;
        for (int i = cell.getX() - 1; i <= cell.getX() + 1; i++) {
            for (int j = cell.getY() - 1; j <= cell.getY() + 1; j++) {
                if (i == cell.getX() && j == cell.getY()) {
                    continue;
                }
                if (i >= 0 && i < BOARD_SIZE && j >= 0 && j < BOARD_SIZE) {
                    var refCell = getCellByPosition(i, j);
                    if (refCell.getState() == State.Alive) {
                        ret++;
                    }
                }
            }
        }
        return ret;
    }

    private void nextGeneration() {
        Cell[][] nextGen = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                nextGen[i][j] = new Cell(board[i][j].getState(), i, j);
                int neighboursAlive = getNeighbourAlive(board[i][j]);
                if (neighboursAlive == 3 || (neighboursAlive == 2 && board[i][j].getState() == State.Alive)) {
                    nextGen[i][j].setState(State.Alive);
                } else {
                    nextGen[i][j].setState(State.Dead);
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(nextGen[i], 0, board[i], 0, board.length);
        }
    }

    public void startSimulation() {
        System.out.println("Press 'ESC' to exit");
        do {
            nextGeneration();
            printBoard();
        } while (true);
    }

    private Cell getCellByPosition(int x, int y) {
        return board[x][y];
    }
}
