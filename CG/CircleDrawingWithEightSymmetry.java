import java.awt.*;
import java.util.Scanner;

public class CircleDrawingWithEightSymmetry extends Frame {

    private int h, k, r;

    public CircleDrawingWithEightSymmetry(int h, int k, int r) {
        this.h = h;
        this.k = k;
        this.r = r;

        // Set up the frame (window)
        setTitle("Circle Drawing with 8-Way Symmetry");
        setSize(1350, 768); // Window size
        setVisible(true);   // Make the window visible

        // Close the window on clicking the close button
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent winEvt) {
                System.exit(0);
            }
        });
    }

    // Function to draw the grid and plot the circle
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Cast to Graphics2D for better control over the graphics
        Graphics2D g2d = (Graphics2D) g;

       

        // Draw the coordinate axes (centered in the middle of the window)
        g2d.setColor(Color.BLACK);
        g2d.drawLine(400, 0, 400, 800); // Y-axis
        g2d.drawLine(0, 400, 800, 400); // X-axis

        // Apply the circle drawing algorithm with 8-way symmetry
        drawCircle(g2d, h, k, r);
    }

    // Circle drawing algorithm using 8-way symmetry
    private void drawCircle(Graphics2D g2d, int h, int k, int r) {
        g2d.setColor(Color.RED); // Color of the lines

        // Adjust for grid scaling and center the coordinates
        h = h * 20 + 400;
        k = 400 - k * 20;

        // Start iterating over x from 0 to r
        for (int x = 0; x <= r; x++) {
            // Calculate the corresponding y for each x using the circle equation: y = sqrt(r^2 - x^2)
            int y = (int) Math.round(Math.sqrt(r * r - x * x));

            // Draw lines using 8-way symmetry
            g2d.drawLine(h + x, k + y, h + x, k + y); // 1st Quadrant
            g2d.drawLine(h - x, k + y, h - x, k + y); // 2nd Quadrant
            g2d.drawLine(h + x, k - y, h + x, k - y); // 4th Quadrant
            g2d.drawLine(h - x, k - y, h - x, k - y); // 3rd Quadrant

            // Diagonal symmetry points
            g2d.drawLine(h + y, k + x, h + y, k + x); // 1st Quadrant (diagonal)
            g2d.drawLine(h - y, k + x, h - y, k + x); // 2nd Quadrant (diagonal)
            g2d.drawLine(h + y, k - x, h + y, k - x); // 4th Quadrant (diagonal)
            g2d.drawLine(h - y, k - x, h - y, k - x); // 3rd Quadrant (diagonal)
        }

        // Draw the horizontal and vertical lines through the center
        g2d.drawLine(h - r, k, h + r, k); // Horizontal line
        g2d.drawLine(h, k - r, h, k + r); // Vertical line

        // Draw diagonal lines through the center
        g2d.drawLine(h - r, k - r, h + r, k + r); // Diagonal line (top-left to bottom-right)
        g2d.drawLine(h - r, k + r, h + r, k - r); // Diagonal line (bottom-left to top-right)
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
        new CircleDrawingWithEightSymmetry(h, k, r);
    }
}
