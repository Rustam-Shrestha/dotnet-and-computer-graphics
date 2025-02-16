import java.awt.*;
import java.util.Scanner;

public class Sergio extends Frame {

    private int h, k, r;

    // Constructor to set up the window and circle details
    public Sergio(int h, int k, int r) {
        this.h = h;
        this.k = k;
        this.r = r;

        // Set up window properties
        setTitle("General Circle Drawing with 8-Way Symmetry (Thicker Stroke)");
        setSize(800, 800);
        setVisible(true);

        // Close window when the close button is clicked
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent winEvt) {
                System.exit(0);
            }
        });
    }

    // Function to draw the circle and coordinate axes
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw X and Y axes (centered in the window)
        g.setColor(Color.BLACK);
        g.drawLine(400, 0, 400, 800); // Y-axis
        g.drawLine(0, 400, 800, 400); // X-axis

        // Draw circle using the general equation with dynamic center shift
        drawCircle(g, h, k, r);
    }

    // Function to draw circle using general equation with 8-way symmetry and thicker stroke
    private void drawCircle(Graphics g, int h, int k, int r) {
        g.setColor(Color.RED);

        // Adjust coordinates to center the grid (scale by a smaller factor)
        int baseCenterX = h * 20 + 400;
        int baseCenterY = 400 - k * 20;

        for (int x = 0; x <= r; x++) {
            // Calculate y using the general circle equation
            int y = (int) Math.round(Math.sqrt(r * r - x * x));

            // Leave gaps on top, bottom, left, and right
            if (x == r || y == 0) continue;

            // Plot points using 8-way symmetry with dynamic center shift
            plotSymmetricPoints(g, baseCenterX, baseCenterY, x, y);
        }
    }

    // Function to plot points in all 8 octants with dynamic center shift
    private void plotSymmetricPoints(Graphics g, int baseCenterX, int baseCenterY, int x, int y) {
        // Dynamic shifts for each quadrant (increased shift by 4)
        int shiftX, shiftY;

        // Top-right quadrant (Octants 1 and 5)
        shiftX = 4;
        shiftY = 4;
        drawThickPoint(g, baseCenterX + x + shiftX, baseCenterY + y + shiftY); // Octant 1
        drawThickPoint(g, baseCenterX + y + shiftX, baseCenterY + x + shiftY); // Octant 5

        // Bottom-right quadrant (Octants 4 and 8)
        shiftX = 4;
        shiftY = -4;
        drawThickPoint(g, baseCenterX + x + shiftX, baseCenterY - y + shiftY); // Octant 4
        drawThickPoint(g, baseCenterX + y + shiftX, baseCenterY - x + shiftY); // Octant 8

        // Bottom-left quadrant (Octants 3 and 7)
        shiftX = -4;
        shiftY = -4;
        drawThickPoint(g, baseCenterX - x + shiftX, baseCenterY - y + shiftY); // Octant 3
        drawThickPoint(g, baseCenterX - y + shiftX, baseCenterY - x + shiftY); // Octant 7

        // Top-left quadrant (Octants 2 and 6)
        shiftX = -4;
        shiftY = 4;
        drawThickPoint(g, baseCenterX - x + shiftX, baseCenterY + y + shiftY); // Octant 2
        drawThickPoint(g, baseCenterX - y + shiftX, baseCenterY + x + shiftY); // Octant 6
    }

    // Function to draw a thicker point by drawing a 2x2 filled rectangle
    private void drawThickPoint(Graphics g, int x, int y) {
        g.fillRect(x - 1, y - 1, 1, 1); // Draw a 2x2 rectangle for thickness
    }

    // Main function to get input from the user and create the window
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for circle center and radius
        System.out.println("Enter the center x-coordinate (h):");
        int h = scanner.nextInt();
        System.out.println("Enter the center y-coordinate (k):");
        int k = scanner.nextInt();
        System.out.println("Enter the radius (r):");
        int r = scanner.nextInt();

        // Create a window and draw the circle
        new Sergio(h, k, r);
    }
}
