import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CircleDrawing extends JPanel {

    private int h, k, r;

    public CircleDrawing(int h, int k, int r) {
        this.h = h;
        this.k = k;
        this.r = r;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawAxes(g);
        drawCircle(g, h, k, r);
    }

    private void drawAxes(Graphics g) {
        // Draw X and Y axes
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight()); // Y-axis
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2); // X-axis
    }

    private void drawCircle(Graphics g, int h, int k, int r) {
        int x = 0;
        int y = r;
        int d = 1 - r;

        drawCirclePoints(g, h, k, x, y);

        while (x < y) {
            if (d < 0) {
                d = d + (2 * x) + 3;
            } else {
                d = d + (2 * (x - y)) + 5;
                y--;
            }
            x++;
            drawCirclePoints(g, h, k, x, y);
        }
    }

    private void drawCirclePoints(Graphics g, int h, int k, int x, int y) {
        // 8-way symmetry with gaps
        g.drawOval(h + x - 1, k + y - 1, 2, 2); // Top right
        g.drawOval(h + y - 1, k + x - 1, 2, 2); // Right top
        g.drawOval(h - x - 1, k + y - 1, 2, 2); // Top left
        g.drawOval(h - y - 1, k + x - 1, 2, 2); // Left top
        g.drawOval(h + x - 1, k - y - 1, 2, 2); // Bottom right
        g.drawOval(h + y - 1, k - x - 1, 2, 2); // Right bottom
        g.drawOval(h - x - 1, k - y - 1, 2, 2); // Bottom left
        g.drawOval(h - y - 1, k - x - 1, 2, 2); // Left bottom
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Midpoint Circle Drawing Algorithm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        JTextField hField = new JTextField(5);
        JTextField kField = new JTextField(5);
        JTextField rField = new JTextField(5);
        JButton drawButton = new JButton("Draw Circle");

        inputPanel.add(new JLabel("h:"));
        inputPanel.add(hField);
        inputPanel.add(new JLabel("k:"));
        inputPanel.add(kField);
        inputPanel.add(new JLabel("r:"));
        inputPanel.add(rField);
        inputPanel.add(drawButton);

        CircleDrawing circlePanel = new CircleDrawing(0, 0, 0);
        frame.add(circlePanel, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int h = Integer.parseInt(hField.getText());
                int k = Integer.parseInt(kField.getText());
                int r = Integer.parseInt(rField.getText());
                circlePanel.h = h;
                circlePanel.k = k;
                circlePanel.r = r;
                circlePanel.repaint();
            }
        });

        frame.setVisible(true);
    }
}
