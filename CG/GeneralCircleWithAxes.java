import java.awt.*;
import java.util.Scanner;

public class CircleWithGaps extends Frame {
    private int h, k, r;

    public CircleWithGaps(int h, int k, int r) {
        this.h = h;
        this.k = k;
        this.r = r;
        
        // Set up the frame (window)
        setTitle("Circle with Gaps");
        setSize(800, 800); // Window size
        setVisible(true);   // Make the window visible
    }

    // Function to draw the grid and the circle with gaps
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Draw the coordinate axes (centered in the middle of the window)
        g.setColor(Color.BLACK);
        g.drawLine(400, 0, 400, 800); // Y-axis
        g.drawLine(0, 400, 800, 400); // X-axis
        
        // Apply general circle drawing algorithm to plot the circle with gaps
        drawCircleWithGaps(g, h, k, r);
    }

    // General circle drawing algorithm with gaps
    private void drawCircleWithGaps(Graphics g, int h, int k, int r) {
        g.setColor(Color.RED); // Color of the circle
        
        // Adjust for grid scaling and center the coordinates
        int centerX = h * 20 + 400;
        int centerY = 400 - k * 20;
        
        // Draw the four lines intersecting at the center
        g.drawLine(centerX - r * 20, centerY, centerX + r * 20, centerY); // Horizontal line
        g.drawLine(centerX, centerY - r * 20, centerX, centerY + r * 20); // Vertical line

        // Calculate points around the circle and draw gaps
        for (int angle = 0; angle < 360; angle += 45) { // Draw every 45 degrees
            double rad = Math.toRadians(angle);
            int x = (int) (r * Math.cos(rad));
            int y = (int) (r * Math.sin(rad));
            
            // Draw points with gaps (only drawing points at certain angles)
            if (angle % 90 != 0) { // Skip every 90 degrees for the gaps
                g.drawOval(centerX + x - 9, centerY - y -9, 4, 4); // Small circle for each point
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input the center and radius of the circle
        System.out.println("Enter the x-coordinate (h):");
        int h = scanner.nextInt();
        System.out.println("Enter the y-coordinate (k):");
        int k = scanner.nextInt();
        System.out.println("Enter the radius (r):");
        int r = scanner.nextInt();
        
        // Create a window and draw the circle with gaps
        new CircleWithGaps(h, k, r);
    }
}
