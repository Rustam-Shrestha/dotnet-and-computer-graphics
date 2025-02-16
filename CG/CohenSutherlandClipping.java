import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import javax.swing.*;

public class CohenSutherlandClipping extends Frame {

    private static final int INSIDE = 0; // 0000
    private static final int LEFT = 1;   // 0001
    private static final int RIGHT = 2;  // 0010
    private static final int BOTTOM = 4; // 0100
    private static final int TOP = 8;    // 1000

    private static final int xMin = 4, yMin = 2, xMax = 10, yMax = 6;
    private static final int xVMin = 6, yVMin = 4, xVMax = 17, yVMax = 8;

    private double x0, y0, x1, y1;

    public CohenSutherlandClipping(double x0, double y0, double x1, double y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        setTitle("Cohen-Sutherland Line Clipping");
        // Set the size and position of the window to avoid overflow
        setSize(1200, 760);  // Reduce the size slightly
        setLocation(120, 120);  // Position the window lower and to the right
        setVisible(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent winEvt) {
                System.exit(0);
            }
        });
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawLine(1200 / 2, 0, 1200 / 2, 768);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        // Draw the window on the left half
        g2.setColor(Color.BLUE);
        g2.drawRect(xMin * 25, (10 - yMax) * 25, (xMax - xMin) * 25, (yMax - yMin) * 25);

        // Draw the viewport on the left half
        g2.setColor(Color.RED);
        g2.drawRect(xVMin * 25, (10 - yVMax) * 25, (xVMax - xVMin) * 25, (yVMax - yVMin) * 25);

        // Draw the original line on the left half
        g2.setColor(Color.BLACK);
        g2.draw(new Line2D.Double(x0 * 25, (10 - y0) * 25, x1 * 25, (10 - y1) * 25));

        // Clip the line
        double[] clippedLine = cohenSutherlandClip(x0, y0, x1, y1);
        if (clippedLine != null) {
            double cx0 = clippedLine[0], cy0 = clippedLine[1], cx1 = clippedLine[2], cy1 = clippedLine[3];
            g2.setColor(Color.GREEN);
            g2.draw(new Line2D.Double(cx0 * 25, (10 - cy0) * 25, cx1 * 25, (10 - cy1) * 25));

            // Map the clipped line to the viewport
            double[] mappedLine = mapToViewport(cx0, cy0, cx1, cy1);
            double mx0 = mappedLine[0], my0 = mappedLine[1], mx1 = mappedLine[2], my1 = mappedLine[3];

            // Draw the viewport on the right half
            g2.setColor(Color.RED);
            g2.drawRect(700 + xVMin * 25, (10 - yVMax) * 25, (xVMax - xVMin) * 25, (yVMax - yVMin) * 25);

            // Draw the mapped line on the right half
            g2.setColor(Color.MAGENTA);
            g2.draw(new Line2D.Double(700 + mx0 * 25, (10 - my0) * 25, 700 + mx1 * 25, (10 - my1) * 25));
        }
    }

    private int computeOutCode(double x, double y) {
        int code = INSIDE;
        if (x < xMin) code |= LEFT;
        else if (x > xMax) code |= RIGHT;
        if (y < yMin) code |= BOTTOM;
        else if (y > yMax) code |= TOP;
        return code;
    }

    private double[] cohenSutherlandClip(double x0, double y0, double x1, double y1) {
        int outcode0 = computeOutCode(x0, y0);
        int outcode1 = computeOutCode(x1, y1);
        boolean accept = false;

        while (true) {
            if ((outcode0 | outcode1) == 0) {
                accept = true;
                break;
            } else if ((outcode0 & outcode1) != 0) {
                break;
            } else {
                double x = 0, y = 0;
                int outcodeOut = (outcode0 != 0) ? outcode0 : outcode1;

                if ((outcodeOut & TOP) != 0) {
                    x = x0 + (x1 - x0) * (yMax - y0) / (y1 - y0);
                    y = yMax;
                } else if ((outcodeOut & BOTTOM) != 0) {
                    x = x0 + (x1 - x0) * (yMin - y0) / (y1 - y0);
                    y = yMin;
                } else if ((outcodeOut & RIGHT) != 0) {
                    y = y0 + (y1 - y0) * (xMax - x0) / (x1 - x0);
                    x = xMax;
                } else if ((outcodeOut & LEFT) != 0) {
                    y = y0 + (y1 - y0) * (xMin - x0) / (x1 - x0);
                    x = xMin;
                }

                if (outcodeOut == outcode0) {
                    x0 = x;
                    y0 = y;
                    outcode0 = computeOutCode(x0, y0);
                } else {
                    x1 = x;
                    y1 = y;
                    outcode1 = computeOutCode(x1, y1);
                }
            }
        }

        if (accept) {
            return new double[]{x0, y0, x1, y1};
        } else {
            return null;
        }
    }

    private double[] mapToViewport(double x0, double y0, double x1, double y1) {
        double sx = (xVMax - xVMin) / (double) (xMax - xMin);
        double sy = (yVMax - yVMin) / (double) (yMax - yMin);

        double vx0 = xVMin + (x0 - xMin) * sx;
        double vy0 = yVMin + (y0 - yMin) * sy;
        double vx1 = xVMin + (x1 - xMin) * sx;
        double vy1 = yVMin + (y1 - yMin) * sy;

        return new double[]{vx0, vy0, vx1, vy1};
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CohenSutherlandClipping(-1, -3, 7, 6));
    }
}
