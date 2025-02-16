
import java.awt.*;
import java.util.Scanner;

public class MidpointCircleDrawing extends Frame {
//initiation of variales 
    private int h, k, r;
//defining midpoint of the circle with center and rardius 
//it will draw the
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
        g.setColor(Color.RED); // Color of the lines

        // Adjust for grid scaling and center the coordinates
        h = h * 20 + 400;
        k = 400 - k * 20;

        int x = 0;
        int y = r;
        int d = 1 - r; // Initial decision parameter

        // Draw the initial points based on the decision parameter
        drawCircleLines(g, h, k, x, y);

        while (x < y) {
            x++;
            if (d < 0) {
                d += 2 * x + 1; // Move to next point in the same row
            } else {
                y--;
                d += 2 * x - 2 * y + 1; // Move to next point in the next row
            }
            drawCircleLines(g, h, k, x, y);
        }

        // Draw the horizontal and vertical lines through the center
        g.setColor(Color.BLUE); // Color of the center lines
        g.drawLine(h - r, k, h + r, k); // Horizontal line
        g.drawLine(h, k - r, h, k + r); // Vertical line

        // Draw diagonal lines through the center
        g.drawLine(h - r, k - r, h + r, k + r); // Diagonal line (top-left to bottom-right)
        g.drawLine(h - r, k + r, h + r, k - r); // Diagonal line (bottom-left to top-right)
    }

    // Function to draw lines based on symmetry
    private void drawCircleLines(Graphics g, int h, int k, int x, int y) {
        // Drawing lines in all 8 symmetrical positions
        drawLine(g, h + x, k + y, h + x, k + y); // 1st Quadrant
        drawLine(g, h - x, k + y, h - x, k + y); // 2nd Quadrant
        drawLine(g, h + x, k - y, h + x, k - y); // 4th Quadrant
        drawLine(g, h - x, k - y, h - x, k - y); // 3rd Quadrant
        drawLine(g, h + y, k + x, h + y, k + x); // 1st Quadrant (diagonal)
        drawLine(g, h - y, k + x, h - y, k + x); // 2nd Quadrant (diagonal)
        drawLine(g, h + y, k - x, h + y, k - x); // 4th Quadrant (diagonal)
        drawLine(g, h - y, k - x, h - y, k - x); // 3rd Quadrant (diagonal)
    }

    // Function to draw a line
    private void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2); // Draw a line
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
