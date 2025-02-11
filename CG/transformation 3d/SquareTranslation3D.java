package ok;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class SquareTranslation3D extends JPanel {
    private int tx, ty; // Translation distances
    private int[][] square1, square2; // Coordinates for the squares

    public SquareTranslation3D(int tx, int ty) {
        this.tx = tx;
        this.ty = ty;

        // Square 1 coordinates (Top right)
        square1 = new int[][] {
            {250, 100}, {300, 100}, {300, 150}, {250, 150}
        };
        // Square 2 coordinates (Bottom right, closer to give a 3D effect)
        square2 = new int[][] {
            {230, 140}, {280, 140}, {280, 190}, {230, 190}
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw axes (X, Y, Z)
        drawAxes(g2d);

        // Draw original squares and connections
        drawSquare(g2d, square1, Color.RED, "Original");
        drawSquare(g2d, square2, Color.RED, "Original");

        // Draw lines connecting corresponding edges
        connectSquares(g2d, square1, square2, Color.BLACK);

        // Apply translation to squares
        int[][] translatedSquare1 = translate(square1, tx, ty);
        int[][] translatedSquare2 = translate(square2, tx, ty);

        // Draw translated squares and connections
        drawSquare(g2d, translatedSquare1, Color.BLUE, "Translated");
        drawSquare(g2d, translatedSquare2, Color.BLUE, "Translated");

        // Draw lines connecting corresponding edges after translation
        connectSquares(g2d, translatedSquare1, translatedSquare2, Color.BLUE);
    }

    private void drawAxes(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);

        // X-axis
        g2d.drawLine(50, 250, 350, 250);
        g2d.drawString("X", 360, 250);

        // Y-axis
        g2d.drawLine(50, 250, 50, 50);
        g2d.drawString("Y", 50, 40);

        // Z-axis (for 3D effect, diagonal line)
        g2d.drawLine(50, 250, 150, 350);
        g2d.drawString("Z", 160, 360);
    }

    private void drawSquare(Graphics2D g2d, int[][] square, Color color, String label) {
        g2d.setColor(color);
        for (int i = 0; i < square.length; i++) {
            int next = (i + 1) % square.length;
            g2d.drawLine(square[i][0], square[i][1], square[next][0], square[next][1]);
        }

        // Draw label near the first vertex
        g2d.drawString(label, square[0][0] - 40, square[0][1] - 10);
    }

    private void connectSquares(Graphics2D g2d, int[][] square1, int[][] square2, Color color) {
        g2d.setColor(color);
        for (int i = 0; i < square1.length; i++) {
            g2d.draw(new Line2D.Float(square1[i][0], square1[i][1], square2[i][0], square2[i][1]));
        }
    }

    private int[][] translate(int[][] square, int tx, int ty) {
        int[][] translatedSquare = new int[square.length][2];
        for (int i = 0; i < square.length; i++) {
            translatedSquare[i][0] = square[i][0] + tx;
            translatedSquare[i][1] = square[i][1] + ty;
        }
        return translatedSquare;
    }

    public static void main(String[] args) {
        int tx = Integer.parseInt(JOptionPane.showInputDialog("Enter translation distance for X-axis:"));
        int ty = Integer.parseInt(JOptionPane.showInputDialog("Enter translation distance for Y-axis:"));

        JFrame frame = new JFrame("3D Square Translation");
        SquareTranslation3D panel = new SquareTranslation3D(tx, ty);
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
