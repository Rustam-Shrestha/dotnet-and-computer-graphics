import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

class GeometricTransformations extends JFrame {
    private JTextField inputField;
    private List<Point> points;
    private JPanel drawingPanel;

    public GeometricTransformations() {
        points = new ArrayList<>();
        
        // Initialize components
        inputField = new JTextField(20);
        JButton translateButton = new JButton("Translate");
        JButton rotateButton = new JButton("Rotate");
        JButton shearButton = new JButton("Shear");
        JButton scaleButton = new JButton("Scale");
        JButton reflectButton = new JButton("Reflect");

        // Action listeners
        translateButton.addActionListener(new TransformActionListener("translate"));
        rotateButton.addActionListener(new TransformActionListener("rotate"));
        shearButton.addActionListener(new TransformActionListener("shear"));
        scaleButton.addActionListener(new TransformActionListener("scale"));
        reflectButton.addActionListener(new TransformActionListener("reflect"));

        // Setup panel for inputs and buttons
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Enter coordinates:"));
        controlPanel.add(inputField);
        controlPanel.add(translateButton);
        controlPanel.add(rotateButton);
        controlPanel.add(shearButton);
        controlPanel.add(scaleButton);
        controlPanel.add(reflectButton);

        // Setup drawing panel
        drawingPanel = new DrawingPanel();
        drawingPanel.setPreferredSize(new Dimension(800, 600));

        // Setup frame
        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Custom panel for drawing points
    class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLUE);
            for (Point p : points) {
                g2d.fillOval(p.x, p.y, 5, 5);
            }
        }
    }

    // Action listener for transformations
    class TransformActionListener implements ActionListener {
        private String action;

        public TransformActionListener(String action) {
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] coords = inputField.getText().split(",");
            if (coords.length % 2 != 0) {
                JOptionPane.showMessageDialog(null, "Please enter valid coordinates.");
                return;
            }
            
            points.clear();
            for (int i = 0; i < coords.length; i += 2) {
                int x = Integer.parseInt(coords[i].trim());
                int y = Integer.parseInt(coords[i + 1].trim());
                points.add(new Point(x, y));
            }

            switch (action) {
                case "translate":
                    translate(50, 50);
                    break;
                case "rotate":
                    rotate(Math.toRadians(45));
                    break;
                case "shear":
                    shear(0.2, 0);
                    break;
                case "scale":
                    scale(1.5, 1.5);
                    break;
                case "reflect":
                    reflect();
                    break;
            }

            drawingPanel.repaint();
        }
    }

    // Transformation methods
    private void translate(double dx, double dy) {
        AffineTransform transform = new AffineTransform();
        transform.translate(dx, dy);
        applyTransformation(transform);
    }

    private void rotate(double theta) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(theta, drawingPanel.getWidth() / 2, drawingPanel.getHeight() / 2);
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

    private void reflect() {
        AffineTransform transform = new AffineTransform();
        transform.scale(-1, 1);
        transform.translate(-drawingPanel.getWidth(), 0);
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
        SwingUtilities.invokeLater(GeometricTransformations::new);
    }
}
