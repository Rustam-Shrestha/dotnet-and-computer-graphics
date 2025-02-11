package set;

import java.awt.*;
import java.util.Scanner;

public class XAxisReflection extends Frame {
    private int[][] points;
    private int[][] reflectedPoints;

    public XAxisReflection() {
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

        // Reflection along the x-axis (y=0)
        reflectedPoints = new int[points.length][3];

        // Reflection matrix along the x-axis
        int[][] reflectionMatrixX = {
            {1, 0, 0},
            {0, -1, 0},
            {0, 0, 1}
        };

        // Perform the reflection transformation on each point
        for (int i = 0; i < points.length; i++) {
            reflectedPoints[i] = applyTransformation(points[i], reflectionMatrixX);
        }

        this.setTitle("Reflection Along X-axis");
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    // Function to multiply a 3x3 matrix with a point (homogeneous transformation)
    private int[] applyTransformation(int[] point, int[][] matrix) {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = 0;
            for (int j = 0; j < 3; j++) {
                result[i] += matrix[i][j] * point[j];
            }
        }
        return result;
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

        // Draw the reflected points and connect them
        g.setColor(Color.BLUE);
        drawShape(g, reflectedPoints, "After");
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
        new XAxisReflection();
    }
}
