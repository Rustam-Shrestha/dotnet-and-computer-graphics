import java.awt.*;
import java.util.Scanner;

public class haha extends Frame {
    private int[][] points;
    private int[][] reflectedPoints;
    private double m, c; // Slope and intercept

    public haha() {
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

        // Get the slope (m) and intercept (c) from the user
        System.out.println("Enter the slope m:");
        m = s.nextDouble();
        System.out.println("Enter the intercept c:");
        c = s.nextDouble();

        // Apply reflection using homogeneous transformation
        reflectedPoints = new int[points.length][3];

        // Step 1: Translate all points by -c to move the line to the origin
        double[][] translateToOrigin = {
            {1, 0, 0},
            {0, 1, -c},
            {0, 0, 1}
        };

        // Step 2: Rotate all points by -arctan(m) to align the line with the x-axis
        double theta = Math.atan(m); // Angle to align the line with the x-axis
        double[][] rotateToXAxis = {
            {Math.cos(theta), Math.sin(theta), 0},
            {-Math.sin(theta), Math.cos(theta), 0},
            {0, 0, 1}
        };

        // Step 3: Reflect across x-axis
        double[][] reflectAcrossXAxis = {
            {1, 0, 0},
            {0, -1, 0},
            {0, 0, 1}
        };

        // Step 4: Rotate back by +arctan(m)
        double[][] rotateBack = {
            {Math.cos(-theta), Math.sin(-theta), 0},
            {-Math.sin(-theta), Math.cos(-theta), 0},
            {0, 0, 1}
        };

        // Step 5: Translate all points back by +c to restore the original position
        double[][] translateBack = {
            {1, 0, 0},
            {0, 1, c},
            {0, 0, 1}
        };

        // Apply all transformations to the points
        for (int i = 0; i < points.length; i++) {
            int[] transformedPoint = points[i];
            transformedPoint = matrixMultiplication(transformedPoint, translateToOrigin); // Translate to origin
            transformedPoint = matrixMultiplication(transformedPoint, rotateToXAxis);    // Rotate to x-axis
            transformedPoint = matrixMultiplication(transformedPoint, reflectAcrossXAxis); // Reflect across x-axis
            transformedPoint = matrixMultiplication(transformedPoint, rotateBack);        // Rotate back
            transformedPoint = matrixMultiplication(transformedPoint, translateBack);     // Translate back
            reflectedPoints[i] = transformedPoint;
        }

        // Print the reflected points for debugging
        System.out.println("Reflected Points:");
        for (int[] point : reflectedPoints) {
            System.out.println("(" + point[0] + ", " + point[1] + ")");
        }

        this.setTitle("Reflection across y=mx+c");
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    // Function to perform matrix multiplication for homogeneous transformation
    private int[] matrixMultiplication(int[] point, double[][] matrix) {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = 0;
            for (int j = 0; j < 3; j++) {
                result[i] += point[j] * matrix[j][i];
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
        drawShape(g, points, "Original");

        // Draw the reflected points and connect them
        g.setColor(Color.BLUE);
        drawShape(g, reflectedPoints, "Reflected");
    }

    private void drawShape(Graphics g, int[][] shape, String label) {
        if (shape.length > 1) {
            for (int i = 0; i < shape.length; i++) {
                int x1 = shape[i][0] + 400;
                int y1 = 400 - shape[i][1];
                int x2 = shape[(i + 1) % shape.length][0] + 400;
                int y2 = 400 - shape[(i + 1) % shape.length][1];
                g.fillOval(x1 - 3, y1 - 3, 6, 6);
                g.drawLine(x1, y1, x2, y2);
                g.drawString(label + " (" + shape[i][0] + "," + shape[i][1] + ")", x1, y1);
            }
        }
    }

    public static void main(String[] args) {
        new haha();
    }
}
