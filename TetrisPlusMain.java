import javax.swing.JFrame;

public class TetrisPlusMain {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris+ Proof of Concept");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TetrisGame game = new TetrisGame();
        TetrisPanel panel = new TetrisPanel(game);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.startGameLoop();
    }
}

