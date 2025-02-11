import java.awt.*;
import java.util.Scanner;

public class refx extends Frame {
        int x1, y1, x2, y2;
        int w, x, y, z;

        public refx() {
                Scanner s = new Scanner(System.in);
                System.out.println("Enter x1");
                x1 = s.nextInt();
                System.out.println("Enter x2");
                x2 = s.nextInt();
                System.out.println("Enter y1");
                y1 = s.nextInt();
                System.out.println("Enter y2");
                y2 = s.nextInt();

                w = -x1;
                x = -y1;
                y = -x2;
                z = -y2;

                this.setTitle("Reflection of line through y=x");
                this.setBounds(100, 100, 800, 800);
                this.setVisible(true);
        }

        public void paint(Graphics g) {
                g.drawLine(x1 + 400, y1 + 400, x2 + 400, y2 + 400);
                g.drawString("before reflection x", x1 + 400, y1 + 400);
                g.drawLine(w + 400, x + 400, y + 400, z + 400);
                g.drawString("after reflection x", w + 400, x + 400);
                g.drawLine(0, 400, 800, 400);
                g.drawLine(400, 0, 400, 800);
                // g.fillOval(100,100,20,20);
        }

        public static void main(String[] args) {
                new refx();
        }
}
