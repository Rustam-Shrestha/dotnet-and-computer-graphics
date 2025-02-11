package set;
import java.awt.*;
import java.util.Scanner;

public class HomogeneousTranslationY extends Frame {
    private int[][] points;
    private int[][] translatedPoints;
    private int ty;

    public HomogeneousTranslationY() {
        Scanner s = new Scanner(System.in);

        // Get the number of coordinates from the user
        System.out.println("Enter the number of coordinates:");
        int numPoints = s.nextInt();
        points = new int[numPoints][3]; // Homogeneous coordinates matrix

        // Get the coordinates from the user
        for (int i = 0; i < numPoints; i++) {
            System.out.println("Enter x" + (i + 1) + ":");
            points[i][0] = s.nextInt();
            System.out.println("Enter y" + (i + 1) + ":");
            points[i][1] = s.nextInt();
            points[i][2] = 1; // Homogeneous coordinate
        }

        // Get the translation factor for Y direction
        System.out.println("Enter translation factor ty:");
        ty = s.nextInt();
        
        // Apply translation to the points (only in Y direction)
        translatedPoints = new int[points.length][3];
        for (int i = 0; i < points.length; i++) {
            translatedPoints[i][0] = points[i][0]; // X remains the same
            translatedPoints[i][1] = points[i][1] + ty;
            translatedPoints[i][2] = 1; // Homogeneous coordinate
        }

        // Print the translated points
        System.out.println("Translated Points (in Y direction):");
        for (int i = 0; i < numPoints; i++) {
            System.out.println("(" + translatedPoints[i][0] + ", " + translatedPoints[i][1] + ")");
        }

        this.setTitle("Homogeneous Translation in Y-Direction");
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Ensure background is painted correctly

        // Draw the x and y axes
        g.drawLine(0, 400, 800, 400); // x-axis
        g.drawLine(400, 0, 400, 800); // y-axis

        // Draw the original points and connect them
        g.setColor(Color.RED);
        drawShape(g, points, "Before");

        // Draw the translated points and connect them
        g.setColor(Color.BLUE);
        drawShape(g, translatedPoints, "After");
    }

    private void drawShape(Graphics g, int[][] shape, String label) {
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;

        if (shape.length > 1) {
            for (int i = 0; i < shape.length - 1; i++) {
                int x1 = shape[i][0] + 400;
                int y1 = 400 - shape[i][1];
                int x2 = shape[i + 1][0] + 400;
                int y2 = 400 - shape[i + 1][1];
                g.fillOval(x1 - 3, y1 - 3, 6, 6);
                g.drawLine(x1, y1, x2, y2);

                // Track the minimum x and y coordinates
                minX = Math.min(minX, x1);
                minY = Math.min(minY, y1);
            }
            // Draw the last point and connect back to the first
            int xLast = shape[shape.length - 1][0] + 400;
            int yLast = 400 - shape[shape.length - 1][1];
            int xFirst = shape[0][0] + 400;
            int yFirst = 400 - shape[0][1];
            g.fillOval(xLast - 3, yLast - 3, 6, 6);
            g.drawLine(xLast, yLast, xFirst, yFirst);

            // Track the minimum x and y coordinates for the last point
            minX = Math.min(minX, xLast);
            minY = Math.min(minY, yLast);

            // Draw the label near the shape
            g.drawString(label, minX - 30, minY - 10);
        }
    }

    public static void main(String[] args) {
        new HomogeneousTranslationY();
    }
}
