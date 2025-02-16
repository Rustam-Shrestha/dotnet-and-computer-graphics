import java.awt.*;
import javax.swing.*;

public class GeneralCircleEquation extends JPanel {
    private int h = 190, k = 180, r = 100;  // Center (h, k) and radius (r)

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCircleUsingGeneralEquation(g, h, k, r);
        drawCrossLines(g, h, k);
    }

    // Circle Drawing using General Equation of Circle (x^2 + y^2 = r^2)
    public void drawCircleUsingGeneralEquation(Graphics g, int h, int k, int r) {
        for (int x = 0; x <= r; x++) {
            // Calculate y using the general circle equation
            int y = (int) Math.sqrt(r * r - x * x);

            // Draw points using 4-fold symmetry (reflecting over x-axis and y-axis)
            drawSymmetricPoints(g, h, k, x, y);
        }
    }

    // Draw 4 symmetric points
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
        JFrame frame = new JFrame("4-Fold Symmetry Circle Using General Equation");
        GeneralCircleEquation panel = new GeneralCircleEquation();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
