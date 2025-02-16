package done;
import java.awt.*;
import java.util.Scanner;

public class CoordinateScaling extends Frame {
    private int[][] points;
    private int[][] scaledPoints;
    private double sx, sy; // Scaling factors
    private int px, py; // Scaling point

    public CoordinateScaling() {
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

        // Get the scaling factors from the user
        System.out.println("Enter scaling factor sx:");
        sx = s.nextDouble();
        System.out.println("Enter scaling factor sy:");
        sy = s.nextDouble();

        // Get the scaling point
        System.out.println("Enter the scaling point (px, py):");
        px = s.nextInt();
        py = s.nextInt();

        // Apply scaling with translation
        scaledPoints = new int[points.length][3];

        // Translation to origin
        int[][] translationToOriginMatrix = {
            {1, 0, -px},
            {0, 1, -py},
            {0, 0, 1}
        };
        // Scaling matrix
        double[][] scalingMatrix = {
            {sx, 0, 0},
            {0, sy, 0},
            {0, 0, 1}
        };

        // Translation back to original position
        int[][] translationBackMatrix = {
            {1, 0, px},
            {0, 1, py},
            {0, 0, 1}
        };

        // Apply translation to origin
        int[][] tempPoints = new int[points.length][3];
        for (int i = 0; i < points.length; i++) {
            tempPoints[i] = matrixMultiplication(points[i], translationToOriginMatrix);
        }

        // Apply scaling
        int[][] scaledTempPoints = new int[points.length][3];
        for (int i = 0; i < tempPoints.length; i++) {
            scaledTempPoints[i] = matrixMultiplication(tempPoints[i], scalingMatrix);
        }

        // Apply translation back
        for (int i = 0; i < scaledPoints.length; i++) {
            scaledPoints[i] = matrixMultiplication(scaledTempPoints[i], translationBackMatrix);
        }

        // Print out original and scaled points for debugging
        System.out.println("Original Points:");
        for (int[] point : points) {
            System.out.println("(" + point[0] + ", " + point[1] + ")");
        }
        System.out.println("Scaled Points:");
        for (int[] point : scaledPoints) {
            System.out.println("(" + point[0] + ", " + point[1] + ")");
        }

        this.setTitle("Scaling of Coordinates");
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

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

        // Draw the scaled points and connect them
        g.setColor(Color.BLUE);
        drawShape(g, scaledPoints, "Scaled");
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
        new CoordinateScaling();
    }
}
