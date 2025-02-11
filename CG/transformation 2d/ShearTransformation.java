package done;
import java.awt.*;
import java.util.Scanner;

public class ShearTransformation extends Frame {
    private int[][] points;
    private int[][] shearedPoints;
    private int a1, a2;
    private double shx, shy;

    public ShearTransformation() {
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

        // Get the arbitrary point (a1, a2)
        System.out.println("Enter arbitrary point a1:");
        a1 = s.nextInt();
        System.out.println("Enter arbitrary point a2:");
        a2 = s.nextInt();

        // Get the shear factors
        System.out.println("Enter shear factor shx:");
        shx = s.nextDouble();
        System.out.println("Enter shear factor shy:");
        shy = s.nextDouble();

        // Apply shearing transformation
        shearedPoints = new int[points.length][3];

        // Translation to origin matrix
        int[][] toOrigin = {
            {1, 0, -a1},
            {0, 1, -a2},
            {0, 0, 1}
        };

        // Shearing matrix
        double[][] shearMatrix = {
            {1, shy, 0},
            {shx, 1, 0},
            {0, 0, 1}
        };

        // Translation back to original position matrix
        int[][] backToOriginal = {
            {1, 0, a1},
            {0, 1, a2},
            {0, 0, 1}
        };

        // Perform the transformations on each point
        for (int i = 0; i < points.length; i++) {
            int[] translatedToOrigin = matrixMultiplication(points[i], toOrigin);
            double[] sheared = matrixMultiplicationDouble(translatedToOrigin, shearMatrix);
            shearedPoints[i] = matrixMultiplication(sheared, backToOriginal);
        }

        this.setTitle("Shear Transformation");
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    // Function to perform matrix multiplication for int matrices
    private int[] matrixMultiplication(int[] point, int[][] matrix) {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = 0;
            for (int j = 0; j < 3; j++) {
                result[i] += point[j] * matrix[j][i];
            }
        }
        return result;
    }

    // Function to perform matrix multiplication for int and double matrices
    private double[] matrixMultiplicationDouble(int[] point, double[][] matrix) {
        double[] result = new double[3];
        for (int i = 0; i < 3; i++) {
            result[i] = 0;
            for (int j = 0; j < 3; j++) {
                result[i] += point[j] * matrix[j][i];
            }
        }
        return result;
    }

    // Function to convert double array to int array and perform matrix multiplication
    private int[] matrixMultiplication(double[] point, int[][] matrix) {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = 0;
            for (int j = 0; j < 3; j++) {
                result[i] += (int)(point[j] * matrix[j][i]);
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

        // Draw the sheared points and connect them
        g.setColor(Color.BLUE);
        drawShape(g, shearedPoints, "After");
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
        new ShearTransformation();
    }
}
