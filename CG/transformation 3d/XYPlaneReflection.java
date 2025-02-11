package ok;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class XYPlaneReflection extends JPanel {
    private int[][] originalSquare1, originalSquare2, reflectedSquare1, reflectedSquare2;

    public XYPlaneReflection() {
        // Coordinates for the two squares (X, Y, Z)
        originalSquare1 = new int[][] {
            {100, 100, 50}, {150, 100, 50}, {150, 150, 50}, {100, 150, 50}
        };
        originalSquare2 = new int[][] {
            {200, 200, 50}, {250, 200, 50}, {250, 250, 50}, {200, 250, 50}
        };

        // Perform reflection across the XY plane (Z flips sign)
        reflectedSquare1 = reflectXY(originalSquare1);
        reflectedSquare2 = reflectXY(originalSquare2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw axes (X, Y, Z)
        drawAxes(g2d);

        // Draw original squares and connections
        drawSquare(g2d, originalSquare1, Color.RED, "Original 1");
        drawSquare(g2d, originalSquare2, Color.RED, "Original 2");
        connectSquares(g2d, originalSquare1, originalSquare2, Color.BLACK);

        // Draw reflected squares and connections
        drawSquare(g2d, reflectedSquare1, Color.BLUE, "Reflected 1");
        drawSquare(g2d, reflectedSquare2, Color.BLUE, "Reflected 2");
        connectSquares(g2d, reflectedSquare1, reflectedSquare2, Color.BLUE);
    }

    private void drawAxes(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);

        // X-axis
        g2d.drawLine(50, 300, 450, 300);
        g2d.drawString("X", 460, 300);

        // Y-axis
        g2d.drawLine(50, 300, 50, 50);
        g2d.drawString("Y", 50, 40);

        // Z-axis (for 3D effect)
        g2d.drawLine(50, 300, 150, 400);
        g2d.drawString("Z", 160, 410);
    }

    private void drawSquare(Graphics2D g2d, int[][] square, Color color, String label) {
        g2d.setColor(color);
        for (int i = 0; i < square.length; i++) {
            int next = (i + 1) % square.length;
            int[] p1 = project(square[i]);
            int[] p2 = project(square[next]);
            g2d.drawLine(p1[0], p1[1], p2[0], p2[1]);
        }
        int[] labelPos = project(square[0]);
        g2d.drawString(label, labelPos[0] - 40, labelPos[1] - 10);
    }

    private void connectSquares(Graphics2D g2d, int[][] square1, int[][] square2, Color color) {
        g2d.setColor(color);
        for (int i = 0; i < square1.length; i++) {
            int[] p1 = project(square1[i]);
            int[] p2 = project(square2[i]);
            g2d.draw(new Line2D.Float(p1[0], p1[1], p2[0], p2[1]));
        }
    }

    private int[][] reflectXY(int[][] square) {
        int[][] reflectedSquare = new int[square.length][3];
        for (int i = 0; i < square.length; i++) {
            reflectedSquare[i][0] = square[i][0]; // X remains the same
            reflectedSquare[i][1] = square[i][1]; // Y remains the same
            reflectedSquare[i][2] = -(square[i][2]); // Z changes sign (reflection across XY plane)
        }
        return reflectedSquare;
    }

    private int[] project(int[] point) {
        int x = point[0] + point[2] / 2;
        int y = point[1] - point[2] / 2;
        return new int[]{x, y};
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reflection across XY Plane");
        XYPlaneReflection panel = new XYPlaneReflection();
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}