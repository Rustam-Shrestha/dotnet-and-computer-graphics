

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SutherlandHodgmanClipping extends JPanel {

    private List<Point> window;   // Window coordinates
    private List<Point> polygon;  // Polygon coordinates
    private List<Point> clippedPolygon;

    public SutherlandHodgmanClipping() {
        // Updated window coordinates (20, 20) to (90, 90)
        window = List.of(new Point(20, 20), new Point(90, 20), new Point(90, 90), new Point(20, 90));

        // Updated polygon vertices
        polygon = List.of(new Point(10, 40), new Point(50, 100), new Point(60, 70), new Point(100, 50));

        // Apply Sutherland-Hodgman algorithm to clip the polygon
        clippedPolygon = sutherlandHodgman(polygon, window);
    }

    // Check if a point is inside the edge
    private boolean inside(Point p, Line edge) {
        return (edge.getP2().x - edge.getP1().x) * (p.y - edge.getP1().y) - 
               (edge.getP2().y - edge.getP1().y) * (p.x - edge.getP1().x) >= 0;
    }

    // Calculate intersection point between edge and line segment
    private Point intersection(Point p1, Point p2, Line edge) {
        double x1 = p1.x, y1 = p1.y;
        double x2 = p2.x, y2 = p2.y;
        double x3 = edge.getP1().x, y3 = edge.getP1().y;
        double x4 = edge.getP2().x, y4 = edge.getP2().y;

        double denom = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (denom == 0) return null; // Parallel lines

        double px = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / denom;
        double py = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / denom;
        return new Point((int) px, (int) py);
    }

    // Sutherland-Hodgman clipping algorithm
    private List<Point> sutherlandHodgman(List<Point> polygon, List<Point> window) {
        List<Point> output = polygon;
        for (int i = 0; i < window.size(); i++) {
            List<Point> input = output;
            output = new ArrayList<>();
            Line edge = new Line(window.get(i), window.get((i + 1) % window.size()));

            if (input.isEmpty()) break;

            Point prevPoint = input.get(input.size() - 1);
            for (Point currPoint : input) {
                if (inside(currPoint, edge)) {
                    if (!inside(prevPoint, edge)) {
                        Point intersect = intersection(prevPoint, currPoint, edge);
                        if (intersect != null) {
                            output.add(intersect); // Add intersection point
                        }
                    }
                    output.add(currPoint); // Add current point
                } else if (inside(prevPoint, edge)) {
                    Point intersect = intersection(prevPoint, currPoint, edge);
                    if (intersect != null) {
                        output.add(intersect); // Add intersection point
                    }
                }
                prevPoint = currPoint;
            }
        }
        return output;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the coordinate systems (Left and Right)
        drawGraph(g2d, 100, 100); // Left graph
        drawGraph(g2d, 400, 100); // Right graph

        // Draw the clipping window in both graphs
        g2d.setColor(Color.LIGHT_GRAY);
        drawWindow(g2d, 100, 100);  // Left window
        drawWindow(g2d, 400, 100);  // Right window

        // Draw the original polygon in the left graph
        g2d.setColor(Color.RED);
        drawPolygon(g2d, polygon, 100, 100);

        // Draw the clipped polygon in the right graph
        g2d.setColor(Color.RED);
        if (!clippedPolygon.isEmpty()) {
            drawPolygon(g2d, clippedPolygon, 400, 100);
        }
    }

    // Draw the graph with coordinate axes
    private void drawGraph(Graphics2D g2d, int xOffset, int yOffset) {
        g2d.setColor(Color.BLACK);
        g2d.drawLine(xOffset - 100, yOffset, xOffset + 100, yOffset); // X-axis
        g2d.drawLine(xOffset, yOffset - 100, xOffset, yOffset + 100); // Y-axis
    }

    // Draw the window (square) on the graph
    private void drawWindow(Graphics2D g2d, int xOffset, int yOffset) {
        int[] xPoints = {20, 90, 90, 20};
        int[] yPoints = {20, 20, 90, 90};
        for (int i = 0; i < xPoints.length; i++) {
            xPoints[i] += xOffset;
            yPoints[i] += yOffset;
        }
        g2d.drawPolygon(xPoints, yPoints, 4);
    }

    // Draw a polygon on the graph
    private void drawPolygon(Graphics2D g2d, List<Point> polygon, int xOffset, int yOffset) {
        if (polygon.isEmpty()) return;

        int[] xPoints = new int[polygon.size()];
        int[] yPoints = new int[polygon.size()];

        for (int i = 0; i < polygon.size(); i++) {
            xPoints[i] = polygon.get(i).x + xOffset;
            yPoints[i] = polygon.get(i).y + yOffset;
        }

        g2d.drawPolygon(xPoints, yPoints, polygon.size());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sutherland-Hodgman Clipping");
        SutherlandHodgmanClipping panel = new SutherlandHodgmanClipping();
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class Line {
    private Point p1, p2;

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }
}
