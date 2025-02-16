import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

class Translation3D_D extends Frame {
    int D_x1, D_y1, D_z1, D_width, D_height, D_depth;
    int D_tx, D_ty, D_tz;

    public Translation3D_D() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter front-top-left x-coordinate of the original 3D rectangle:");
            D_x1 = input.nextInt();
            System.out.println("Enter front-top-left y-coordinate of the original 3D rectangle:");
            D_y1 = input.nextInt();
            System.out.println("Enter front-top-left z-coordinate of the original 3D rectangle:");
            D_z1 = input.nextInt();
            System.out.println("Enter width (x-direction) of the original 3D rectangle:");
            D_width = input.nextInt();
            System.out.println("Enter height (y-direction) of the original 3D rectangle:");
            D_height = input.nextInt();
            System.out.println("Enter depth (z-direction) of the original 3D rectangle:");
            D_depth = input.nextInt();
            System.out.println("Enter translation distance in x-direction (tx):");
            D_tx = input.nextInt();
            System.out.println("Enter translation distance in y-direction (ty):");
            D_ty = input.nextInt();
            System.out.println("Enter translation distance in z-direction (tz):");
            D_tz = input.nextInt();
        }

        this.setTitle("Center-Based 3D Translation");
        this.setLayout(null);
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    private int[] project_D(int x, int y, int z) {
        int[] point_D = new int[2];
        point_D[0] = (int) (x + z * 0.5);
        point_D[1] = (int) (y + z * 0.5);
        return point_D;
    }

    public void paint(Graphics graph) {
        graph.setColor(Color.GRAY);
        graph.drawLine(0, 400, 800, 400);
        graph.drawLine(400, 0, 400, 800);

        int[] p1_D = project(D_x1, D_y1, D_z1);
        int[] p2_D = project(D_x1 + D_width, D_y1, D_z1);
        int[] p3_D = project(D_x1 + D_width, D_y1 + D_height, D_z1);
        int[] p4_D = project(D_x1, D_y1 + D_height, D_z1);
        int[] p5_D = project(D_x1, D_y1, D_z1 + D_depth);
        int[] p6_D = project(D_x1 + D_width, D_y1, D_z1 + D_depth);
        int[] p7_D = project(D_x1 + D_width, D_y1 + D_height, D_z1 + D_depth);
        int[] p8_D = project(D_x1, D_y1 + D_height, D_z1 + D_depth);

        graph.setColor(Color.BLACK);
        drawCuboid_D(graph, p1_D, p2_D, p3_D, p4_D, p5_D, p6_D, p7_D, p8_D, "Before translation");

        int translatedX_D = D_x1 + D_tx;
        int translatedY_D = D_y1 + D_ty;
        int translatedZ_D = D_z1 + D_tz;

        int[] pt1_D = project(translatedX_D, translatedY_D, translatedZ_D);
        int[] pt2_D = project(translatedX_D + D_width, translatedY_D, translatedZ_D);
        int[] pt3_D = project(translatedX_D + D_width, translatedY_D + D_height, translatedZ_D);
        int[] pt4_D = project(translatedX_D, translatedY_D + D_height, translatedZ_D);
        int[] pt5_D = project(translatedX_D, translatedY_D, translatedZ_D + D_depth);
        int[] pt6_D = project(translatedX_D + D_width, translatedY_D, translatedZ_D + D_depth);
        int[] pt7_D = project(translatedX_D + D_width, translatedY_D + D_height, translatedZ_D + D_depth);
        int[] pt8_D = project(translatedX_D, translatedY_D + D_height, translatedZ_D + D_depth);

        graph.setColor(Color.RED);
        drawCuboid_D(graph, pt1_D, pt2_D, pt3_D, pt4_D, pt5_D, pt6_D, pt7_D, pt8_D, "After translation");
    }

    private void drawCuboid_D(Graphics graph, int[] p1_D, int[] p2_D, int[] p3_D, int[] p4_D, int[] p5_D, int[] p6_D, int[] p7_D, int[] p8_D, String label_D) {
        graph.drawLine(p1_D[0] + 400, 400 - p1_D[1], p2_D[0] + 400, 400 - p2_D[1]);
        graph.drawLine(p2_D[0] + 400, 400 - p2_D[1], p3_D[0] + 400, 400 - p3_D[1]);
        graph.drawLine(p3_D[0] + 400, 400 - p3_D[1], p4_D[0] + 400, 400 - p4_D[1]);
        graph.drawLine(p4_D[0] + 400, 400 - p4_D[1], p1_D[0] + 400, 400 - p1_D[1]);

        graph.drawLine(p5_D[0] + 400, 400 - p5_D[1], p6_D[0] + 400, 400 - p6_D[1]);
        graph.drawLine(p6_D[0] + 400, 400 - p6_D[1], p7_D[0] + 400, 400 - p7_D[1]);
        graph.drawLine(p7_D[0] + 400, 400 - p7_D[1], p8_D[0] + 400, 400 - p8_D[1]);
        graph.drawLine(p8_D[0] + 400, 400 - p8_D[1], p5_D[0] + 400, 400 - p5_D[1]);

        graph.drawLine(p1_D[0] + 400, 400 - p1_D[1], p5_D[0] + 400, 400 - p5_D[1]);
        graph.drawLine(p2_D[0] + 400, 400 - p2_D[1], p6_D[0] + 400, 400 - p6_D[1]);
        graph.drawLine(p3_D[0] + 400, 400 - p3_D[1], p7_D[0] + 400, 400 - p7_D[1]);
        graph.drawLine(p4_D[0] + 400, 400 - p4_D[1], p8_D[0] + 400, 400 - p8_D[1]);

        graph.drawString(label_D, p1_D[0] + 400, 400 - p1_D[1] - 10);
    }

    public static void main(String[] args) {
        new Translation3D_D();
    }
}
