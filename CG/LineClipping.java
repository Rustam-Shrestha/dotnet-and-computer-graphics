import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class LineClipping extends JPanel {

    // Window coordinates
    int winXmin = 2, winYmin = 3;
    int winXmax = 8, winYmax = 8;

    // Viewport coordinates
    int viewXmin = 4, viewYmin = 4;
    int viewXmax = 11, viewYmax = 10;

    // Line coordinates (start and end)
    double x1 = -1, y1 = -3, x2 = 7, y2 = 6;

    // Region codes for Cohen-Sutherland
    final int INSIDE = 0; // 0000
    final int LEFT = 1;   // 0001
    final int RIGHT = 2;  // 0010
    final int BOTTOM = 4; // 0100
    final int TOP = 8;    // 1000

    // Scaling factor for window and viewport
    int scale = 30;

    // Cohen-Sutherland clipping algorithm
    int computeRegionCode(double x, double y) {
        int code = INSIDE;

        if (x < winXmin)
            code |= LEFT;
        else if (x > winXmax)
            code |= RIGHT;
        if (y < winYmin)
            code |= BOTTOM;
        else if (y > winYmax)
            code |= TOP;

        return code;
    }

    void cohenSutherlandClip(Graphics2D g) {
        double x1Clipped = x1, y1Clipped = y1, x2Clipped = x2, y2Clipped = y2;

        int code1 = computeRegionCode(x1, y1);
        int code2 = computeRegionCode(x2, y2);

        boolean accept = false;

        while (true) {
            if ((code1 | code2) == 0) {
                // Both points inside the window
                accept = true;
                break;
            } else if ((code1 & code2) != 0) {
                // Both points share an outside region, trivially reject
                break;
            } else {
                // Some segment lies outside the window
                double x = 0, y = 0;

                int outcodeOut = (code1 != 0) ? code1 : code2;

                if ((outcodeOut & TOP) != 0) {
                    // point is above the window
                    x = x1 + (x2 - x1) * (winYmax - y1) / (y2 - y1);
                    y = winYmax;
                } else if ((outcodeOut & BOTTOM) != 0) {
                    // point is below the window
                    x = x1 + (x2 - x1) * (winYmin - y1) / (y2 - y1);
                    y = winYmin;
                } else if ((outcodeOut & RIGHT) != 0) {
                    // point is to the right of the window
                    y = y1 + (y2 - y1) * (winXmax - x1) / (x2 - x1);
                    x = winXmax;
                } else if ((outcodeOut & LEFT) != 0) {
                    // point is to the left of the window
                    y = y1 + (y2 - y1) * (winXmin - x1) / (x2 - x1);
                    x = winXmin;
                }

                if (outcodeOut == code1) {
                    x1Clipped = x;
                    y1Clipped = y;
                    code1 = computeRegionCode(x1Clipped, y1Clipped);
                } else {
                    x2Clipped = x;
                    y2Clipped = y;
                    code2 = computeRegionCode(x2Clipped, y2Clipped);
                }
            }
        }

        if (accept) {
            // Draw clipped line in the window
            drawLine(g, x1Clipped, y1Clipped, x2Clipped, y2Clipped, false);
        }
    }

    // Mapping the clipped line from window to viewport
    double[] mapToViewport(double x1Clipped, double y1Clipped, double x2Clipped, double y2Clipped) {
        double sx = (viewXmax - viewXmin) / (double) (winXmax - winXmin);
        double sy = (viewYmax - viewYmin) / (double) (winYmax - winYmin);

        double vx1 = viewXmin + (x1Clipped - winXmin) * sx;
        double vy1 = viewYmin + (y1Clipped - winYmin) * sy;

        double vx2 = viewXmin + (x2Clipped - winXmin) * sx;
        double vy2 = viewYmin + (y2Clipped - winYmin) * sy;

        return new double[]{vx1, vy1, vx2, vy2};
    }

    // Draw a line, either unclipped or clipped
    void drawLine(Graphics2D g, double x1, double y1, double x2, double y2, boolean unclipped) {
        if (unclipped) {
            g.setColor(Color.BLUE); // Unclipped line is blue
            g.draw(new Line2D.Double(50 + x1 * scale, 350 - y1 * scale, 50 + x2 * scale, 350 - y2 * scale));
        } else {
            g.setColor(Color.RED); // Clipped line in viewport is red
            g.draw(new Line2D.Double(450 + x1 * scale, 350 - y1 * scale, 450 + x2 * scale, 350 - y2 * scale));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Left: Show window, viewport, and unclipped line
        g2.setColor(Color.BLACK);
        g2.drawRect(50 + winXmin * scale, 350 - winYmax * scale, (winXmax - winXmin) * scale, (winYmax - winYmin) * scale); // Window
        g2.drawRect(250, 200, (viewXmax - viewXmin) * scale, (viewYmax - viewYmin) * scale); // Viewport
        g2.drawString("Window", 50, 380);
        g2.drawString("Viewport", 250, 380);
        
        // Unclipped line in the left window
        drawLine(g2, x1, y1, x2, y2, true);

        // Right: Show viewport with clipped and mapped line
        g2.setColor(Color.BLACK);
        g2.drawRect(450, 200, (viewXmax - viewXmin) * scale, (viewYmax - viewYmin) * scale); // Viewport for clipped line
        g2.drawString("Viewport (Clipped Line)", 450, 380);

        // Cohen-Sutherland clipping and drawing clipped line in viewport
        cohenSutherlandClip(g2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Line Clipping with Cohen-Sutherland Algorithm");
        LineClipping panel = new LineClipping();

        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
