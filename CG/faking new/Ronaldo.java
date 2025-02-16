import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.Scanner;

public class Ronaldo extends JPanel {
private int[][] sl1, sl2, man1, man2;
private static final int ORIGIN_X = 200;  // X-coordinate of the origin (intersection of axes)
private static final int ORIGIN_Y = 300;  // Y-coordinate of the origin (intersection of axes)

public Ronaldo(int[][] sl1, int[][] sl2) {
    this.sl1 = sl1;
    this.sl2 = sl2;
    man1 = reflectYZ(sl1);
    man2 = reflectYZ(sl2);

    // Debug: Print original and reflected coordinates
    System.out.println("Original Square 1: " + Arrays.deepToString(sl1));
    System.out.println("Reflected Square 1: " + Arrays.deepToString(man1));
    System.out.println("Original Square 2: " + Arrays.deepToString(sl2));
    System.out.println("Reflected Square 2: " + Arrays.deepToString(man2));
}

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // Draw axes
    drawAxes(g2d);

    // Draw original squares and connections
    drawSquare(g2d, sl1, Color.BLACK, "Original 1");
    drawSquare(g2d, sl2, Color.BLACK, "Original 2");
    connectSquares(g2d, sl1, sl2, Color.BLACK);

    // Draw reflected squares and connections
    drawSquare(g2d, man1, Color.BLUE, "Reflected 1");
    drawSquare(g2d, man2, Color.BLUE, "Reflected 2");
    connectSquares(g2d, man1, man2, Color.BLUE);
}

private void drawAxes(Graphics2D g2d) {
    g2d.setColor(Color.GRAY);

    // X-axis
    g2d.drawLine(ORIGIN_X - 150, ORIGIN_Y, ORIGIN_X + 200, ORIGIN_Y);
    g2d.drawString("X", ORIGIN_X + 210, ORIGIN_Y);

    // Y-axis
    g2d.drawLine(ORIGIN_X, ORIGIN_Y + 150, ORIGIN_X, ORIGIN_Y - 200);
    g2d.drawString("Y", ORIGIN_X - 10, ORIGIN_Y - 210);

    // Z-axis
    g2d.drawLine(ORIGIN_X, ORIGIN_Y, ORIGIN_X - 100, ORIGIN_Y + 100);
    g2d.drawString("Z", ORIGIN_X - 110, ORIGIN_Y + 110);
}

private void drawSquare(Graphics2D g2d, int[][] square, Color color, String label) {
    g2d.setColor(color);
    for (int i = 0; i < square.length; i++) {
        int[] p1 = project(square[i]);
        int[] p2 = project(square[(i + 1) % square.length]);
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

private int[][] reflectYZ(int[][] square) {
    int[][] reflectedSquare = new int[square.length][3];
    for (int i = 0; i < square.length; i++) {
        reflectedSquare[i][0] = -square[i][0];
        reflectedSquare[i][1] = square[i][1];
        reflectedSquare[i][2] = square[i][2];
    }
    return reflectedSquare;
}

private int[] project(int[] point) {
    // Project the 3D point to 2D using simple orthographic projection
    int x = ORIGIN_X + point[0] + point[2] / 2;
    int y = ORIGIN_Y - (point[1] - point[2] / 2);
    return new int[]{x, y};
}

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int[][] sl1 = new int[4][3];
    int[][] sl2 = new int[4][3];

    System.out.println("Enter coordinates for the front face of the cuboid:");
    for (int i = 0; i < 4; i++) {
        System.out.print("Point " + (i + 1) + " (X Y Z): ");
        sl1[i][0] = scanner.nextInt();
        sl1[i][1] = scanner.nextInt();
        sl1[i][2] = scanner.nextInt();
    }

    System.out.println("Enter coordinates for the back face of the cuboid:");
    for (int i = 0; i < 4; i++) {
        System.out.print("Point " + (i + 1) + " (X Y Z): ");
        sl2[i][0] = scanner.nextInt();
        sl2[i][1] = scanner.nextInt();
        sl2[i][2] = scanner.nextInt();
    }

    SwingUtilities.invokeLater(() -> {
        JFrame frame = new JFrame("Reflection across YZ Plane");
        frame.add(new Ronaldo(sl1, sl2));
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    });
}
}
