package chapter2;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class RotatedTriangleGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rotated Triangle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.add(new TrianglePanel());
            frame.setVisible(true);
        });
    }
}

class TrianglePanel extends JPanel {
    private int[][] triangle = {
            {1, 1},
            {2, 3},
            {3, 1}
    };

    private double theta = Math.toRadians(45); // Rotation angle in radians

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set up the rotation transformation
        AffineTransform rotation = new AffineTransform();
        rotation.rotate(theta, getWidth() / 2.0, getHeight() / 2.0);

        // Apply the rotation to each vertex
        for (int i = 0; i < 3; i++) {
            Point rotatedPoint = new Point();
            rotation.transform(new Point(triangle[i][0], triangle[i][1]), rotatedPoint);
            triangle[i][0] = rotatedPoint.x;
            triangle[i][1] = rotatedPoint.y;
        }

        // Draw the rotated triangle
        g2d.setColor(Color.BLUE);
        g2d.drawPolygon(triangle[0], triangle[1], 3);
    }
}