import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class ReflectionInGraphics extends Frame implements ActionListener {
    private int[][] points;
    private int[][] reflectedPoints;
    private String reflectionType = "x-axis";

    public ReflectionInGraphics() {
        // Setting up GUI components
        Button xAxisButton = new Button("Reflect on X-axis");
        Button yAxisButton = new Button("Reflect on Y-axis");
        Button originButton = new Button("Reflect on Origin");
        Button yEqualsXButton = new Button("Reflect on Y=X");
        Button yEqualsNegXButton = new Button("Reflect on Y=-X");

        xAxisButton.setBounds(50, 700, 150, 30);
        yAxisButton.setBounds(210, 700, 150, 30);
        originButton.setBounds(370, 700, 150, 30);
        yEqualsXButton.setBounds(530, 700, 150, 30);
        yEqualsNegXButton.setBounds(690, 700, 150, 30);

        add(xAxisButton);
        add(yAxisButton);
        add(originButton);
        add(yEqualsXButton);
        add(yEqualsNegXButton);

        xAxisButton.addActionListener(this);
        yAxisButton.addActionListener(this);
        originButton.addActionListener(this);
        yEqualsXButton.addActionListener(this);
        yEqualsNegXButton.addActionListener(this);

        // Input from the user
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of points:");
        int numPoints = s.nextInt();
        points = new int[numPoints][3]; // Homogeneous coordinates matrix

        for (int i = 0; i < numPoints; i++) {
            System.out.println("Enter x" + (i + 1) + ":");
            points[i][0] = s.nextInt();
            System.out.println("Enter y" + (i + 1) + ":");
            points[i][1] = s.nextInt();
            points[i][2] = 1; // Homogeneous coordinate
        }

        // Apply initial reflection
        reflectPoints();

        setTitle("Reflection in Graphics");
        setSize(800, 800);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Determine the reflection type based on button pressed
        String command = e.getActionCommand();
        reflectionType = command.split(" ")[2].toLowerCase();

        reflectPoints(); // Recalculate reflection
        repaint(); // Redraw the graphics with updated reflection
    }

    private void reflectPoints() {
        reflectedPoints = new int[points.length][3];
        double[][] reflectionMatrix = new double[3][3];

        switch (reflectionType) {
            case "x-axis":
                reflectionMatrix = new double[][] {
                    {1, 0, 0},
                    {0, -1, 0},
                    {0, 0, 1}
                };
                break;

            case "y-axis":
                reflectionMatrix = new double[][] {
                    {-1, 0, 0},
                    {0, 1, 0},
                    {0, 0, 1}
                };
                break;

            case "origin":
                reflectionMatrix = new double[][] {
                    {-1, 0, 0},
                    {0, -1, 0},
                    {0, 0, 1}
                };
                break;

            case "y=x":
                reflectionMatrix = new double[][] {
                    {0, 1, 0},
                    {1, 0, 0},
                    {0, 0, 1}
                };
                break;

            case "y=-x":
                reflectionMatrix = new double[][] {
                    {0, -1, 0},
                    {-1, 0, 0},
                    {0, 0, 1}
                };
                break;
        }

        // Apply reflection matrix
        for (int i = 0; i < points.length; i++) {
            reflectedPoints[i] = matrixMultiplication(points[i], reflectionMatrix);
        }
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

        // Draw the original points and connect them in red
        g.setColor(Color.RED);
        drawShape(g, points, "Original");

        // Draw the reflected points and connect them in blue
        g.setColor(Color.BLUE);
        drawShape(g, reflectedPoints, "Reflected");
    }

    private void drawShape(Graphics g, int[][] shape, String label) {
        if (shape.length > 1) {
            for (int i = 0; i < shape.length - 1; i++) {
                int x1 = shape[i][0] + 400;
                int y1 = 400 - shape[i][1];
                int x2 = shape[i + 1][0] + 400;
                int y2 = 400 - shape[i + 1][1];
                g.fillOval(x1 - 3, y1 - 3, 6, 6);
                g.drawLine(x1, y1, x2, y2);
            }
            // Connect last point to first point
            int xLast = shape[shape.length - 1][0] + 400;
            int yLast = 400 - shape[shape.length - 1][1];
            int xFirst = shape[0][0] + 400;
            int yFirst = 400 - shape[0][1];
            g.fillOval(xLast - 3, yLast - 3, 6, 6);
            g.drawLine(xLast, yLast, xFirst, yFirst);
        }
    }

    public static void main(String[] args) {
        new ReflectionInGraphics();
    }
}
