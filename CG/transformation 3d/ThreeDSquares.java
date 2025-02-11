import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreeDSquares extends JPanel implements ActionListener {
    private int[][] square1;
    private int[][] square2;
    private int tx, ty, tz;  // Translation distances
    private double rotationAngle;  // Rotation angle in degrees
    private String rotationAxis;  // Rotation axis: "x", "y", or "z"
    private boolean translated = false;  // Flag to indicate if translation has occurred
    private boolean rotated = false;  // Flag to indicate if rotation has occurred

    public ThreeDSquares() {
        // Initialize the coordinates of two squares in 3D space
        square1 = new int[][]{
                {100, 100, 0},  // Vertex 1 of square 1
                {200, 100, 0},  // Vertex 2 of square 1
                {200, 200, 0},  // Vertex 3 of square 1
                {100, 200, 0}   // Vertex 4 of square 1
        };

        square2 = new int[][]{
                {120, 120, 50}, // Vertex 1 of square 2
                {220, 120, 50}, // Vertex 2 of square 2
                {220, 220, 50}, // Vertex 3 of square 2
                {120, 220, 50}  // Vertex 4 of square 2
        };

        // Prompt user for translation distances
        String txInput = JOptionPane.showInputDialog("Enter Translation Distance in x (tx): ");
        String tyInput = JOptionPane.showInputDialog("Enter Translation Distance in y (ty): ");
        String tzInput = JOptionPane.showInputDialog("Enter Translation Distance in z (tz): ");
        
        try {
            tx = Integer.parseInt(txInput);
            ty = Integer.parseInt(tyInput);
            tz = Integer.parseInt(tzInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid integers.");
            System.exit(1);
        }

        // Prompt user for rotation axis and angle
        rotationAxis = JOptionPane.showInputDialog("Enter Rotation Axis (x, y, or z): ");
        String angleInput = JOptionPane.showInputDialog("Enter Rotation Angle in degrees: ");
        
        try {
            rotationAngle = Double.parseDouble(angleInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid angle.");
            System.exit(1);
        }

        // Convert angle to radians
        rotationAngle = Math.toRadians(rotationAngle);

        // Set translated and rotated flags to true after taking input
        translated = true;
        rotated = true;

        // Timer for updating the panel after translation
        Timer timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw 3D Axes
        drawAxes(g2);

        // Draw original 3D squares
        g2.setColor(Color.RED);
        draw3DSquare(g2, square1, false);
        draw3DSquare(g2, square2, false);

        if (translated) {
            // Translate squares vertices and draw the translated squares
            int[][] translatedSquare1 = translateSquare(square1, tx, ty, tz);
            int[][] translatedSquare2 = translateSquare(square2, tx, ty, tz);

            g2.setColor(Color.BLUE);
            draw3DSquare(g2, translatedSquare1, true);
            draw3DSquare(g2, translatedSquare2, true);

            if (rotated) {
                // Rotate the translated squares vertices and draw the rotated squares
                int[][] rotatedSquare1 = rotateSquare(translatedSquare1, rotationAxis, rotationAngle);
                int[][] rotatedSquare2 = rotateSquare(translatedSquare2, rotationAxis, rotationAngle);

                g2.setColor(Color.GREEN);
                draw3DSquare(g2, rotatedSquare1, true);
                draw3DSquare(g2, rotatedSquare2, true);
            }
        }
    }

    private void drawAxes(Graphics2D g) {
        g.setColor(Color.BLACK);

        // X-axis
        g.drawLine(50, 300, 400, 300);
        g.drawString("X", 410, 300);

        // Y-axis
        g.drawLine(50, 300, 50, 50);
        g.drawString("Y", 50, 40);

        // Z-axis (diagonal for perspective)
        g.drawLine(50, 300, 200, 400);
        g.drawString("Z", 210, 410);
    }

    private void draw3DSquare(Graphics2D g, int[][] vertices, boolean isTranslated) {
        // Draw base square
        g.drawLine(projectX(vertices[0]), projectY(vertices[0]), projectX(vertices[1]), projectY(vertices[1]));
        g.drawLine(projectX(vertices[1]), projectY(vertices[1]), projectX(vertices[2]), projectY(vertices[2]));
        g.drawLine(projectX(vertices[2]), projectY(vertices[2]), projectX(vertices[3]), projectY(vertices[3]));
        g.drawLine(projectX(vertices[3]), projectY(vertices[3]), projectX(vertices[0]), projectY(vertices[0]));

        // Draw top square (connected edges to base)
        int[][] topSquare = translateSquare(vertices, 0, 0, -50);
        g.drawLine(projectX(topSquare[0]), projectY(topSquare[0]), projectX(topSquare[1]), projectY(topSquare[1]));
        g.drawLine(projectX(topSquare[1]), projectY(topSquare[1]), projectX(topSquare[2]), projectY(topSquare[2]));
        g.drawLine(projectX(topSquare[2]), projectY(topSquare[2]), projectX(topSquare[3]), projectY(topSquare[3]));
        g.drawLine(projectX(topSquare[3]), projectY(topSquare[3]), projectX(topSquare[0]), projectY(topSquare[0]));

        // Draw connections between the base and top squares
        g.drawLine(projectX(vertices[0]), projectY(vertices[0]), projectX(topSquare[0]), projectY(topSquare[0]));
        g.drawLine(projectX(vertices[1]), projectY(vertices[1]), projectX(topSquare[1]), projectY(topSquare[1]));
        g.drawLine(projectX(vertices[2]), projectY(vertices[2]), projectX(topSquare[2]), projectY(topSquare[2]));
        g.drawLine(projectX(vertices[3]), projectY(vertices[3]), projectX(topSquare[3]), projectY(topSquare[3]));
    }

    private int projectX(int[] point) {
        // Perspective projection formula for X
        return (int) (point[0] - point[2] * 0.5);
    }

    private int projectY(int[] point) {
        // Perspective projection formula for Y
        return (int) (point[1] - point[2] * 0.5);
    }

    private int[][] translateSquare(int[][] vertices, int tx, int ty, int tz) {
        int[][] newVertices = new int[vertices.length][3];
        for (int i = 0; i < vertices.length; i++) {
            newVertices[i][0] = vertices[i][0] + tx;
            newVertices[i][1] = vertices[i][1] + ty;
            newVertices[i][2] = vertices[i][2] + tz;
        }
        return newVertices;
    }

    private int[][] rotateSquare(int[][] vertices, String axis, double angle) {
        int[][] newVertices = new int[vertices.length][3];
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        
        for (int i = 0; i < vertices.length; i++) {
            int x = vertices[i][0];
            int y = vertices[i][1];
            int z = vertices[i][2];
            
            switch (axis.toLowerCase()) {
                case "x":
                    newVertices[i][0] = x;
                    newVertices[i][1] = (int) (y * cosAngle - z * sinAngle);
                    newVertices[i][2] = (int) (y * sinAngle + z * cosAngle);
                    break;
                case "y":
                    newVertices[i][0] = (int) (x * cosAngle + z * sinAngle);
                    newVertices[i][1] = y;
                    newVertices[i][2] = (int) (-x * sinAngle + z * cosAngle);
                    break;
                case "z":
                    newVertices[i][0] = (int) (x * cosAngle - y * sinAngle);
                    newVertices[i][1] = (int) (x * sinAngle + y * cosAngle);
                    newVertices[i][2] = z;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid rotation axis! Use 'x', 'y', or 'z'.");
                    System.exit(1);
            }
        }
        return newVertices;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Request a repaint to show the translated and rotated shapes
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("3D Squares Rotation");
        ThreeDSquares rotationPanel = new ThreeDSquares();
        frame.add(rotationPanel);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
