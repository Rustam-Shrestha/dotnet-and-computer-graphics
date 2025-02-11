import java.awt.*;
import java.util.Scanner;

public class MidpointEllipseDrawing extends Frame {

    private int h, k, rx, ry;

    public MidpointEllipseDrawing(int h, int k, int rx, int ry) {
        this.h = h;
        this.k = k;
        this.rx = rx;
        this.ry = ry;

        // Set up the frame (window)
        setTitle("Midpoint Ellipse Drawing with 8-Way Symmetry and Radius Lines");
        setSize(800, 800); // Window size
        setVisible(true);   // Make the window visible
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw the coordinate axes (centered in the middle of the window)
        g.setColor(Color.BLACK);
        g.drawLine(400, 0, 400, 800); // Y-axis
        g.drawLine(0, 400, 800, 400); // X-axis

        // Draw the ellipse and 4 lines that pass through the radius
        drawEllipse(g, h, k, rx, ry);
        drawRadiusLines(g, h, k, rx, ry);
    }

    // Midpoint ellipse drawing algorithm using 8-way symmetry
    private void drawEllipse(Graphics g, int h, int k, int rx, int ry) {
        g.setColor(Color.RED); // Color of the ellipse

        // Adjust for grid scaling and center the coordinates
        h = h * 20 + 400;
        k = 400 - k * 20;

        int x = 0;
        int y = ry;

        int rx2 = rx * rx;
        int ry2 = ry * ry;
        int twoRx2 = 2 * rx2;
        int twoRy2 = 2 * ry2;

        // Region 1 (when the slope is < 1)
        int p1 = ry2 - rx2 * ry + (rx2 / 4); // Initial decision parameter for region 1

        int dx = 2 * ry2 * x;
        int dy = 2 * rx2 * y;

        // Plot points for Region 1
        while (dx < dy) {
            drawEllipsePoints(g, h, k, x, y);

            x++;
            dx += twoRy2;

            if (p1 < 0) {
                p1 += dx + ry2;
            } else {
                y--;
                dy -= twoRx2;
                p1 += dx - dy + ry2;
            }
        }

        // Region 2 (when the slope is >= 1)
        int p2 = (int) (ry2 * (x + 0.5) * (x + 0.5) + rx2 * (y - 1) * (y - 1) - rx2 * ry2);

        // Plot points for Region 2
        while (y > 0) {
            drawEllipsePoints(g, h, k, x, y);

            y--;
            dy -= twoRx2;

            if (p2 > 0) {
                p2 += rx2 - dy;
            } else {
                x++;
                dx += twoRy2;
                p2 += dx - dy + rx2;
            }
        }
    }

    // Function to draw points based on 8-way symmetry
    private void drawEllipsePoints(Graphics g, int h, int k, int x, int y) {
        // Draw points in all 8 symmetrical positions
        if (x % 6 == 0) { // Reduce point density by only drawing every 6th point
            g.fillOval(h + x - 2, k + y - 2, 4, 4); // Quadrant 1
            g.fillOval(h - x - 2, k + y - 2, 4, 4); // Quadrant 2
            g.fillOval(h + x - 2, k - y - 2, 4, 4); // Quadrant 4
            g.fillOval(h - x - 2, k - y - 2, 4, 4); // Quadrant 3

            g.fillOval(h + y - 2, k + x - 2, 4, 4); // Reflect for other symmetry lines
            g.fillOval(h - y - 2, k + x - 2, 4, 4);
            g.fillOval(h + y - 2, k - x - 2, 4, 4);
            g.fillOval(h - y - 2, k - x - 2, 4, 4);
        }
    }

    // Draw four lines passing through the radius of the ellipse
    private void drawRadiusLines(Graphics g, int h, int k, int rx, int ry) {
        g.setColor(Color.BLUE); // Color for the radius lines

        // Draw horizontal line (major axis)
        g.drawLine(h - rx * 20, k, h + rx * 20, k);

        // Draw vertical line (minor axis)
        g.drawLine(h, k - ry * 20, h, k + ry * 20);

        // Draw two diagonal lines (45-degree axes)
        g.drawLine(h - rx * 20, k - ry * 20, h + rx * 20, k + ry * 20);
        g.drawLine(h - rx * 20, k + ry * 20, h + rx * 20, k - ry * 20);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the center coordinates and axes lengths for the ellipse
        System.out.println("Enter the center x-coordinate (h):");
        int h = scanner.nextInt();
        System.out.println("Enter the center y-coordinate (k):");
        int k = scanner.nextInt();
        System.out.println("Enter the semi-major axis length (rx):");
        int rx = scanner.nextInt();
        System.out.println("Enter the semi-minor axis length (ry):");
        int ry = scanner.nextInt();

        // Create a window and draw the ellipse
        new MidpointEllipseDrawing(h, k, rx, ry);
    }
}
