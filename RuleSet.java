import java.util.Random;

public interface RuleSet {
    Tetromino createRandomTetromino();
}

class BasicRuleSet implements RuleSet {

    private Random random = new Random();

    @Override
    public Tetromino createRandomTetromino() {
        // For proof-of-concept, just a couple of shapes.
        int choice = random.nextInt(2);

        if (choice == 0) {
            // I-shape
            return new Tetromino(new int[][] {
                {1, 1, 1, 1}
            });
        } else {
            // simple square
            return new Tetromino(new int[][] {
                {1, 1},
                {1, 1}
            });
        }
    }
}

