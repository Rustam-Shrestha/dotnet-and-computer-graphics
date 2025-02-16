import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import javax.swing.*;

public class C81 extends Frame {

    private int h, k, r;

    public C81(int h, int k, int r) {
        this.h = h;
        this.k = k;
        this.r = r;
        setTitle("General Circle Drawing with 8-Way Symmetry");
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
        drawCircle(g2, h, k, r);
        drawCrossLines(g2, h, k, r); // Draw cross lines through the center
    }

    private void drawCircle(Graphics2D g, int h, int k, int r) {
        g.setColor(Color.GRAY);
        h = h * 20 + 400;
        k = 400 - k * 20;
        int x = 0;
        int y = r;
        int p = 1 - r;
    
        // Gap between arcs (adjust as needed)
        int gap = 10;
    
        // Angle for arc rotation (adjust as needed)
        int rotationAngle = 45;
    
        while (x <= y) {
            // Draw arcs with rotation
            g.drawArc(h + x - gap / 2, k + y - gap / 2, gap, gap, rotationAngle, 360 - rotationAngle); // 1st octant
            g.drawArc(h + y - gap / 2, k + x - gap / 2, gap, gap, rotationAngle, 360 - rotationAngle); // 2nd octant
            g.drawArc(h + y - gap / 2, k - x - gap / 2, gap, gap, rotationAngle, 360 - rotationAngle); // 3rd octant
            g.drawArc(h + x - gap / 2, k - y - gap / 2, gap, gap, rotationAngle, 360 - rotationAngle); // 4th octant
            g.drawArc(h - x - gap / 2, k - y - gap / 2, gap, gap, rotationAngle, 360 - rotationAngle); // 5th octant
            g.drawArc(h - y - gap / 2, k - x - gap / 2, gap, gap, rotationAngle, 360 - rotationAngle); // 6th octant
            g.drawArc(h - y - gap / 2, k + x - gap / 2, gap, gap, rotationAngle, 360 - rotationAngle); // 7th octant
            g.drawArc(h - x - gap / 2, k + y - gap / 2, gap, gap, rotationAngle, 360 - rotationAngle); // 8th octant
    
            x++;
            if (p < 0) {
                p += 2 * x + 1;
            } else {
                y--;
                p += 2 * (x - y) + 1;
            }
        }
    }

    private void plot8SymmetricPoints(Graphics2D g, int h, int k, int x, int y) {
        g.draw(new Line2D.Float(h + x, k + y, h + x, k + y)); // 1st octant
        g.draw(new Line2D.Float(h + y, k + x, h + y, k + x)); // 2nd octant
        g.draw(new Line2D.Float(h + y, k - x, h + y, k - x)); // 3rd octant
        g.draw(new Line2D.Float(h + x, k - y, h + x, k - y)); // 4th octant
        g.draw(new Line2D.Float(h - x, k - y, h - x, k - y)); // 5th octant
        g.draw(new Line2D.Float(h - y, k - x, h - y, k - x)); // 6th octant
        g.draw(new Line2D.Float(h - y, k + x, h - y, k + x)); // 7th octant
        g.draw(new Line2D.Float(h - x, k + y, h - x, k + y)); // 8th octant
    }

    private void drawCrossLines(Graphics2D g, int h, int k, int r) {
        g.setColor(Color.GRAY);
        h = h * 20 + 400;
        k = 400 - k * 20;
        g.drawLine(h - r, k, h + r, k); // Horizontal line through center
        g.drawLine(h, k - r, h, k + r); // Vertical line through center
        g.drawLine(h - r, k - r, h + r, k + r); // Diagonal line (top-left to bottom-right)
        g.drawLine(h - r, k + r, h + r, k - r); // Diagonal line (bottom-left to top-right)
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JTextField hField = new JTextField(5);
            JTextField kField = new JTextField(5);
            JTextField rField = new JTextField(5);

            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("h:"));
            myPanel.add(hField);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("k:"));
            myPanel.add(kField);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("r:"));
            myPanel.add(rField);

            int result = JOptionPane.showConfirmDialog(null, myPanel, 
                     "Please Enter h, k and r Values", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                int h = Integer.parseInt(hField.getText());
                int k = Integer.parseInt(kField.getText());
                int r = Integer.parseInt(rField.getText());
                new C81(h, k, r);
            }
        });
    }
}
