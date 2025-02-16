import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class FloodFillDemo extends JPanel {
    private final Color circleFillColor = Color.MAGENTA;  // Magenta color for circle fill
    private final Color triangleFillColor = Color.GREEN;  // Green color for triangle fill
    private final Color triangleBorderColor1 = Color.RED; // Red border for one side of the triangle
    private final Color triangleBorderColor2 = Color.BLUE; // Blue border for another side
    private final Color triangleBorderColor3 = Color.BLACK; // Yellow border for the third side
    private boolean fillShapes = false; // Default state: shapes are not filled
    private boolean[][] filled = new boolean[800][600];

    public FloodFillDemo() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawShapes((Graphics2D) g);
        if (fillShapes) {
            fillShapes(g);
        }
    }

    private void drawShapes(Graphics2D g) {
        g.setStroke(new BasicStroke(3)); // Set thicker stroke for shapes

        // Circle: Initially no fill, just the border
        g.setColor(Color.BLACK);
        g.drawOval(250, 200, 100, 100);

        // Triangle: Initially no fill, just the borders
        g.setColor(triangleBorderColor1);
        g.drawLine(400, 400, 325, 300); // Red side of the triangle
        g.setColor(triangleBorderColor2);
        g.drawLine(400, 400, 475, 300); // Blue side of the triangle
        g.setColor(triangleBorderColor3);
        g.drawLine(325, 300, 475, 300); // Yellow side of the triangle
    }

    // When the button is clicked, fill the shapes with color
    private void fillShapes(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3)); // Thicker stroke

        // Fill the circle with magenta
        g.setColor(circleFillColor);
        g.fillOval(250, 200, 100, 100);

        // Fill the triangle with green and retain borders
        g.setColor(triangleFillColor);
        g.fillPolygon(new int[]{400, 325, 475}, new int[]{400, 300, 300}, 3); // Fill triangle with green

        // Redraw the border of the triangle with red, blue, and yellow strokes
        g.setColor(triangleBorderColor1);
        g.drawLine(400, 400, 325, 300); // Red side of the triangle
        g.setColor(triangleBorderColor2);
        g.drawLine(400, 400, 475, 300); // Blue side of the triangle
        g.setColor(triangleBorderColor3);
        g.drawLine(325, 300, 475, 300); // Yellow side of the triangle
    }

    public void triggerFillShapes() {
        fillShapes = true; // Enable filling
        repaint(); // Repaint to apply the fills
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flood Fill Demo");
        FloodFillDemo demo = new FloodFillDemo();
        JButton fillButton = new JButton("Fill Shapes");

        // Fill shapes when the button is clicked
        fillButton.addActionListener(e -> demo.triggerFillShapes());

        frame.setLayout(new BorderLayout());
        frame.add(demo, BorderLayout.CENTER);
        frame.add(fillButton, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
