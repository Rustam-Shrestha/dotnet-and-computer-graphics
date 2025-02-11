package done;
import java.awt.*;
import java.util.Scanner;

public class MidpointCircleDrawing extends Frame {

    private int h, k, r;

    public MidpointCircleDrawing(int h, int k, int r) {
        this.h = h;
        this.k = k;
        this.r = r;

        // Set up the frame (window)
        setTitle("Midpoint Circle Drawing with Symmetrical Lines");
        setSize(800, 800); // Window size
        setVisible(true);   // Make the window visible
    }

    // Function to draw the grid and plot the circle
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw the coordinate axes (centered in the middle of the window)
        g.setColor(Color.BLACK);
        g.drawLine(400, 0, 400, 800); // Y-axis
        g.drawLine(0, 400, 800, 400); // X-axis

        // Apply the midpoint circle drawing algorithm
        drawCircle(g, h, k, r);
    }

    // Midpoint circle drawing algorithm using 8-way symmetry
    private void drawCircle(Graphics g, int h, int k, int r) {
        g.setColor(Color.RED); // Color of the dots

        // Adjust for grid scaling and center the coordinates
        h = h * 20 + 400;
        k = 400 - k * 20;

        int x = 0;
        int y = r;
        int d = 1 - r; // Initial decision parameter

        // Draw the initial points based on the decision parameter
        drawCirclePoints(g, h, k, x, y);
        drawSymmetryLines(g, h, k, r); // Draw symmetry lines

        while (x < y) {
            x++;
            if (d < 0) {
                d += 2 * x + 1; // Move to next point in the same row
            } else {
                y--;
                d += 2 * x - 2 * y + 1; // Move to next point in the next row
            }
            drawCirclePoints(g, h, k, x, y);
        }
    }

    // Function to draw points based on symmetry
    private void drawCirclePoints(Graphics g, int h, int k, int x, int y) {
        // Drawing points in all 8 symmetrical positions
        if (x % 6 == 0) {
            drawDot(g, h + x, k + y); // 1st Quadrant
            drawDot(g, h - x, k + y); // 2nd Quadrant
            drawDot(g, h + x, k - y); // 4th Quadrant
            drawDot(g, h - x, k - y); // 3rd Quadrant
            drawDot(g, h + y, k + x); // 1st Quadrant (diagonal)
            drawDot(g, h - y, k + x); // 2nd Quadrant (diagonal)
            drawDot(g, h + y, k - x); // 4th Quadrant (diagonal)
            drawDot(g, h - y, k - x); // 3rd Quadrant (diagonal)
        }
    }

    // Function to draw symmetry lines
    private void drawSymmetryLines(Graphics g, int h, int k, int r) {
        g.setColor(Color.BLUE); // Color of the symmetry lines
        // 4-way symmetry lines
        g.drawLine(h - r, k, h + r, k); // Horizontal line
        g.drawLine(h, k - r, h, k + r); // Vertical line

        // 8-way symmetry lines (diagonal lines)
        g.drawLine(h - r, k - r, h + r, k + r); // Diagonal from top-left to bottom-right
        g.drawLine(h - r, k + r, h + r, k - r); // Diagonal from bottom-left to top-right
    }

    // Function to draw a dot at a given point
    private void drawDot(Graphics g, int x, int y) {
        if (x >= 0 && x <= 800 && y >= 0 && y <= 800) { // Check if within bounds
            g.fillOval(x - 2, y - 2, 4, 4); // Small dot
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the center coordinates and radius for the circle
        System.out.println("Enter the center x-coordinate (h):");
        int h = scanner.nextInt();
        System.out.println("Enter the center y-coordinate (k):");
        int k = scanner.nextInt();
        System.out.println("Enter the radius (r):");
        int r = scanner.nextInt();

        // Create a window and draw the circle
        new MidpointCircleDrawing(h, k, r);
    }
}
