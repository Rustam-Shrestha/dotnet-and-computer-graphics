

import java.awt.*;
import java.util.Scanner;

public class XScaling extends Frame {
    private int[][] points;
    private int[][] scaledPoints;
    private double sx; // Scaling factor for x-direction
    private int px, py; // Midpoint

    public XScaling() {
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

        // Calculate the midpoint (centroid) of the shape
        calculateMidPoint();

        // Get the scaling factor for x-direction
        System.out.println("Enter scaling factor sx:");
        sx = s.nextDouble();

        // Apply scaling in x-direction
        scaledPoints = new int[points.length][3];

        // Create translation and scaling matrices
        double[][] translationToMidpoint = {
            {1, 0, px},
            {0, 1, py},
            {0, 0, 1}
        };

        double[][] scalingMatrixX = {
            {sx, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };

        double[][] translationBack = {
            {1, 0, -px},
            {0, 1, -py},
            {0, 0, 1}
        };

        // Combine the matrices: T(mid) * S * T(-mid)
        double[][] finalMatrix = new double[3][3];
        double[][] tempMatrix = new double[3][3];

        // First multiply scaling matrix with negative translation
        multiplyMatrices(translationToMidpoint,scalingMatrixX,tempMatrix);
        // Then multiply the result with the positive translation
        multiplyMatrices(tempMatrix, translationBack,finalMatrix);

        // Multiply final homogeneous matrix with the given coordinates
        for (int i = 0; i < points.length; i++) {
            scaledPoints[i] = matrixMultiplication(finalMatrix,points[i]);
        }

        this.setTitle("Scaling in X direction");
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    // Method to calculate the midpoint (centroid) of the shape
    private void calculateMidPoint() {
        int sumX = 0, sumY = 0;
        for (int[] point : points) {
            sumX += point[0];
            sumY += point[1];
        }
        px = sumX / points.length; // X midpoint
        py = sumY / points.length; // Y midpoint
    }

    // Method to multiply two matrices
    private void multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix, double[][] result) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = 0;
                for (int k = 0; k < 3; k++) {
                    result[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
    }

    // Method to transform coordinates using a matrix
    private int[] matrixMultiplication(double[][] matrix, int[] point) {
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
        g.drawLine(0, 400, 800, 400); // x-axis
        g.drawLine(400, 0, 400, 800); // y-axis

        g.setColor(Color.RED);
        drawShape(g, points, "Original");

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
        new XScaling();
    }
}
