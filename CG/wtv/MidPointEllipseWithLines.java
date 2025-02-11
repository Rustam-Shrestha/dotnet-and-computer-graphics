import javax.swing.*;
import java.awt.*;

public class MidPointEllipseWithLines extends JPanel {

    private int rx, ry, xc, yc;
    private int scaleFactor = 20; // Adjust this as needed

    public MidPointEllipseWithLines(int rx, int ry, int xc, int yc) {
        this.rx = rx;
        this.ry = ry;
        this.xc = xc;
        this.yc = yc;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawEllipse(g, rx, ry, xc, yc);
        drawCrossLines(g, xc, yc);
        drawDiagonalCross(g, xc, yc);
    }

    private void drawEllipse(Graphics g, int rx, int ry, int xc, int yc) {
        float dx, dy, d1, d2, x, y;
        x = 0;
        y = ry;

        // Initial decision parameter of region 1
        d1 = (ry * ry) - (rx * rx * ry) + (0.25f * rx * rx);
        dx = 2 * ry * ry * x;
        dy = 2 * rx * rx * y;

        // For region 1
        while (dx < dy) {
            drawDot(g, x, y, xc, yc);

            // Update decision parameter
            if (d1 < 0) {
                x++;
                dx += (2 * ry * ry);
                d1 += dx + (ry * ry);
            } else {
                x++;
                y--;
                dx += (2 * ry * ry);
                dy -= (2 * rx * rx);
                d1 += dx - dy + (ry * ry);
            }
        }

        // Decision parameter of region 2
        d2 = (float) ((ry * ry) * ((x + 0.5) * (x + 0.5)) + (rx * rx) * ((y - 1) * (y - 1)) - (rx * rx * ry * ry));

        // Plotting points of region 2
        while (y >= 0) {
            drawDot(g, x, y, xc, yc);

            // Update decision parameter
            if (d2 > 0) {
                y--;
                dy -= (2 * rx * rx);
                d2 += (rx * rx) - dy;
            } else {
                y--;
                x++;
                dx += (2 * ry * ry);
                dy -= (2 * rx * rx);
                d2 += dx - dy + (rx * rx);
            }
        }
    }

    private void drawDot(Graphics g, float x, float y, int xc, int yc) {
        g.fillRect(xc + (int) x * 10, yc - (int) y * 10, 2, 2); // Scale by 10
        g.fillRect(xc - (int) x * 10, yc - (int) y * 10, 2, 2);
        g.fillRect(xc + (int) x * 10, yc + (int) y * 10, 2, 2);
        g.fillRect(xc - (int) x * 10, yc + (int) y * 10, 2, 2);
    }

    private void drawCrossLines(Graphics g, int xc, int yc) {
        if (g == null) {
            System.out.println("Graphics object is null");
            return;
        }
    
        g.setColor(Color.BLUE);
    
        // Assuming scaleFactor is defined and initialized
        int scaleFactor = 1; // Replace with actual scaleFactor value
    
        // Calculate the center point in pixels
        int centerX = xc * scaleFactor;
        int centerY = yc * scaleFactor;
    
        // Draw horizontal line through the center of the ellipse
        g.drawLine(0, centerY, getWidth(), centerY); // Horizontal line
    
        // Draw vertical line through the center of the ellipse
        g.drawLine(centerX, 0, centerX, getHeight()); // Vertical line
    }
    

    private void drawDiagonalCross(Graphics g, int centerX, int centerY) {
        g.setColor(Color.RED); // Change color if desired
    
        // Large offset to simulate infinity
        int offset = 10000; // Adjust this value as needed
    
        // Draw diagonal lines that appear infinite
        g.drawLine(centerX - offset, centerY - offset, centerX + offset, centerY + offset); // Top-left to bottom-right
        g.drawLine(centerX - offset, centerY + offset, centerX + offset, centerY - offset); // Bottom-left to top-right
    }
    
    

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mid-Point Ellipse Drawing with Dots and Cross Lines");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        // Ellipse parameters
        int rx = 15, ry = 10, xc = 250, yc = 250; // Example values, adjust as needed

        MidPointEllipseWithLines ellipse = new MidPointEllipseWithLines(rx, ry, xc, yc);
        frame.add(ellipse);
        frame.setVisible(true);
    }
}
