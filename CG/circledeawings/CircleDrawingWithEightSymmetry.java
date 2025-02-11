import java.awt.*;
import java.util.Scanner;

public class CircleDrawingWithFourSymmetry extends Frame {

    private int h, k, r;

    public CircleDrawingWithFourSymmetry(int h, int k, int r) {
        this.h = h;
        this.k = k;
        this.r = r;

        // Set up the frame (window)
        setTitle("Circle Drawing with 4-Way Symmetry");
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

        // Apply the circle drawing algorithm with 4-way symmetry
        drawCircle(g, h, k, r);
    }

    // Circle drawing algorithm using 4-way symmetry and the general circle equation
    private void drawCircle(Graphics g, int h, int k, int r) {
        g.setColor(Color.RED); // Color of the dots

        // Adjust for grid scaling and center the coordinates
        h = h * 20 + 400;
        k = 400 - k * 20;

        // Start iterating over x from 0 to r
        for (int x = 0; x <= r; x++) {
            // Calculate the corresponding y for each x using the circle equation: y = sqrt(r^2 - x^2)
            int y = (int) Math.round(Math.sqrt(r * r - x * x));

            // Plot the points using 4-way symmetry
            drawDot(g, h + x, k + y); // 1st Quadrant
            drawDot(g, h - x, k + y); // 2nd Quadrant
            drawDot(g, h + x, k - y); // 4th Quadrant
            drawDot(g, h - x, k - y); // 3rd Quadrant
        }

        // Draw the horizontal and vertical lines through the center
        g.drawLine(h - r, k, h + r, k); // Horizontal line
        g.drawLine(h, k - r, h, k + r); // Vertical line
    }

    // Function to draw a dot at a given point
    private void drawDot(Graphics g, int x, int y) {
        if (x >= 0 && x <= 800 && y >= 0 && y <= 800) { // Check if within bounds
            g.fillOval(x - 2, y - 2, 2, 2); // Small dot
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
        new CircleDrawingWithFourSymmetry(h, k, r);
    }
}
