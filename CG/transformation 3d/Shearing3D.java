package ok;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

class Shearing3D extends Frame {
    int x, y, z, width, height, depth;
    double shearXY, shearXZ, shearYX, shearYZ, shearZX, shearZY;
    int[][] originalVertices;
    int[][] shearedVertices;

    public Shearing3D() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter front-top-left x-coordinate of the original 3D cuboid:");
            x = sc.nextInt();
            System.out.println("Enter front-top-left y-coordinate of the original 3D cuboid:");
            y = sc.nextInt();
            System.out.println("Enter front-top-left z-coordinate of the original 3D cuboid:");
            z = sc.nextInt();
            System.out.println("Enter width (x-direction) of the original 3D cuboid:");
            width = sc.nextInt();
            System.out.println("Enter height (y-direction) of the original 3D cuboid:");
            height = sc.nextInt();
            System.out.println("Enter depth (z-direction) of the original 3D cuboid:");
            depth = sc.nextInt();
            System.out.println("Enter shearing factor in XY plane:");
            shearXY = sc.nextDouble();
            System.out.println("Enter shearing factor in XZ plane:");
            shearXZ = sc.nextDouble();
            System.out.println("Enter shearing factor in YX plane:");
            shearYX = sc.nextDouble();
            System.out.println("Enter shearing factor in YZ plane:");
            shearYZ = sc.nextDouble();
            System.out.println("Enter shearing factor in ZX plane:");
            shearZX = sc.nextDouble();
            System.out.println("Enter shearing factor in ZY plane:");
            shearZY = sc.nextDouble();
        }
        this.setTitle("3D Shearing");
        this.setLayout(null);
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);

        // Calculate the vertices of the original cuboid
        originalVertices = new int[][]{
            {x, y, z},          // front-top-left
            {x + width, y, z},  // front-top-right
            {x + width, y + height, z},  // front-bottom-right
            {x, y + height, z},  // front-bottom-left
            {x, y, z + depth},   // back-top-left
            {x + width, y, z + depth},  // back-top-right
            {x + width, y + height, z + depth},  // back-bottom-right
            {x, y + height, z + depth}  // back-bottom-left
        };

        // Apply shearing transformation
        shearedVertices = new int[8][3];
        for (int i = 0; i < 8; i++) {
            int xOrig = originalVertices[i][0];
            int yOrig = originalVertices[i][1];
            int zOrig = originalVertices[i][2];
            shearedVertices[i][0] = (int) (xOrig + shearXY * yOrig + shearXZ * zOrig);
            shearedVertices[i][1] = (int) (yOrig + shearYX * xOrig + shearYZ * zOrig);
            shearedVertices[i][2] = (int) (zOrig + shearZX * xOrig + shearZY * yOrig);
        }

        // Add a window listener to handle window closing
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    // Helper function to project 3D point onto 2D plane
    private int[] project(int x, int y, int z) {
        int[] point = new int[2];
        point[0] = (int) (x + z * 0.5);
        point[1] = (int) (y + z * 0.5);
        return point;
    }

    // Helper function to draw a cuboid
    private void drawCuboid(Graphics g, int[][] vertices) {
        int[] p1, p2;

        // Draw front face
        p1 = project(vertices[0][0], vertices[0][1], vertices[0][2]);
        p2 = project(vertices[1][0], vertices[1][1], vertices[1][2]);
g.drawLine(p1[0] + 400, 400 - p1[1], p2[0] + 400, 400 - p2[1]);
        p1 = project(vertices[1][0], vertices[1][1], vertices[1][2]);
        p2 = project(vertices[2][0], vertices[2][1], vertices[2][2]);
        g.drawLine(p1[0] + 400, 400 - p1[1], p2[0] + 400, 400 - p2[1]);
        p1 = project(vertices[2][0], vertices[2][1], vertices[2][2]);
        p2 = project(vertices[3][0], vertices[3][1], vertices[3][2]);
        g.drawLine(p1[0] + 400, 400 - p1[1], p2[0] + 400, 400 - p2[1]);
        p1 = project(vertices[3][0], vertices[3][1], vertices[3][2]);
        p2 = project(vertices[0][0], vertices[0][1], vertices[0][2]);
        g.drawLine(p1[0] + 400, 400 - p1[1], p2[0] + 400, 400 - p2[1]);

        // Draw back face
        p1 = project(vertices[4][0], vertices[4][1], vertices[4][2]);
        p2 = project(vertices[5][0], vertices[5][1], vertices[5][2]);
        g.drawLine(p1[0] + 400, 400 - p1[1], p2[0] + 400, 400 - p2[1]);
        p1 = project(vertices[5][0], vertices[5][1], vertices[5][2]);
        p2 = project(vertices[6][0], vertices[6][1], vertices[6][2]);
        g.drawLine(p1[0] + 400, 400 - p1[1], p2[0] + 400, 400 - p2[1]);
        p1 = project(vertices[6][0], vertices[6][1], vertices[6][2]);
        p2 = project(vertices[7][0], vertices[7][1], vertices[7][2]);
        g.drawLine(p1[0] + 400, 400 - p1[1], p2[0] + 400, 400 - p2[1]);
        p1 = project(vertices[7][0], vertices[7][1], vertices[7][2]);
        p2 = project(vertices[4][0], vertices[4][1], vertices[4][2]);
        g.drawLine(p1[0] + 400, 400 - p1[1], p2[0] + 400, 400 - p2[1]);

        // Draw connecting lines between front and back faces
        for (int i = 0; i < 4; i++) {
            p1 = project(vertices[i][0], vertices[i][1], vertices[i][2]);
            p2 = project(vertices[i + 4][0], vertices[i + 4][1], vertices[i + 4][2]);
            g.drawLine(p1[0] + 400, 400 - p1[1], p2[0] + 400, 400 - p2[1]);
        }
    }

    public void paint(Graphics g) {
        // Draw coordinate axes
        g.setColor(Color.GRAY);
        g.drawLine(0, 400, 800, 400);  // X-axis
        g.drawLine(400, 0, 400, 800);  // Y-axis

        // Draw original cuboid
        g.setColor(Color.BLACK);
        drawCuboid(g, originalVertices);
        g.drawString("Original Cuboid", 400 + project(originalVertices[0][0], originalVertices[0][1], originalVertices[0][2])[0], 
                    400 - project(originalVertices[0][0], originalVertices[0][1], originalVertices[0][2])[1] - 10);

        // Draw sheared cuboid
        g.setColor(Color.RED);
        drawCuboid(g, shearedVertices);
        g.drawString("Sheared Cuboid", 400 + project(shearedVertices[0][0], shearedVertices[0][1], shearedVertices[0][2])[0], 
                    400 - project(shearedVertices[0][0], shearedVertices[0][1], shearedVertices[0][2])[1] - 10);
    }

    public static void main(String[] args) {
        new Shearing3D();
    }
}