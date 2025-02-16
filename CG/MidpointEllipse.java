
import javax.swing.*;
import java.awt.*;

public class MidpointEllipse extends JPanel {
    private int h; // x-coordinate of the center
    private int k; // y-coordinate of the center
    private int a; // semi-major axis length
    private int b; // semi-minor axis length

    public MidpointEllipse(int h, int k, int a, int b) {
        this.h = h;
        this.k = k;
        this.a = a;
        this.b = b;
        setBackground(Color.WHITE); // Set background color to white
    }

    // Midpoint ellipse drawing algorithm
    private void drawEllipse(Graphics g) {
        int x = 0;
        int y = b;
        int a2 = a * a;
        int b2 = b * b;
        int fx = 0;
        int fy = a2 * y;

        // Region 1
        int p1 = (int) (b2 - (a2 * b) + (0.25 * a2));
        while (fx < fy) {
            drawPoints(g, x, y);
            x++;
            fx += b2;
            if (p1 < 0) {
                p1 += b2 + fx;
            } else {
                y--;
                fy -= a2;
                p1 += b2 + fx - fy;
            }
        }

        // Region 2
        int p2 = (int) (b2 * (x + 0.5) * (x + 0.5) + a2 * (y - 1) * (y - 1) - a2 * b2);
        while (y > 0) {
            drawPoints(g, x, y);
            y--;
            fy -= a2;
            if (p2 > 0) {
                p2 += a2 - fy;
            } else {
                x++;
                fx += b2;
                p2 += a2 - fy + fx;
            }
        }
    }

    // Method to draw points for the ellipse
    private void drawPoints(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2)); // Increase stroke thickness
        g2d.drawLine(h + x, k + y, h + x, k + y);
        g2d.drawLine(h - x, k + y, h - x, k + y);
        g2d.drawLine(h + x, k - y, h + x, k - y);
        g2d.drawLine(h - x, k - y, h - x, k - y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the ellipse
        drawEllipse(g);
        
        // Draw the axes
        g.drawLine(h - 400, k, h + 400, k); // X-axis
        g.drawLine(h, k - 400, h, k + 400); // Y-axis
        
        // Draw diagonal lines through the center
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2)); // Increase stroke thickness for diagonal lines
       
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Midpoint Ellipse Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        // Center of ellipse (0, 0) and semi-major, semi-minor axes
        MidpointEllipse ellipse = new MidpointEllipse(400, 400, 200, 100);
        frame.add(ellipse);
        frame.setVisible(true);
    }
}
