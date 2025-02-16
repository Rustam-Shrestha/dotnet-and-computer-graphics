import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import javax.swing.*;

public class MidpointEllipse2 extends Frame {

    private int xc, yc, rx, ry;

    public MidpointEllipse2(int xc, int yc, int rx, int ry) {
        this.xc = xc;
        this.yc = yc;
        this.rx = rx;
        this.ry = ry;
        setTitle("Midpoint Ellipse Drawing Algorithm");
        setSize(800, 800);
        setVisible(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent winEvt) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2)); // Set the thickness of the lines
        g2.setColor(Color.GRAY);
        g2.drawLine(400, 0, 400, 800); // Vertical line
        g2.drawLine(0, 400, 800, 400); // Horizontal line
        drawEllipse(g2, xc, yc, rx, ry);
        drawCrossLines(g2, xc, yc, rx, ry); // Draw cross lines through the center
    }

    private void drawEllipse(Graphics2D g, int xc, int yc, int rx, int ry) {
        g.setColor(Color.GRAY);
        xc = xc * 20 + 400;
        yc = 400 - yc * 20;

        int x = 0;
        int y = ry;
        double dx = 2 * ry * ry * x;
        double dy = 2 * rx * rx * y;

        // Region 1
        double d1 = (ry * ry) - (rx * rx * ry) + (0.25 * rx * rx);
        while (dx < dy) {
            plot4SymmetricPoints(g, xc, yc, x, y);
            if (d1 < 0) {
                x++;
                dx = dx + (2 * ry * ry);
                d1 = d1 + dx + (ry * ry);
            } else {
                x++;
                y--;
                dx = dx + (2 * ry * ry);
                dy = dy - (2 * rx * rx);
                d1 = d1 + dx - dy + (ry * ry);
            }
        }

        // Region 2
        double d2 = ((ry * ry) * ((x + 0.5) * (x + 0.5))) + ((rx * rx) * ((y - 1) * (y - 1))) - (rx * rx * ry * ry);
        while (y >= 0) {
            plot4SymmetricPoints(g, xc, yc, x, y);
            if (d2 > 0) {
                y--;
                dy = dy - (2 * rx * rx);
                d2 = d2 + (rx * rx) - dy;
            } else {
                y--;
                x++;
                dx = dx + (2 * ry * ry);
                dy = dy - (2 * rx * rx);
                d2 = d2 + dx - dy + (rx * rx);
            }
        }
    }

    private void plot4SymmetricPoints(Graphics2D g, int xc, int yc, int x, int y) {
        g.draw(new Line2D.Float(xc + x, yc + y, xc + x, yc + y)); // 1st quadrant
        g.draw(new Line2D.Float(xc - x, yc + y, xc - x, yc + y)); // 2nd quadrant
        g.draw(new Line2D.Float(xc + x, yc - y, xc + x, yc - y)); // 3rd quadrant
        g.draw(new Line2D.Float(xc - x, yc - y, xc - x, yc - y)); // 4th quadrant
    }

    private void drawCrossLines(Graphics2D g, int xc, int yc, int rx, int ry) {
        g.setColor(Color.GRAY);
        xc = xc * 20 + 400;
        yc = 400 - yc * 20;
        g.drawLine(xc - rx, yc, xc + rx, yc); // Horizontal line through center
        g.drawLine(xc, yc - ry, xc, yc + ry); // Vertical line through center
        g.drawLine(xc - rx, yc - ry, xc + rx, yc + ry); // Diagonal line (top-left to bottom-right)
        g.drawLine(xc - rx, yc + ry, xc + rx, yc - ry); // Diagonal line (bottom-left to top-right)
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JTextField xcField = new JTextField(5);
            JTextField ycField = new JTextField(5);
            JTextField rxField = new JTextField(5);
            JTextField ryField = new JTextField(5);

            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("xc:"));
            myPanel.add(xcField);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("yc:"));
            myPanel.add(ycField);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("rx:"));
            myPanel.add(rxField);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("ry:"));
            myPanel.add(ryField);

            int result = JOptionPane.showConfirmDialog(null, myPanel, 
                     "Please Enter xc, yc, rx, and ry Values", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                int xc = Integer.parseInt(xcField.getText());
                int yc = Integer.parseInt(ycField.getText());
                int rx = Integer.parseInt(rxField.getText());
                int ry = Integer.parseInt(ryField.getText());
                new MidpointEllipse2(xc, yc, rx, ry);
            }
        });
    }
}
