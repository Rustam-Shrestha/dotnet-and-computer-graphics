package undone;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Scanner;

public class CoordinateRotationUsingAffineTransform extends Frame {
    private int[][] points;
    private double angle; // Rotation angle in degrees
    private int px, py; // Rotation point
    private Path2D originalShape;
    private Path2D rotatedShape;

    public CoordinateRotationUsingAffineTransform() {
        Scanner s = new Scanner(System.in);

        // Get the number of coordinates from the user
        System.out.println("Enter the number of coordinates:");
        int numPoints = s.nextInt();
        points = new int[numPoints][2];

        // Get the coordinates from the user
        for (int i = 0; i < numPoints; i++) {
            System.out.println("Enter x" + (i + 1) + ":");
            points[i][0] = s.nextInt();
            System.out.println("Enter y" + (i + 1) + ":");
            points[i][1] = s.nextInt();
        }

        // Get the rotation angle from the user
        System.out.println("Enter rotation angle in degrees:");
        angle = s.nextDouble();

        // Get the rotation point
        System.out.println("Enter the rotation point (px, py):");
        px = s.nextInt();
        py = s.nextInt();

        // Create the original shape
        originalShape = new Path2D.Double();
        originalShape.moveTo(points[0][0], points[0][1]);
        for (int i = 1; i < points.length; i++) {
            originalShape.lineTo(points[i][0], points[i][1]);
        }
        originalShape.closePath(); // Close the shape if it's a polygon

        // Create a transform for rotation around (px, py)
        AffineTransform transform = new AffineTransform();
        transform.translate(px, py); // Translate to the rotation point
        transform.rotate(Math.toRadians(angle)); // Apply rotation
        transform.translate(-px, -py); // Translate back

        // Apply the transformation to create the rotated shape
        rotatedShape = (Path2D) originalShape.clone();
        rotatedShape.transform(transform);

        this.setTitle("2D Rotation with AffineTransform");
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        // Draw the x and y axes
        g2d.drawLine(0, 400, 800, 400); // x-axis
        g2d.drawLine(400, 0, 400, 800); // y-axis

        // Move the graphics context to the center of the frame
        g2d.translate(400, 400);

        // Draw the original shape
        g2d.setColor(Color.RED);
        g2d.draw(originalShape);
        g2d.drawString("Before", (int) originalShape.getBounds2D().getX() - 30, (int) originalShape.getBounds2D().getY() - 10);

        // Draw the rotated shape
        g2d.setColor(Color.BLUE);
        g2d.draw(rotatedShape);
        g2d.drawString("After", (int) rotatedShape.getBounds2D().getX() - 30, (int) rotatedShape.getBounds2D().getY() - 10);
    }

    public static void main(String[] args) {
        new CoordinateRotationUsingAffineTransform();
    }
}
