import java.awt.*;

public class C4 extends Frame {

    public C4() {
        setTitle("Circle Drawing with 4-Way Symmetry");
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
        g.setColor(Color.BLACK);
        g.drawLine(400, 0, 400, 800);
        g.drawLine(0, 400, 800, 400);
        drawCircle(g, 0, 0, 100);
    }

    private void drawCircle(Graphics g, int h, int k, int r) {
        g.setColor(Color.BLACK);
        h = h * 20 + 400;
        k = 400 - k * 20;
        for (int x = 0; x <= r; x++) {
            int y = (int) Math.round(Math.sqrt(r * r - x * x));
            g.drawLine(h + x, k + y, h + x, k + y);
            g.drawLine(h - x, k + y, h - x, k + y);
            g.drawLine(h + x, k - y, h + x, k - y);
            g.drawLine(h - x, k - y, h - x, k - y);
        }
        g.drawLine(h - r, k, h + r, k);
        g.drawLine(h, k - r, h, k + r);
    }

    public static void main(String[] args) {
        new C4();
    }
}
