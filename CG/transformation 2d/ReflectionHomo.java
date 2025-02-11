import java.awt.*;
import java.util.Scanner;

public class ReflectionHomo extends Frame {
    private int[][] points;
    private int[][] reflectedPoints;
    private String reflectionType;

    public ReflectionHomo() {
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

        // Ask the user for the type of reflection
        System.out.println("Choose reflection type: 0: x-axis,\n 1: y-axis,\n 2: origin,\n 3: y=x,\n 4: y=-x");
        reflectionType = s.next();

        // Create the appropriate reflection matrix
        int[][] reflectionMatrix = getReflectionMatrix(reflectionType);

        // Apply reflection to the points
        reflectedPoints = new int[points.length][3];
        for (int i = 0; i < points.length; i++) {
            reflectedPoints[i] = matrixMultiplication(points[i], reflectionMatrix);
        }

        // Print original and reflected points for debugging
        System.out.println("Original Points:");
        for (int[] point : points) {
            System.out.println("(" + point[0] + ", " + point[1] + ")");
        }
        System.out.println("Reflected Points:");
        for (int[] point : reflectedPoints) {
            System.out.println("(" + point[0] + ", " + point[1] + ")");
        }

        this.setTitle("Reflection Transformation");
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    // Function to get the reflection matrix based on user input
    private int[][] getReflectionMatrix(String type) {
        switch (type.toLowerCase()) {
            case "0":
                return new int[][]{
                    {1, 0, 0},
                    {0, -1, 0},
                    {0, 0, 1}
                };
            case "1":
                return new int[][]{
                    {-1, 0, 0},
                    {0, 1, 0},
                    {0, 0, 1}
                };
            case "2":
                return new int[][]{
                    {-1, 0, 0},
                    {0, -1, 0},
                    {0, 0, 1}
                };
            case "3":
                return new int[][]{
                    {0, 1, 0},
                    {1, 0, 0},
                    {0, 0, 1}
                };
            case "4":
                return new int[][]{
                    {0, -1, 0},
                    {-1, 0, 0},
                    {0, 0, 1}
                };
            default:
                System.out.println("Invalid reflection type. Using identity matrix.");
                return new int[][]{
                    {1, 0, 0},
                    {0, 1, 0},
                    {0, 0, 1}
                };
        }
    }

    // Function to perform matrix multiplication for homogeneous transformation
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
        new ReflectionHomo();
    }
}
