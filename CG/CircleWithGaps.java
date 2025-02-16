import java.awt.*;
import javax.swing.*;

public class CircleWithGaps extends JPanel {
    private int h = 180, k = 180, r = 100;  // Center (h, k) and radius (r)

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCircleWithGaps(g, h, k, r);
    }

    // Midpoint Circle Drawing Algorithm with 8-fold symmetry and gaps on axes
    public void drawCircleWithGaps(Graphics g, int h, int k, int r) {
        int x = 0, y = r;
        int p = 1 - r;  // Initial decision parameter

        drawSymmetricPoints(g, h, k, x, y);

        while (x < y) {
            x++;
            if (p < 0) {
                p += 2 * x + 1;
            } else {
                y--;
                p += 2 * (x - y) + 1;
            }
            drawSymmetricPoints(g, h, k, x, y);
        }
    }

    // Draw 8 symmetric points with intentional gaps on the axes
    public void drawSymmetricPoints(Graphics g, int h, int k, int x, int y) {
        // Skip points on axes to create gaps
        if (x != 0) {
            g.drawLine(h + x, k + y, h + x, k + y);  // Octant 1
            g.drawLine(h - x, k + y, h - x, k + y);  // Octant 2
            g.drawLine(h + x, k - y, h + x, k - y);  // Octant 8
            g.drawLine(h - x, k - y, h - x, k - y);  // Octant 7
        }
        if (y != 0) {
            g.drawLine(h + y, k + x, h + y, k + x);  // Octant 3
            g.drawLine(h - y, k + x, h - y, k + x);  // Octant 4
            g.drawLine(h + y, k - x, h + y, k - x);  // Octant 6
            g.drawLine(h - y, k - x, h - y, k - x);  // Octant 5
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("8-Fold Symmetry Circle with Gaps");
        CircleWithGaps panel = new CircleWithGaps();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
