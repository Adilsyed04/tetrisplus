import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TetrisPanel extends JPanel {

    private TetrisGame game;
    private Timer timer;
    private static final int CELL_SIZE = 30;

    public TetrisPanel(TetrisGame game) {
        this.game = game;
        setPreferredSize(new Dimension(TetrisGame.COLS * CELL_SIZE,
                                       TetrisGame.ROWS * CELL_SIZE));
        setBackground(Color.BLACK);

        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        game.moveLeft();
                        repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.moveRight();
                        repaint();
                        break;
                    case KeyEvent.VK_UP:
                        game.rotate();
                        repaint();
                        break;
                    case KeyEvent.VK_DOWN:
                        game.update();
                        repaint();
                        break;
                }
            }
        });
    }

    public void startGameLoop() {
        timer = new Timer(500, e -> {
            game.update();
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[][] board = game.getBoard();

        // draw board
        for (int r = 0; r < TetrisGame.ROWS; r++) {
            for (int c = 0; c < TetrisGame.COLS; c++) {
                if (board[r][c] != 0) {
                    drawCell(g, c, r, Color.CYAN);
                } else {
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }

        // draw current piece
        if (game.getCurrentPiece() != null) {
            int[][] shape = game.getCurrentPiece().getShape();
            int baseRow = game.getCurrentPosition().x;
            int baseCol = game.getCurrentPosition().y;

            for (int r = 0; r < shape.length; r++) {
                for (int c = 0; c < shape[0].length; c++) {
                    if (shape[r][c] != 0) {
                        drawCell(g, baseCol + c, baseRow + r, Color.GREEN);
                    }
                }
            }
        }
    }

    private void drawCell(Graphics g, int col, int row, Color color) {
        g.setColor(color);
        g.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }
}
