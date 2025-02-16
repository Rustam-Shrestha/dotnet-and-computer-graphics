import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import javax.swing.*;

public class C8 extends Frame {

    private int h, k, r;

    public C8(int h, int k, int r) {
        this.h = h;
        this.k = k;
        this.r = r;
        setTitle("Midpoint Circle Drawing with 8-Way Symmetry");
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
        g2.setStroke(new BasicStroke(3)); // Set the thickness of the lines
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

        while (x <= y) {
            plot8SymmetricPoints(g, h, k, x, y);
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
                new C8(h, k, r);
            }
        });
    }
}
