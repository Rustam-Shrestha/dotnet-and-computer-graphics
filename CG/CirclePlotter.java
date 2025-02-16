import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CirclePlotter extends JFrame {
    private int radius;
    private int centerX;
    private int centerY;
    private JTextField radiusField, centerXField, centerYField;
    private JPanel graphPanel;

    public CirclePlotter() {
        setTitle("Circle Plotter");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        radiusField = new JTextField(5);
        centerXField = new JTextField(5);
        centerYField = new JTextField(5);
        JButton plotButton = new JButton("Plot");

        inputPanel.add(new JLabel("Radius:"));
        inputPanel.add(radiusField);
        inputPanel.add(new JLabel("Center X:"));
        inputPanel.add(centerXField);
        inputPanel.add(new JLabel("Center Y:"));
        inputPanel.add(centerYField);
        inputPanel.add(plotButton);

        graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawCoordinateSystem(g);
                if (radius > 0) {
                    drawCircle(g);
                    drawSymmetryLines(g);
                }
            }
        };
        graphPanel.setPreferredSize(new Dimension(500, 500));

        add(inputPanel, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);

        plotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    radius = Integer.parseInt(radiusField.getText());
                    centerX = Integer.parseInt(centerXField.getText());
                    centerY = Integer.parseInt(centerYField.getText());
                    graphPanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CirclePlotter.this, "Invalid input. Please enter integers.");
                }
            }
        });
    }

    private void drawCoordinateSystem(Graphics g) {
        int width = graphPanel.getWidth();
        int height = graphPanel.getHeight();
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, height / 2, width, height / 2);
        g.drawLine(width / 2, 0, width / 2, height);
    }

    private void drawCircle(Graphics g) {
        g.setColor(Color.RED);
        int x = 0;
        int y = radius;
        int d = 3 - 2 * radius;
        drawSymmetricPoints(g, x, y);
        while (y >= x) {
            x++;
            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }
            drawSymmetricPoints(g, x, y);
        }
    }

    private void drawSymmetricPoints(Graphics g, int x, int y) {
        int centerScreenX = graphPanel.getWidth() / 2 + centerX;
        int centerScreenY = graphPanel.getHeight() / 2 - centerY;
        g.fillOval(centerScreenX + x, centerScreenY - y, 2, 2);
        g.fillOval(centerScreenX - x, centerScreenY - y, 2, 2);
        g.fillOval(centerScreenX + x, centerScreenY + y, 2, 2);
        g.fillOval(centerScreenX - x, centerScreenY + y, 2, 2);
        g.fillOval(centerScreenX + y, centerScreenY - x, 2, 2);
        g.fillOval(centerScreenX - y, centerScreenY - x, 2, 2);
        g.fillOval(centerScreenX + y, centerScreenY + x, 2, 2);
        g.fillOval(centerScreenX - y, centerScreenY + x, 2, 2);
    }

    private void drawSymmetryLines(Graphics g) {
        int width = graphPanel.getWidth();
        int height = graphPanel.getHeight();
        int centerScreenX = width / 2 + centerX;
        int centerScreenY = height / 2 - centerY;

        g.setColor(Color.BLUE);
        g.drawLine(centerScreenX, 0, centerScreenX, height);
        g.drawLine(0, centerScreenY, width, centerScreenY);
        g.drawLine(0, centerScreenY - centerScreenX, width, centerScreenY + centerScreenX);
        g.drawLine(centerScreenX - height / 2, 0, centerScreenX + height / 2, height);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CirclePlotter().setVisible(true);
            }
        });
    }
}