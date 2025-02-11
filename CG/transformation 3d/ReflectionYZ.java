import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class ReflectionYZ extends JPanel {
    private int[][] square1, square2;

    public ReflectionYZ() {
        // Coordinates for the two squares
        square1 = new int[][] {
            {250, 100, 0}, {300, 100, 0}, {300, 150, 0}, {250, 150, 0}
        };
        square2 = new int[][] {
            {230, 140, 50}, {280, 140, 50}, {280, 190, 50}, {230, 190, 50}
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw original shape
        drawShape(g2d, square1, square2, Color.RED, "Original");

        // Reflect across YZ plane (X coordinate negated)
        int[][] reflectedSquare1 = reflectYZ(square1);
        int[][] reflectedSquare2 = reflectYZ(square2);

        // Draw reflected shape
        drawShape(g2d, reflectedSquare1, reflectedSquare2, Color.BLUE, "Reflected");
    }

    private void drawShape(Graphics2D g2d, int[][] square1, int[][] square2, Color color, String label) {
        g2d.setColor(color);

        // Draw first square
        for (int i = 0; i < square1.length; i++) {
            int next = (i + 1) % square1.length;
            g2d.drawLine(square1[i][0], square1[i][1], square1[next][0], square1[next][1]);
        }

        // Draw second square
        for (int i = 0; i < square2.length; i++) {
            int next = (i + 1) % square2.length;
            g2d.drawLine(square2[i][0], square2[i][1], square2[next][0], square2[next][1]);
        }

        // Connect corresponding points of the two squares
        for (int i = 0; i < square1.length; i++) {
            g2d.draw(new Line2D.Float(square1[i][0], square1[i][1], square2[i][0], square2[i][1]));
        }

        // Label the shape
        g2d.drawString(label, square1[0][0] - 40, square1[0][1] - 10);
    }

    private int[][] reflectYZ(int[][] square) {
        int[][] reflectedSquare = new int[square.length][3];
        for (int i = 0; i < square.length; i++) {
            reflectedSquare[i][0] = -square[i][0];  // Reflect across YZ plane
            reflectedSquare[i][1] = square[i][1];
            reflectedSquare[i][2] = square[i][2];
        }
        return reflectedSquare;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reflection across YZ Plane");
        ReflectionYZ panel = new ReflectionYZ();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
