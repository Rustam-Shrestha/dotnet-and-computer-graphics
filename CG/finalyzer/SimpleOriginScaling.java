package done;
import java.awt.*;
import java.util.Scanner;

public class SimpleOriginScaling extends Frame {
    private int[][] points;
    private int[][] scaledPoints;
    private double sx, sy; // Scaling factors

    public SimpleOriginScaling() {
        Scanner s = new Scanner(System.in);

        // Get the number of coordinates from the user
        System.out.println("Enter the number of coordinates:");
        int numPoints = s.nextInt();
        points = new int[numPoints][2]; // Original coordinates

        // Get the coordinates from the user
        for (int i = 0; i < numPoints; i++) {
            System.out.println("Enter x" + (i + 1) + ":");
            points[i][0] = s.nextInt();
            System.out.println("Enter y" + (i + 1) + ":");
            points[i][1] = s.nextInt();
        }

        // Get the scaling factors from the user
        System.out.println("Enter scaling factor sx:");
        sx = s.nextDouble();
        System.out.println("Enter scaling factor sy:");
        sy = s.nextDouble();

        // Apply scaling to the points with respect to the origin
        scaledPoints = new int[points.length][2];
        for (int i = 0; i < points.length; i++) {
            scaledPoints[i][0] = (int) (points[i][0] * sx);
            scaledPoints[i][1] = (int) (points[i][1] * sy);
        }

        // Print the original and scaled points
        System.out.println("Original Points:");
        for (int[] point : points) {
            System.out.println("(" + point[0] + ", " + point[1] + ")");
        }
        System.out.println("Scaled Points:");
        for (int[] point : scaledPoints) {
            System.out.println("(" + point[0] + ", " + point[1] + ")");
        }

        // Set up the frame
        this.setTitle("Simple Origin Scaling");
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw the x and y axes
        g.drawLine(0, 400, 800, 400); // x-axis
        g.drawLine(400, 0, 400, 800); // y-axis

        // Draw the original points and connect them
        g.setColor(Color.RED);
        drawShape(g, points, "Original");

        // Draw the scaled points and connect them
        g.setColor(Color.BLUE);
        drawShape(g, scaledPoints, "Scaled");
    }

    private void drawShape(Graphics g, int[][] shape, String label) {
        for (int i = 0; i < shape.length; i++) {
            int x = shape[i][0] + 400; // Translate to the center of the window
            int y = 400 - shape[i][1]; // Translate and invert y-axis
            g.fillOval(x - 3, y - 3, 6, 6);
            g.drawString(label + " (" + shape[i][0] + "," + shape[i][1] + ")", x, y);

            // Connect the points to form a shape
            if (i > 0) {
                int prevX = shape[i - 1][0] + 400;
                int prevY = 400 - shape[i - 1][1];
                g.drawLine(prevX, prevY, x, y);
            }
        }
    }

    public static void main(String[] args) {
        new SimpleOriginScaling();
    }
}
