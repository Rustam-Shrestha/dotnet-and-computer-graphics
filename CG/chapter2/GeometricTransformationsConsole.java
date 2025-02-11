import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GeometricTransformationsConsole extends JFrame {
    private ArrayList<Point> points;
    private JPanel drawingPanel;

    public GeometricTransformationsConsole() {
        points = new ArrayList<>();
        
        // Setup drawing panel
        drawingPanel = new DrawingPanel();
        drawingPanel.setPreferredSize(new Dimension(800, 800));

        // Setup frame
        setLayout(new BorderLayout());
        add(drawingPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);

            // Draw axes
            int width = getWidth();
            int height = getHeight();
            g2d.drawLine(width / 2, 0, width / 2, height);
            g2d.drawLine(0, height / 2, width, height / 2);

            // Draw points
            g2d.setColor(Color.BLUE);
            for (int i = 0; i < points.size(); i++) {
                Point p = points.get(i);
                int x = width / 2 + p.x;
                int y = height / 2 - p.y;
                g2d.fillOval(x - 2, y - 2, 5, 5);
            }
        }
    }

    private void translate(double dx, double dy) {
        AffineTransform transform = new AffineTransform();
        transform.translate(dx, dy);
        applyTransformation(transform);
    }

    private void rotate(double theta) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(theta);
        applyTransformation(transform);
    }

    private void shear(double shx, double shy) {
        AffineTransform transform = new AffineTransform();
        transform.shear(shx, shy);
        applyTransformation(transform);
    }

    private void scale(double sx, double sy) {
        AffineTransform transform = new AffineTransform();
        transform.scale(sx, sy);
        applyTransformation(transform);
    }

    private void reflect(boolean xAxis, boolean yAxis) {
        AffineTransform transform = new AffineTransform();
        transform.scale(xAxis ? -1 : 1, yAxis ? -1 : 1);
        applyTransformation(transform);
    }

    private void applyTransformation(AffineTransform transform) {
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            double[] src = {p.x, p.y};
            double[] dst = new double[2];
            transform.transform(src, 0, dst, 0, 1);
            points.set(i, new Point((int) dst[0], (int) dst[1]));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GeometricTransformationsConsole::new);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter coordinates (x y) separated by spaces, followed by transformation commands:");

        GeometricTransformationsConsole app = new GeometricTransformationsConsole();

        while (true) {
            System.out.print("Enter coordinates (e.g., 100 100 -50 50): ");
            String[] coords = scanner.nextLine().split(" ");
            app.points.clear();
            try {
                for (int i = 0; i < coords.length; i += 2) {
                    int x = Integer.parseInt(coords[i].trim());
                    int y = Integer.parseInt(coords[i + 1].trim());
                    app.points.add(new Point(x, y));
                }
                app.drawingPanel.repaint();
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid input. Please enter valid coordinates.");
                continue;
            }

            System.out.print("Enter transformation (e.g., translate 50 50, rotate 45, shear 0.5 0, scale 1.5 1.5, reflect x y): ");
            String[] transform = scanner.nextLine().split(" ");
            if (transform.length == 0) {
                System.out.println("No transformation command entered.");
                continue;
            }

            String command = transform[0].toLowerCase();
            try {
                switch (command) {
                    case "translate":
                        if (transform.length < 3) {
                            System.out.println("Invalid input. Translation requires dx and dy.");
                            continue;
                        }
                        double dx = Double.parseDouble(transform[1]);
                        double dy = Double.parseDouble(transform[2]);
                        app.translate(dx, dy);
                        break;
                    case "rotate":
                        if (transform.length < 2) {
                            System.out.println("Invalid input. Rotation requires an angle.");
                            continue;
                        }
                        double theta = Math.toRadians(Double.parseDouble(transform[1]));
                        app.rotate(theta);
                        break;
                    case "shear":
                        if (transform.length < 3) {
                            System.out.println("Invalid input. Shearing requires shx and shy.");
                            continue;
                        }
                        double shx = Double.parseDouble(transform[1]);
                        double shy = Double.parseDouble(transform[2]);
                        app.shear(shx, shy);
                        break;
                    case "scale":
                        if (transform.length < 3) {
                            System.out.println("Invalid input. Scaling requires sx and sy.");
                            continue;
                        }
                        double sx = Double.parseDouble(transform[1]);
                        double sy = Double.parseDouble(transform[2]);
                        app.scale(sx, sy);
                        break;
                    case "reflect":
                        if (transform.length < 3) {
                            System.out.println("Invalid input. Reflection requires x and y directions.");
                            continue;
                        }
                        boolean xAxis = transform[1].equalsIgnoreCase("x");
                        boolean yAxis = transform[2].equalsIgnoreCase("y");
                        app.reflect(xAxis, yAxis);
                        break;
                    default:
                        System.out.println("Unknown command.");
                        break;
                }
                app.drawingPanel.repaint();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers for the transformation.");
            }
        }
    }
}
