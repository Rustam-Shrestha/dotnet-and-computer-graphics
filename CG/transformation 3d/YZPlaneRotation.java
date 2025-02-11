package ok;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Scanner;

public class YZPlaneRotation extends JPanel {
    private int[][] originalSquare1, originalSquare2, rotatedSquare1, rotatedSquare2;
    private double theta; // Rotation angle in radians

    public YZPlaneRotation(double theta) {
        this.theta = theta;

        // Coordinates for the two squares (X, Y, Z)
        originalSquare1 = new int[][] {
            {25, -25, -25}, {25, -50, -25}, {50, -50, -25}, {50, -25, -25}
        };
        originalSquare2 = new int[][] {
            {50, -50, 25}, {50, -100, 25}, {100, -100, 25}, {100, -50, 25}
        };

        // Perform rotation around the YZ plane
        rotatedSquare1 = rotateYZ(originalSquare1);
        rotatedSquare2 = rotateYZ(originalSquare2);
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

        // Draw rotated squares and connections
        drawSquare(g2d, rotatedSquare1, Color.BLUE, "Rotated 1");
        drawSquare(g2d, rotatedSquare2, Color.BLUE, "Rotated 2");
        connectSquares(g2d, rotatedSquare1, rotatedSquare2, Color.BLUE);
    }

    private void drawAxes(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);

        // X-axis
        g2d.drawLine(getWidth() / 2, getHeight() / 2, getWidth(), getHeight() / 2);
        g2d.drawString("X", getWidth() - 10, getHeight() / 2 + 10);

        // Y-axis
        g2d.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, 0);
        g2d.drawString("Y", getWidth() / 2 + 10, 10);

        // Z-axis (for 3D effect)
        g2d.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2 + 100, getHeight() / 2 + 100);
        g2d.drawString("Z", getWidth() / 2 + 110, getHeight() / 2 + 110);
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

    private int[][] rotateYZ(int[][] square) {
        int[][] rotatedSquare = new int[square.length][3];
        for (int i = 0; i < square.length; i++) {
            rotatedSquare[i][0] = square[i][0]; // X remains the same
            rotatedSquare[i][1] = (int) (square[i][1] * Math.cos(theta) - square[i][2] * Math.sin(theta)); // Y rotates
            rotatedSquare[i][2] = (int) (square[i][1] * Math.sin(theta) + square[i][2] * Math.cos(theta)); // Z rotates
        }
        return rotatedSquare;
    }

    private int[] project(int[] point) {
        int x = getWidth() / 2 + point[0] + point[2] / 2;
        int y = getHeight() / 2 - point[1] - point[2] / 2;
        return new int[]{x, y};
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rotation around YZ Plane");
        // take integer input from java
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of degrees: ");
        int theta = sc.nextInt();
        YZPlaneRotation panel = new YZPlaneRotation(Math.toRadians(theta)); // Example rotation angle
        frame.add(panel);
        frame.setSize(800, 800); // Make the JFrame bigger
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
