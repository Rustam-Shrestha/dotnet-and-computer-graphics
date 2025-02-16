import java.awt.*;
import javax.swing.*;

public class MidpointEllipseEquation extends JPanel {
    private int h = 190, k = 180;  // Center (h, k)
    private int a = 120, b = 80;   // Semi-major axis (a) and semi-minor axis (b)

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawEllipseUsingMidpointAlgorithm(g, h, k, a, b);
        drawCrossLines(g, h, k);  // Keeping the cross lines from your previous code
    }

    // Ellipse Drawing using Midpoint Algorithm
    public void drawEllipseUsingMidpointAlgorithm(Graphics g, int h, int k, int a, int b) {
        int x = 0, y = b;  // Start at the top of the ellipse
        double a2 = a * a;
        double b2 = b * b;

        // Region 1 decision parameter
        double d1 = b2 - (a2 * b) + (0.25 * a2);
        double dx = 2 * b2 * x;
        double dy = 2 * a2 * y;

        // Region 1: Initial region where slope of the curve is less than -1
        while (dx < dy) {
            // Draw 4 symmetric points in the ellipse
            drawSymmetricPoints(g, h, k, x, y);

            if (d1 < 0) {
                x++;
                dx = dx + 2 * b2;
                d1 = d1 + dx + b2;
            } else {
                x++;
                y--;
                dx = dx + 2 * b2;
                dy = dy - 2 * a2;
                d1 = d1 + dx - dy + b2;
            }
        }

        // Region 2 decision parameter
        double d2 = (b2) * ((x + 0.5) * (x + 0.5)) + (a2) * ((y - 1) * (y - 1)) - (a2 * b2);

        // Region 2: Final region where slope of the curve is greater than -1
        while (y >= 0) {
            // Draw 4 symmetric points in the ellipse
            drawSymmetricPoints(g, h, k, x, y);

            if (d2 > 0) {
                y--;
                dy = dy - 2 * a2;
                d2 = d2 + a2 - dy;
            } else {
                y--;
                x++;
                dx = dx + 2 * b2;
                dy = dy - 2 * a2;
                d2 = d2 + dx - dy + a2;
            }
        }
    }

    // Draw 4 symmetric points for an ellipse
    public void drawSymmetricPoints(Graphics g, int h, int k, int x, int y) {
        g.drawLine(h + x, k + y, h + x, k + y);  // Quadrant 1
        g.drawLine(h - x, k + y, h - x, k + y);  // Quadrant 2
        g.drawLine(h + x, k - y, h + x, k - y);  // Quadrant 4
        g.drawLine(h - x, k - y, h - x, k - y);  // Quadrant 3
    }

    public void drawCrossLines(Graphics g, int h, int k) {
        // Get the dimensions of the panel to extend the lines
        int width = getWidth();
        int height = getHeight();

        // Draw horizontal and vertical lines passing through the center (h, k)
        g.drawLine(h, 0, h, height);  // Vertical line through (h, k)
        g.drawLine(0, k, width, k);   // Horizontal line through (h, k)

        // Draw diagonal lines passing through the center (h, k)
        g.drawLine(0, 0, width, height);        // Diagonal from top-left to bottom-right
        g.drawLine(0, height, width, 0);        // Diagonal from bottom-left to top-right
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Midpoint Ellipse Drawing Algorithm");
        MidpointEllipseEquation panel = new MidpointEllipseEquation();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
