public class Tetromino {

    // 2D array representation: 1 = block, 0 = empty
    private int[][] shape;

    public Tetromino(int[][] shape) {
        this.shape = shape;
    }

    public int[][] getShape() {
        return shape;
    }

    public void setShape(int[][] newShape) {
        this.shape = newShape;
    }

    // simple clockwise rotation
    public int[][] rotate() {
        int rows = shape.length;
        int cols = shape[0].length;
        int[][] rotated = new int[cols][rows];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rotated[c][rows - 1 - r] = shape[r][c];
            }
        }
        return rotated;
    }
}
