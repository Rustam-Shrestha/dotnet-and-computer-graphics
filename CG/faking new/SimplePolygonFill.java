import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimplePolygonFill extends JPanel {
    private int[] xPoints = {100, 150, 200, 250, 200, 150};  // Polygon X-coordinates
    private int[] yPoints = {200, 150, 100, 150, 250, 250};  // Polygon Y-coordinates
    private int nPoints = xPoints.length;                    // Number of points in the polygon

    public SimplePolygonFill() {
        setPreferredSize(new Dimension(400, 400));  // Panel size
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);  // Polygon border color
        g2d.drawPolygon(xPoints, yPoints, nPoints);  // Draw polygon

        // Call the scanline fill algorithm
        scanlineFill(g2d, xPoints, yPoints, nPoints, Color.GREEN);  // Fill polygon with green
    }

    // Simple scanline polygon filling algorithm
    private void scanlineFill(Graphics2D g, int[] x, int[] y, int n, Color fillColor) {
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        // Find the minimum and maximum Y values
        for (int i = 0; i < n; i++) {
            if (y[i] < minY) minY = y[i];
            if (y[i] > maxY) maxY = y[i];
        }

        g.setColor(fillColor);

        for (int scanY = minY; scanY <= maxY; scanY++) {
            List<Integer> intersections = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int x1 = x[i], y1 = y[i], x2 = x[(i + 1) % n], y2 = y[(i + 1) % n];

                // Check if the scanline intersects the polygon edge
                if ((y1 < scanY && y2 >= scanY) || (y2 < scanY && y1 >= scanY)) {
                    int intersectX = x1 + (scanY - y1) * (x2 - x1) / (y2 - y1);
                    intersections.add(intersectX);
                }
            }

            // Sort intersections and fill between pairs
            Collections.sort(intersections);
            for (int i = 0; i < intersections.size() - 1; i += 2) {
                g.drawLine(intersections.get(i), scanY, intersections.get(i + 1), scanY);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Polygon Fill Example");
        frame.add(new SimplePolygonFill());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
