import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class MultiColorBoundaryFillDemo extends JPanel {
    private final Color circleFillColor = Color.MAGENTA;
    private final Color triangleFillColor = Color.GREEN;
    private final Color triangleBoundaryColor1 = Color.RED;
    private final Color triangleBoundaryColor2 = Color.BLUE;
    private final Color triangleBoundaryColor3 = Color.BLACK;
    private final Color circleBoundaryColor = Color.BLACK;
    private final Color rectangleBoundaryColor = Color.BLACK;
    private BufferedImage canvas;

    public MultiColorBoundaryFillDemo() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
        canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = canvas.createGraphics();
        drawShapes(g2d);
        g.drawImage(canvas, 0, 0, this);
    }

    private void drawShapes(Graphics2D g) {
        g.setStroke(new BasicStroke(3));

        // Draw the large rectangle with a black border
        g.setColor(rectangleBoundaryColor);
        g.drawRect(50, 50, 700, 500);

        // Draw circle with black border
        g.setColor(circleBoundaryColor);
        g.drawOval(250, 200, 100, 100);

        // Draw triangle with multi-colored borders
        g.setColor(triangleBoundaryColor1);
        g.drawLine(400, 400, 325, 300);
        g.setColor(triangleBoundaryColor2);
        g.drawLine(400, 400, 475, 300);
        g.setColor(triangleBoundaryColor3); 
        g.drawLine(325, 300, 475, 300);
    }

    private void boundaryFill(int x, int y, Color fillColor, Color boundaryColor) {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) return;

        int currentColor = canvas.getRGB(x, y);
        if (currentColor == boundaryColor.getRGB() || currentColor == fillColor.getRGB()) return;

        canvas.setRGB(x, y, fillColor.getRGB());  
        repaint();

        boundaryFill(x + 1, y, fillColor, boundaryColor);
        boundaryFill(x - 1, y, fillColor, boundaryColor);
        boundaryFill(x, y + 1, fillColor, boundaryColor);
        boundaryFill(x, y - 1, fillColor, boundaryColor);
    }

    public void triggerFillShapes() {
        // Fill the circle (starting point is inside the circle at (300, 250)) with magenta
        boundaryFill(300, 250, circleFillColor, Color.BLACK);

        // Fill the triangle and the rest of the rectangle (starting point is inside the triangle at (400, 350)) with green
        boundaryFill(400, 350, triangleFillColor, Color.BLACK);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Multi-Color Boundary Fill Demo");
        MultiColorBoundaryFillDemo demo = new MultiColorBoundaryFillDemo();
        JButton fillButton = new JButton("Fill Shapes");

        fillButton.addActionListener(e -> demo.triggerFillShapes());

        frame.setLayout(new BorderLayout());
        frame.add(demo, BorderLayout.CENTER);
        frame.add(fillButton, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}