import java.awt.*;
import java.util.Scanner;

public class DDALineDrawingWithDots extends Frame {

    private int x1, y1, x2, y2;

    public DDALineDrawingWithDots(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        // Set up the frame (window)
        setTitle("DDA Line Drawing with Dots");
        setSize(800, 800); // Window size
        setVisible(true);   // Make the window visible
    }

    // Function to draw the grid and plot the DDA line
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw the coordinate axes (centered in the middle of the window)
        g.setColor(Color.BLACK);
        g.drawLine(400, 0, 400, 800); // Y-axis
        g.drawLine(0, 400, 800, 400); // X-axis

        // Apply DDA line drawing algorithm to plot the line
        ddaLine(g, x1, y1, x2, y2);
    }

    // DDA algorithm to plot the line and mark points with dots
    private void ddaLine(Graphics g, int x1, int y1, int x2, int y2) {
        g.setColor(Color.RED); // Color of dots

        // Adjust for grid scaling
        x1 = x1 * 20 + 400;
        y1 = 400 - y1 * 20;
        x2 = x2 * 20 + 400;
        y2 = 400 - y2 * 20;

        // Calculate dx and dy
        int dx = x2 - x1;
        int dy = y2 - y1;

        // Calculate the number of steps needed
        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        // Calculate the increment in x and y for each step
        double xIncrement = (double) dx / steps;
        double yIncrement = (double) dy / steps;

        // Starting point
        double x = x1;
        double y = y1;

        // Plot each point on the line, but only at intervals
        for (int i = 0; i <= steps; i++) {
            // Draw a dot every few steps (e.g., every 3rd step)
            if (i % 4 == 0) {
                drawDot(g, (int) Math.round(x), (int) Math.round(y));
            }

            // Update x and y for the next step
            x += xIncrement;
            y += yIncrement;
        }
    }

    // Function to draw a dot at a given point
    private void drawDot(Graphics g, int x, int y) {
        if (x >= 0 && x <= 800 && y >= 0 && y <= 800) { // Check if within bounds
            g.fillOval(x - 2, y - 2, 4, 4); // Small dot
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the start and end points for the line
        System.out.println("Enter the starting x-coordinate:");
        int x1 = scanner.nextInt();
        System.out.println("Enter the starting y-coordinate:");
        int y1 = scanner.nextInt();
        System.out.println("Enter the ending x-coordinate:");
        int x2 = scanner.nextInt();
        System.out.println("Enter the ending y-coordinate:");
        int y2 = scanner.nextInt();

        // Create a window and draw the line with dots
        new DDALineDrawingWithDots(x1, y1, x2, y2);
    }
}
