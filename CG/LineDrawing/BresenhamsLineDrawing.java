import java.awt.*;
import java.util.Scanner;

public class BresenhamsLineDrawing extends Frame {

    private int x1, y1, x2, y2;

    public BresenhamsLineDrawing(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        // Set up the frame (window)
        setTitle("Bresenham's Line Drawing");
        setSize(800, 800); // Window size
        setVisible(true);   // Make the window visible
    }

    // Function to draw the grid and plot the Bresenham line
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw the coordinate axes (centered in the middle of the window)
        g.setColor(Color.BLACK);
        g.drawLine(400, 0, 400, 800); // Y-axis
        g.drawLine(0, 400, 800, 400); // X-axis

        // Apply Bresenham's line drawing algorithm to plot the line
        bresenhamLine(g, x1, y1, x2, y2);
    }

    // Bresenham's line drawing algorithm
    private void bresenhamLine(Graphics g, int x1, int y1, int x2, int y2) {
        g.setColor(Color.RED); // Color of the line

        // Adjust for grid scaling and center the coordinates
        x1 = x1 * 20 + 400;
        y1 = 400 - y1 * 20;
        x2 = x2 * 20 + 400;
        y2 = 400 - y2 * 20;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;

        int err = dx - dy;
        int counter=0;
        while (true) {
            if(counter%6==0){
            drawDot(g, x1, y1); // Draw the current point
        }
            if (x1 == x2 && y1 == y2) break;
            int err2 = err * 2;
            if (err2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (err2 < dx) {
                err += dx;
                y1 += sy;
            }
            counter++;
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

        // Create a window and draw the line
        new BresenhamsLineDrawing(x1, y1, x2, y2);
    }
}
