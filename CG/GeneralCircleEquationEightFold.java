import java.awt.*;
import javax.swing.*;

public class GeneralCircleEquationEightFold extends JPanel {
    private int h = 190, k = 180, r = 100;  // Center (h, k) and radius (r)

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Cast Graphics to Graphics2D for more control
        Graphics2D g2 = (Graphics2D) g;
        
        // Set the stroke width (line thickness)
        g2.setStroke(new BasicStroke(2));  // Increase stroke width to 3
        
        // Draw the circle
        drawCircleUsingGeneralEquation(g2, h, k, r);
        
        // Draw four lines passing through the center (h, k)
        drawCrossLines(g2, h, k);
    }

    // Circle Drawing using General Equation of Circle (x^2 + y^2 = r^2)
    public void drawCircleUsingGeneralEquation(Graphics g, int h, int k, int r) {
        for (int x = 0; x <= r / Math.sqrt(2); x++) {
            // Calculate y using the general circle equation
            int y = (int) Math.sqrt(r * r - x * x);

            // Draw points using 8-fold symmetry (reflecting over all octants)
            drawSymmetricPoints(g, h, k, x, y);
        }
    }

    // Draw 8 symmetric points with thicker stroke
    public void drawSymmetricPoints(Graphics g, int h, int k, int x, int y) {
        // First quadrant (x, y), reflected over 8 octants
        g.drawLine(h + x, k + y, h + x, k + y);  // Octant 1
        g.drawLine(h - x, k + y, h - x, k + y);  // Octant 2
        g.drawLine(h + x, k - y, h + x, k - y);  // Octant 8
        g.drawLine(h - x, k - y, h - x, k - y);  // Octant 7

        // Reflect y and x to cover other octants
        g.drawLine(h + y, k + x, h + y, k + x);  // Octant 3
        g.drawLine(h - y, k + x, h - y, k + x);  // Octant 4
        g.drawLine(h + y, k - x, h + y, k - x);  // Octant 6
        g.drawLine(h - y, k - x, h - y, k - x);  // Octant 5
    }

    // Method to draw four lines passing through the center (h, k)
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
        JFrame frame = new JFrame("8-Fold Symmetry Circle with Cross Lines");
        GeneralCircleEquationEightFold panel = new GeneralCircleEquationEightFold();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
