import java.awt.Point;

public class TetrisGame {

    public static final int ROWS = 20;
    public static final int COLS = 10;

    private int[][] board; // 0 = empty, 1 = filled (simple)
    private Tetromino current;
    private Point position; // row, col
    private RuleSet ruleSet;

    public TetrisGame() {
        board = new int[ROWS][COLS];
        ruleSet = new BasicRuleSet(); // could swap out later
        spawnNewPiece();
    }

    public int[][] getBoard() {
        return board;
    }

    public Tetromino getCurrentPiece() {
        return current;
    }

    public Point getCurrentPosition() {
        return position;
    }

    public void update() {
        // move piece down if possible, else lock and spawn new
        Point newPos = new Point(position.x + 1, position.y);
        if (canMove(current.getShape(), newPos)) {
            position = newPos;
        } else {
            lockPiece();
            clearLines();
            spawnNewPiece();
        }
    }

    public void moveLeft() {
        Point newPos = new Point(position.x, position.y - 1);
        if (canMove(current.getShape(), newPos)) {
            position = newPos;
        }
    }

    public void moveRight() {
        Point newPos = new Point(position.x, position.y + 1);
        if (canMove(current.getShape(), newPos)) {
            position = newPos;
        }
    }

    public void rotate() {
        int[][] rotated = current.rotate();
        if (canMove(rotated, position)) {
            current.setShape(rotated);
        }
    }

    private void spawnNewPiece() {
        current = ruleSet.createRandomTetromino();
        position = new Point(0, COLS / 2 - 1);
    }

    private boolean canMove(int[][] shape, Point pos) {
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[0].length; c++) {
                if (shape[r][c] != 0) {
                    int br = pos.x + r;
                    int bc = pos.y + c;
                    if (br < 0 || br >= ROWS || bc < 0 || bc >= COLS) {
                        return false;
                    }
                    if (board[br][bc] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void lockPiece() {
        int[][] shape = current.getShape();
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[0].length; c++) {
                if (shape[r][c] != 0) {
                    int br = position.x + r;
                    int bc = position.y + c;
                    if (br >= 0 && br < ROWS && bc >= 0 && bc < COLS) {
                        board[br][bc] = 1;
                    }
                }
            }
        }
    }

    private void clearLines() {
        // very simple: full row -> clear and move everything down
        for (int r = ROWS - 1; r >= 0; r--) {
            boolean full = true;
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == 0) {
                    full = false;
                    break;
                }
            }
            if (full) {
                for (int rr = r; rr > 0; rr--) {
                    board[rr] = board[rr - 1].clone();
                }
                board[0] = new int[COLS];
                r++; // recheck this row index since we pulled down
            }
        }
    }
}

